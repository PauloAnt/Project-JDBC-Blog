package model.dao;

import java.util.List;

import entities.Post;
import entities.User;

public interface UserDao {
	void insert(User user);
	void update(User user);
	void deleteById(Integer num);
	User findById(Integer num);
	List<User> findAll();
}
