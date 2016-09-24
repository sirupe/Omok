package actions.waitingRoom;

import java.awt.event.ActionEvent;

import actions.adapters.Adapters;

public class WaitingRoomAction extends Adapters{
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getSource().toString();
		
		if(source.contains("createRoomButton")) {
			this.createRoom();
		}
	}
	
	public void createRoom() {
		
	}
	
}
