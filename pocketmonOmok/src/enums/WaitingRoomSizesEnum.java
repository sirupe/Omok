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
	WAITING_ROOM_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 75),
	WAITING_ROOM_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 58),
	//대기방의  x,y 위치
	WAITING_ROOM_LIST_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	WAITING_ROOM_LIST_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 7),
	
	//채팅 출력창 가로,세로 길이 
	WAITING_CHATTING_OUTPUT_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 60),
	WAITING_CHATTING_OUTPUT_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 31),
	//채팅 출력창 x,y 위치
	WAITING_CHATTING_OUTPUT_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	WAITING_CHATTING_OUTPUT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 66),
	
	//채팅 입력창 가로, 세로 길이
	WAITING_CHATTING_INPUT_SIZE_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 60),
	WAITING_CHATTING_INPUT_SIZE_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 6),
	//채팅 입력창 x,y 위치
	WAITING_CHATTING_INPUT_POSITION_X(WAITING_ROOM_INFO_POSITION_X.getSize()),
	WAITING_CHATTING_INPUT_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 98),
	
	//게임시작 버튼 가로, 세로 길이
	GAMESTART_JBUTTON_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 14),
	GAMESTART_JBUTTON_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 18),
	//게임시작 x,y 위치
	GAMESTART_JBUTTON_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 62),
	GAMESTART_JBUTTON_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 66),
	
	//방생성 버튼 가로, 세로 길이
	CREATEROOM_JBUTTON_WIDTH((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100) * 14),
	CREATEROOM_JBUTTON_HEIGHT((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 18),
	//방생성 x,y 위치
	CREATEROOM_JBUTTON_POSITION_X((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() /100) * 62),
	CREATEROOM_JBUTTON_POSITION_Y((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100) * 86);
	
	

	
	
	private int size;
	
	private Dimension dimension;
	
	private WaitingRoomSizesEnum() {}
	
	//()<- 이안에 들어갈 것들이 문자형인데 이것을 정수형으로 바꿔주기위해 
	private WaitingRoomSizesEnum(int x){
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
