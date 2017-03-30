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
	public List<Playlist> getAllPlayLists() throws SQLException{
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
	
	
	public List<Playlist> getUserPlaylists(int userId) throws SQLException{
		String sqlQuery = "SELECT p.playlistId, p.title, p.userId FROM spotify.playlists p "
				+ "join spotify.users using(userId) where userId = ?;";
		
		List<Playlist> allPlayLists = new ArrayList<>();
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			ResultSet resultSet = ps.executeQuery(sqlQuery);
			
			while(resultSet.next()){
				allPlayLists.add(new Playlist(resultSet.getInt("playlistId"),
						resultSet.getInt("userId"), 
						resultSet.getString("title")));
			}
		}
		
		finally{
			ps.close();
		}
		return Collections.unmodifiableList(allPlayLists);
	}
	

	
	public int getPlaylistId(String name){
		String sql = "select playlistId from spotify.playlists where title = ?;";
		PreparedStatement ps = null;
		int id = 0;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next()){
				id = rs.getInt("playlistId");
			}
		}
		catch(SQLException e)
		{
			System.out.println("DB problem selecting the playlist.");
		}
		return id;
	}

		
}

