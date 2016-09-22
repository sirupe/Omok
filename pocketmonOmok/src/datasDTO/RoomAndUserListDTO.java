package datasDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import enums.etc.UserPositionEnum;
import server.omokGameServer.OmokPersonalServer;

public class RoomAndUserListDTO extends AbstractEnumsDTO implements Serializable {
	private Map<String, OmokPersonalServer> loginUsersMap;
	private List<GameRoomInfoVO> gameRoomList;
//	private UserGamedataInfoDTO userGameData;
	
	
	public RoomAndUserListDTO(UserPositionEnum position) {
		super(position);
	}
	

	
	public void setLoginUsersMap(Map<String, OmokPersonalServer> loginUsersMap) {
		this.loginUsersMap = loginUsersMap;
	}
	
	public void setGameRoomList(List<GameRoomInfoVO> gameRoomList) {
		this.gameRoomList = gameRoomList;
	}
	
//	public void setUserGameData(UserGamedataInfoDTO userGameData) {
//		this.userGameData = userGameData;
//	}
	
	public List<GameRoomInfoVO> getGameRoomList() {
		return gameRoomList;
	}
	
	public Map<String, OmokPersonalServer> getLoginUsersMap() {
		return loginUsersMap;
	}
	
//	public UserGamedataInfoDTO getUserGameData() {
//		return userGameData;
//	}
	
}
