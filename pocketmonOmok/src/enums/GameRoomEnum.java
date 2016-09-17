package enums;

import java.awt.Font;
import java.awt.Rectangle;

public enum GameRoomEnum {
//오목판 설정값--------------------------------------------------------------
	GAME_BOARD_PANEL_RECT(new Rectangle(
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 1,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 3,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 64,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 64			
	)),
	

//바둑알패널 설정-----------------------------------------------------------
	GAME_STONEPANEL_RECT(new Rectangle(
			(int)(GAME_BOARD_PANEL_RECT.getRect().width / 100 * 2.6),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height / 100 * 2.6),
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.96),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.96)
	)),	
	
	GAME_STONE_LOCATION_RECT(new Rectangle(
			0,
			0,
			(int)(GAME_BOARD_PANEL_RECT.getRect().width / 31 * 1.98),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height / 31 * 1.98)
	)),
	
	
//유저이미지 설정값------------------------------------------------------------	
	GAME_USERIMAGE_PANEL_RECT(new Rectangle(
			(int) (GAME_BOARD_PANEL_RECT.getRect().getMaxX() + 10),
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 8,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 34,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 25
	)),

	GAME_USERIMAGE_LEFT_RECT(new Rectangle(
			0,
			0,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 2,
			GAME_USERIMAGE_PANEL_RECT.getRect().height
	)),
	
	GAME_USERIMAGE_RIGHT_RECT(new Rectangle (
			GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width,
			0,
			GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width,
			GAME_USERIMAGE_PANEL_RECT.getRect().height			
	)),
	
	
//게임메뉴 및 아이템 버튼 설정값---------------------------------------------------	
	GAME_MENU_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			(int) (GAME_USERIMAGE_PANEL_RECT.getRect().getMaxY() + 10),
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 20
	)),
	
	GAME_BUTTON_SIZE_RECT(new Rectangle(
			0,
			0,
			GAME_MENU_PANEL_RECT.getRect().width / 4 * 1,
			GAME_MENU_PANEL_RECT.getRect().height / 2 * 1
	)),
	
	GAME_BUTTONNAME(new String[] {
			"start",
			"lonely",
			"withdraw",
			"exit",
			"itemUnity",
			"itemTimePlus",
			"itemReturn",
			"shop"
	}),

	
//시간설정패널 설정값----------------------------------------------------------
	GAME_TIMELIMIT_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			GAME_BOARD_PANEL_RECT.getRect().y,
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 4
	)),
	
	GAME_TIMELIMIT_PROGRESS_RECT(new Rectangle(
			0,
			0,
			GAME_TIMELIMIT_PANEL_RECT.getRect().width / 100 * 90,
			GAME_TIMELIMIT_PANEL_RECT.getRect().height
	)),
	
	GAME_TIMELIMIT_TIMELABEL_RECT(new Rectangle(
			GAME_TIMELIMIT_PROGRESS_RECT.getRect().width + 20,
			0,
			GAME_TIMELIMIT_PANEL_RECT.getRect().width - GAME_TIMELIMIT_PROGRESS_RECT.getRect().width,
			GAME_TIMELIMIT_PANEL_RECT.getRect().height
	)),
	
	
//채팅 패널 설정값-------------------------------------------------------------
	GAME_CHATTING_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			(int) (GAME_MENU_PANEL_RECT.getRect().getMaxY() + 10),
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 39
	)),
	
	GAME_SCROLL_PANE_RECT(new Rectangle(
			0,
			0,
			GAME_CHATTING_PANEL_RECT.getRect().width,
			GAME_CHATTING_PANEL_RECT.getRect().height / 10 * 9
	)),
	
	GAME_CHATTINGFIELD_RECT(new Rectangle(
			GAME_SCROLL_PANE_RECT.getRect().x,
			GAME_SCROLL_PANE_RECT.getRect().height,
			GAME_CHATTING_PANEL_RECT.getRect().width,
			GAME_CHATTING_PANEL_RECT.getRect().height / 100 * 13
	)),
	
	
//폰트설정----------------------------------------------------------------	
	GAME_TIMELABEL_FONT(new Font("Consolas", Font.BOLD, 35)),
	GAME_CHATTING_FONT(new Font("돋움", Font.PLAIN, 25));
//======================================================================
	private Rectangle rect;
	private String[] buttonName;
	private Font font;
	
	private GameRoomEnum() {
	}
	
	private GameRoomEnum(Rectangle rect) {
		this.rect = rect;
	}
	
	private GameRoomEnum(String[] buttonName) {
		this.buttonName = buttonName;
	}
	
	private GameRoomEnum(Font font) {
		this.font = font;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public String[] getButtonName() {
		return buttonName;
	}
	
	public Font getFont() {
		return font;
	}
}
