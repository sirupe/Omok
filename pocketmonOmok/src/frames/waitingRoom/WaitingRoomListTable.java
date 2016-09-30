package frames.waitingRoom;

import java.io.IOException;
import java.util.List;

import datasDTO.GameRoomInfoVO;

public class WaitingRoomListTable {
	
	private String[] waitingRoomListColumn;
	private Object[][] waitingRoomListData;
	
	public WaitingRoomListTable (List<GameRoomInfoVO> roomList) throws IOException{
		
		this.waitingRoomListColumn = new String[] {"OX","NO","TITLE","MASTER","NUM"};
		this.waitingRoomListData = new Object[roomList.size()][];
		
		for(int i = 0, size = waitingRoomListData.length; i < size; i++) {
			GameRoomInfoVO gameRoomInfo = roomList.get(i);
			waitingRoomListData[i] = new Object[] {
					gameRoomInfo.getImage(),
					gameRoomInfo.getRoomNumber(),
					gameRoomInfo.getRoomName(),
					gameRoomInfo.getOwner(),
					gameRoomInfo.getPersons()
			};
		}	
	}
	
	public Object[][] getWaitingRoomListData() {
		return waitingRoomListData;
	}
	
	public String[] getWaitingRoomListColumn() {
		return waitingRoomListColumn;
	}
	
	public void setWaitingRoomListData(Object[][] waitingRoomListData) {
		this.waitingRoomListData = waitingRoomListData;
	}
}
