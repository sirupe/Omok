package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import datas.UserPositionIndex;
// 서버에서 보내주는 데이터를 읽어들이는 녀석.
public class ClientReciever extends Thread {
	private ObjectInputStream clientIS;
	
	public ClientReciever(ClientAccept accept) {
		System.out.println("ClientReciever 생성자 실행");
		this.clientIS = accept.getClientIS();
	}
	
	//TODO 데이터가 넘나들게 될 것.
	@Override
	public void run() {
		System.out.println("ClientReciever run!!");
		while(true) {
			boolean isAccept = true;
			try {
				while(isAccept) {
					Object object = this.clientIS.readObject();
					UserPositionIndex userPosition = (UserPositionIndex)object;
					switch(userPosition.getPosition()) {
					case POSITION_LOGIN :             
						break;
					case POSITION_WAITING_ROON :      
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
						break;
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
