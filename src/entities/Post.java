package entities;

import java.io.Serializable;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public int id;
	public String title;
	public String category;
	public String content;
	public String creator;
	
	public Post(int id, String creator, String title, String category, String content) {
		this.id = id;
		this.creator = creator;
		this.title = title;
		this.category = category;
		this.content = content;
	}
	public Post(String creator, String title, String category, String content) {
		this.creator = creator;
		this.title = title;
		this.category = category;
		this.content = content;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: " + title + "\n");
		sb.append("Categoria: " + category + "\n");
		sb.append("Content: " + content + "\n");
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	

	
	
}
