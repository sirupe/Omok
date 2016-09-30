package enums.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum ChargeEnum {
	
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//충전 프레임 크기,위치
	CHARGE_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	CHARGE_FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() * 0.3)),	
	CHARGE_FRAME_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (CHARGE_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	CHARGE_FRAME_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (CHARGE_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	/***************************chargePanel***************************/
	//chargePanel 위치크기
	CHARGE_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02 ),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.07),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.9),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize()  * 0.33)
	)),
	
	//"충전할금액" 위치, 크기
	CHARGE_LABEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.04),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.4),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
	
	//1000원 버튼 위치, 크기
	CHARGE_1000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.01),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//5000원 버튼 위치, 크기
	CHARGE_5000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.158),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//10000원 버튼 위치, 크기
	CHARGE_10000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.305),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	//50000원 버튼 위치, 크기
	CHARGE_50000_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.453),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.26),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.22),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.17)
	)),
	
	/***************************emailPanel***************************/
	//이메일 패널 위치, 크기
	EMAIL_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 0.7),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.9),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.21)
	)),
	
	//"이메일 입력" 위치, 크기
	EMAIL_LABEL_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.02),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.05),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.6),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)		
	)),
	
	//이메일 입력 위치,크기
	
	EMAIL_INPUT_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.02),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.31),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)

	)),
	
	//이메일 @ 위치 크기
	EMAIL_AT_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.33),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.56),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.14),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.14)
	)),
	
	//이메일 주소창 위치, 크기
	EMAIL_ADDRESS_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.38),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)
			
	)),
	
	//이메일 주소선택 리스트 
	EMAIL_ADDRESS(new String[] {
			"직접입력",
			"naver.com",
			"hanmail.net",
			"nate.com",
			"gmail.com"
	}),
	
	//이메일 콤보박스 위치, 크기
	EMAIL_ADDRESS_COMBOBOX_SIZE_RECT(new Rectangle(
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.7),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.47),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(EMAIL_PANEL_SIZE_RECT.getRect().height * 0.34)				
	)),
	
	/***************************certifyPanel***************************/
	
	//인증 패널 위치, 크기
	CERTIFY_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 1.05),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.95),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	)),
	
	//인증 버튼 위치, 크기
	CERTIFY_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.015),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.09),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.25),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.45)
	)),
		
	//인증 번호 위치, 크기
	CERTIFY_NUM_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.3),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.091),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.6),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.4)				
	)),
	
	//인증번호 확인 텍스트 위치, 크기
	CERTIFY_TEXTAREA_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.08),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.7),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.65),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.3)			
	)),
	
	//인증번호 시간 텍스트 위치, 크기
	CERTIFY_TIME_SIZE_RECT(new Rectangle(
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.74),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.7),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().width * 0.15),
			(int)(CERTIFY_PANEL_SIZE_RECT.getRect().height * 0.3)			
	)),
		
	/***************************buttonsPanel***************************/
	
	//버튼들 패널 위치 크기
	BUTTONS_PANEL_SIZE_RECT(new Rectangle(
			(int)(CHARGE_FRAME_SIZE_POSITION_X.getSize() * 0.02),
			(int)(CHARGE_FRAME_SIZE_POSITION_Y.getSize() * 1.45),
			(int)(CHARGE_FRAME_SIZE_WIDTH.getSize() * 0.95),
			(int)(CHARGE_FRAME_SIZE_HEIGHT.getSize() * 0.1)
	)),
		
	//확인버튼 위치, 크기
	CONFIRM_BUTTON_SIZE_RECT(new Rectangle(
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.27),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.3),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.2),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.7)			
	)),
	
	//취소버튼 위치, 크기	
	RESET_BUTTON_SIZE_RECT(new Rectangle(
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.52),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.3),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().width * 0.2),
			(int)(BUTTONS_PANEL_SIZE_RECT.getRect().height * 0.7)				
	)),
	
	/***************************chargeConfirmFrame***************************/
	
	//충전확인 프레임 크기
	CHARGE_CONFIRM_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.22)),
	CHARGE_CONFIRM_FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() *0.17)),	
	CHARGE_CONFIRM_FRAME_SIZE_POSITION_X(
			(int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() / 2 ))),
	CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y(
			(int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() / 2))),
	
	//"충전이 완료되었습니다" 위치, 크기
	CHARGE_SUCCESS_TEXT_SIZE_RECT(new Rectangle(
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_X.getSize() * 0.16),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y.getSize() * 0.18),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() * 0.6),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() * 0.2)
	)),
	
	
	//확인 버튼 위치, 크기
	CHARGE_CONFIRM_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_X.getSize() * 0.21),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_POSITION_Y.getSize() * 0.45),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_WIDTH.getSize() * 0.25),
			(int)(CHARGE_CONFIRM_FRAME_SIZE_HEIGHT.getSize() * 0.13)		
	)),
	
	LABELFONT_DEFAULT(new Font("a으라차차", Font.BOLD, ChargeEnum.SCREEN_SIZE.getDimension().width / 100 * 2));
			
			

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
