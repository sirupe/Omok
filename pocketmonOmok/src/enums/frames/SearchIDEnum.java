package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum SearchIDEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	SEARCHFRAME_SIZE_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	SEARCHFRAME_SIZE_HEIGHT((int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.7)),	
	SEARCHFRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (SEARCHFRAME_SIZE_WIDTH.getSize() / 2 ))),
	SEARCHFRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (SEARCHFRAME_SIZE_HEIGHT.getSize() / 2))),
	
	
    //���̵� ���̺�
	SEARCH_ID_LABEL(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.13),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.14),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.12),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	//�̸��� ���̺�
	SEARCH_EMAIL_LABEL(new Rectangle(
			
			(int)(SEARCHFRAME_POSITION_X.getSize() *  0.13),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.29),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.2),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
    //���� ���̺�
	SEARCH_ERROR_LABEL(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.19),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.42),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.7),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
    //���̵�  �ؽ�Ʈ �ʵ�
	SEARCH_ID_TEXTFIELD(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.27),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.14),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.10)
	)),
	//�̸��� �ؽ�Ʈ �ʵ�
	SEARCH_EMAIL_TEXTFIELD(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.27),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.29),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	//��� ��ư 
	SEARCH_BACK_BUTTON(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.38),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.54),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.14),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.16)
	)),
	
	//Ȯ�ι�ư ����
	SEARCH_CONFIRM_BUTTON(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.25),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.54),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.2),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//Ȩ���ι�ư ����
	GO_HOME_BUTTON(new Rectangle(
			(int)(SEARCHFRAME_POSITION_X.getSize() * 0.25),
			(int)(SEARCHFRAME_POSITION_Y.getSize() * 0.54),
			(int)(SEARCHFRAME_SIZE_WIDTH.getSize() * 0.2),
			(int)(SEARCHFRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//��ư�̸�
	BUTTON_NAME_BACK("backButton"),
	BUTTON_NAME_CONFIRM("confirmButton"),
	BUTTON_NAME_GOHOME("homeButton"),
	//�ؽ�Ʈ�ʵ���Ʈ
	TEXTFIELD_FONT(new Font("a��������",Font.PLAIN, (int) (SearchIDEnum.Screen_SIZE.getDimension().width / 100 * 1.1))),
	//�Ϲ� ��Ʈ
	LABELFONT_DEFAULT(new Font("a��������",Font.BOLD, (int) (SearchIDEnum.Screen_SIZE.getDimension().width / 100 * 1.4))),
	//���� �޼��� ��Ʈ
	LABELFONT_ERROR(new Font("a��������",Font.BOLD, (int) (SearchIDEnum.Screen_SIZE.getDimension().width / 100 * 1.3))),
	//���� �޼�������
	LABELCOLOR_ERROR(Color.red),
	//�Ϲݻ���
	LABELCOLOR_DEFAULT(Color.green);
	
	private Dimension dimension;
	private String buttonName;
	private int size;
	private Color color;
	private Font font;
	private Rectangle rec;
		
	//set
	private SearchIDEnum(Rectangle rec) {
		this.rec = rec;
	}
	private SearchIDEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private SearchIDEnum(String buttonName) {
		this.buttonName = buttonName;
	}
	private SearchIDEnum(int size) {
		this.size = size;
	}
	private SearchIDEnum(Color color) {
		this.color = color;
	}
	private SearchIDEnum(Font font) {
		this.font = font;
	}
	
	//get
	public Rectangle getRectangle() {
		return rec;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public String getButtonName() {
		return buttonName;
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
