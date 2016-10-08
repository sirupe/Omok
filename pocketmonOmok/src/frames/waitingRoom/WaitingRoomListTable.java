package frames.waitingRoom;

import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import datasDTO.GameRoomInfoVO;
import enums.frames.WaitingRoomEnum;
import utility.GetResources;

public class WaitingRoomListTable {
	
	private String[] waitingRoomListColumn;
	private Object[][] waitingRoomListData;
	
	public WaitingRoomListTable (List<GameRoomInfoVO> roomList) throws IOException{
		
		this.waitingRoomListColumn = new String[] {"OX","NO","TITLE","MASTER","NUM"};
		this.waitingRoomListData = new Object[roomList.size()][];
		
		for(int i = 0, size = waitingRoomListData.length; i < size; i++) {
			GameRoomInfoVO gameRoomInfo = roomList.get(i);
			ImageIcon image = GetResources.getImageIcon(gameRoomInfo.getEnterImage(), 
					WaitingRoomEnum.ROOMLIST_STATUS_SIZE_WIDTH.getSize() ,
					WaitingRoomEnum.ROOMLIST_STATUS_SIZW_HEIGHT.getSize());
			image.setDescription(gameRoomInfo.getEnterImage());
			waitingRoomListData[i] = new Object[] {
					image,
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
