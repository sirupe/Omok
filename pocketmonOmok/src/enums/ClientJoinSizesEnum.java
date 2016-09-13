package enums;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;


public enum ClientJoinSizesEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	JOINFRAME_SIZE_WIDTH((int)Screen_SIZE.getDimension().getWidth() / 100 * 30),
	JOINFRMAE_SIZE_HEIGHT(((int)Screen_SIZE.getDimension().getHeight() / 100 * 70)),
	JOINFRMAE_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (ClientJoinSizesEnum.JOINFRAME_SIZE_WIDTH.getSize()/2 ))),
	JOINFRMAE_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (ClientJoinSizesEnum.JOINFRMAE_SIZE_HEIGHT.getSize() /2))),
	
	JOINFRAME_START_X(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 13),
	
	JOIN_ID_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 6), //옆위치
	JOIN_ID_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 13), //아래 위치
	
	JOIN_PWD_POSITION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_PWD_POSITION_Y(JOIN_ID_POSITION_Y.getSize() + 40),
	
	JOIN_REPWD_POSITION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_REPWD_POSITION_Y(JOIN_PWD_POSITION_Y.getSize() + 40),
	
	JOIN_NAME_POSITION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_NAME_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 44),
	
	JOIN_GENDER_POSITTION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_GENDER_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 54),
	
	JOIN_BIRTH_POSITTION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_BIRTH_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 *64),
	
	JOIN_YEAR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 63),
	JOIN_YEAR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 64),
	
	JOIN_MONTH_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 89),
	JOIN_MONTH_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 *64),
	
	JOIN_DATE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 115),
	JOIN_DATE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 64),
	
	JOIN_EMAIL_POSITTION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_EMAIL_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 74),
	
	JOIN_TEL_POSITTION_X(JOIN_ID_POSITION_X.getSize()),
	JOIN_TEL_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 92),
	
	
	
//텍스트필드  위치 조절-------------------------------------------
	
	JOIN_IDT_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_IDT_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 13),
	
	JOIN_PWDT_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42 ),
	JOIN_PWDT_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 23 ),
	
	JOIN_REPWDT_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_REPWDT_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 33),
	
	JOIN_NAMET_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_NAMET_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 43),
	
	// 성별
	GENDER_MAN_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	GENDER_MAN_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 53),
	
	GENDER_WOMAN_POSITION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 62),
	GENDER_WOMAN_POSITION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 53),
	//

	
	JOIN_YEARCHOICE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_YEARCHOICE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 63),
	
	JOIN_MONTHCHOICE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 68),
	JOIN_MONTHCHOICE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 63),
	
	
	JOIN_DATECHOICE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 94),
	JOIN_DATECHOICE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 63),
	
		
	JOIN_EMAILT_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_EMAILT_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 73),
	
	JOIN_EMAILADRT_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 67),
	JOIN_EMAILADRT_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 73),
	
	JOIN_AT_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 62),
	JOIN_AT_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 73),
	
	
	JOIN_EMAILCHOICE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 89),
	JOIN_EMAILCHOICE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 73),
	

	JOIN_CONFIRMT_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 67),
	JOIN_CONFIRMT_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 80),
	
	
	JOIN_NUMCHOICE_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_NUMCHOICE_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 91),
	
	JOIN_HYPHEN1_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 63),
	JOIN_HYPHEN1_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 91),
	
	JOIN_HYPHEN2_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 80),
	JOIN_HYPHEN2_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 91),
	
	JOIN_TELT_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 66),
	JOIN_TELT_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 91),
	
	JOIN_TELT2_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 82),
	JOIN_TELT2_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 91),

	// 회원가입 취소 이메일 인증 버튼
	JOIN_CONFIRM_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_CONFIRM_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 80),
	
	JOIN_RESET_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 40),
	JOIN_RESET_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 100),
	
	JOIN_JOIN_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 70),
	JOIN_JOIN_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 100),

	//errorMessage
	JOIN_IDERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_IDERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 18),
	
	JOIN_PWDERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_PWDERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 28),
	
	JOIN_REPWDERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_REPWDERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 38),
	
	JOIN_NAMEERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_NAMEERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 48),
	

	JOIN_GENDERERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_GENDERERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 58),
	
	JOIN_EMAILERROR_POSITTION_X(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 42),
	JOIN_EMAILERROR_POSITTION_Y(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 86),

	
	//year,month,date,date,tel,tel2 텍스트  조절
	SIZE_TEXT_WIDTH(40),
	SIZE_TEXT_HEIGHT(20),
	
	//email,emilAdr텍스트 크기 조절 , 이메일,전화번호 콤보박스, 성별 라디오박스, 버튼
	SIZE_EMAIL_WIDTH(60),
	SIZE_EMAIL_HEIGHT(20),
	
	//JOIN_PW_POSITION_X()
	
	// 모든 라벨 크기 조절 , 아이디,비밀번호,재비밀번호,이름 텍스트 설정
	SIZE_LABEL_WIDTH(93),
	SIZE_LABEL_HEIGHT(17),
	
	//회원가입 버튼 
	SIZE_JOIN_WIDTH(87),
	SIZE_JOIN_HEIGHT(20),
	
	
	
	//ErrorMessage 크기
	
	SIZE_ERROR_WIDTH(220),
	SIZE_ERROR_HEIGHT(16),
	
	LABELCOLOR_ERROR(Color.red),
	LABELCOLOR_DEFAULT(Color.green);
	
	

	private Dimension dimension;
	private int size;
	
	private Color color;
	
	private ClientJoinSizesEnum(Color color) {
		this.color = color;
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
	
}
