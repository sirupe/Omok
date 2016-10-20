package omokGame.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import actions.gameRoom.GameRoomServerAction;
import actions.join.JoinServerAction;
import actions.login.LoginServerAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserMessageVO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.ServerIPEnum;
import enums.etc.SoundEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.BasicFrame;
import frames.waitingRoom.WaitingRoomListTable;
import frames.waitingRoom.WaitingRoomPanel;
import utility.GetResources;

// 클라이언트 실행시 클라이언트 소켓 및 프레임 등등 생성
public class ClientAccept {
	private Socket clientSocket;
	private ObjectInputStream clientIS;
	private ObjectOutputStream clientOS;
	private BasicFrame basicFrame;
	private LoginServerAction loginRequestAction;
	private JoinServerAction joinRequestAction;
	private GameRoomServerAction gameRoomRequestAction;
	
	public ClientAccept() throws UnknownHostException, IOException {
		this.clientSocket = new Socket(ServerIPEnum.SERVER_IP.getServerIP(), ServerIPEnum.SERVER_PORT.getServerPort());
		this.clientOS 	  = new ObjectOutputStream(this.clientSocket.getOutputStream());
		this.clientIS	  = new ObjectInputStream(this.clientSocket.getInputStream());
		this.basicFrame	  = new BasicFrame(this);
		
		this.loginRequestAction		= new LoginServerAction(this.basicFrame.getLoginPanel());
		this.gameRoomRequestAction 	= new GameRoomServerAction(this.basicFrame.getGameRoomPanel());
		
		ClientReceiver reciever = new ClientReceiver(this, this.basicFrame);
		reciever.start();
	}
//로그인---------------------------------------------------------------------------------------------
	public void loginSuccessCheck(AbstractEnumsDTO data) throws IOException {		
		// 서버의 메세지
		switch(data.getServerAction()) {
		// - 로그인 중 유저가 입력을 잘못했을 시
		case LOGIN_FAIL_INPUT_ERROR :
			this.loginRequestAction.loginFail("아이디, 패스워드를 제대로 입력해주세요.");
			break;
		// - 이미 탈퇴한 유저의 정보라면
		case LOGIN_DROP_USER :
			this.loginRequestAction.loginFail("이미 탈퇴한 유저입니다.");
			break;
		// - 로그인 중 이미 접속한 유저의 정보를 입력했을 시
		case LOGIN_FAIL_OVERLAP_ACCEPT :
			this.loginRequestAction.loginFail("이미 접속중인 유저입니다.");
			break;
		// 로그인에 성공했을 시
		case LOGIN_SUCCESS :
			// 대기실로 이동하겠다는 정보를 담아 서버에 전송 (실제로 이동하진 않는다.)
			this.loginRequestAction.loginSuccess(data);
			break;
		default :
			break;
		}
	}
	
//회원가입----------------------------------------------------------------------------------------------------------
	public void joinFrameInputAction(AbstractEnumsDTO data) throws IOException {
		switch(data.getUserAction()) {
		case USER_JOIN_ID_OVERLAP_CHECK :
			this.joinRequestAction.joinOverlapCheck(data);
			break;
		case USER_JOIN_JOINACTION : 
			this.joinRequestAction.joinTry(data);
			break;
		case USER_JOIN_CERTIFICATION_SUCCESS :
			this.joinRequestAction.certificationNumSuccess();
			break;
		case USER_JOIN_CERTIFICATION_FAIL :
			this.joinRequestAction.certificationNumFail();
			break;
		default : 
			break;
		}
	}
//대기실------------------------------------------------------------------------------------------
	public void waitingRoomAction(AbstractEnumsDTO data) throws IOException {
		WaitingRoomPanel panel = this.basicFrame.getWaitingRoomPanel();
		switch(data.getServerAction() != null ? data.getServerAction() : ServerActionEnum.NOTHING) {
		// 서버에서 보낸 정보가 "새로운 유저가 접속했다" 는 정보라면
		case LOGIN_NEW_USER :
			UserGamedataInfoDTO newUserData = (UserGamedataInfoDTO)data;
			this.basicFrame.getWaitingRoomPanel().userListAddSetting(newUserData);
			this.basicFrame.getWaitingRoomPanel().getChattingOutput().append(newUserData.getUserID() + " 님께서 접속하셨습니다.\n");
			break;
			
		// 서버에서 보낸 정보가 "대기실 입장" 이라면 
		case WAITING_ROOM_ENTER :
			RoomAndUserListDTO waitingRoomInfo = (RoomAndUserListDTO)data;
			
			WaitingRoomListTable roomTable = 
					new WaitingRoomListTable(waitingRoomInfo.getGameRoomList());
			panel.setUserInfo(waitingRoomInfo.getUserGameData());
			panel.roomListSetting(roomTable);			
			panel.userListSetting(waitingRoomInfo.getUserList());
			panel.getChattingOutput().append(waitingRoomInfo.getUserGameData().getUserID() + " 님, 환영합니다.\n");
			GetResources.soundPlay(SoundEnum.LOGIN_SUCCESS_SOUND.getSound());
			this.basicFrame.showWaitingRoom();
			break;
		
		// 서버에서 보낸 정보가 "방생성 성공" 이라면
		case GAME_CREATEROOM_SUCCESS :
			UserInGameRoomDTO inGameUserInfo = (UserInGameRoomDTO)data;
			panel.getWaitingRoomActions().createRoomFrameExit();
			this.basicFrame.showGameRoom(inGameUserInfo);
			this.basicFrame.setVisible(true);
			break;
		//TODO 방생성 실패 해야 함.
		// 서버에서 보낸 정보가 "방 리스트 추가" 라면
		case GAME_ROOM_ADD :
			panel.addGameRoom((GameRoomInfoVO)data);
			break;
		// 서버에서 보낸 정보가 다른 유저 정보 보기라면 
		case OTHER_USER_INFO : 
			panel.setUserInfo((UserGamedataInfoDTO)data);
			break;
		// 서버에서 메세지를 전송 !!
		case MESSAGE_SEND_SUCCESS :
			panel.getChattingOutput().append(((UserMessageVO)data).getMessage());
			break;
		// 방리스트 정보에 변경이 생겼다.
		case GAME_ROOM_LIST_MODIFY :
			RoomAndUserListDTO waitingRoomModInfo = (RoomAndUserListDTO)data;
			panel.modGameRoom(waitingRoomModInfo);
			break;
		// 사용자가 비밀방 입장을 시도하고 있다.
		case ENTER_PRIVATE_GAME_ROOM :
			GameRoomInfoVO roomVO = (GameRoomInfoVO)data;
			boolean isPasswordFail = true;
			
			while(isPasswordFail) {
				String passwd = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");

				if(roomVO.getPwd().equals(passwd)) {
					isPasswordFail = false;
					GameRoomInfoVO enterRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_WAITING_ROOM);
					enterRoomVO.setEnterImage(roomVO.getEnterImage());
					enterRoomVO.setRoomNumber(roomVO.getRoomNumber());
					enterRoomVO.setRoomName(roomVO.getRoomName());
					enterRoomVO.setOwner(roomVO.getOwner());
					enterRoomVO.setPersons(1);
					enterRoomVO.setPwd(roomVO.getPwd());
					enterRoomVO.setGuest(this.basicFrame.getUserID());
					enterRoomVO.setUserAction(UserActionEnum.USER_ENTER_ROOM);
					this.basicFrame.sendDTO(enterRoomVO);
				} else if(passwd == null) {
					isPasswordFail = false;
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
				}
			}
			break;
			
		default :
			break;
		}
	}
	
//게임룸------------------------------------------------------------------------------
	public void inGameRoom(AbstractEnumsDTO data) {
		switch(data.getServerAction()) {
		case ENTER_ROOM_SUCCESS_GUEST :
			this.gameRoomRequestAction.guestEnterRoom(data);
			break;
		case ENTER_ROOM_SUCCESS_OWNER :
			this.gameRoomRequestAction.ownerGameRoomModify(data);
			break;
		case GAME_ROOM_USER_CHATTING :
			this.basicFrame.getGameRoomPanel().chattingAreaSetting(data);
			break;
		case GAME_ROOM_GUEST_READY_DECHECK :
		case GAME_ROOM_GUEST_READY_CHECK :
			this.basicFrame.getGameRoomPanel().changeStartGuestReadyCheck(data);
			break;
		case GAME_ROOM_GAME_START :
			this.basicFrame.getGameRoomPanel().gameStart();
			break;
		case GAME_ROOM_SEND_BOARD_INFO :
			this.basicFrame.getGameRoomPanel().boardSettingAndMyTurnStart(data);
			break;
		case GAME_ROOM_WINNER_INFO :
			this.basicFrame.getGameRoomPanel().gameEnd(data);
			break;
		case GAME_ROOM_EXIT_OTHER_USER :
			this.basicFrame.getGameRoomPanel().otherUserExitGame(data);
		default :
			break;
		}
	}

//개인정보 수정---------------------------------------------------------------------------------------------
	public void modifyAction(AbstractEnumsDTO infoDTO) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)infoDTO;
		switch(userPersonalDTO.getServerAction()) {
		case MODIFY_USER_PERSONAL_INFO :
			this.basicFrame.newModifyMyInfoFrame(userPersonalDTO);
			break;
		case MODIFY_USER_DROPCHECK_FAIL :
			this.basicFrame.getModifyMyInfoFrame().getCorrectPwdFrame().dropPwCheckFail();
			break;
		case MODIFY_USER_DROPCHECK_SUCCESS :
			this.basicFrame.getModifyMyInfoFrame().getCorrectPwdFrame().dropPwCheckSuccess();
			break;
		case MODIFY_USER_FAIL :
			this.basicFrame.getModifyMyInfoFrame().updateFail();
			break;
		case MODIFY_USER_PASSWD_FAIL :
			this.basicFrame.getModifyMyInfoFrame().passwdFail();
			break;
		case MODIFY_USER_SUCCESS :
			this.basicFrame.getModifyMyInfoFrame().updateSuccess();
			break;
		case MODIFY_USER_DROPOUT_SUCCESS :
			this.basicFrame.getModifyMyInfoFrame().getCorrectPwdFrame().dropOutSuccess();
			break;
		case MODIFY_USER_DROPOUT_FAIL :
			this.basicFrame.getModifyMyInfoFrame().getCorrectPwdFrame().dropOutFail();
			break;
		default:
			break;
		}
	}

	public void gameExit(AbstractEnumsDTO infoDTO) throws IOException {
		if(infoDTO.getServerAction() == ServerActionEnum.OTHERS_UER_EXIT) {
			this.basicFrame.getWaitingRoomPanel().deleteUserSetting((UserPersonalInfoDTO)infoDTO);
		} else {
			this.clientOS.close();
			this.clientIS.close();
			this.clientSocket.close();
			this.basicFrame.dispose();
			System.exit(0);
		}
	}
	
	public void sendDTO(AbstractEnumsDTO dto) {
		try {
			this.clientOS.writeObject(dto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObjectInputStream getClientIS() {
		return clientIS;
	}

	public ObjectOutputStream getClientOS() {
		return clientOS;
	}
	
	public void setJoinRequestAction(JoinServerAction joinRequestAction) {
		this.joinRequestAction = joinRequestAction;
	}
}
