package automationjar.testscript.Sqlite.copy;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO {
	String m_conn;

	public UserDAO(String conn) {
		m_conn = conn;
	}

	public void CreateDatebase() {

		try {
			Connection conn = DriverManager.getConnection(m_conn);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void CreateUsersTable() {

		String sql = "CREATE TABLE IF NOT EXISTS users_t (\n" + " user_id integer PRIMARY KEY,\n"
				+ " name text NOT NULL,\n" + " account_type text,\n" + " password text NOT NULL\n" + ");";
		try {
			Connection conn = DriverManager.getConnection(m_conn);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insert(int id, String name, String account_type, String password) {
		String sql = "INSERT INTO users_t (user_id, name, account_type, password) VALUES(?,?,?,?)";

		try {
			Connection conn = DriverManager.getConnection(m_conn);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, account_type);
			pstmt.setString(4, password);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void update(String quary) {
		try (Connection conn = DriverManager.getConnection(m_conn)) {

			if (conn != null) {
				Statement stat = conn.createStatement();
				int row_ind = stat.executeUpdate(quary);
				System.out.println(row_ind + " record updated");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeUserById(int id) {
		String quary = "DELETE FROM users  \r\n" + "WHERE user_id == " + id;
		try (Connection conn = DriverManager.getConnection(m_conn)) {

			if (conn != null) {
				Statement stat = conn.createStatement();
				stat.executeUpdate(quary);
				System.out.println("record deleted ");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(m_conn)) {

			if (conn != null) {

				String sql = "SELECT * FROM USERS_T";

				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);

				while (rs.next()) {
					users.add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("account_type"),
							rs.getString("password")));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	public void select(String query) {
		try (Connection conn = DriverManager.getConnection(m_conn)) {

			if (conn != null) {
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(query);
				String s = "";
				while (rs.next()) {
					s += rs.getInt("user_id") + " \t" + rs.getString("name") + " \t" + rs.getString("account_type")
							+ " \t" + rs.getString("password") + " \n";
				}
				System.out.println(s);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean isValidPassword(String password) 
	{

		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the password is empty
		// return false
		if (password == null) {
			return false;
		}

		// Pattern class contains matcher() method
		// to find matching between given password
		// and regular expression.
		Matcher m = p.matcher(password);

		// Return if the password
		// matched the ReGex
		return m.matches();
	}
}
