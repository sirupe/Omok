package enums.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public enum GameRoomEnum {
//오목판 설정값--------------------------------------------------------------
	GAME_BOARD_PANEL_RECT(new Rectangle(
			(int)(LoginPanelEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01),
			(int)(LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03),
			(int)(LoginPanelEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.64),
			(int)(LoginPanelEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.64)			
	)),
	
	GAME_BOARD_SIZE(15),

//바둑알패널 설정-----------------------------------------------------------
	GAME_STONEPANEL_RECT(new Rectangle(
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.02 * 1.45),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.02 * 1.45),
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.95),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.95)
	)),	
	
	GAME_STONE_LOCATION_RECT(new Rectangle(
			0,
			0,
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.06 * 1.06),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.06 * 1.06)
	)),
	
	
//유저이미지 설정값------------------------------------------------------------	
	GAME_USERIMAGE_PANEL_RECT(new Rectangle(
			(int) (GAME_BOARD_PANEL_RECT.getRect().getMaxX() + 10),
			LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 8,
			LoginPanelEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 34,
			LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 35
	)),

	GAME_USERIMAGE_LEFT_RECT(new Rectangle(
			0,
			0,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 2,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 60
	)),
	
	GAME_USERIMAGE_RIGHT_RECT(new Rectangle (
			GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width,
			0,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 2,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 60	
	)),
//유저의 등급과 아이디-----------------------------------------------------------
	
	GAME_USERID_LEFT_LABEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 10 + 8 ,
			GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 65,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 3,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 10
			
	)),
	GAME_USERID_RIGHT_LABEL_RECT(new Rectangle(
			GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width + 50,
			GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 65,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 3,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 10
	)),
	
	GAME_USERLEVEL_LEFT_IMAGE_RECT(new Rectangle(
			0,
			GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 65,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 9,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 7
	)),
	
	GAME_USERLEVEL_RIGHT_IMAGE_RECT(new Rectangle(
			GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width,
			GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect().height / 2 + 65,
			GAME_USERIMAGE_PANEL_RECT.getRect().width / 9,
			GAME_USERIMAGE_PANEL_RECT.getRect().height / 7
	)),
	

//게임메뉴 및 아이템 버튼 설정값---------------------------------------------------	
	GAME_MENU_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			(int)(GAME_USERLEVEL_RIGHT_IMAGE_RECT.getRect().y * 1.7),
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			(int) (LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.15)
	)),
	//TODO 
	GAME_BUTTON_SIZE_RECT(new Rectangle(
			0,
			0,
			GAME_MENU_PANEL_RECT.getRect().width / 3 * 1,
			GAME_MENU_PANEL_RECT.getRect().height / 5 * 3
	)),
	
	GAME_BUTTONNAME_OWNER(new String[] {
			"start",
//			"lonely",
			"withdraw",
			"exit",
//			"itemUnity",
//			"itemTimePlus",
//			"itemReturn",
//			"shop"
	}),
	
	GAME_BUTTONNAME_GUEST(new String[] {
			"ready",
//			"lonely",
			"withdraw",
			"exit",
//			"itemUnity",
//			"itemTimePlus",
//			"itemReturn",
//			"shop"
	}),

	
//시간설정패널 설정값----------------------------------------------------------
	GAME_TIMELIMIT_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			GAME_BOARD_PANEL_RECT.getRect().y,
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 4
	)),
	
	GAME_TIMELIMIT_PROGRESS_RECT(new Rectangle(
			0,
			0,
			(int)(GAME_TIMELIMIT_PANEL_RECT.getRect().width * 0.8),
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
			(int) (GAME_MENU_PANEL_RECT.getRect().getMaxY() * 0.94),
			(int) (GAME_USERIMAGE_PANEL_RECT.getRect().width),
			(int) (LoginPanelEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.44)//스크로로
	)),
	
	GAME_SCROLL_PANE_RECT(new Rectangle(
			0,
			0,
			GAME_CHATTING_PANEL_RECT.getRect().width,
			(int) (GAME_CHATTING_PANEL_RECT.getRect().height * 0.82)
	)),
	
	GAME_CHATTINGFIELD_RECT(new Rectangle(
			GAME_SCROLL_PANE_RECT.getRect().x,
			GAME_SCROLL_PANE_RECT.getRect().height,
			GAME_CHATTING_PANEL_RECT.getRect().width,
			(int) (GAME_CHATTING_PANEL_RECT.getRect().height * 0.1)
	)),
	
//기권 팝업창 설정값-------------------------------------------------------------
	
	GIVEUP_FRAME_SIZE_RECT(new Rectangle(
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.9),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.5),
			(int)(GAME_BOARD_PANEL_RECT.getRect().width * 0.6),
			(int)(GAME_BOARD_PANEL_RECT.getRect().height * 0.4)
	)),	
	
	GIVEUP_TEXT_SIZE_RECT(new Rectangle(
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.13),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.4)
	)),	
	
	GIVEUP_YES_BUTTON_RECT(new Rectangle(
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.24),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.5),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.22),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.14)
	)),	
	
	GIVEUP_NO_BUTTON_RECT(new Rectangle(
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.53),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.5),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.22),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.14)
	)),
	
	DIALOG_CHECK_BUTTON_RECT(new Rectangle(
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.4),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.5),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().width * 0.22),
			(int)(GIVEUP_FRAME_SIZE_RECT.getRect().height * 0.14)
	)),	

//다이얼로그 내부컴포넌트 설정값------------------------------------------------
	GAME_END_DIALOG_LABEL_RECT(new Rectangle(
			
	)),
	GAME_END_DIALOG_BUTTON_RECT(),
	
//폰트설정----------------------------------------------------------------	
	GAMEROOM_USERID_FONT(new Font("a으라차차",Font.BOLD,20)),
	GAMEROOM_USERIF_FONT_COLOR(Color.white),
	GAME_TIMELABEL_FONT(new Font("Consolas", Font.BOLD, LoginPanelEnum.SCREEN_SIZE.getDimension().width / 100)),
	GAME_CHATTING_FONT(new Font("돋움", Font.PLAIN, LoginPanelEnum.SCREEN_SIZE.getDimension().width / 130));
//======================================================================
	private Rectangle rect;
	private String[] buttonName;
	private Font font;
	private int size;
	private Color color;
	
	private GameRoomEnum() {
	}
	
	private GameRoomEnum(int size) {
		this.size = size;
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
	
	private GameRoomEnum(Color color) {
		this.color = color;
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
	
	public int getSize() {
		return size;
	}
	
	public Color getColor() {
		return color;
	}
}
