package automationjar.testscript.Sqlite.copy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;


public class UnitTests {

	private UserDAO userDao;
			
	@Before 
	public void init() {
		userDao = new UserDAO("jdbc:sqlite:C:/sqlite/bank_db.db");		
	}
	
	@Test
	public void verifyStrongPasswordTest() {
		// verify IsValidPassword function with some cases
		assertEquals(userDao.isValidPassword("aabbcc"), false);
		assertEquals(userDao.isValidPassword("Aabb!@12345"), true);
		assertEquals(userDao.isValidPassword("@@@@@@123"), false);
		assertEquals(userDao.isValidPassword("aaaa"), false);
		assertEquals(userDao.isValidPassword("1111"), false);
		assertEquals(userDao.isValidPassword("aaaccc!1"), false);
		assertEquals(userDao.isValidPassword("Aa!@3"), false);
	}
	
	@Test
	public void verifyCreateTableTest() {
		userDao.CreateUsersTable();
	}
	
	@Test
	public void verifyInsertUserTest() {
		Optional<User> u;
		int id = 11151;
		
		ArrayList<User> users = userDao.getAllUsers();
		u = users.stream().filter(f -> f.getUserId() == id).findAny();
		// verify user isn't exist
		assertTrue(u.isEmpty());
		// insert user
		userDao.insert(id, "AA BB", "zinuk_account", "passwd123");
		users = userDao.getAllUsers();
		u = users.stream().filter(f -> f.getUserId() == id).findAny();
		// verify user is exist
		assertTrue(!u.isEmpty());
	}
	
	@Test
	public void verifyUpdateTableTest() {
		int id;
		Optional<User> u;
		
		
		ArrayList<User> users = userDao.getAllUsers();
		// get user with zinuk account
		u = users.stream().filter(f -> f.getAcountType() == "zinuk account").findFirst();
	
		if (!u.isEmpty())
		{
			id = u.get().getUserId();
			System.out.println(u.get().getAcountType());
			//update the account type
			userDao.update("UPDATE USERS SET ACCOUNT_TYPE='student account' WHERE USER_ID="+id);
			users = userDao.getAllUsers();
			// verify the account updated
			u = users.stream().filter(f -> f.getUserId() == id).findFirst();	
			System.out.println(u.get().getAcountType());
			assertTrue(u.get().getAcountType()=="student account");	
		}
	}
	
	@Test
	public void verifyRemoveTest() {
		Optional<User> u;
		ArrayList<User> users = userDao.getAllUsers();

		int id = 7777;
		users = userDao.getAllUsers();
		u = users.stream().filter(f -> f.getUserId() == id).findFirst();
		
		if (!u.isEmpty()) {
			// insert user 
			userDao.insert(id, "AA BB", "zinuk_account", "passwd123");
		}
		
		//remove user
		userDao.removeUserById(id);
		u = users.stream().filter(f -> f.getUserId() == id).findFirst();
		// verify the user deleted
		assertTrue(u.isEmpty());
		
		
	}

	@Test
	public void verifySelectTest() throws InterruptedException {
		Thread.sleep(1);
	}
}
