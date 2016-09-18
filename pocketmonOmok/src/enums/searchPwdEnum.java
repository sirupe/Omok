package enums;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import com.sun.glass.ui.Size;

public enum searchPwdEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	SEARCH_PWD_FRAME_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	SEARCH_PWD_FRAME_HEIGHT((int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.9)),	
	SEARCH_PWD_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (SEARCH_PWD_FRAME_WIDTH.getSize() / 2 ))),
	SEARCH_PWD_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (SEARCH_PWD_FRAME_HEIGHT.getSize() / 2))),
	
//이름, 이메일 레이블
	SEARCH_ID_LABEL(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 7),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 15),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 16),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),
	SEARCH_EMAIL_LABEL(new Rectangle(
			
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 7),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 37),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 16),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),
//// 에러 레이블
	SEARCH_ERROR_LABEL(new Rectangle(
			
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 15),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 75),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 80),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 13)
			)),
////인증 레이블
//	
//// 이름 텍스트 필드 x,y, 가로 세로 
	SEARCH_ID_TEXTFIELD(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 22),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 15),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 50),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),
	// 이메일 텍스트 필드 x,y, 가로 세로 
	SEARCH_EMAIL_TEXTFIELD(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 22),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 37),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 50),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),
	// 인증  텍스트 필드 x,y, 가로 세로 
	SEARCH_CONFIRM_TEXTFIELD(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 22),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 59),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 35),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),
	// 해당 사항의 정보가 맞는지 아닌지에 대한 대답 텍스트 필드   x,y, 가로 세로 
	SEARCH_ANSWER_LABEL(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 7),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 95),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 90),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 15)
			)),
	//3분 제한시간 레이블  x,y, 가로 세로 
	SEARCH_Time_LABEL(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 45),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 55),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 15),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 15)
			)),
	
//	//인증확인 버튼ㄴ 생성
	SEARCH_CONFIRM_BUTTON(new Rectangle(
			(int)(SEARCH_PWD_FRAME_POSITION_X.getSize() / 100 * 7),
			(int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() / 100 * 59),
			(int)(SEARCH_PWD_FRAME_WIDTH.getSize() / 100 * 16),
			(int)(SEARCH_PWD_FRAME_HEIGHT.getSize() / 100 * 8)
			)),

	//일반 폰트
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,16)),
	//에러 메세지 폰트
	LABELFONT_ERROR(new Font("a으라차차",Font.BOLD,18)),
	//텍스트 테두리 없애기
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0)),	
	
	//에러 메세지색깔
		LABELCOLOR_ERROR(Color.red),
	// 일반색깔
		LABELCOLOR_DEFAULT(Color.BLUE);
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private EmptyBorder border;
	private Rectangle rec;
	
	
	private searchPwdEnum(Rectangle rec) {
		this.rec = rec;
	}
	private searchPwdEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private searchPwdEnum(int size) {
		this.size = size;
	}
	private searchPwdEnum(Color color) {
		this.color = color;
	}
	private searchPwdEnum(Font font) {
		this.font = font;
	}
	private searchPwdEnum(EmptyBorder border) {
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
