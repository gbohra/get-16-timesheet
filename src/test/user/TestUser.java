package test.user;

import java.sql.Date;

import com.timesheet.dao.UserDAO;
import com.timesheet.dao.model.User;

public class TestUser {
	
	
	
	public static void main(String[] args) {
		
		UserDAO userDAO =  new UserDAO();
		User user = new User();
		user.setId(1);
		user.setFirstName("a");
		user.setLastName("w");
		user.setEmail("a@a.a");
		user.setPassword("123");
		Date date = new Date(1994, 10, 10);

		userDAO.insert(user);
		
	}

}
