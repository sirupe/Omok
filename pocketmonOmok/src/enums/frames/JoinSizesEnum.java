package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;

public enum JoinSizesEnum {
	
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	JOINFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.3)),
	JOINFRMAE_SIZE_HEIGHT((int)(JOINFRAME_SIZE_WIDTH.getSize() * 1.5)),	
	JOINFRMAE_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (JOINFRAME_SIZE_WIDTH.getSize() / 2 ))),
	JOINFRMAE_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (JOINFRMAE_SIZE_HEIGHT.getSize() / 2))),
	
	//JOINFRAME_START_X(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 13),

	//전체 레이블 위치
	//아이디 레이블 위치 x,y
	JOIN_IDLABEL_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.02)), //옆
	JOIN_IDLABEL_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)), //아래 위치
	
	//패스워드 레이블 위치 x,y
	JOIN_PWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_PWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 2), // JOIN_ID_POSITION_Y.getSize() * 2 -- >아이디레이블 위치 잡고 그 위치에 따라 * 2씩 함
	
	//패스워드 재입력 레이블 위치 x,y
	JOIN_REPWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_REPWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 3),
	
	//이름 레이블  위치 x,y
	JOIN_NAMELABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_NAMELABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 4),
	
	//성별 위치 x,y
	JOIN_GENDERLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_GENDERLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 5),
	
	//생년월일 위치  x,y
	JOIN_BIRTHLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_BIRTHLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 6),
	
	//년,월,일 글씨 레이블 x,y위치
	JOIN_YEARLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.48)),
	JOIN_YEARLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_MONTHLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_MONTHLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_DATE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.92)),
	JOIN_DATE_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	//이메일 레이블 위치 x,y
	JOIN_EMAIL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_EMAIL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 7),
	
	//전화번호 레이블 위치 x,y
	JOIN_TEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_TEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 9),
	
	
	
//텍스트필드  위치 조절-------------------------------------------
	
	//
	// 아이디 텍스트 위치 x,y
	JOIN_IDT_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDT_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)),
	
	// 비밀번호 텍스트 x,y
	JOIN_PWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_PWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 2), //JOIN_IDT_POSITION_Y 을 위치로 잡고  * 2씩 증가
	
	// 비밀번호 텍스트 x,y
	JOIN_REPWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 3),
	
	// 이름 텍스트 x,y
	JOIN_NAMET_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMET_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 4),
	
	// 성별 텍스트 x,y 
	GENDER_MAN_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	GENDER_MAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	GENDER_WOMAN_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.46)),
	GENDER_WOMAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	//
	//이메일 아이디 텍스트 x,y
	JOIN_EMAILT_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.56)),
	
	// @ 레이블 위치 x,y
	JOIN_AT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.47)),
	JOIN_AT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),
	
	// 이메일 주소 텍스트 위치 x,y
	JOIN_EMAILADRT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_EMAILADRT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),

	// 이메일 인증 텍스트 위치 x,y
	JOIN_CONFIRMT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_CONFIRMT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	//전화 번호 텍스트 위치 x,y
	JOIN_TELT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.5)),
	JOIN_TELT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.72)),
	
	JOIN_TELT2_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_TELT2_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	//전화 번호 하이픈 레이블 우ㅣ치 
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

	
	//이메일 인증 버튼
	JOIN_CONFIRM_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_CONFIRM_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	//취소  버튼
	JOIN_RESET_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.30)),
	JOIN_RESET_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.80)),
	
	//회원가입  버튼
	JOIN_JOIN_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.6)),
	JOIN_JOIN_POSITTION_Y(JOIN_RESET_POSITTION_Y.getSize()),

	
	//errorMessage
	// 아이디 에러 레이블 위치 
	JOIN_IDERROR_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDERROR_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.12)),
	
	// 패스워드 레이블 위치
	JOIN_PWDERROR_POSITTION_X(JOIN_IDERROR_POSITION_X.getSize()),
	JOIN_PWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.2)),
	
	// 패스워드 재입력 레이블 위치 
	JOIN_REPWDERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.28)),
	
	// 이름입력 레이블 위치 
	JOIN_NAMEERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMEERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.36)),
	
	// 성별 입력 레이블 위치
	JOIN_GENDERERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_GENDERERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.44)),
	
	// 이메일 에러 레이블 위치 
	JOIN_EMAILERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.65)),
	
	JOIN_TELERROR_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_TELERROR_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.76)),

	
	//year,month,date,date,tel,tel2 텍스트  조절
	SIZE_TEXT_WIDTH(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 20),
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
	  
	CHOICEBACKGROUND(Color.black),
	
	//레이블 폰트

	LABELFONT_DEFAULT(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 100)),
	
	//컴포넌트 폰트
	JOIN_COMPFONT_DEFAULT(new Font("a으라차차", Font.PLAIN, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 120)),

	//정합성검사 라벨 폰트
	JOIN_CHECKLABEL_FONT_DEFAULT(new Font("a으라차차", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 150)),
	
	LABEL_DEFAULT_BORDER(new EmptyBorder(0,0,0,0)),
	
	//버튼 크기
	BUTTONIMAGE_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 12),
	BUTTONIMAGE_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 5)),
	
	//이메일 및 전화번호 정보
	JOIN_EMAIL_ADDRESS(new String[] {
			"직접입력",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	JOIN_TEL_FRONT_NUM(new String[] {
			"선택", "010", "011", "016", "019", "017"
	}),
	
	//메세지들
	JOIN_MESSAGE(joinMessageMap()),
	
	//email Combo 설정값 ------------------------------------------------
	JOIN_EMAIL_COMBOBOX_BACKGROUND(Color.white),
	
	
	//JoinSuccessFrame 설정값------------------------------------------------------------------------------------------------
	JOIN_SUCCESS_FRAME_WIDTH((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width * 0.2)),
	JOIN_SUCCESS_FRAME_HEIGHT((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().height * 0.2)),
	JOIN_SUCCESS_FRAME_X((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 2) - (JOIN_SUCCESS_FRAME_WIDTH.getSize() / 2)),
	JOIN_SUCCESS_FRAME_Y((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().height / 2) - (JOIN_SUCCESS_FRAME_HEIGHT.getSize() / 2)),
	
	JOIN_SUCCESS_LABEL_WIDTH((int)(JOIN_SUCCESS_FRAME_WIDTH.getSize() * 0.34)),
	JOIN_SUCCESS_LABEL_HEIGHT((int)(JOIN_SUCCESS_FRAME_HEIGHT.getSize() * 0.5)),
	JOIN_SUCCESS_LABEL_X((JOIN_SUCCESS_FRAME_WIDTH.getSize() / 2) - (JOIN_SUCCESS_LABEL_WIDTH.getSize() / 2)),
	JOIN_SUCCESS_LABEL_Y((JOIN_SUCCESS_FRAME_HEIGHT.getSize() / 2) - (int)(JOIN_SUCCESS_LABEL_HEIGHT.getSize() / 1.3)),
	
	JOIN_SUCCESS_BUTTON_WIDTH((int)(JOIN_SUCCESS_FRAME_WIDTH.getSize() * 0.15)),
	JOIN_SUCCESS_BUTTON_HEIGHT((int)(JOIN_SUCCESS_FRAME_HEIGHT.getSize() * 0.15)),
	JOIN_SUCCESS_BUTTON_X((JOIN_SUCCESS_FRAME_WIDTH.getSize() / 2) - (JOIN_SUCCESS_BUTTON_WIDTH.getSize() / 2)),
	JOIN_SUCCESS_BUTTON_Y((JOIN_SUCCESS_FRAME_HEIGHT.getSize() / 2) - (int)(JOIN_SUCCESS_BUTTON_HEIGHT.getSize() / 2));
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private EmptyBorder border;
	private String[] strArr;
	private Map<String, String> messageMap;
	
	private JoinSizesEnum(Color color) {
		this.color = color;
	}
	private JoinSizesEnum(Font font) {
		this.font = font;
	}
	private JoinSizesEnum(EmptyBorder border) {
		this.border = border;
	}
	
	private JoinSizesEnum(int size) {
		this.size = size;
	}
	
	private JoinSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private JoinSizesEnum(String[] strArr) {
		this.strArr = strArr;
	}
	
	private JoinSizesEnum(Map<String, String> map) {
		this.messageMap = map;
	}

	private static Map<String, String> joinMessageMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("join필수",			 "필수 입력사항입니다.");
		map.put("join선택", 			 "선택사항입니다.");
		map.put("join성공", 		  	 "사용 가능");
		map.put("joinID정합성",		 "영문자와 숫자만 입력이 가능합니다.");
		map.put("joinID길이",			 "3~15자 이내의 ID를 입력해주세요.");
		map.put("joinID중복",			 "이미 존재하는 ID입니다.");
		map.put("joinPW길이",			 "6~16자 이내로 입력해주세요.");
		map.put("joinPW아이디", 		 "아이디와 다르게 설정해주세요.");
		map.put("joinPW정합성",		 "영문자와 숫자, 특수문자를 1개 이사 포함시켜 주세요.");
		map.put("joinPW불일치", 		 "상단에 입력하신 패스워드와 일치하지 않습니다.");
		map.put("joinName길이", 		 "2글자 이상 입력해주세요.");
		map.put("joinName정합성",		 "한글만 입력이 가능합니다.");
		map.put("joinMail인증불일치",	 "인증번호가 일치하지 않습니다. 다시 확인해주세요.");
		map.put("joinMail정합성", 	 "이메일 형식이 정확하지 않습니다.");
		map.put("joinMail아이디미입력", "email 아이디를 입력해주세요.");
		map.put("joinMail도메인미입력", "email 도메인을 입력해주세요."); 
		map.put("joinMail인증일치", 	 "인증번호가 일치합니다.");
		map.put("joinMail시간초과", 	 "시간이 초과되었습니다. 인증번호를 다시 받아주세요.");
		map.put("joinMail인증필", 	 "이메일 인증을 받아주세요.");
		map.put("joinMail발송", 		 "입력하신 이메일로 인증번호가 발송되었습니다.");
//		map.put("joinMail발송중", 	 "이메일을 발송중입니다. 잠시만 기다려주세요.");
		map.put("joinTel정합성",		 "정보를 정확히 입력해주세요.");
		return map;
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
	
	public Map<String, String> getMessageMap() {
		return messageMap;
	}
}
