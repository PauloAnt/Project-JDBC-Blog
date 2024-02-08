package model.dao;
import db.DB;
import model.dao.jdbc.PostDaoJDBC;
import model.dao.jdbc.UserDaoJDBC;

public class DaoFactory {
	public static UserDao createUserDao() {
		return new UserDaoJDBC(DB.getConnection());
	}
	
	public static PostDao createPostDao() {
		return new PostDaoJDBC();
	}
}
