package frames;

import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;

class WaitingRoomListTable {
	
	private ImageIcon ox;
	private String[] waitingRoomListColumn;
	private Object[][] waitingRoomListData;
	
	public WaitingRoomListTable () throws IOException{
		Font font = new Font("a生虞託託", Font.BOLD, 15 );
		
		this.ox = new ImageIcon("resources/user/userbegining.png");
		this.waitingRoomListColumn = new String[] {"OX","NO","TITLE","MASTER","NUMBER"};
		this.waitingRoomListData= new Object[][] {
				{new ImageIcon("resources/user/userbegining.png"),"1","照い食馬室食?","duswhd","(1/2)"},
				{ox,"1","照い食馬室食?","duswhd","(1/2)"},
				{ox,"1","照い食馬室食?","duswhd","(1/2)"},
				{ox,"1","照い食馬室食?","duswhd","(1/2)"},
				{ox,"1","照い食馬室食?","duswhd","(1/2)"}
		};	
	
	}
	
	public ImageIcon getOx() {
		return ox;
	}
	
	public String[] getWaitingRoomListColumn() {
		return waitingRoomListColumn;
	}
	
	public Object[][] getWaitingRoomListData() {
		return waitingRoomListData;
	}
	
}
