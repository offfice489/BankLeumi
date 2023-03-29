package automationjar.testscript.Sqlite.copy;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		UserDAO userDao = new UserDAO("jdbc:sqlite:C:/sqlite/bank_db.db");
//		userDao.CreateDatebase();
//		userDao.CreateUsersTable();
//		userDao.insert(456789, "CCCC DDDD", "student account", "pwd");
//		userDao.insert(3456789, "CCCC DDDD", "student account", "pwd");
//		System.out.println("end");
//		userDao.select("SELECT * FROM USERS ");
//		userDao.removeUserById(3456789);
//		userDao.select("SELECT * FROM USERS ");
//		userDao.update("UPDATE USERS SET ACCOUNT_TYPE='zinuk account' WHERE =123456");
//		userDao.select("SELECT * FROM USERS ");
		ArrayList<User> users = userDao.getAllUsers();
		System.out.println("end");

	}

}
