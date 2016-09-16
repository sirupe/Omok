package enums;

import java.awt.Rectangle;

public enum GameRoomEnum {
	GAME_BOARD_PANEL_RECT(new Rectangle(
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 1,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 3,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 64,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 64			
	)),
	
	GAME_USERIMAGE_PANEL_RECT(new Rectangle(
			(int) (GAME_BOARD_PANEL_RECT.getRect().getMaxX() + 10),
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 8,
			LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 100 * 34,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 25
	)),
	
	GAME_MENU_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			(int) (GAME_USERIMAGE_PANEL_RECT.getRect().getMaxY() + 10),
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 20
	)),
	
	GAME_CHATTING_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			(int) (GAME_MENU_PANEL_RECT.getRect().getMaxY() + 10),
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 39
	)),
	
	GAME_TIMELIMIT_PANEL_RECT(new Rectangle(
			GAME_USERIMAGE_PANEL_RECT.getRect().x,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 2,
			GAME_USERIMAGE_PANEL_RECT.getRect().width,
			LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 100 * 5
	));
	
	private Rectangle rect;
	
	
	private GameRoomEnum() {
	}
	private GameRoomEnum(Rectangle rect) {
		this.rect = rect;
	}
	public Rectangle getRect() {
		return rect;
	}
}
