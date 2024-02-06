package model.dao;

import java.util.List;

import entities.Post;

public interface PostDao{
	void insert(Post post);
	void update(Post post);
	void deleteById(Integer num);
	Post findById(Integer num);
	List<Post> findAll();
}
