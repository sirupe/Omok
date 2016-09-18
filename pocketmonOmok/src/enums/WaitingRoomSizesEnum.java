package enums;

import java.awt.Dimension;

public enum WaitingRoomSizesEnum {
		
	//대기방 방정보입력 가로, 세로길이
	WAITING_ROOM_INFO_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 75),
	WAITING_ROOM_INFO_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3),
	//대기방 방정보입력 x,y 위치
	WAITING_ROOM_INFO_POSITION_X(10),
	WAITING_ROOM_INFO_POSITION_Y(18),
		
	//대기방 가로,세로 길이
	WAITING_ROOM_LIST_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 75),
	WAITING_ROOM_LIST_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 58),
	//대기방의  x,y 위치
	WAITING_ROOM_LIST_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	WAITING_ROOM_LIST_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 7),
	
	//대기방 배경 가로, 세로 길이
	WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 75),
	WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 58),
	//대기방 배경 x,y 위치
	WAITING_ROOM_LIST_BACKGROUND_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	WAITING_ROOM_LIST_BACKGROUND_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 7),
	
	//채팅 출력창 가로,세로 길이 
	CHATTING_OUTPUT_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 60),
	CHATTING_OUTPUT_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 31),
	//채팅 출력창 x,y 위치
	CHATTING_OUTPUT_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	CHATTING_OUTPUT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 66),
		
	//채팅 입력창 가로, 세로 길이
	CHATTING_INPUT_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 53),
	CHATTING_INPUT_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 6),
	//채팅 입력창 x,y 위치
	CHATTING_INPUT_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	CHATTING_INPUT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 98),
	
	//메시지 보내기 버튼 가로, 세로 길이
	SEND_MESSAGE_BUTTON_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 8),
	SEND_MESSAGE_BUTTON_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 6),
	//메시지 보내기 버튼 x,y 위치
	SEND_MESSAGE_BUTTON_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 54),
	SEND_MESSAGE_BUTTON_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 98),
	

	//게임시작 버튼 가로, 세로 길이
	GAMESTART_JBUTTON_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 14),
	GAMESTART_JBUTTON_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 12),
	//게임시작 x,y 위치
	GAMESTART_JBUTTON_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 62),
	GAMESTART_JBUTTON_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 69),
	
	//방생성 버튼 가로, 세로 길이
	CREATEROOM_JBUTTON_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 14),
	CREATEROOM_JBUTTON_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 12),
	//방생성 x,y 위치
	CREATEROOM_JBUTTON_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() /100) * 62),
	CREATEROOM_JBUTTON_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 85),
	
	//접속자창 가로, 세로 길이
	PLAYERS_LIST_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 25),
	PLAYERS_LIST_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 55),
	//접속자창 x,y 위치
	PLAYERS_LIST_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 79),
	PLAYERS_LIST_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 8),
	
	//접속자창 배경 가로, 세로 길이
	PLAYERS_LIST_BACKGROUND_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 29),
	PLAYERS_LIST_BACKGROUND_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 61),
	//접속자창  배경x,y 위치
	PLAYERS_LIST_BACKGROUND_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 77),
	PLAYERS_LIST_BACKGROUND_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 5 ),
	
	//내정보 이미지 가로, 세로 길이
	MY_INFO_IMAGE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 10),
	MY_INFO_IMAGE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 20),
	//내정보 이미지 x,y 위치
	MY_INFO_IMAGE_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 79),
	MY_INFO_IMAGE_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 68),
	
	//내정보 아이디 가로, 세로 길이
	MY_INFO_USERID_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 6),
	MY_INFO_USERID_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3),
	//내정보 아이디 x,y 위치
	MY_INFO_USERID_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 80),
	MY_INFO_USERID_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 90),
	
	//내정보 등급
	MY_INFO_LEVEL_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 6),
	MY_INFO_LEVEL_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3),
	//내정보 등급
	MY_INFO_LEVEL_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 87),
	MY_INFO_LEVEL_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 96),
		 
	//내정보 전적
	MY_INFO_SCORE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 4),
	MY_INFO_SCORE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3),
	//내정보 전적
	MY_INFO_SCORE_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 90),
	MY_INFO_SCORE_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 71),
	
	//내정보 승률
	MY_INFO_WINNINGRATE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 10),
	MY_INFO_WINNINGRATE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 5),
	//내정보 승률
	MY_INFO_WINNINGRATE_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 90),
	MY_INFO_WINNINGRATE_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 75),
	
	//내정보 승점
	MY_INFO_POINT_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 4),
	MY_INFO_POINT_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 3),
	//내정보 승점
	MY_INFO_POINT_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 90),
	MY_INFO_POINT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 81),
	
	//내정보 수정
	MY_INFO_CORRECT_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 5),
	MY_INFO_CORRECT_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 5),
	//내정보 수정
	MY_INFO_CORRECT_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 98),
	MY_INFO_CORRECT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 95),
	
	//내정보 가로, 세로 길이
	MY_INFO_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 29),
	MY_INFO_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 40),
	//내정보 x,y 위치
	MY_INFO_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 77),
	MY_INFO_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 65);

	
	
	
	
	private int size;
	
	private Dimension dimension;
	
	private WaitingRoomSizesEnum() {}
	
	
	//()<- 이안에 들어갈 것들이 문자형인데 이것을 정수형으로 바꿔주기위해 
	private WaitingRoomSizesEnum(int x) {
		this.size = x;
	}
	
	private WaitingRoomSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	

	
	public int getSize() {
		return size;
	}
	public Dimension getDimension() {
		return dimension;
	}
}
