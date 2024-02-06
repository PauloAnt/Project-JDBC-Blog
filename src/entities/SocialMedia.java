package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.Blog;

// Acrescentar a programação defensiva
// Transferir os dados para o banco de dados

public class SocialMedia implements Blog{
	private Map<User, Boolean> users = new HashMap<>();
	public Map<User, List<Post>> posts = new HashMap<>();
	public List<Post> recents_posts = new ArrayList<>();
	
	public void registerUser(String username, String email, String data_nasc, String password) {
		LocalDate aux = LocalDate.parse(data_nasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		User user = new User(username, email, aux, password);
		users.put(user, false);
		System.out.println("Registrado com sucesso!");
	}
	
	public void logar(String username, String password) {
		for(User user: users.keySet()) {
			if (user.equals(username)) {
				System.out.println("Logado com sucesso!");
				break;
			}
		}
		System.out.println("Conta inexistente!");
	}
	
	@Override
	public void createPost(User user, Post post) {
		recents_posts.add(post);
		if (posts.containsKey(user)) {
			List<Post> aux = posts.get(user);
			aux.add(post);
		} else {
			List<Post> aux = new ArrayList<>();
			aux.add(post);
			posts.put(user, aux);
		}
	}
	
	@Override
	public void mostrarPostUser(User user) {
		List<Post> aux = posts.get(user);
		int cont = 0;
		for(Post publi: aux) {
			cont += 1;
			System.out.print(cont + "- ");
			System.out.println(publi);
		}
	}
	
	@Override
	public void mostrarPostRecents() {
		for(int i = posts.size(); i > posts.size() - 3; i--) {
			System.out.println(recents_posts.get(i));
		}
	}
	
	@Override
	public void delPost(User user, int indice) {
		posts.get(user).remove(indice);
	}
	
	
}
