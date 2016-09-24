package omokGame.client;

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
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import frames.joinFrames.JoinSuccessFrame;
import frames.waitingRoomPanels.WaitingRoomListTable;

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

	public void loginSuccessCheck(AbstractEnumsDTO data, BasicFrame basicFrame) throws IOException {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		System.out.println(userPersonalDTO.getServerAction());
		
		// 서버의 메세지
		switch(userPersonalDTO.getServerAction()) {
		// - 로그인 중 유저가 입력을 잘못했을 시
		case LOGIN_FAIL_INPUT_ERROR :
			this.basicFrame.getLoginPanel().loginFailLabelReset();
			this.basicFrame.getLoginPanel().loginFail("아이디, 패스워드 오류입니다.");
			break;
		// - 로그인 중 이미 접속한 유저의 정보를 입력했을 시
		case LOGIN_FAIL_OVERLAP_ACCEPT :
			this.basicFrame.getLoginPanel().loginFailLabelReset();
			this.basicFrame.getLoginPanel().loginFail("이미 접속중인 아이디입니다.");
			break;
		// 로그인에 성공했을 시
		case LOGIN_SUCCESS :
			// 대기실로 이동하겠다는 정보를 담아 서버에 전송 (실제로 이동하진 않는다.)
			userPersonalDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
			userPersonalDTO.setUserAction(UserActionEnum.USER_LOGIN_SUCCESS);
			this.basicFrame.getClientOS().writeObject(userPersonalDTO);
			break;
		default :
			break;
		}
	}
	
	// 회원가입 화면에 대한 서버의 응답
	public void joinFrameInputAction(AbstractEnumsDTO data, BasicFrame basicFrame) throws IOException {
		// 아이디 중복체크
		System.out.println(data.getUserAction());
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
			System.out.println("아이디중복체크");
			UserPersonalInfoDTO userPersonalInfoDTO = (UserPersonalInfoDTO)data;
			String checkMsg = null;
			Color color 	= null;
			
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
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "회원가입 완료:)");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
			
			} else {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "오류 발생 :(");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
				
			}
		}
	}

	public void waitingRoomAction(AbstractEnumsDTO data, BasicFrame basicFrame) throws IOException {
		// 서버에서 보낸 정보가 "새로운 유저가 접속했다" 는 정보라면
		if(data.getServerAction() == ServerActionEnum.LOGIN_NEW_USER) {
			System.out.println(data.getServerAction() + "if");
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			this.basicFrame.getWaitingRoomPanel().userAddSetting(newUserData);
		
		// 서버에서 보낸 정보가 "대기실 입장" 이라면
		} else if(data.getServerAction() == ServerActionEnum.WAITING_ROOM_ENTER) {
			System.out.println(data.getServerAction() + "else");
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			//-----------TODO Test데이터----------------//
			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			this.basicFrame.getWaitingRoomPanel().roomListSetting(roomTable);			
			this.basicFrame.getWaitingRoomPanel().userListSetting(waitingRoomInfo.getUserList());
			this.basicFrame.inWaitingRoom();
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
