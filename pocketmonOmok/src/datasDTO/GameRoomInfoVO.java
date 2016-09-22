package datasDTO;

import java.io.Serializable;

import enums.etc.ImageEnum;

public class GameRoomInfoVO implements Serializable{
	private ImageEnum imageDir;
	private int roomNumber;
	private String roomName;
	private String owner;
	private String guest;
	private int persons;
	
	public ImageEnum getImageDir() {
		return imageDir;
	}
	
	public void setImageDir(ImageEnum imageDir) {
		this.imageDir = imageDir;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getGuest() {
		return guest;
	}
	
	public void setGuest(String guest) {
		this.guest = guest;
	}
	
	public int getPersons() {
		return persons;
	}
	
	public void setPersons(int persons) {
		this.persons = persons;
	}
}