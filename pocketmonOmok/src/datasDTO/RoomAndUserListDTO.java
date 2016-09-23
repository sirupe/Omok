package datasDTO;

import java.util.ArrayList;
import java.util.List;

import enums.etc.UserPositionEnum;

public class RoomAndUserListDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 1534234543L;

	// 차례대로
	//대기실 우층 유저목록에 뜰 유저의 정보 리스트
	//대기실 방리스트 정보 리스트
	//접속한 유저의 게임정보
	private List<UserGamedataInfoDTO> userList;
	private List<GameRoomInfoVO> gameRoomList;
	private UserGamedataInfoDTO userGameData;
	
	
	public RoomAndUserListDTO(UserPositionEnum position) {
		super(position);
	}
	

	public void setUserList(List<UserGamedataInfoDTO> userList) {
		this.userList = userList;
	}
	
	public void setGameRoomList(List<GameRoomInfoVO> gameRoomList) {
		this.gameRoomList = gameRoomList;
	}
	
	public void setUserGameData(UserGamedataInfoDTO userGameData) {
		this.userGameData = userGameData;
	}
	
	public List<GameRoomInfoVO> getGameRoomList() {
		return gameRoomList;
	}

	public List<UserGamedataInfoDTO> getUserList() {
		return userList;
	}
	
	public UserGamedataInfoDTO getUserGameData() {
		return userGameData;
	}
	
}
