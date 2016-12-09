package omokGame.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datasDTO.AbstractEnumsDTO;

public class OmokPersonalServer extends Thread {
	private OmokServer omokServer;
	private Socket personalSocket;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	private String certificationNumber;
	private int idCheck;
	


	public OmokPersonalServer(OmokServer omokServer, Socket socket) throws IOException {
		this.omokServer 		= omokServer;
		this.personalSocket 	= socket;
		this.serverInputStream  = new ObjectInputStream(this.personalSocket.getInputStream());
		this.serverOutputStream = new ObjectOutputStream(this.personalSocket.getOutputStream());
	}
	
	@Override
	public void run() {
		boolean isAccept = true;
		synchronized (this) {	
			try {
				while(isAccept) {
					Object object = this.serverInputStream.readObject();
					AbstractEnumsDTO userPosition = (AbstractEnumsDTO) object;
					switch(userPosition.getPosition()) {
					case POSITION_LOGIN :
						this.omokServer.login(userPosition, this);
						break;
					case POSITION_WAITING_ROOM :
						this.omokServer.waitingRoom(userPosition, this);
						break;
					case POSITION_JOIN :
						this.omokServer.join(userPosition, this);
						break;
					//연종
					case POSITION_FIND_ID :
						this.omokServer.findID(userPosition, this);
						break;
					//수진
					case POSITION_FIND_PW :
						this.omokServer.findPw(userPosition, this);
						break;
					case POSITION_GAME_ROOM :
						this.omokServer.gameRoom(userPosition, this);
						break;
					case POSITION_MODIFY_MY_INFO :
						this.omokServer.modifyMyInfo(userPosition, this);
						break;
					case POSITION_EXIT :
						isAccept = false;
						this.omokServer.exitProgram(userPosition, this);
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

	public ObjectInputStream getServerInputStream() {
		return serverInputStream;
	}

	public ObjectOutputStream getServerOutputStream() {
		return serverOutputStream;
	}

	public Socket getPersonalSocket() {
		return personalSocket;
	}
	
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	
	public String getCertificationNumber() {
		return certificationNumber;
	}
	
	public int getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(int idCheck) {
		this.idCheck = idCheck;
	}
	
	
}
