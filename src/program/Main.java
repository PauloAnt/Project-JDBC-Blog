package program;

import java.sql.Date;
import java.util.List;

import entities.User;
import model.dao.DaoFactory;
import model.dao.UserDao;

public class Main {

	public static void main(String[] args) {
		UserDao userDao = DaoFactory.createUserDao();
		User user = new User();
		Date date_nasc = Date.valueOf("2005-03-22");	
		user.setUsername("Paulo9676");
		user.setEmail("paulo@gmail.com");
		user.setPassword("paulo123");
		user.setDate_nasc(date_nasc);
		
		userDao.insert(user);
		System.out.println(userDao.findById(4));
		
		List<User> result = userDao.findAll();
		for(User obj : result) {
			System.out.println(obj);
		}
		
	}

}
