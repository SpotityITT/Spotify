package Model;
import java.util.ArrayList;

public class Song implements Comparable<Song>{
	
	
	private int songId;
	private int userId;
	private int playlistId;
	private int albumId;
	private String title;
	private Album album;//?
	private String artist;
	private int timesPlayed;
	private ArrayList<User> usersLiked;
	
	
	public Song(int songId,int userId,int playlistId,int albumId,String title, Album album,String artist) {
		this.songId = songId;
		this.userId = userId;
		this.playlistId = playlistId;
		this.title = title;
		this.album = album;
		this.albumId = album.getId();
		this.artist = album.getArtist();
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	public void addLikes(User user){
		this.usersLiked.add(user);
	}

	
}
