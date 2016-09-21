package enums.frames;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import com.sun.glass.ui.Size;

public enum searchIdEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	SEARCHFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	SEARCHFRAME_SIZE_HEIGHT((int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.7)),	
	SEARCHFRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (SEARCHFRAME_SIZE_WIDTH.getSize() / 2 ))),
	SEARCHFRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (SEARCHFRAME_SIZE_HEIGHT.getSize() / 2))),
	
	
//이름, 이메일 레이블
	SEARCH_ID_LABEL(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 5),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 20),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 16),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 10)
			)),
	SEARCH_EMAIL_LABEL(new Rectangle(
			
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 5),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 42),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 16),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 10)
			)),
// 에러 레이블
	SEARCH_ERROR_LABEL(new Rectangle(
			
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 20),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 54),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 50),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 13)
			)),
// 이름, 이메일 텍스트 필드
	SEARCH_ID_TEXTFIELD(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 20),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 22),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 50),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 10)
			)),
	SEARCH_EMAIL_TEXTFIELD(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 20),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 42),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 50),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 10)
			)),
	
	//버튼 생성
	SEARCH_CONFIRM_BUTTON(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() / 100 * 25),
			(int)(SEARCHFRAME_POSITION_Y.getSize() / 100 * 75),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() / 100 * 30),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() / 100 * 20)
			)),
	
	//일반 폰트
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,15)),
	//에러 메세지 폰트
	LABELFONT_ERROR(new Font("a으라차차",Font.BOLD,20)),
	//텍스트 테두리 없애기
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0)),	
	
	//에러 메세지색깔
		LABELCOLOR_ERROR(Color.red),
	// 일반색깔
		LABELCOLOR_DEFAULT(Color.green);
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private EmptyBorder border;
	private Rectangle rec;
	
	
	private searchIdEnum(Rectangle rec) {
		this.rec = rec;
	}
	private searchIdEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private searchIdEnum(int size) {
		this.size = size;
	}
	private searchIdEnum(Color color) {
		this.color = color;
	}
	private searchIdEnum(Font font) {
		this.font = font;
	}
	private searchIdEnum(EmptyBorder border) {
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
