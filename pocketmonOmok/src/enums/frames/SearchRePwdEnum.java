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
	
//��й�ȣ, ���̺�
	SEARCH_PWD_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.07),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.15),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.2),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.08)
	)),
	//���й�ȣ ���̺�
	SEARCH_REPWD_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.07),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.37),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.3),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() *0.08)
	)),
	//������ ��й�ȣ�� ���й�ȣ�� ���� ��� �޼��� 
		SEARCH_ERROR_LABEL(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.1),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.55),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.9),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.2)
		)),
	// ��й�ȣ �ٲ���ٴ� �޼��� -- > ȭ�� ��ȯ(searchChangePanel)
		SEARCH_CONFIRM_CHANGE_LABEL(new Rectangle(
				(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.06),
				(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.3),
				(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() / 100 * 100),
				(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.35)
		)),
		
		//��й�ȣ ���й�ȣ �ؽ�Ʈs
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
		
		//searchRePwd Ȯ�� ��ư ����
		SEARCH_CONFIRM_BUTTON(new Rectangle(
			(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.2),
			(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.9),
			(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.4),
			(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.1)
			)),
		//searchChangePwd ��ư ����
		SEARCH__CHANGE_CONFIRM_BUTTON(new Rectangle(
				(int)(SEARCH_REPWD_FRAME_POSITION_X.getSize() * 0.2),
				(int)(SEARCH_REPWD_FRAME_POSITION_Y.getSize() * 0.9),
				(int)(SEARCH_REPWD_FRAME_WIDTH.getSize() * 0.4),
				(int)(SEARCH_REPWD_FRAME_HEIGHT.getSize() * 0.15)
				)),
		

	//�Ϲ� ��Ʈ
	LABELFONT_DEFAULT(new Font("a��������",Font.BOLD,17)),
	LABELREPWDFONT_DEFAULT(new Font("a��������",Font.BOLD,13)),
	//���� �޼��� ��Ʈ
	LABELFONT_ERROR(new Font("a��������",Font.BOLD,19)),

	//���� �޼�������
	LABELCOLOR_ERROR(Color.red),
	// �Ϲݻ���
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
