package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import datas.UserPositionIndex;
import frames.BasicFrame;
// 서버에서 보내주는 데이터를 읽어들이는 녀석.
public class ClientReciever extends Thread {
	private ClientAccept clientAccept;
	private ObjectInputStream clientIS;
	private BasicFrame basicFrame;
	
	public ClientReciever(ClientAccept accept, BasicFrame basicFrame) {
		this.clientAccept = accept;
		this.clientIS 	  = accept.getClientIS();
		this.basicFrame   = basicFrame;
	}
	
	//TODO 데이터가 넘나들게 될 것.
	@Override
	public void run() {
		System.out.println("ClientReciever run!!");
		boolean isAccept = true;
		try {
			while(isAccept) {
				System.out.println("클라이언트 리시브 시작");
				Object object = this.clientIS.readObject();
				System.out.println("");
				UserPositionIndex userPosition = (UserPositionIndex)object;
				switch(userPosition.getPosition()) {
				case POSITION_LOGIN :   
					this.clientAccept.loginSuccessCheck(userPosition, this.basicFrame);
					break;
				case POSITION_WAITING_ROOM :      
					break;
				case POSITION_JOIN :              
					break;
				case POSITION_FIND_ID :   
					break;
				case POSITION_FIND_PW :  
					break;
				case POSITION_GAME_ROOM :         
					break;
				case POSITION_STORE :             
					break;
				case POSITION_OTHER_USER_INFO :   
					break;
				case POSITION_MODIFY_MY_INFO :    
					break;
				case POSITION_EXIT :  
					this.clientAccept.gameExit();
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
