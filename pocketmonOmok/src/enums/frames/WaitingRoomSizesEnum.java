package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public enum WaitingRoomSizesEnum {
	//로그인창의 x,y 값
	WAITINGROOM_LABEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01)),
	WAITINGROOM_LABEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	//대기방 가로,세로 길이
	WAITING_ROOM_LIST_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//대기방의  x,y 위치
	WAITING_ROOM_LIST_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01)),
	WAITING_ROOM_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//대기방 배경 가로, 세로 길이
	WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//대기방 배경 x,y 위치
	WAITING_ROOM_LIST_BACKGROUND_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	WAITING_ROOM_LIST_BACKGROUND_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//대기방 O△X 가로 세로 길이
	ROOMLIST_STATUS_SIZE_WIDTH((int)(WAITING_ROOM_LIST_SIZE_WIDTH.getSize() * 0.03)),
	ROOMLIST_STATUS_SIZW_HEIGHT((int)(WAITING_ROOM_LIST_SIZE_HEIGHT.getSize() * 0.05)),
	
	//==================================CHATTING==================================
	//채팅 출력창 가로,세로 길이 
	CHATTING_OUTPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.8)),
	CHATTING_OUTPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.28)),
	//채팅 출력창 x,y 위치
	CHATTING_OUTPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	CHATTING_OUTPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.59)),
		
	//채팅 입력창 가로, 세로 길이
	CHATTING_INPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.38)),
	CHATTING_INPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//채팅 입력창 x,y 위치
	NOTICE_TEXTFIELD_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	NOTICE_TEXTFIELD_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),

	CHATTING_INPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize() + NOTICE_TEXTFIELD_WIDTH.getSize()),
	CHATTING_INPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	//메시지 보내기 버튼 가로, 세로 길이
	SEND_MESSAGE_BUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),
	SEND_MESSAGE_BUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//메시지 보내기 버튼 x,y 위치
	SEND_MESSAGE_BUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.49)),
	SEND_MESSAGE_BUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	
	//==================================BUTTONS==================================
	// 은정 변경 수정하기 가로 세로
	MODIFYINFO_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	MODIFYINFO_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	// 은정 변경 수정하기 x, y
	MODIFYINFO_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	MODIFYINFO_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	//방생성 버튼 가로, 세로 길이
	GAMESTART_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	GAMESTART_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	//방생성 x,y 위치
	GAMESTART_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	GAMESTART_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.74)),
	
	//==================================PLAYER LIST==================================
	//접속자창 가로, 세로 길이
	PLAYERS_LIST_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.24)),
	PLAYERS_LIST_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() *0.48)),
	//접속자창 x,y 위치
	PLAYERS_LIST_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.014)),
	PLAYERS_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	
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
	//내정보 이미지 가로, 세로 길이 
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
	//내정보 아이디 텍스트창 가로, 세로 길이
	MY_INFO_USERID_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.18)),
	MY_INFO_USERID_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	//내정보 아이디 텍스트창 x,y 위치
	MY_INFO_USERID_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.78)),
	MY_INFO_USERID_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.811)),
	
	//내정보 등급 가로, 세로 길이
	MY_INFO_LEVEL_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_LEVEL_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 등급
	MY_INFO_LEVEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.81)),
	MY_INFO_LEVEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.855)), 
	//내정보 등급 텍스트이미지 가로, 세로 길이
	MY_INFO_LEVEL_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_LEVEL_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 등급 텍스트 이미지 x,y 위치
	MY_INFO_LEVEL_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.75)),
	MY_INFO_LEVEL_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.85)),
	
	//내정보 전적가로, 세로 길이
	MY_INFO_SCORE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_SCORE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 전적
	MY_INFO_SCORE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_SCORE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	//내정보 전적 텍스트 가로, 세로 길이
	MY_INFO_SCORE_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_SCORE_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 전적 텍스트 x,y 위치
	MY_INFO_SCORE_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_SCORE_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	
	//내정보 승률 가로, 세로 길이
	MY_INFO_WINNINGRATE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_WINNINGRATE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승률
	MY_INFO_WINNINGRATE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_WINNINGRATE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.67)),
	
	//내정보 승률 텍스트 가로, 세로 길이
	MY_INFO_WINNINGRATE_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_WINNINGRATE_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승룰 텍스트 x,y 위치
	MY_INFO_WINNINGRATE_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_WINNINGRATE_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.67)),
	
	//내정보 승점 가로, 세로 길이
	MY_INFO_POINT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_POINT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승점
	MY_INFO_POINT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_POINT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.72)),
	
	//내정보 승점 텍스트 가로, 세로 길이
	MY_INFO_POINT_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_POINT_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//내정보 승점 텍스트 x,y 위치
	MY_INFO_POINT_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_POINT_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.72)),
	
	
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
	MY_INFO_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.58)),

	USER_INFO_VIEW_SIZE_X((int)(MY_INFO_IMAGE_WIDTH.getSize() * 0.05)),
	USER_INFO_VIEW_SIZE_Y((int)(MY_INFO_IMAGE_HEIGHT.getSize() * 0.05)),
	USER_INFO_VIEW_SIZE_WIDTH((int)(MY_INFO_IMAGE_WIDTH.getSize() * 0.9)),
	USER_INFO_VIEW_SIZE_HEIGHT((int)(MY_INFO_IMAGE_HEIGHT.getSize() * 0.9)),
	
	//==================================FONTSIZE&COLOR==================================
	
	FONTCOLOR_ERROR(Color.red),
	FONTCOLOR_DEFAULT(Color.green),
	
	
	LABELFONT_SIZE60(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 60)),
	LABELFONT_SIZE70(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 70)),
	LABELFONT_SIZE80(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 80)),
	LABELFONT_SIZE90(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 90)),
	LABELFONT_SIZE100(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 100)),
	LABELFONT_SIZE130(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 130));
	
	
	
	
	
	private int size;
	private Font font;
	private Color color;
	private Dimension dimension;
	
	private WaitingRoomSizesEnum() {}
	

	
	//()<- 이안에 들어갈 것들이 문자형인데 이것을 정수형으로 바꿔주기위해 
	private WaitingRoomSizesEnum(int x) {
		this.size = x;
	}
	private WaitingRoomSizesEnum(Font font) {
		this.font = font;
	}
	private WaitingRoomSizesEnum(Color color) {
		this.color = color;
	}
	private WaitingRoomSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	
	public int getSize() {
		return size;
	}
	public Font getfont() {
		return font;
	}
	public Color getColot() {	
		return color;
	}
	public Dimension getDimension() {
		return dimension;
	}
	

}
