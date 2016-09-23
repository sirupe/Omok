package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum GameRoomCreateEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//전체 크기 지정
	GAMEROOM_CREATE_FRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAMEROOM_CREATE_FRAME_SIZE_HEIGHT((int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.73)),	
	GAMEROOM_CREATE_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	GAMEROOM_CREATE_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//방이름 라벨
		GAMEROOM_CREATE_ROOM_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 8),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 20),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 40)
				)),
	//방 비밀번호 라벨	
		GAMEROOM_CREATE_ROOM_PWD_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 8),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 45),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 30),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 40)
				)),
	//===========================================================================
	//방이름 텍스트필드
		GAMEROOM_CREATE_ROOM_NAME_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 30),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 18),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 40),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 12)
				)),
	//방비밀번호 텍스트필드
		GAMEROOM_CREATE_ROOM_PWD_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 30),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 60),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 40),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 12)
				)),
	//===========================================================================
	//확인 버튼
		GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 18),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 83),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 20),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 15)
				)),
		//취소버튼
		GAMEROOM_CREATE_ROOM_CANCEL_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 35),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 83),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 20),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 15)
				)),
		//=========================================================================
		//공개방  라디오 버튼
		GAMEROOM_CREATE_ROOM_PRIVATE_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 * 10),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 35),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 30),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 20)
				)),
		//비공개방
		GAMEROOM_CREATE_ROOM_OPEN_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() / 100 *30),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() / 100 * 35),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 100 * 30),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 100 * 20)
				)),
		
		
	
	LABELCOLOR_ERROR(Color.red),
	
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,18)),
	LABELCOLOR_DEFAULT(Color.blue),
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0));
	
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private Rectangle rec;
	private EmptyBorder border;

	private GameRoomCreateEnum(Rectangle rec) {
		this.rec = rec;
	}
	private GameRoomCreateEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private GameRoomCreateEnum(int size) {
		this.size = size;
	}
	private GameRoomCreateEnum(Color color) {
		this.color = color;
	}
	private GameRoomCreateEnum(Font font) {
		this.font = font;
	}
	private GameRoomCreateEnum(EmptyBorder border) {
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
	public Color getColor() {
		return color;
	}
	public Font getFont() {
		return font;
	}
	public EmptyBorder getBorder() {
		return border;
	}
	
}
