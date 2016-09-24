package frames.waitingRoomPanels;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import datasDTO.GameRoomInfoVO;
import enums.frames.WaitingRoomSizesEnum;



public class WaitingRoomListTable {
	
	private ImageIcon ox;
	private String[] waitingRoomListColumn;
	private Object[][] waitingRoomListData;
	
	public WaitingRoomListTable (List<GameRoomInfoVO> roomList) throws IOException{
			
//		this.ox=new ImageIcon(ImageIO.read(
//					new File("resources/user/userbegining.png")).getScaledInstance(
//						WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZE_WIDTH.getSize() ,
//						WaitingRoomSizesEnum.ROOMLIST_STATUS_SIZW_HEIGHT.getSize(),
//						Image.SCALE_AREA_AVERAGING)
//		);
		
		this.waitingRoomListColumn = new String[] {"OX","NO","TITLE","MASTER","NUM"};
		this.waitingRoomListData= new Object[roomList.size()][];
		
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
//				{
//				{ox,"1","방제목이얼마나길어질지모르겠네요","1234567890101112","(1/2)"},
//				{ox,"1","안ㄴ방제목이얼마나길어질지모르겠네요g44g?","dus","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(2/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"},
//				{ox,"1","안ㄴ여하세여?","duswhd","(1/2)"}
//		};	
	
	}
	
	public String[] getWaitingRoomListColumn() {
		return waitingRoomListColumn;
	}
	
	public Object[][] getWaitingRoomListData() {
		return waitingRoomListData;
	}
}
