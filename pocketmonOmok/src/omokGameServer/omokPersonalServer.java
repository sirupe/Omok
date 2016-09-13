package omokGameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import datas.UserPositionIndex;

public class OmokPersonalServer extends Thread{
	private OmokServer omokServer;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	
	
	
	public OmokPersonalServer(OmokServer omokServer) throws IOException {
		this.omokServer = omokServer;
		this.serverInputStream  = new ObjectInputStream(this.omokServer.getSocket().getInputStream());
		this.serverOutputStream = new ObjectOutputStream(this.omokServer.getSocket().getOutputStream());
	}
	
	@Override
	public void run() {
		boolean isAccept = true;
		//TODO ¿©±â¼­ 
		try {
			while(isAccept) {
				Object object = this.serverInputStream.readObject();
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
