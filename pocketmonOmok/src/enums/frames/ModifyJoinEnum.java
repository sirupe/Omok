package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;

public enum ModifyJoinEnum {
	
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	MODIFY_JOINFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.3)),
	MODIFY_JOINFRMAE_SIZE_HEIGHT((int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 1.4)),	
	MODIFY_JOINFRMAE_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (MODIFY_JOINFRAME_SIZE_WIDTH.getSize() / 2 ))),
	MODIFY_JOINFRMAE_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() / 2))),
	
//======================================라벨=====================================================================
	//아이디 라벨
	MODIFY_ID_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.2),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//비밀번호라벨
	MODIFY_PWD_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//재비밀번호라벨
	MODIFY_REPWD_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//비밀번호 재비밀번호 일치 하지 않을시 나타나는 에러 메세지
	MODIFY_REPWDERROR_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.38),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//이름
	MODIFY_NAME_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//성별 라벨
	MODIFY_GENDER_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	
	//생일 라벨
	MODIFY_BIRTH_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//년도 라벨
	MODIFY_YEAR_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.44),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//월 라벨
	MODIFY_MONTH_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.62),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//일라벨
	MODIFY_DATE_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.8),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//이메일 라벨
	MODIFY_EMAIL_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	MODIFY_AT_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.44),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.6),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	MODIFY_TELNAME_LABELMID(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.9),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
		
	MODIFY_TELHYPHEN1_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.44),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	MODIFY_TELHYPHEN2_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.62),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
//=================================gender라디오박스=================================
	MODIFY_GENDERMAN_RADIOBUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	MODIFY_GENDERWOMAN_RADIOBUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),

//=====================================텍스트====================================================================
	//아이디 텍스트필드
	MODIFY_ID_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	//비밀번호 텍스트 필드
	MODIFY_PWD_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.9),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),

	MODIFY_REPWD_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.5),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	MODIFY_NAME_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.1),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	
	MODIFY_EAMILID_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.6),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	MODIFY_EAMILADDR_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.48),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.6),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	MODIFY_TELMID_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.48),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	MODIFY_TELEND_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	//===================================초이스박스===================================
	//년도
	MODIFY_YEAR_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//월
	MODIFY_MONTH_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.47),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//도
	MODIFY_DATE_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//전화번호 앞자리
	MODIFY_TELFRONTNUM_COMBO(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	//이메일주소
	MODIFY_EMAILADDR_COMBO(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.6),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.2),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//=======================================버튼=====================================
	//수정버튼
	MODIFY_MODIFY_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.25),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	//취소
	MODIFY_CANCEL_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	//탈퇴버튼
	MODIFY_DROPOUT_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.55),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	
	//레이블 폰트

	//이메일 및 전화번호 정보
	MODIFY_EMAIL_COMBO_ADDRESS(new String[] {
			"직접입력",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	MODIFY_TEL_FRONT_NUM_COMBO(new String[] {
			"선택", "010", "011", "016", "019", "017"
	}),
	
	LABELFONT_DEFAULT(new Font("a으라차차", Font.BOLD, ModifyJoinEnum.Screen_SIZE.getDimension().width / 100)),
	
	//컴포넌트 폰트
	JOIN_COMPFONT_DEFAULT(new Font("a으라차차", Font.PLAIN, ModifyJoinEnum.Screen_SIZE.getDimension().width / 120)),

	//정합성검사 라벨 폰트
	JOIN_CHECKLABEL_FONT_DEFAULT(new Font("a으라차차", Font.BOLD, ModifyJoinEnum.Screen_SIZE.getDimension().width / 150)),
	
	//콤보박스 배경화면
	CHOICEBACKGROUND(Color.black),
	//콤보박스 이메일 배경화면
	MODIFY_EMAIL_COMBOBOX_BACKGROUND(Color.white);

	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private EmptyBorder border;
	private Rectangle rec;
	private String[] strArr;
	
	private ModifyJoinEnum(Color color) {
		this.color = color;
	}
	private ModifyJoinEnum(Font font) {
		this.font = font;
	}
	private ModifyJoinEnum(EmptyBorder border) {
		this.border = border;
	}
	
	private ModifyJoinEnum(int size) {
		this.size = size;
	}
	
	private ModifyJoinEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private ModifyJoinEnum(Rectangle rec) {
		this.rec = rec;
	}
	public Rectangle getRectangle() {
		return rec;
	}
	
	private ModifyJoinEnum(String[] strArr) {
		this.strArr = strArr;
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
	public String[] getStrArr() {
		return strArr;
	}
	
}

