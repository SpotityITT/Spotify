package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Model.Album;

public class AlbumDAO {
	
	private static AlbumDAO instance;
	private Connection c = DatabaseManager.getInstance().getConnection();
	
	private static final String SELECT_GENRES = "SELECT name FROM spotify.genres;";
	private static final String SELECT_ALBUMS_BY_GENRE =
		        "SELECT a.albumId,a.title,a.artist,a.genre FROM spotify.albums a JOIN spotify.genres g USING(genre_id) WHERE g.genre_id = ?;";
	private static final String INSERT_ALBUM = "INSERT INTO spotify.albums (title, artist, description, genre,genre_Id) VALUES (?, ?, ?, ?, ?)";

	private AlbumDAO() {

	}

	public static AlbumDAO getInstance() {
		if (instance == null) {
			instance = new AlbumDAO();
		}
		return instance;
	}
	
	
	public List<String> getAllGenres() {

		List<String> genres = new ArrayList<String>();
		Statement st;
		try {
			st = DatabaseManager.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_GENRES);
			while (resultSet.next()) {
				genres.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return Collections.unmodifiableList(genres);
	}
	
	  
	
	
	public List<Album> getGenreAlbums(int genre_id) {
		List<Album> albumsInGenre = new ArrayList<Album>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(SELECT_ALBUMS_BY_GENRE);
			ps.setInt(1, genre_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Album album = new Album(rs.getInt("a.albumId"), 
						rs.getString("a.title"),
						rs.getString("a.artist"), 
						rs.getString("a.genre"));
				
			}
		} catch (SQLException e) {
			System.out.println("DB error.");
		}
		return Collections.unmodifiableList(albumsInGenre);
	}
	
	
	
	// add an album in DB -> by us
		public int addAlbum(String title, String artist, String description, String genre, int genre_id){
			PreparedStatement prepStatement = null;
			int albumId = 0;
			try {
				prepStatement = c.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, title);
				prepStatement.setString(2, artist);
				prepStatement.setString(3, description);
				prepStatement.setString(4, genre);
				prepStatement.setInt(5, genre_id);
				albumId = prepStatement.executeUpdate();
				ResultSet resultSet = prepStatement.getGeneratedKeys();
				while(resultSet.next()){
					albumId = resultSet.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Problem with inserting an album - " + e.getMessage());
			}
			finally{
				try {
					prepStatement.close();
				} catch (SQLException e) {
					System.out.println("Something went wrong when closing statement! - " + e.getMessage());
				}
			}
			return albumId;
		}
	
	

}


