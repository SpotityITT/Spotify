package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentDAO {
	
	Connection connection = DatabaseManager.getInstance().getConnection();
	private static CommentDAO instance;
	
	private CommentDAO(){
		
	}
	
	public static synchronized CommentDAO getInstance(){
		if(instance == null){
			instance = new CommentDAO();
		}
		return instance;
	}

	// post comment on Album (add comment in DataBase)
	public int postCommentOnAlbum(int user_id, int album_id ,LocalDateTime datePosted, String content) throws SQLException{
		String sqlQuery = "INSERT INTO spotify.comments (userId, albumId, content, pstedDate) VALUES (?, ?, ?, ?)";
		PreparedStatement prepStatement = null;
		int commentId = 0;
		try{
			prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, user_id);
			prepStatement.setInt(2, album_id);
			prepStatement.setString(3, content);
			prepStatement.setTimestamp(4, Timestamp.valueOf(datePosted));
			prepStatement.executeUpdate();
			ResultSet result = prepStatement.getGeneratedKeys();
			if(result.next()){
				commentId = result.getInt(1);
			}
		}
		finally{
			prepStatement.close();
		}
		return commentId;
	}
	
	// delete comment from Album
	public void deleteCommentFromAlbum(){
		
	}
	
}

