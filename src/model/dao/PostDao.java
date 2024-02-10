package model.dao;

import java.util.List;

import entities.Post;
import entities.User;

public interface PostDao{
	void insert(Post post, User user);
	void update(Post post, User user, int id);
	void deleteById(Integer num, User user);
	Post findById(Integer num);
	List<Post> findAll();
}
