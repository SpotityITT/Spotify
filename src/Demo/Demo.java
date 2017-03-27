package Demo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import DB.AlbumDAO;
import DB.DatabaseManager;

public class Demo {

	public static void main(String[] args) {
		
		
		Connection connection = null;
		try {
			connection = DatabaseManager.getInstance().getConnection();
			
			System.out.println("Working");
		}
		finally {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					System.out.println(e.getClass().getName());
//				}
		}
		
//		AlbumDAO.getInstance().addAlbum("Starboy", "The Weeknd", "Starboy is the third studio album by Canadian singer and songwriter The Weeknd.", "Pop", 4);
//		AlbumDAO.getInstance().addAlbum("Anti", "Rihanna", " 'ANTI' is the long-awaited eighth album from global superstar and icon Rihanna.", "RnB", 6);
//		AlbumDAO.getInstance().addAlbum("AM", "Arctic Monkeys", "AM has been recognised as one of the bestselling vinyl albums of the decade, selling 27,000 units.","Indie",1);
//		

	}

}
