package datasDTO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import enums.frames.WaitingRoomSizesEnum;

public class GameRoomInfoVO implements Serializable{
	private ImageIcon image;
	private int roomNumber;
	private String roomName;
	private String owner;
	private String guest;
	private String persons;
	
	public ImageIcon getImage() {
		return image;
	}
	
	public void setImage(String imageEnum) throws IOException {
		this.image = new ImageIcon(ImageIO.read(
			new File(imageEnum)).getScaledInstance(
				WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZE_WIDTH.getSize() ,
				WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZW_HEIGHT.getSize(),
				Image.SCALE_AREA_AVERAGING)
		);
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
	
	public String getPersons() {
		return persons;
	}
	
	public void setPersons(int persons) {
		this.persons = "(" + persons + "/2)";
	}
}