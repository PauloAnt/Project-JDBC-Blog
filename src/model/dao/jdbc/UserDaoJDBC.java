package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import entities.User;
import model.dao.UserDao;

public class UserDaoJDBC implements UserDao{

	private Connection conn;
	
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(User user) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
			"INSERT INTO user(username, email, date_nasc, password) VALUES(?, ?, ?, ?)"
			);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setDate(3, user.getDate_nasc());
			ps.setString(4, user.getPassword());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(User user) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
			"UPDATE user SET username = ?, email = ?, date_nasc = ?, password = ? WHERE id = ?"
			);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setDate(3, user.getDate_nasc());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer num) {
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(
					"DELETE FROM user WHERE id = ?"
			);
			ps.setInt(1, num);
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
	public User findByUsername(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT id, username, email, date_nasc from user WHERE user.username = ?");
			st.setString(1, username);
			rs = st.executeQuery();
			if (rs.next()) {
				User user = instUser(rs);
				return user;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public User findById(Integer num) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT id, username, email, date_nasc from user WHERE user.id = ?");
			st.setInt(1, num);
			rs = st.executeQuery();
			if (rs.next()) {
				User user = instUser(rs);
				return user;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<User> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM user"
			);
			rs = ps.executeQuery();
			while(rs.next()) {
				result.add(instUser(rs));
			}
			return result;	

		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	
	private User instUser(ResultSet rs) throws SQLException{
		User user = new User();
		user.setId(rs.getInt(1));
		user.setUsername(rs.getString(2));
		user.setEmail(rs.getString(3));
		user.setDate_nasc(rs.getDate(4));
		return user;
	}
}
