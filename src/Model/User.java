package Model;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exceptions.InvalidPlaylistException;
import Exceptions.PlayListWithSameNameException;

public class User {
	
	private int userId;
	private String name;
	private String username;
	private String password;
	private String city;
	private String email;
	private String profilePhoto;
	private Date birthday;
	private String gender;
	private String mobileNumber;
	private ArrayList<Playlist> playLists;
	private HashSet<Song> likedSongs;
	
	
	public User(String name,String username, String password, String email, String city, String gender, Date birthday, String mobileNumber) {
		
		this.name = name;
	//validations
			this.mobileNumber = mobileNumber;
		
			this.email = email;
	
			this.password = password;
	
			this.username = username;
		
			this.gender = gender;
			this.likedSongs = new HashSet();
			this.playLists = new ArrayList<>();
			this.birthday = birthday;
			this.city = city;
	}
	
	

	public void createPlayList(int id,String title,Song song) throws PlayListWithSameNameException{
		for(Playlist p : this.playLists){
			if(p.getTitle().equals(title)){
				throw new PlayListWithSameNameException();
			}
		}
		this.playLists.add(new Playlist(id,userId, title,song));
	}
	

	public void addToPlayList(String playList,Song song) throws InvalidPlaylistException {
		for(Playlist p : this.playLists){
			if(p.getTitle().equals(playList)){
				p.addSong(song);
			}
		}
		throw new InvalidPlaylistException();
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void setPhoto(String photo) {
        this.profilePhoto = photo;
    }



	public void updateProfile(){
		//TODO
	}
	
	
	public void playASong(Song song){
		song.setTimesPlayed();
	}
	
	
	//TODO not unmodifiable?
	public void postAComment(Album album,Comment c){
		album.addComment(c);
	}
	
	
	public void likeSong(Song song){
		this.likedSongs.add(song);
		song.addLikes(this);
	}
	
	
	public void removeLikedSong(Song song){
		this.likedSongs.remove(song);
	}
	
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password; //would fix this, public for the moment because of UserDAO
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public String getGender() {
		// TODO Auto-generated method stub
		return this.gender;
	}


	public String getCity() {
		// TODO Auto-generated method stub
		return this.city;
	}


	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return this.mobileNumber;
	}


	public Date getBirthday() {
		// TODO Auto-generated method stub
		return this.birthday;
	}


}
