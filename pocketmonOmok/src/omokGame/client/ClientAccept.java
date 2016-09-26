package omokGame.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import frames.BasicFrame;
import frames.LoginPanel;
import frames.joinFrames.JoinFrame;
import frames.joinFrames.JoinSuccessFrame;
import frames.waitingRoom.WaitingRoomListTable;
import frames.waitingRoom.WaitingRoomPanel;

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
//로그인---------------------------------------------------------------------------------------------
	public void loginSuccessCheck(AbstractEnumsDTO data, LoginPanel loginPanel) throws IOException {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		
		// 서버의 메세지
		switch(userPersonalDTO.getServerAction()) {
		// - 로그인 중 유저가 입력을 잘못했을 시
		case LOGIN_FAIL_INPUT_ERROR :
			loginPanel.loginFailLabelReset();
			loginPanel.loginFail("아이디, 패스워드 오류입니다.");
			break;
		// - 로그인 중 이미 접속한 유저의 정보를 입력했을 시
		case LOGIN_FAIL_OVERLAP_ACCEPT :
			loginPanel.loginFailLabelReset();
			loginPanel.loginFail("이미 접속중인 아이디입니다.");
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
	
//회원가입----------------------------------------------------------------------------------------------------------
	public void joinFrameInputAction(AbstractEnumsDTO data, JoinFrame joinFrame) throws IOException {
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
			
			joinFrame.labelSetting(
					joinFrame.getIdErrorLabel(), 
					color, checkMsg);
			
		// 회원가입
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_JOINACTION) {
			if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "회원가입 완료:)");
				joinFrame.setVisible(false);
				joinFrame.dispose();
			
			} else {
				new JoinSuccessFrame(this.basicFrame.getJoinFrame(), "오류 발생 :(");
				joinFrame.setVisible(false);
				joinFrame.dispose();
				
			}
		} else if(data.getUserAction() == UserActionEnum.USER_JOIN_CERTIFICATION) {
			System.out.println("인증번호 등록중.." + ((UserPersonalInfoDTO)data).getCertificationNumber());
			joinFrame.getJoinAction().setCertificationNumber(((UserPersonalInfoDTO)data).getCertificationNumber());
		}
	}
//대기실------------------------------------------------------------------------------------------
	public void waitingRoomAction(AbstractEnumsDTO data, WaitingRoomPanel waitingRoomPanel) throws IOException {
		switch(data.getServerAction() != null ? data.getServerAction() : ServerActionEnum.NOTHING) {
		// 서버에서 보낸 정보가 "새로운 유저가 접속했다" 는 정보라면
		case LOGIN_NEW_USER :
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			waitingRoomPanel.userListAddSetting(newUserData);
			waitingRoomPanel.getChattingOutput().append(newUserData.getUserID() + " 님께서 접속하셨습니다.\n");
			break;
			
		// 서버에서 보낸 정보가 "대기실 입장" 이라면 
		case WAITING_ROOM_ENTER :
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			WaitingRoomListTable roomTable = new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			waitingRoomPanel.setUserInfo(waitingRoomInfo.getUserGameData());
			waitingRoomPanel.roomListSetting(roomTable);			
			waitingRoomPanel.userListSetting(waitingRoomInfo.getUserList());
			waitingRoomPanel.getChattingOutput().append(waitingRoomInfo.getUserGameData().getUserID() + " 님, 환영합니다.\n");
			this.basicFrame.inWaitingRoom();
			break;
		
		// 서버에서 보낸 정보가 "방생성 성공" 이라면
		case GAME_CREATEROOM_SUCCESS :
			waitingRoomPanel.getCreateGameRoomFrame().dispose();
			this.basicFrame.inGameRoom();
			this.basicFrame.setVisible(true);
			break;
		//TODO 방생성 실패 해야 함.
		// 서버에서 보낸 정보가 "방 추가" 라면
		case GAME_ROOM_ADD :
			waitingRoomPanel.addGameRoom((GameRoomInfoVO)data);
			break;
		// 서버에서 보낸 정보가 다른 유저 정보 보기라면 TODO
		case OTHER_USER_INFO : 
			waitingRoomPanel.setUserInfo((UserGamedataInfoDTO)data);
			break;
		// 서버에서 메세지를 전송 !!
		case MESSAGE_SEND_SUCCESS :
			waitingRoomPanel.getChattingOutput().append(((UserMessageVO)data).getMessage());
			break;
		default:
			break;
			
		}
	}
	
	public void gameExit(AbstractEnumsDTO infoDTO) throws IOException {
		if(infoDTO.getServerAction() == ServerActionEnum.OTHER_USER_EXIT) {
			this.basicFrame.getWaitingRoomPanel().deleteUserSetting((UserPersonalInfoDTO)infoDTO);
		} else {
			
			
			this.clientOS.close();
			this.clientIS.close();
			this.clientSocket.close();
			this.basicFrame.dispose();
			System.exit(0);
		}
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
