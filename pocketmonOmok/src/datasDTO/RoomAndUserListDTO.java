package datasDTO;

import java.util.ArrayList;

import enums.etc.UserPositionEnum;

public class RoomAndUserListDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 4354861237163809825L;
	// ���ʴ��
	//���� ���� ������Ͽ� �� ������ ���� ����Ʈ
	//���� �渮��Ʈ ���� ����Ʈ
	//������ ������ ��������
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
