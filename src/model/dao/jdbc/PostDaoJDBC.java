package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import entities.Post;
import entities.User;
import model.dao.PostDao;

public class PostDaoJDBC implements PostDao{
	private Connection conn = null;
	
	
	public PostDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Post post, User user) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO post(id, creator, title, category, content) VALUES(?, ?, ?, ?, ?)");
			ps.setInt(1, post.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, post.getTitle());		
			ps.setString(4, post.getCategory());
			ps.setString(5, post.getContent());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Post post, User user, int id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE post SET title = ?, category = ?, content = ? WHERE creator = ? AND id = ?");
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getCategory());
			ps.setString(3, post.getContent());
			ps.setString(4, user.getUsername());
			ps.setInt(5, id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer num, User user) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM post WHERE id = ? AND creator = ?");
			ps.setInt(1, num);
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Post findById(Integer num) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM post WHERE id = ?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if (rs.next()) {
				Post post = instPost(rs);
				return post;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally{
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Post> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Post> posts = new ArrayList();
			ps = conn.prepareStatement("SELECT * FROM post");
			rs = ps.executeQuery();
			while(rs.next()) {
				Post post = instPost(rs);
				posts.add(post);
			}
			return posts;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally{
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Post instPost(ResultSet rs) {
		try {
			Post post = new Post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			return post;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}
}
