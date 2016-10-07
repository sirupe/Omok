package omokGame.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import datasDTO.AbstractEnumsDTO;

import frames.BasicFrame;
import frames.searchFrames.SearchPwdFrame;
import frames.searchFrames.SearchIdFrame;
import frames.searchFrames.SearchIdPanel;
// 서버에서 보내주는 데이터를 읽어들이는 녀석.
public class ClientReceiver extends Thread {
	private ClientAccept clientAccept;
	private ObjectInputStream clientIS;
	private BasicFrame basicFrame;
	
	public ClientReceiver(ClientAccept accept, BasicFrame basicFrame) {
		this.clientAccept = accept;
		this.clientIS 	  = accept.getClientIS();
		this.basicFrame   = basicFrame;
	}
	
	@Override
	public void run() {
		boolean isAccept = true;
		try {
			while(isAccept) {
				Object object = this.clientIS.readObject();
				AbstractEnumsDTO userPosition = (AbstractEnumsDTO)object;
				switch(userPosition.getPosition()) {
				case POSITION_LOGIN :
					this.clientAccept.loginSuccessCheck(userPosition);
					break;
				case POSITION_WAITING_ROOM :
					this.clientAccept.waitingRoomAction(userPosition);
					break;
				case POSITION_JOIN :
					this.clientAccept.joinFrameInputAction(userPosition);
					break;
				case POSITION_FIND_ID :
					SearchIdFrame searchIdFrame = this.basicFrame.getSearchIdFrame();
					SearchIdPanel searchIdPanel = searchIdFrame.getSearchIdPanel();
					searchIdPanel.findIdResult(userPosition);
					break;
				case POSITION_FIND_PW : 
					SearchPwdFrame searchPwdFrame = this.basicFrame.getSearchPwdFrame();
					searchPwdFrame.receiverSuccess(userPosition);
					break;
				case POSITION_GAME_ROOM :
					this.clientAccept.inGameRoom(userPosition);
					break;
//				case POSITION_STORE :             
//					break;
				case POSITION_MODIFY_MY_INFO :
					this.clientAccept.modifyAction(userPosition);
					break;
				case POSITION_EXIT :  
					this.clientAccept.gameExit(userPosition);
					break;
				default:
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
	}
}
