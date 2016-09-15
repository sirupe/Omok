package enums;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;

public enum ClientJoinSizesEnum {
	
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	JOINFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.3)),
	JOINFRMAE_SIZE_HEIGHT((int)(JOINFRAME_SIZE_WIDTH.getSize() * 1.5)),	
	JOINFRMAE_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (JOINFRAME_SIZE_WIDTH.getSize() / 2 ))),
	JOINFRMAE_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (JOINFRMAE_SIZE_HEIGHT.getSize() / 2))),
	
	//JOINFRAME_START_X(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 13),

	//전체 레이블 위치
	
	JOIN_IDLABEL_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.02)), //옆
	JOIN_IDLABEL_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)), //아래 위치
	
	
	JOIN_PWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_PWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 2), // JOIN_ID_POSITION_Y.getSize() * 2 -- >아이디레이블 위치 잡고 그 위치에 따라 * 2씩 함
	
	JOIN_REPWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_REPWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 3),
	
	JOIN_NAMELABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_NAMELABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 4),
	
	JOIN_GENDERLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_GENDERLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 5),
	
	JOIN_BIRTHLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_BIRTHLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 6),
	
	//년,월,일 글씨 
	JOIN_YEARLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.48)),
	JOIN_YEARLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_MONTHLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_MONTHLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_DATE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.92)),
	JOIN_DATE_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_EMAIL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_EMAIL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 7),
	
	JOIN_TEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_TEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 9),
	
	
	
//텍스트필드  위치 조절-------------------------------------------
	
	//TEXT_GAP()
	//
	JOIN_IDT_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDT_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)),
	
	JOIN_PWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_PWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 2), //JOIN_IDT_POSITION_Y 을 위치로 잡고  * 2씩 증가
	
	JOIN_REPWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 3),
	
	JOIN_NAMET_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMET_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 4),
	
	// 성별 텍스트
	GENDER_MAN_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	GENDER_MAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	
	GENDER_WOMAN_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.46)),
	GENDER_WOMAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	//

	JOIN_EMAILT_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.56)),
	
	JOIN_AT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.47)),
	JOIN_AT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),
	
	JOIN_EMAILADRT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_EMAILADRT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),

	JOIN_CONFIRMT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_CONFIRMT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	JOIN_TELT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.5)),
	JOIN_TELT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.72)),
	
	JOIN_TELT2_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_TELT2_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	JOIN_HYPHEN1_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.47)),
	JOIN_HYPHEN1_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	JOIN_HYPHEN2_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.67)),
	JOIN_HYPHEN2_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	
	//년, 월 , 일 이메일 전화번호 콤보 박스
	JOIN_YEARCHOICE_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_YEARCHOICE_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.48)),
	
	JOIN_MONTHCHOICE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.52)),
	JOIN_MONTHCHOICE_POSITTION_Y(JOIN_YEARCHOICE_POSITTION_Y.getSize()),
	
	JOIN_DATECHOICE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.74)),
	JOIN_DATECHOICE_POSITTION_Y(JOIN_YEARCHOICE_POSITTION_Y.getSize()),
	
	JOIN_EMAILCHOICE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_EMAILCHOICE_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),
	

	JOIN_NUMCHOICE_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NUMCHOICE_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.72)),

	
	// 회원가입 취소 이메일 인증 버튼
	JOIN_CONFIRM_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_CONFIRM_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	JOIN_RESET_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.30)),
	JOIN_RESET_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.80)),
	
	JOIN_JOIN_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.6)),
	JOIN_JOIN_POSITTION_Y(JOIN_RESET_POSITTION_Y.getSize()),

	
	//errorMessage
	JOIN_IDERROR_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDERROR_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.12)),
	
	JOIN_PWDERROR_POSITTION_X(JOIN_IDERROR_POSITION_X.getSize()),
	JOIN_PWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.2)),
	
	JOIN_REPWDERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.28)),
	
	JOIN_NAMEERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMEERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.36)),
	
	JOIN_GENDERERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_GENDERERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.44)),
	
	JOIN_EMAILERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.65)),

	
	//year,month,date,date,tel,tel2 텍스트  조절
	SIZE_TEXT_WIDTH(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 16),
	SIZE_TEXT_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.3)),
	
	//email,emilAdr텍스트 크기 조절 , 이메일,전화번호 콤보박스, 성별 라디오박스, 버튼
	SIZE_EMAIL_WIDTH((int)(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 17.25)),
	SIZE_EMAIL_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.6)),

	//JOIN_PW_POSITION_X()
	
	// 모든 라벨 크기 조절 , 아이디,비밀번호,재비밀번호,이름 텍스트 설정
	SIZE_LABEL_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 17),
	SIZE_LABEL_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.5)),

	//회원가입 버튼 
	SIZE_JOIN_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 17),
	SIZE_JOIN_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 4)),
	
	//ErrorMessage 크기

	SIZE_ERROR_WIDTH(220),
	SIZE_ERROR_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.5)),
	
	//에러 메세지색깔
	LABELCOLOR_ERROR(Color.red),
	LABELCOLOR_DEFAULT(Color.green),
	
	//레이블 폰트
	LABELFONT_DEFAULT(new Font("맑은 고딕",Font.BOLD,14)),
	
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0)),
	
	//버튼 크기
	BUTTONIMAGE_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 15),
	BUTTONIMAGE_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 8));
	
	

	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private EmptyBorder border;
	
	private ClientJoinSizesEnum(Color color) {
		this.color = color;
	}
	private ClientJoinSizesEnum(Font font) {
		this.font = font;
	}
	private ClientJoinSizesEnum(EmptyBorder border) {
		this.border = border;
	}

	
	private ClientJoinSizesEnum(int size) {
		this.size = size;
	}
	
	private ClientJoinSizesEnum(Dimension dimension) {
		this.dimension = dimension;
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
