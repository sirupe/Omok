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
	
//======================================��=====================================================================
	//���̵� ��
	MODIFY_ID_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.2),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//��й�ȣ��
	MODIFY_PWD_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//���й�ȣ��
	MODIFY_REPWD_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//��й�ȣ ���й�ȣ ��ġ ���� ������ ��Ÿ���� ���� �޼���
	MODIFY_REPWDERROR_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.38),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//�̸�
	MODIFY_NAME_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//���� ��
	MODIFY_GENDER_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	
	//���� ��
	MODIFY_BIRTH_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
			)),
	//�⵵ ��
	MODIFY_YEAR_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.44),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//�� ��
	MODIFY_MONTH_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.62),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//�϶�
	MODIFY_DATE_LABEL(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.8),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.1)
	)),
	//�̸��� ��
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
//=================================gender�����ڽ�=================================
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

//=====================================�ؽ�Ʈ====================================================================
	//���̵� �ؽ�Ʈ�ʵ�
	MODIFY_ID_TEXT(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.3),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	//��й�ȣ �ؽ�Ʈ �ʵ�
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
	//===================================���̽��ڽ�===================================
	//�⵵
	MODIFY_YEAR_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//��
	MODIFY_MONTH_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.47),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//��
	MODIFY_DATE_COMBOBOX(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.8),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//��ȭ��ȣ ���ڸ�
	MODIFY_TELFRONTNUM_COMBO(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	//�̸����ּ�
	MODIFY_EMAILADDR_COMBO(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 3.6),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.2),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
	)),
	
	//=======================================��ư=====================================
	//������ư
	MODIFY_MODIFY_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.25),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	//���
	MODIFY_CANCEL_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.4),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	//Ż���ư
	MODIFY_DROPOUT_BUTTON(new Rectangle(
			(int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.55),
			(int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 4.7),
			(int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.15),
			(int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
	)),
	
	//���̺� ��Ʈ

	//�̸��� �� ��ȭ��ȣ ����
	MODIFY_EMAIL_COMBO_ADDRESS(new String[] {
			"�����Է�",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	MODIFY_TEL_FRONT_NUM_COMBO(new String[] {
			"����", "010", "011", "016", "019", "017"
	}),
	
	LABELFONT_DEFAULT(new Font("a��������", Font.BOLD, ModifyJoinEnum.Screen_SIZE.getDimension().width / 100)),
	
	//������Ʈ ��Ʈ
	JOIN_COMPFONT_DEFAULT(new Font("a��������", Font.PLAIN, ModifyJoinEnum.Screen_SIZE.getDimension().width / 120)),

	//���ռ��˻� �� ��Ʈ
	JOIN_CHECKLABEL_FONT_DEFAULT(new Font("a��������", Font.BOLD, ModifyJoinEnum.Screen_SIZE.getDimension().width / 150)),
	
	//�޺��ڽ� ���ȭ��
	CHOICEBACKGROUND(Color.black),
	//�޺��ڽ� �̸��� ���ȭ��
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

