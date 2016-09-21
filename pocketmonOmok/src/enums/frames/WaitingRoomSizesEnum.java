package enums.frames;

import java.awt.Dimension;
import java.awt.Rectangle;

public enum WaitingRoomSizesEnum {
	//로그인창의 x,y 값
	WAITINGROOM_LABEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01)),
	WAITINGROOM_LABEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	//대기방  OX, NO, TITLE, MASTER, NUMBER
	WAITINGROOM_INFO_LABEL_RECTS(
		new Rectangle[] {
			new Rectangle(//OX
					WAITINGROOM_LABEL_POSITION_X.getSize(),
					WAITINGROOM_LABEL_POSITION_Y.getSize(),
					(int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.3),
					(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3
			), 
			new Rectangle(//NO
					WAITINGROOM_LABEL_POSITION_X.getSize() + (int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05),
					WAITINGROOM_LABEL_POSITION_Y.getSize(),
					(int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.3),
					(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3
			), 
			new Rectangle(//TITLE
					WAITINGROOM_LABEL_POSITION_X.getSize() + (int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.25),
					WAITINGROOM_LABEL_POSITION_Y.getSize(),
					(int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.5),
					(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3
			), 
			new Rectangle(//MASTER
					WAITINGROOM_LABEL_POSITION_X.getSize() + (int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.5),
					WAITINGROOM_LABEL_POSITION_Y.getSize(),
					(int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.3),
					(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3
			), 
			new Rectangle(//NUMBER
					WAITINGROOM_LABEL_POSITION_X.getSize() + (int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.6),
					WAITINGROOM_LABEL_POSITION_Y.getSize(),
					(int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.3),
					(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3
			)
		}
	),	
	//대기방 가로,세로 길이
	WAITING_ROOM_LIST_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//대기방의  x,y 위치
	WAITING_ROOM_LIST_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	WAITING_ROOM_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//대기방 배경 가로, 세로 길이
	WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//대기방 배경 x,y 위치
	WAITING_ROOM_LIST_BACKGROUND_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	WAITING_ROOM_LIST_BACKGROUND_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//==================================CHATTING==================================
	//채팅 출력창 가로,세로 길이 
	CHATTING_OUTPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.8)),
	CHATTING_OUTPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.28)),
	//채팅 출력창 x,y 위치
	CHATTING_OUTPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	CHATTING_OUTPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.59)),
		
	//채팅 입력창 가로, 세로 길이
	CHATTING_INPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.48)),
	CHATTING_INPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//채팅 입력창 x,y 위치
	CHATTING_INPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	CHATTING_INPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	
	//메시지 보내기 버튼 가로, 세로 길이
	SEND_MESSAGE_BUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),
	SEND_MESSAGE_BUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//메시지 보내기 버튼 x,y 위치
	SEND_MESSAGE_BUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.49)),
	SEND_MESSAGE_BUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	
	//==================================BUTTONS==================================
	//게임시작 버튼 가로, 세로 길이
	GAMESTART_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	GAMESTART_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	//게임시작 x,y 위치
	GAMESTART_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	GAMESTART_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	//방생성 버튼 가로, 세로 길이
	CREATEROOM_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	CREATEROOM_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	//방생성 x,y 위치
	CREATEROOM_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	CREATEROOM_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.74)),
	
	//==================================PLAYER LIST==================================
	//접속자창 가로, 세로 길이
	PLAYERS_LIST_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.24)),
	PLAYERS_LIST_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() *0.48)),
	//접속자창 x,y 위치
	PLAYERS_LIST_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.735)),
	PLAYERS_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.08)),
	
	//접속자창 배경 가로, 세로 길이
	PLAYERS_LIST_BACKGROUND_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.27)),
	PLAYERS_LIST_BACKGROUND_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() *0.54)),
	//접속자창  배경x,y 위치
	PLAYERS_LIST_BACKGROUND_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.72)),
	PLAYERS_LIST_BACKGROUND_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	
	//등급 아이콘 가로, 세로 길이
	LEVEL_ICON_SIZE_WIDTH((int)(PLAYERS_LIST_WIDTH.getSize() * 0.12)),
	LEVEL_ICON_SIZE_HEIGHT((int)(PLAYERS_LIST_HEIGHT.getSize() * 0.08)),
	
	//==================================MY INFO==================================
	//내정보 이미지 가로, 세로 길이 2:1 비율
	MY_INFO_IMAGE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	MY_INFO_IMAGE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.2)),
	//내정보 이미지 x,y 위치
	MY_INFO_IMAGE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.74)),
	MY_INFO_IMAGE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.6)),
	
	//내정보 아이디 가로, 세로 길이
	MY_INFO_USERID_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.03)),
	MY_INFO_USERID_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	//내정보 아이디 x,y 위치
	MY_INFO_USERID_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.75)),
	MY_INFO_USERID_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.81)),
	
	//내정보 등급 가로, 세로 길이
	MY_INFO_LEVEL_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_LEVEL_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 등급
	MY_INFO_LEVEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.82)),
	MY_INFO_LEVEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.85)),
		 
	//내정보 전적가로, 세로 길이
	MY_INFO_SCORE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_SCORE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 전적
	MY_INFO_SCORE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_SCORE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	//내정보 승률 가로, 세로 길이
	MY_INFO_WINNINGRATE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_WINNINGRATE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승률
	MY_INFO_WINNINGRATE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_WINNINGRATE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.67)),
	
	//내정보 승점 가로, 세로 길이
	MY_INFO_POINT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_POINT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승점
	MY_INFO_POINT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_POINT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.72)),
	
	//내정보 수정가로, 세로 길이
	MY_INFO_CORRECT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_CORRECT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 수정
	MY_INFO_CORRECT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.92)),
	MY_INFO_CORRECT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.85)),
	
	//내정보 가로, 세로 길이
	MY_INFO_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.27)),
	MY_INFO_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.36)),
	//내정보 x,y 위치
	MY_INFO_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.72)),
	MY_INFO_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.58));

	

	
	private int size;
	
	private Dimension dimension;
	
	private WaitingRoomSizesEnum() {}
	
	private Rectangle[] rect;
	
	
	//()<- 이안에 들어갈 것들이 문자형인데 이것을 정수형으로 바꿔주기위해 
	private WaitingRoomSizesEnum(int x) {
		this.size = x;
	}
	
	private WaitingRoomSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private WaitingRoomSizesEnum(Rectangle[] rect) {
		this.rect = rect;
	}

	
	public int getSize() {
		return size;
	}
	public Dimension getDimension() {
		return dimension;
	}
	
	public Rectangle[] getRect() {
		return rect;
	}
}
