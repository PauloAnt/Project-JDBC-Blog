package entities;

import java.io.Serializable;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String title;
	public String category;
	public String content;
	public User user;
	
	public Post(User user, String title, String category, String content) {
		this.user = user;
		this.title = title;
		this.category = category;
		this.content = content;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: " + title);
		sb.append("Categoria: " + category);
		sb.append("Content: " + content);
		return sb.toString();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
