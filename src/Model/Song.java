package Model;
import java.util.ArrayList;

public class Song implements Comparable<Song>{
	
	
	private int songId;
	private int userId;
	private int playlistId;
	private int albumId;
	private String title;
	private String artist;
	private int timesPlayed;
	private ArrayList<User> usersLiked;
	
	
	public Song(int songId,int albumId,String title,String artist) {
		this.songId = songId;
		this.title = title;
		this.albumId = albumId;
		this.artist = artist;
	}


	public String getTitle() {
		return this.title;
	}


	public void setTimesPlayed() {
		this.timesPlayed++;
		
	}


	@Override
	public int compareTo(Song o) {
		return this.title.compareTo(o.title);
	}


	public int getTimesPlayed() {
		// TODO Auto-generated method stub
		return this.timesPlayed;
	}

	
	public int getId(){
		return this.songId;
	}



	
	public void addLikes(User user){
		this.usersLiked.add(user);
	}

	
}
