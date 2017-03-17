package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import Model.User;

public class UserDAO {
	
	
	private static UserDAO instance;

	private UserDAO() {

	}

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	
	public synchronized boolean saveUser(User user) {

		try {
			String sql = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
		

			// Date sqlDate = DateFormat.parse(5, user.getBirthday());

			statement.setString(1, user.getName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getCity());
			statement.setString(6, user.getGender());
			 statement.setString(7, null);
			statement.setString(8, user.getMobileNumber());
			statement.setString(9, null);
			System.out.println("DAO" + user.getName());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Saving user operation successful.");
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Cannot add to DB." + e.getClass().getName() + " " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public boolean isValidLogin(String username, String password) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next() == false) {
				System.out.println("Wrong credentials.");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("User cannot be logged in.");

		}
		return true;
	}

}
