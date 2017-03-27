package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Playlist;

public class PlayListDAO {
	
	Connection connection = DatabaseManager.getInstance().getConnection();
	private static PlayListDAO instance;
	
	private PlayListDAO(){
		
		
	}
	
	public static synchronized PlayListDAO getInstance(){
		if(instance == null){
			instance = new PlayListDAO();
		}
		return instance;
	}

	// add playList with title for user_id, return int za da zapishem id-to na pleilista?
	public int addPlayList(int user_id, String playListTitle) throws SQLException{
		String sqlQuery = "INSERT INTO spotify.playlists (userId, title) VALUES (?,?) ";
		int playListId = 0;
		PreparedStatement prepStatement = null;
		try{
			prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, user_id);
			prepStatement.setString(2, playListTitle);
			prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();
			if(resultSet.next()){
				playListId = resultSet.getInt(1);
			}
		}
		finally{
			prepStatement.close();
		}
		return playListId;
	}
	
	// add a song to playList
	public void addSongInPlayList(int playList_id, int song_id) throws SQLException{
		String sqlQuery = "INSERT INTO spotify.playlists_songs (playlistId, songId) VALUES (?,?) ";
		PreparedStatement prepStatement = null;
		try{
			prepStatement = connection.prepareStatement(sqlQuery);
			prepStatement.setInt(1, playList_id);
			prepStatement.setInt(2, song_id);
			prepStatement.executeUpdate();
		}
		finally{
			prepStatement.close();
		}
	}
	
	// samo da gi vidim 
	public List<Playlist> getUserPlayLists() throws SQLException{
		String sqlQuery = "SELECT playlistId, title, userId FROM spotify.playlists";
		List<Playlist> allPlayLists = new ArrayList<>();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				allPlayLists.add(new Playlist(resultSet.getInt("playlistId"), resultSet.getInt("userId"), resultSet.getString("title")));
			}
		}
		finally{
			statement.close();
		}
		return Collections.unmodifiableList(allPlayLists);
	}

}
