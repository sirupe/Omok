package datasDTO;

import java.util.ArrayList;

import enums.etc.UserPositionEnum;

public class RoomAndUserListDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 4354861237163809825L;
	// 차례대로
	//대기실 우층 유저목록에 뜰 유저의 정보 리스트
	//대기실 방리스트 정보 리스트
	//접속한 유저의 게임정보
	private ArrayList<UserGamedataInfoDTO> userList;
	private ArrayList<GameRoomInfoVO> gameRoomList;
	private UserGamedataInfoDTO userGameData;
	
	
	public RoomAndUserListDTO(UserPositionEnum position) {
		super(position);
	}
	

	public void setUserList(ArrayList<UserGamedataInfoDTO> userList) {
		this.userList = userList;
	}
	
	public void setGameRoomList(ArrayList<GameRoomInfoVO> gameRoomList) {
		this.gameRoomList = gameRoomList;
	}
	
	public void setUserGameData(UserGamedataInfoDTO userGameData) {
		this.userGameData = userGameData;
	}
	
	public ArrayList<GameRoomInfoVO> getGameRoomList() {
		return gameRoomList;
	}

	public ArrayList<UserGamedataInfoDTO> getUserList() {
		return userList;
	}
	
	public UserGamedataInfoDTO getUserGameData() {
		return userGameData;
	}
	
}
