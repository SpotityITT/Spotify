package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Genre;

public class SongDAO {
	
	Connection connection = DatabaseManager.getInstance().getConnection();
	private static SongDAO instance;
	
	private SongDAO(){
		
	}
	
	public static synchronized SongDAO getInstance(){
		if(instance == null){
			instance = new SongDAO();
		}
		return instance;
	}
	
	// add song in DataBase -> by us
	public int addSong(String title, String artist, String genre, int album_id){
		String sqlQuery = "INSERT INTO spotify.songs (title, artist, genre, albumId) VALUES (?, ?, ?, ?)";
		PreparedStatement prepStatement = null;
		int songId = 0;
		try {
			prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, title);
			prepStatement.setString(2, artist);
			prepStatement.setString(3, genre);
			prepStatement.setInt(4, album_id);
			songId = prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();
			while(resultSet.next()){
				songId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong in addSong in SongDAO - " + e.getMessage());
		}
		finally{
			try {
				prepStatement.close();
			} catch (SQLException e) {
				System.out.println("Something went wrong when closing statement! - " + e.getMessage());
			}
		}
		return songId;
	}
	
	// search for song with title
	public void searchForASong(String title){
		
	}

}
