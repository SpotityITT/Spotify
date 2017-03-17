package Demo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import DB.DatabaseManager;

public class Demo {

	public static void main(String[] args) {
		
		
		Connection connection = null;
		try {
			connection = DatabaseManager.getInstance().getConnection();
			
			System.out.println("Working");
		}
		finally {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getClass().getName());
				}
		}

	}

}
