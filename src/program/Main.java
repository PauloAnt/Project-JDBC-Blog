package program;

import java.sql.Date;
import java.util.Scanner;

import db.DB;
import entities.Post;
import entities.SocialMedia;
import entities.User;

public class Main {
	public static void menu() {
		System.out.println("SocialMedia:\n"
				+ "(R) Registrar conta\n"
				+ "(L) Logar\n"
				+ "\n"
				+ "(M) Mostrar posts recentes\n"
				+ "(V) Ver seus posts\n"
				+ "(C) Criar post\n"
				+ "(E) Editar post\n"
				+ "(D) Deletar post\n"
				+ "(Q) Sair\n");
	}
	
	public static void main(String[] args) {
		String option = null;
		Scanner sc = new Scanner(System.in);
		SocialMedia sm = new SocialMedia();
		String username, email, password, title , category, content;
		String date_nasc;
		Date datesql;
		User user = null;
		
		
		while(true) {
			menu();
			System.out.print("Opção: ");
			option = sc.next().toLowerCase();
			
			if(option.equals("r")) {	
				System.out.print("Username: ");
				username = sc.next();
				System.out.println();
				System.out.print("Email: ");
				email = sc.next();
				System.out.println();
				System.out.print("Data de nascimento (yyyy/mm/dd): ");
				date_nasc = sc.next();
				datesql = Date.valueOf(date_nasc);
				System.out.print("Senha: ");
				password = sc.next();
				user = new User(username, email, datesql, password);
				sm.register(user);
			}
			else if (option.equals("l")) {
				System.out.print("Username: ");
				username = sc.next();
				System.out.println();
				System.out.print("Senha: ");
				password = sc.next();
				User aux = sm.findByUsername(username);
				if (aux != null) {
					user = new User(aux.getUsername(), aux.getEmail(), aux.getDate_nasc(), aux.getPassword());
					sm.logar(username, password);
				}
				else System.out.println("Conta inexistente!");
				
			}
			
			else if(option.equals("m")) {
				sm.mostrarPostRecents();
			}
			else if(option.equals("v")) {
				sm.mostrarPostUser(user);
			}
			else if(option.equals("c")) {
				Post post;
				System.out.println();
				System.out.print("Título: ");
				title = sc.nextLine();
				title = sc.nextLine();
				System.out.print("Categoria: ");
				category = sc.nextLine();
				System.out.print("Descrição: ");
				content = sc.nextLine();

				post = new Post(user.getUsername(), title, category, content);
				sm.createPost(user, post);
			}
			else if(option.equals("e")) {
				Post post;
				sm.mostrarPostUser(user);
				System.out.print("Número da postagem: ");
				int num = sc.nextInt();
				System.out.println();
				System.out.print("Título: ");
				title = sc.nextLine();
				title = sc.nextLine();
				System.out.print("Categoria: ");
				category = sc.nextLine();
				System.out.print("Descrição: ");
				content = sc.nextLine();
				
				post = new Post(user.getUsername(), title, category, content);
				sm.editPost(user, post, num);
			}
			else if(option.equals("d")) {
				sm.mostrarPostUser(user);
				System.out.print("Número da postagem: ");
				int num = sc.nextInt();
				
				sm.delPost(user, num);
				
			}
			else if(option.equals("q")) {
				System.out.println("Finalizando programa...");
				sc.close();
				DB.closeConnection();
				break;
				
			}
		}
		
	}

}
