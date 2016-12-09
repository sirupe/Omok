package datasDTO;

import java.io.Serializable;

import enums.etc.UserPositionEnum;

public class GameRoomInfoVO extends AbstractEnumsDTO implements Serializable{
	private static final long serialVersionUID = 3443624527270954244L;

	public GameRoomInfoVO(UserPositionEnum position) {
		super(position);
	}
	
	private String enterImage;
//	private ImageIcon guestImage;
	private int roomNumber;
	private int personNum;
	private String roomName;
	private String owner;
	private String guest;
	private String persons;
	private String pwd;
	
	public String getEnterImage() {
		return enterImage;
	}
	
	public void setEnterImage(String imageEnum) {
		this.enterImage = imageEnum;
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

	@Override
	public String toString() {
		return "GameRoomInfoVO [enterImage=" + enterImage + ", roomNumber=" + roomNumber + ", personNum=" + personNum
				+ ", roomName=" + roomName + ", owner=" + owner + ", guest=" + guest + ", persons=" + persons + ", pwd="
				+ pwd + "]";
	}
	
	
}