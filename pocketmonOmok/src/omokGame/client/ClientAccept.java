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
import frames.waitingRoom.WaitingRoomListTable;

// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	private String userID;
	
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
			this.userID = userPersonalDTO.getUserID();
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
		if(data.getUserAction() == UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK) {
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
			if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "회원가입 완료:)");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
			
			} else {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "오류 발생 :(");
				this.basicFrame.getJoinFrame().setVisible(false);
				this.basicFrame.getJoinFrame().dispose();
				
			}
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			System.out.println("인증번호 등록중.." + ((UserPersonalInfoDTO)data).getCertificationNumber());
			this.basicFrame.getJoinFrame().getJoinAction().setCertificationNumber(((UserPersonalInfoDTO)data).getCertificationNumber());
		}
	}

	public void waitingRoomAction(AbstractEnumsDTO data, BasicFrame basicFrame) throws IOException {
		switch(data.getServerAction() != null ? data.getServerAction() : null) {
		// 서버에서 보낸 정보가 "새로운 유저가 접속했다" 는 정보라면
		case LOGIN_NEW_USER :
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			this.basicFrame.getWaitingRoomPanel().userAddSetting(newUserData);
			break;
			
		// 서버에서 보낸 정보가 "대기실 입장" 이라면
		case WAITING_ROOM_ENTER :
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			//----------- Test데이터----------------//
			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			this.basicFrame.getWaitingRoomPanel().roomListSetting(roomTable);			
			this.basicFrame.getWaitingRoomPanel().userListSetting(waitingRoomInfo.getUserList());
			this.basicFrame.inWaitingRoom();
			break;
		
		// 서버에서 보낸 정보가 "방생성 성공" 이라면 TODO
		case GAME_CREATEROOM_SUCCESS :
			this.basicFrame.getWaitingRoomPanel().getCreateGameRoomFrame().dispose();
			this.basicFrame.inGameRoom();
			this.basicFrame.setVisible(true);
			break;
		//TODO 방생성 실패 해야 함.
		// 서버에서 보낸 정보가 "방 추가" 라면
		case GAME_ROOM_ADD :
			
			break;
		default:
			break;
			
		}
//		if(data.getServerAction() == ServerActionEnum.LOGIN_NEW_USER) {
//		} else if(data.getServerAction() == ServerActionEnum.WAITING_ROOM_ENTER) {
//			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
//			
//			//-----------TODO Test데이터----------------//
//			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
//			this.basicFrame.getWaitingRoomPanel().roomListSetting(roomTable);			
//			this.basicFrame.getWaitingRoomPanel().userListSetting(waitingRoomInfo.getUserList());
//			this.basicFrame.inWaitingRoom();
//		} else if() {
//			
//		}
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
	
	public String getUserID() {
		return userID;
	}

}
