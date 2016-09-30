package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum SearchRePwdEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	SEARCH_REPWD_FRAME_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	SEARCH_REPWD_FRAME_HEIGHT((int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.9)),	
	SEARCH_REPWD_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (SEARCH_REPWD_FRAME_WIDTH.getSize() / 2 ))),
	SEARCH_REPWD_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (SEARCH_REPWD_FRAME_HEIGHT.getSize() / 2))),
	
//비밀번호, 레이블
	SEARCH_PWD_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.07),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.15),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.2),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.08)
	)),
	//재비밀번호 레이블
	SEARCH_REPWD_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.07),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.37),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.3),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() *0.08)
	)),
	//설정된 비밀번호와 재비밀번호의 에러 결과 메세지 
		SEARCH_ERROR_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.1),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.55),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.9),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.2)
		)),
	// 비밀번호 바뀌었다는 메세지 -- > 화면 전환(searchChangePanel)
		SEARCH_CONFIRM_CHANGE_LABEL(new Rectangle(
				(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.06),
				(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.3),
				(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() / 100 * 100),
				(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.35)
		)),
		
		//비밀번호 재비밀번호 텍스트s
		SEARCH_PWD_TEXTFIELD(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.28),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.15),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.5),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.08)
		)),
		SEARCH_REPWD_TEXTFIELD(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.28),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.37),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.5),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.08)
				)),
		
		//searchRePwd 확인 버튼 생성
		SEARCH_CONFIRM_BUTTON(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.2),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.9),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.4),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.1)
			)),
		//searchChangePwd 버튼 생성
		SEARCH__CHANGE_CONFIRM_BUTTON(new Rectangle(
				(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.2),
				(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.9),
				(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.4),
				(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.15)
				)),
		

	//일반 폰트
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,17)),
	LABELREPWDFONT_DEFAULT(new Font("a으라차차",Font.BOLD,13)),
	//에러 메세지 폰트
	LABELFONT_ERROR(new Font("a으라차차",Font.BOLD,19)),

	//에러 메세지색깔
	LABELCOLOR_ERROR(Color.red),
	// 일반색깔
	LABELCOLOR_DEFAULT(Color.BLUE);
	
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private Rectangle rec;
	
	
	private SearchRePwdEnum(Rectangle rec) {
		this.rec = rec;
	}
	private SearchRePwdEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private SearchRePwdEnum(int size) {
		this.size = size;
	}
	private SearchRePwdEnum(Color color) {
		this.color = color;
	}
	private SearchRePwdEnum(Font font) {
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
