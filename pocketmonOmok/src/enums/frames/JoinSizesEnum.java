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

	//��ü ���̺� ��ġ
	//���̵� ���̺� ��ġ x,y
	JOIN_IDLABEL_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.02)), //��
	JOIN_IDLABEL_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)), //�Ʒ� ��ġ
	
	//�н����� ���̺� ��ġ x,y
	JOIN_PWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_PWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 2), // JOIN_ID_POSITION_Y.getSize() * 2 -- >���̵��̺� ��ġ ��� �� ��ġ�� ���� * 2�� ��
	
	//�н����� ���Է� ���̺� ��ġ x,y
	JOIN_REPWDLABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_REPWDLABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 3),
	
	//�̸� ���̺�  ��ġ x,y
	JOIN_NAMELABEL_POSITION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_NAMELABEL_POSITION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 4),
	
	//���� ��ġ x,y
	JOIN_GENDERLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_GENDERLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 5),
	
	//������� ��ġ  x,y
	JOIN_BIRTHLABEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_BIRTHLABEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 6),
	
	//��,��,�� �۾� ���̺� x,y��ġ
	JOIN_YEARLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.48)),
	JOIN_YEARLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_MONTHLABEL_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_MONTHLABEL_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	JOIN_DATE_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.92)),
	JOIN_DATE_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.482)),
	
	//�̸��� ���̺� ��ġ x,y
	JOIN_EMAIL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_EMAIL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 7),
	
	//��ȭ��ȣ ���̺� ��ġ x,y
	JOIN_TEL_POSITTION_X(JOIN_IDLABEL_POSITION_X.getSize()),
	JOIN_TEL_POSITTION_Y(JOIN_IDLABEL_POSITION_Y.getSize() * 9),
	
	
	
//�ؽ�Ʈ�ʵ�  ��ġ ����-------------------------------------------
	
	//
	// ���̵� �ؽ�Ʈ ��ġ x,y
	JOIN_IDT_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDT_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.08)),
	
	// ��й�ȣ �ؽ�Ʈ x,y
	JOIN_PWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_PWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 2), //JOIN_IDT_POSITION_Y �� ��ġ�� ���  * 2�� ����
	
	// ��й�ȣ �ؽ�Ʈ x,y
	JOIN_REPWDT_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDT_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 3),
	
	// �̸� �ؽ�Ʈ x,y
	JOIN_NAMET_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMET_POSITION_Y(JOIN_IDT_POSITION_Y.getSize() * 4),
	
	// ���� �ؽ�Ʈ x,y 
	GENDER_MAN_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	GENDER_MAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	GENDER_WOMAN_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.46)),
	GENDER_WOMAN_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.4)),
	//
	//�̸��� ���̵� �ؽ�Ʈ x,y
	JOIN_EMAILT_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.56)),
	
	// @ ���̺� ��ġ x,y
	JOIN_AT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.47)),
	JOIN_AT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),
	
	// �̸��� �ּ� �ؽ�Ʈ ��ġ x,y
	JOIN_EMAILADRT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_EMAILADRT_POSITTION_Y(JOIN_EMAILT_POSITTION_Y.getSize()),

	// �̸��� ���� �ؽ�Ʈ ��ġ x,y
	JOIN_CONFIRMT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.51)),
	JOIN_CONFIRMT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	//��ȭ ��ȣ �ؽ�Ʈ ��ġ x,y
	JOIN_TELT_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.5)),
	JOIN_TELT_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.72)),
	
	JOIN_TELT2_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.7)),
	JOIN_TELT2_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	//��ȭ ��ȣ ������ ���̺� ���ġ 
	JOIN_HYPHEN1_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.47)),
	JOIN_HYPHEN1_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	JOIN_HYPHEN2_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.67)),
	JOIN_HYPHEN2_POSITTION_Y(JOIN_TELT_POSITTION_Y.getSize()),
	
	
	//��, �� , �� �̸��� ��ȭ��ȣ �޺� �ڽ�
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

	
	//�̸��� ���� ��ư
	JOIN_CONFIRM_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_CONFIRM_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.6)),
	
	//���  ��ư
	JOIN_RESET_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.30)),
	JOIN_RESET_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.80)),
	
	//ȸ������  ��ư
	JOIN_JOIN_POSITTION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.6)),
	JOIN_JOIN_POSITTION_Y(JOIN_RESET_POSITTION_Y.getSize()),

	
	//errorMessage
	// ���̵� ���� ���̺� ��ġ 
	JOIN_IDERROR_POSITION_X((int)(JOINFRAME_SIZE_WIDTH.getSize() * 0.3)),
	JOIN_IDERROR_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.12)),
	
	// �н����� ���̺� ��ġ
	JOIN_PWDERROR_POSITTION_X(JOIN_IDERROR_POSITION_X.getSize()),
	JOIN_PWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.2)),
	
	// �н����� ���Է� ���̺� ��ġ 
	JOIN_REPWDERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_REPWDERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.28)),
	
	// �̸��Է� ���̺� ��ġ 
	JOIN_NAMEERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_NAMEERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.36)),
	
	// ���� �Է� ���̺� ��ġ
	JOIN_GENDERERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_GENDERERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.44)),
	
	// �̸��� ���� ���̺� ��ġ 
	JOIN_EMAILERROR_POSITTION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_EMAILERROR_POSITTION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.65)),
	
	JOIN_TELERROR_POSITION_X(JOIN_IDT_POSITION_X.getSize()),
	JOIN_TELERROR_POSITION_Y((int)(JOINFRMAE_SIZE_HEIGHT.getSize() * 0.76)),

	
	//year,month,date,date,tel,tel2 �ؽ�Ʈ  ����
	SIZE_TEXT_WIDTH(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 20),
	SIZE_TEXT_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.3)),
	
	//email,emilAdr�ؽ�Ʈ ũ�� ���� , �̸���,��ȭ��ȣ �޺��ڽ�, ���� �����ڽ�, ��ư
	SIZE_EMAIL_WIDTH((int)(JOINFRAME_SIZE_WIDTH.getSize() / 100 * 17.25)),
	SIZE_EMAIL_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.6)),

	//JOIN_PW_POSITION_X()
	
	// ��� �� ũ�� ���� , ���̵�,��й�ȣ,���й�ȣ,�̸� �ؽ�Ʈ ����
	SIZE_LABEL_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 17),
	SIZE_LABEL_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.5)),

	//ȸ������ ��ư 
	SIZE_JOIN_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 17),
	SIZE_JOIN_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 4)),
	
	//ErrorMessage ũ��

	SIZE_ERROR_WIDTH(220),
	SIZE_ERROR_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 3.5)),
	
	//���� �޼�������
	LABELCOLOR_ERROR(Color.red),
	LABELCOLOR_DEFAULT(Color.green),
	  
	CHOICEBACKGROUND(Color.black),
	
	//���̺� ��Ʈ

	LABELFONT_DEFAULT(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 100)),
	
	//������Ʈ ��Ʈ
	JOIN_COMPFONT_DEFAULT(new Font("a��������", Font.PLAIN, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 120)),

	//���ռ��˻� �� ��Ʈ
	JOIN_CHECKLABEL_FONT_DEFAULT(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 150)),
	
	LABEL_DEFAULT_BORDER(new EmptyBorder(0,0,0,0)),
	
	//��ư ũ��
	BUTTONIMAGE_WIDTH(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 12),
	BUTTONIMAGE_HEIGHT((int)(JOINFRMAE_SIZE_HEIGHT.getSize() / 100 * 5)),
	
	//�̸��� �� ��ȭ��ȣ ����
	JOIN_EMAIL_ADDRESS(new String[] {
			"�����Է�",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	JOIN_TEL_FRONT_NUM(new String[] {
			"����", "010", "011", "016", "019", "017"
	}),
	
	//�޼�����
	JOIN_MESSAGE(joinMessageMap()),
	
	//email Combo ������ ------------------------------------------------
	JOIN_EMAIL_COMBOBOX_BACKGROUND(Color.white),
	
	
	//JoinSuccessFrame ������------------------------------------------------------------------------------------------------
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
		map.put("join�ʼ�",			 "�ʼ� �Է»����Դϴ�.");
		map.put("join����", 			 "���û����Դϴ�.");
		map.put("join����", 		  	 "��� ����");
		map.put("joinID���ռ�",		 "�����ڿ� ���ڸ� �Է��� �����մϴ�.");
		map.put("joinID����",			 "3~15�� �̳��� ID�� �Է����ּ���.");
		map.put("joinID�ߺ�",			 "�̹� �����ϴ� ID�Դϴ�.");
		map.put("joinPW����",			 "6~16�� �̳��� �Է����ּ���.");
		map.put("joinPW���̵�", 		 "���̵�� �ٸ��� �������ּ���.");
		map.put("joinPW���ռ�",		 "�����ڿ� ����, Ư�����ڸ� 1�� �̻� ���Խ��� �ּ���.");
		map.put("joinPW����ġ", 		 "��ܿ� �Է��Ͻ� �н������ ��ġ���� �ʽ��ϴ�.");
		map.put("joinName����", 		 "2���� �̻� �Է����ּ���.");
		map.put("joinName���ռ�",		 "�ѱ۸� �Է��� �����մϴ�.");
		map.put("joinMail��������ġ",	 "������ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
		map.put("joinMail���ռ�", 	 "�̸��� ������ ��Ȯ���� �ʽ��ϴ�.");
		map.put("joinMail���̵���Է�", "email ���̵� �Է����ּ���.");
		map.put("joinMail�����ι��Է�", "email �������� �Է����ּ���."); 
		map.put("joinMail������ġ", 	 "������ȣ�� ��ġ�մϴ�.");
		map.put("joinMail�ð��ʰ�", 	 "�ð��� �ʰ��Ǿ����ϴ�. ������ȣ�� �ٽ� �޾��ּ���.");
		map.put("joinMail������", 	 "�̸��� ������ �޾��ּ���.");
		map.put("joinMail�߼�", 		 "�Է��Ͻ� �̸��Ϸ� ������ȣ�� �߼۵Ǿ����ϴ�.");
//		map.put("joinMail�߼���", 	 "�̸����� �߼����Դϴ�. ��ø� ��ٷ��ּ���.");
		map.put("joinTel���ռ�",		 "������ ��Ȯ�� �Է����ּ���.");
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
