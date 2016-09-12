package enums;

import java.awt.Dimension;
import java.awt.Toolkit;
// 태성(경로)

public enum LoginFrameSizesEnum {
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	LOGIN_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.67)),
	LOGIN_FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getHeight() * 0.85)),
	LOGIN_FRAME_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2))),
	LOGIN_FRAME_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2)) - (LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2)),	
	
	SIZE_LABEL_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),
	SIZE_LABEL_HEIGHT((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	
	LOGIN_RESOURCE_ID_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.36)),
	LOGIN_RESOURCE_ID_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.34)),
		
	LOGIN_RESOURCE_PASSWORD_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize()),
	LOGIN_RESOURCE_PASSWORD_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 58),
	
	LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize() + 130),
	LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 178),
	
	LOGIN_RESOURCE_JOIN_BUTTON_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize()),
	LOGIN_RESOURCE_JOIN_BUTTON_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 126),
	
	LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize() + 130),
	LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 126),
	
	LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize() + 250),
	LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 126),
	
	LOGIN_RESOURCE_ID_FIELD_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize() + 
									   LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize()),
	LOGIN_RESOURCE_ID_FIELD_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 10),
	
	LOGIN_RESOURCE_PW_FIELD_POSITION_X(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_FIELD_POSITION_X.getSize()),
	LOGIN_RESOURCE_PW_FIELD_POSITION_Y(LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize() + 68),
	
	LOGIN_ICON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),
	LOGIN_ICON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.11)),
	LOGIN_ICON_POSITION_X((int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2))),
	
	SIZE_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.14)),
	SIZE_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	SIZE_JOIN_ICON_HEIGTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.03)),
	SIZE_PW_ICON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.12)),
	
	//============================================================================================
	
	LOGIN_ACCESS_SIZE_WIDTH((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().getWidth() * 0.2)),
	LOGIN_ACCESS_SIZE_HEIGHT((int)(LoginFrameSizesEnum.SCREEN_SIZE.getDimension().getHeight() * 0.27)),
	
	LOGIN_ACCESS_FRAME_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_WIDTH.getSize() / 2))),
	LOGIN_ACCESS_FRAME_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 2))),
	
	LOGIN_ACCESS_LABEL_WIDTH(300),
	LOGIN_ACCESS_LABEL_HEIGTH(100),
	
	LOGIN_ACCESS_LABEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_WIDTH.getSize() / 4.2)),
	LOGIN_ACCESS_LABEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 6)),
	
	LOGIN_ACCESS_OK_BUTTON_WIDTH(100),
	LOGIN_ACCESS_OK_BUTTON_HEIGHT(50),
	
	LOGIN_ACCESS_OK_BUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_WIDTH.getSize() / 2.66)),
	LOGIN_ACCESS_OK_BUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 2));
	
	private int size;

	private Dimension dimension;
	
	private LoginFrameSizesEnum() {}
	
	private LoginFrameSizesEnum(int x) {
		this.size = x;
	}
	
	private LoginFrameSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public int getSize() {
		return size;
	}

	public Dimension getDimension() {
		return dimension;
	}
}
