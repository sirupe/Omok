package datasDTO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import enums.etc.UserPositionEnum;
import enums.frames.WaitingRoomSizesEnum;

public class GameRoomInfoVO extends AbstractEnumsDTO implements Serializable{
	private static final long serialVersionUID = 3443624527270954244L;

	public GameRoomInfoVO(UserPositionEnum position) {
		super(position);
	}
	
	private ImageIcon image;
	private int roomNumber;
	private int personNum;
	private String roomName;
	private String owner;
	private String guest;
	private String persons;
	private String pwd;
	
	public ImageIcon getImage() {
		return image;
	}
	
	public void setImage(String imageEnum) {
		try {
			this.image = new ImageIcon(ImageIO.read(
				new File(imageEnum)).getScaledInstance(
					WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZE_WIDTH.getSize() ,
					WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZW_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING)
			);
			this.image.setDescription(imageEnum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.image.setDescription(imageEnum);
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
		this.personNum	= persons;
		this.persons	= "(" + persons + "/2)";
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getPersonNum() {
		return personNum;
	}
}