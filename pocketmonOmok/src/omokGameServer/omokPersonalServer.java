package omokGameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class omokPersonalServer extends Thread{
	private OmokServer omokServer;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	private String test; 
	
	private String test1;
	
//	
//	public omokPersonalServer(OmokServer omokServer) throws IOException {
//		this.omokServer = omokServer;
//		this.serverInputStream = new ObjectInputStream(this.omokServer.getSocket().getInputStream());
//		this.serverOutputStream = new ObjectOutputStream(this.omokServer.getSocket().getOutputStream());
//	}
	
	@Override
	public void run() {
		//TODO ¿©±â¼­ 
		while(true) {
			
		}
	}
}
