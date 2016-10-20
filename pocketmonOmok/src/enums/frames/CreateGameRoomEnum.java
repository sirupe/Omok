package enums.frames;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum CreateGameRoomEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//전체 크기 지정
	GAMEROOM_CREATE_FRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAMEROOM_CREATE_FRAME_SIZE_HEIGHT((int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.73)),	
	GAMEROOM_CREATE_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	GAMEROOM_CREATE_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//방이름 라벨
	GAMEROOM_CREATE_ROOM_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.13),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.03),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.4)
	)),
		
	//방 비밀번호 라벨	
	GAMEROOM_CREATE_ROOM_PWD_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.06),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.29),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.4)
				)),
	//===========================================================================
	//방이름 텍스트필드
	GAMEROOM_CREATE_ROOM_NAME_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.25),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.15),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.5),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
	)),
	
	//방비밀번호 텍스트필드
	GAMEROOM_CREATE_ROOM_PWD_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.25),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.41),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.5),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
	)),
	//===========================================================================
	//확인 버튼
	GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.18),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.55),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
	)),
	
	//취소버튼
	GAMEROOM_CREATE_ROOM_CANCEL_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.35),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.55),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
	)),
	
	//=========================================================================
	//공개방  라디오 버튼
	GAMEROOM_CREATE_ROOM_PRIVATE_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.15),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.25),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	)),
	
	//비공개방
	GAMEROOM_CREATE_ROOM_OPEN_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.35),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.25),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	));
	
	private Dimension dimension;
	private int size;
	private Rectangle rec;
	private EmptyBorder border;

	private CreateGameRoomEnum(Rectangle rec) {
		this.rec = rec;
	}
	
	private CreateGameRoomEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private CreateGameRoomEnum(int size) {
		this.size = size;
	}
	private CreateGameRoomEnum(EmptyBorder border) {
		this.border = border;
	}
	
	public Rectangle getRectangle() {
		return rec;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public int getSize() {
		return size;
	}
	
	public EmptyBorder getBorder() {
		return border;
	}
}
