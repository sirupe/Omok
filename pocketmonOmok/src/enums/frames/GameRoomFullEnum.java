package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum GameRoomFullEnum {
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//전체 크기 지정
	GAMEROOMFULLFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.23)),
	GAMEROOMFULLFRAME_SIZE_HEIGHT((int)(GAMEROOMFULLFRAME_SIZE_WIDTH.getSize() * 0.7)),	
	GAMEROOMFULLFRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAMEROOMFULLFRAME_SIZE_WIDTH.getSize() / 2 ))),
	GAMEROOMFULLFRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAMEROOMFULLFRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//들어 가려는 방의 정보 --- > 빈방인지 아닌지
	GAMEROOM_CONFIRM_LABEL(new Rectangle(
			(int)(GAMEROOMFULLFRAME_POSITION_X.getSize() * 0.13),
			(int)(GAMEROOMFULLFRAME_POSITION_Y.getSize() * 0.15),
			(int)(GAMEROOMFULLFRAME_SIZE_WIDTH.getSize() * 0.7),
			(int)(GAMEROOMFULLFRAME_SIZE_HEIGHT.getSize() * 0.4)
			)),
	
	//버튼 생성
	GAMEROOM_CONFIRM_BUTTON(new Rectangle(
				(int)(GAMEROOMFULLFRAME_POSITION_X.getSize() * 0.22),
				(int)(GAMEROOMFULLFRAME_POSITION_Y.getSize() * 0.42),
				(int)(GAMEROOMFULLFRAME_SIZE_WIDTH.getSize() * 0.25),
				(int)(GAMEROOMFULLFRAME_SIZE_HEIGHT.getSize() * 0.15)
				)),
	
	//에러 메세지색깔
	LABELCOLOR_ERROR(Color.red),
			
	LABELFONT_ERROR(new Font("a으라차차",Font.BOLD,17)),
	LABELCOLOR_DEFAULT(Color.blue);
	
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private Rectangle rec;

	private GameRoomFullEnum(Rectangle rec) {
		this.rec = rec;
	}
	private GameRoomFullEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private GameRoomFullEnum(int size) {
		this.size = size;
	}
	private GameRoomFullEnum(Color color) {
		this.color = color;
	}
	private GameRoomFullEnum(Font font) {
		this.font = font;
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
	
}
