package datasDTO;

import enums.etc.UserPositionEnum;

public class UserInGameRoomDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = -4745744281528295153L;
	private UserGamedataInfoDTO userGameData;
	private GameRoomInfoVO gameRoomInfo;
	private UserStoreInfoDTO userItemInfo;
	private UserStoreSkinInfoDTO userSkinInfo;
	
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

	public UserStoreInfoDTO getUserItemInfo() {
		return userItemInfo;
	}

	public void setUserItemInfo(UserStoreInfoDTO userItemInfo) {
		this.userItemInfo = userItemInfo;
	}

	public UserStoreSkinInfoDTO getUserSkinInfo() {
		return userSkinInfo;
	}

	public void setUserSkinInfo(UserStoreSkinInfoDTO userSkinInfo) {
		this.userSkinInfo = userSkinInfo;
	}	
}
