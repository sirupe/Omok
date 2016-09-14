package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import datas.UserPersonalInfoDTO;
import datas.UserPositionIndex;
import enums.ServerIPEnum;
import frames.BasicFrame;
import frames.LoginPanel;
// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
@SuppressWarnings("serial")
public class ClientAccept implements Serializable {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS = new ObjectInputStream(this.clientSocket.getInputStream());
		this.basicFrame = new BasicFrame(this);
		ClientReciever reciever = new ClientReciever(this, this.basicFrame);
		reciever.start();
	}

	
	public void loginSuccessCheck(UserPositionIndex index, BasicFrame basicFrame) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)index;
		if(userPersonalDTO.getUserID() == null) {
			this.basicFrame.getLoginPanel().loginFailTextReset();
			this.basicFrame.getLoginPanel().loginFail("아이디, 패스워드 오류입니다.");
		} else {
			
			this.basicFrame.inWaitingRoom();
		}
		
	}
	
	public void gameExit() throws IOException {
		this.clientOS.close();
		this.clientIS.close();
		this.clientSocket.close();
		System.exit(0);
	}
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	

}
