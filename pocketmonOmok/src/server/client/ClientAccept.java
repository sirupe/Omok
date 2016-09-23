package server.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import datasDTO.AbstractEnumsDTO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import frames.joinFrames.JoinSuccessFrame;

// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS 	  = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS	  = new ObjectInputStream(this.clientSocket.getInputStream());
		this.basicFrame	  = new BasicFrame(this);
		ClientReciever reciever = new ClientReciever(this, this.basicFrame);
		reciever.start();

	}

	public void loginSuccessCheck(AbstractEnumsDTO data, BasicFrame basicFrame) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		if(userPersonalDTO.getUserID() == null) {
			this.basicFrame.getLoginPanel().loginFailLabelReset();
			this.basicFrame.getLoginPanel().loginFail("아이디, 패스워드 오류입니다.");
		} else {
			
			this.basicFrame.inWaitingRoom();
		}
		
	}
	
	// 회원가입 화면에 대한 서버의 응답
	public void joinFrameInputAction(AbstractEnumsDTO data, BasicFrame basicFrame) {
		// 아이디 중복체크
		System.out.println(data.getUserAction());
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			System.out.println("아이디중복체크");
			UserPersonalInfoDTO userPersonalInfoDTO = (UserPersonalInfoDTO)data;
			String checkMsg = null;
			Color color = null;
			if(userPersonalInfoDTO.getUserID() == null) {
				checkMsg = "join성공";
				color = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
			} else {
				checkMsg = "joinID중복";
				color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
			}
			
			this.basicFrame.getJoinFrame().labelSetting(
					this.basicFrame.getJoinFrame().getIdErrorLabel(), 
					color, checkMsg);
			
		// 회원가입
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_JOINACTION) {
			System.out.println("회원가입");
			if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "회원가입이 완료되었습니다.");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
			} else {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "오류가 발생하였습니다 다시 시도해주세요.");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
				
			}
		}
	}
	//TODO
	public void waitingRoomAction(AbstractEnumsDTO data, BasicFrame basicFrame) throws IOException {
		if(data.getServerAction() == ServerActionEnum.LOGIN_NEWUSER) {
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			this.basicFrame.getWaitingRoomPanel().userAddSetting(newUserData);
		} else {
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			this.basicFrame.getWaitingRoomPanel().userListSetting(waitingRoomInfo.getUserList());
		}
	}
	
	public void gameExit() throws IOException {
		this.clientOS.close();
		this.clientIS.close();
		this.clientSocket.close();
		this.basicFrame.dispose();
		System.exit(0);
	}
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	

}
