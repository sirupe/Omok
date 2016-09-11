package enums;

import java.awt.Dimension;
import java.awt.Toolkit;
// 태성(경로)
public enum LoginFrameSizesEnum {
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	LOGIN_FRAME_SIZE_WIDTH(1300),
	LOGIN_FRAME_SIZE_HEIGHT(900),
	LOGIN_FRAME_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2))),
	LOGIN_FRMAE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2)) - (LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2)),	
	
	LOGIN_ICON_WIDTH(100),
	LOGIN_ICON_HEIGHT(100),
	LOGIN_ICON_POSITION_X((int)((LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() / 2) - (LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize() / 2))),
	
	SIZE_LABEL_WIDTH(100),
	SIZE_LABEL_HEIGHT(50),
	
	SIZE_TEXT_WIDTH(150),
	SIZE_TEXT_HEIGHT(30),
	
	SIZE_JOIN_ICON_WIDTH(50),
	SIZE_PW_ICON_HEIGHT(110);
	
	
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
