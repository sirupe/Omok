package datasDTO;

import enums.etc.UserPositionEnum;

public class UserInGameRoomDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = -4745744281528295153L;
	private UserGamedataInfoDTO userGameData;
	private UserGamedataInfoDTO otherGameData;
	private GameRoomInfoVO gameRoomInfo;
	private int ownerGender;
	private int guestGender;
	
	public UserInGameRoomDTO(UserPositionEnum position) {
		super(position);
	}	

	public UserGamedataInfoDTO getUserGameData() {
		return userGameData;
	}

	public void setUserGameData(UserGamedataInfoDTO userGameData) {
		this.userGameData = userGameData;
	}

	public GameRoomInfoVO getGameRoomInfo() {
		return gameRoomInfo;
	}

	public void setGameRoomInfo(GameRoomInfoVO gameRoomInfo) {
		this.gameRoomInfo = gameRoomInfo;
	}

	public int getOwnerGender() {
		return ownerGender;
	}

	public void setOwnerGender(int ownerGender) {
		this.ownerGender = ownerGender;
	}

	public int getGuestGender() {
		return guestGender;
	}

	public void setGuestGender(int guestGender) {
		this.guestGender = guestGender;
	}

	public UserGamedataInfoDTO getOtherGameData() {
		return otherGameData;
	}

	public void setOtherGameData(UserGamedataInfoDTO otherGameData) {
		this.otherGameData = otherGameData;
	}	
	
	
	
}
