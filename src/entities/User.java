package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import exception.UserException;

public class User implements Serializable{
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", date_nasc=" + date_nasc
				+ ", password=" + password + "]";
	}
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String email;
	private Date date_nasc;
	private String password;
	
	public User() {
		
	}

	public User(int id, String username, String email, Date date_nasc, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.date_nasc = date_nasc;
		this.password = password;
	}
	public User(String username, String email, Date date_nasc, String password) {
		this.username = username;
		this.email = email;
		this.date_nasc = date_nasc;
		this.password = password;
	}


	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username);
	}

	public void fogotPassword(String new_password) {
		if (new_password.length() < 4) {
			throw new UserException("The password must be longer than 4 letters");
		}
		else password = new_password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_nasc() {
		return date_nasc;
	}

	public void setDate_nasc(Date date_nasc) {
		this.date_nasc = date_nasc;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
