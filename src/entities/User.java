package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import exception.UserException;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private LocalDate date_nasc;
	private String password;
	
	public User(String username, String email, LocalDate date_nasc, String password) {
		if (password.length() < 4) {
			throw new UserException("The password must be longer than 4 letters");
		}
		else {
			this.username = username;
			this.email = email;
			this.date_nasc = date_nasc;
			this.password = password;
		}
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

	public LocalDate getDate_nasc() {
		return date_nasc;
	}

	public void setDate_nasc(LocalDate date_nasc) {
		this.date_nasc = date_nasc;
	}

	public String getPassword() {
		return password;
	}
}
