package enums.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum ChargeEnum {
	
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//���� ������ ũ��,��ġ
	CHARGE_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	CHARGE_FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() * 0.3)),	
	CHARGE_FRAME_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (CHARGE_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	CHARGE_FRAME_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (CHARGE_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	/***************************chargePanel***************************/
	//chargePanel ��ġũ��
	CHARGE_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02 ),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.07),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.9),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize()  * 0.33)
	)),
	
	//"�����ұݾ�" ��ġ, ũ��
	CHARGE_LABEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.04),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	//1000�� ��ư ��ġ, ũ��
	CHARGE_1000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.01),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//5000�� ��ư ��ġ, ũ��
	CHARGE_5000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.158),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//10000�� ��ư ��ġ, ũ��
	CHARGE_10000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.305),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//50000�� ��ư ��ġ, ũ��
	CHARGE_50000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.453),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	/***************************emailPanel***************************/
	//�̸��� �г� ��ġ, ũ��
	EMAIL_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.7),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.9),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.21)
	)),
	
	//"�̸��� �Է�" ��ġ, ũ��
	EMAIL_LABEL_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.02),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.05),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.6),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)		
	)),
	
	//�̸��� �Է� ��ġ,ũ��
	
	EMAIL_INPUT_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.02),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.31),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)

	)),
	
	//�̸��� @ ��ġ ũ��
	EMAIL_AT_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.33),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.56),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.14),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.14)
	)),
	
	//�̸��� �ּ�â ��ġ, ũ��
	EMAIL_ADDRESS_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.38),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)
			
	)),
	
	//�̸��� �ּҼ��� ����Ʈ 
	EMAIL_ADDRESS(new String[] {
			"�����Է�",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	//�̸��� �޺��ڽ� ��ġ, ũ��
	EMAIL_ADDRESS_COMBOBOX_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.7),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)				
	)),
	
	/***************************certifyPanel***************************/
	
	//���� �г� ��ġ, ũ��
	CERTIFY_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 1.05),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.95),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	)),
	
	//���� ��ư ��ġ, ũ��
	CERTIFY_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.015),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.09),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.25),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.45)
	)),
		
	//���� ��ȣ ��ġ, ũ��
	CERTIFY_NUM_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.091),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.6),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.4)				
	)),
	
	//������ȣ Ȯ�� �ؽ�Ʈ ��ġ, ũ��
	CERTIFY_TEXTAREA_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.08),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.7),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.65),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.3)			
	)),
	
	//������ȣ �ð� �ؽ�Ʈ ��ġ, ũ��
	CERTIFY_TIME_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.74),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.7),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.15),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.3)			
	)),
		
	/***************************buttonsPanel***************************/
	
	//��ư�� �г� ��ġ ũ��
	BUTTONS_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 1.45),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.95),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
		
	//Ȯ�ι�ư ��ġ, ũ��
	CONFIRM_BUTTON_SIZE_RECT(new Rectangle(
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.27),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.3),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.2),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.7)			
	)),
	
	//��ҹ�ư ��ġ, ũ��	
	RESET_BUTTON_SIZE_RECT(new Rectangle(
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.52),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.3),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.2),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.7)				
	)),
	
	/***************************chargeConfirmFrame***************************/
	
	//����Ȯ�� ������ ũ��
	CHARGE_CONFIRM_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.22)),
	CHARGE_CONFIRM_FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() *0.17)),	
	CHARGE_CONFIRM_FRAME_SIZE_POSITION_X(
			(int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y(
			(int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//"������ �Ϸ�Ǿ����ϴ�" ��ġ, ũ��
	CHARGE_SUCCESS_TEXT_SIZE_RECT(new Rectangle(
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_X.getSize() * 0.16),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y.getSize() * 0.18),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() * 0.6),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	)),
	
	
	//Ȯ�� ��ư ��ġ, ũ��
	CHARGE_CONFIRM_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_X.getSize() * 0.21),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y.getSize() * 0.45),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() * 0.25),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() * 0.13)		
	)),
	
	LABELFONT_DEFAULT(new Font("a��������", Font.BOLD, ChargeEnum.SCREEN_SIZE.getDimension().width / 100 * 2));
			
			

	private Rectangle rect;
	private int size;
	private Dimension dimension;
	private String[] strArr;
	private Font font;

	private ChargeEnum() {}
	
	//set
	private ChargeEnum(int x) {
		   this.size = x;
	}
	private ChargeEnum(Rectangle rect) {
		this.rect = rect;
	}
	private ChargeEnum(Dimension dimension) {
		  this.dimension = dimension;
	}
	private ChargeEnum(String[] strArr) {
		this.strArr = strArr;
	}
	
	private ChargeEnum(Font font) {
		this.font = font;
	}
	
	//get
	public int getSize() {
		return size;
	}
	public Rectangle getRect() {
		return rect;
	}
	public Dimension getDimension() {
		  return dimension;
	}
	public String[] getStrArr() {
		return strArr;
	}
	public Font getFont() {
		return font;
	}
	
}
