package actions.gameRoom;

import datasDTO.AbstractEnumsDTO;
import frames.gameRoom.GameRoomPanel;

public class GameRoomServerAction {
	private GameRoomPanel gameRoomPanel;
	
	public GameRoomServerAction(GameRoomPanel gameRoomPanel) {
		this.gameRoomPanel = gameRoomPanel;
	}
	
	public void guestEnterRoom(AbstractEnumsDTO data) {
		this.gameRoomPanel.getBasicFrame().inGameRoom();
	}
}
