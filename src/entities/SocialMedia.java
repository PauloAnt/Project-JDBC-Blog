package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.DaoFactory;
import model.dao.PostDao;
import model.dao.UserDao;

public class SocialMedia{
	private UserDao userBD = DaoFactory.createUserDao();
	private PostDao postBD = DaoFactory.createPostDao();
	
	private Map<Integer, Boolean> isLogado = new HashMap();
	
	public void register(User user) {
		userBD.insert(user);
		System.out.println("Registrado com sucesso!");
	}
	
	public void logar(String username, String password) {
		User findUser = userBD.findByUsername(username);
		if(findUser != null) {
			isLogado.put(findUser.getId(), true);
			System.out.println("Logado com sucesso!");
		}
		else System.out.println("Conta inexistente, crie sua conta.");
		
	}
	
	public void mostrarPostRecents() {
		List<Post> postRecents = postBD.findAll();
		if (postRecents.size() > 1) {
			for(int i = postRecents.size() - 2; i < postRecents.size(); i++) {
				System.out.println(postRecents.get(i) + "\n");
			}
		}
		else System.out.println("Você não possui postagens.");
		
	}

	public void mostrarPostUser(User user) {
		List<Post> postsUser = postBD.findAll();
		List<Post> foundPost = new ArrayList();
		for(Post obj : postsUser) {
			if (obj.getCreator().equals(user.getUsername())) {
				foundPost.add(obj);
			}
		}
		if (foundPost.size() > 0) {
			for(Post obj : foundPost) {
				System.out.println("Número da postagem: " + obj.getId());
				System.out.println(obj);
			}
		}
		else System.out.println("Você não tem posts criados.");
		
	}

	public User findByUsername(String username) {
		return userBD.findByUsername(username);
	}
	
	public void createPost(User user, Post post) {
		postBD.insert(post, user);
		System.out.println("Post criado com sucesso!");
		
	}

	public void editPost(User user, Post post, int id) {
		postBD.update(post, user, id);
		System.out.println("Post editado com sucesso!");
	}
	
	public void delPost(User user, int indice) {
		postBD.deleteById(indice, user);
		System.out.println("Post deletado com sucesso!");
		
	}
	
	
	
	
}
