package interfaces;

import entities.Post;
import entities.User;

public interface Blog {
	void mostrarPostRecents();
	
	void mostrarPostUser(User user);

	void createPost(User user, Post post);
	
	void delPost(User user, int indice);
	
}
