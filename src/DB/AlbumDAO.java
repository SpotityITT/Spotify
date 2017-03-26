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
	
	

}


