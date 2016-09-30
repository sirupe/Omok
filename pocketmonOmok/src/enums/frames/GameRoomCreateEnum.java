package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum GameRoomCreateEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//��ü ũ�� ����
	GAMEROOM_CREATE_FRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAMEROOM_CREATE_FRAME_SIZE_HEIGHT((int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.73)),	
	GAMEROOM_CREATE_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	GAMEROOM_CREATE_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//���̸� ��
		GAMEROOM_CREATE_ROOM_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.08),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.03),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.4)
				)),
	//�� ��й�ȣ ��	
		GAMEROOM_CREATE_ROOM_PWD_NAME_LABEL(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.08),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.32),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.4)
				)),
	//===========================================================================
	//���̸� �ؽ�Ʈ�ʵ�
		GAMEROOM_CREATE_ROOM_NAME_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.18),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.4),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
				)),
	//���й�ȣ �ؽ�Ʈ�ʵ�
		GAMEROOM_CREATE_ROOM_PWD_TEXT(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.45),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.4),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
				)),
	//===========================================================================
	//Ȯ�� ��ư
		GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.18),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.64),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
				)),
		//��ҹ�ư
		GAMEROOM_CREATE_ROOM_CANCEL_BUTTON(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.35),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.64),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.2),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.12)
				)),
		//=========================================================================
		//������  ���� ��ư
		GAMEROOM_CREATE_ROOM_PRIVATE_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.1),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize()* 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
				)),
		//�������
		GAMEROOM_CREATE_ROOM_OPEN_RADIO(new Rectangle(
				(int)(GAMEROOM_CREATE_FRAME_POSITION_X.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_POSITION_Y.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize() * 0.3),
				(int)(GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
				));
//		
//		
//	
//	LABELCOLOR_ERROR(Color.red),
//	
//	LABELFONT_DEFAULT(new Font("a��������",Font.BOLD,18)),
//	LABELCOLOR_DEFAULT(Color.blue),
//	LABEL_DEFAULT(new EmptyBorder(0,0,0,0));
	
	
	private Dimension dimension;
	private int size;
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
	public EmptyBorder getBorder() {
		return border;
	}
	
}
