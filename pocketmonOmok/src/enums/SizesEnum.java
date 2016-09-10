package enums;


public enum SizesEnum {
	SIZE_X(1300),
	SIZE_Y(900),
	SIZE_ICON_X(100),
	SIZE_ICON_Y(100),
	SIZE_LABEL_X(100),
	SIZE_LABEL_Y(50),
	SIZE_TEXT_X(150),
	SIZE_TEXT_Y(30),
	SIZE_JOIN_ICON_Y(50),
	SIZE_PW_ICON_X(110),
	SCREEN_SIZE();
	
	private int size;
	
	private SizesEnum() {}
	
	private SizesEnum(int x) {
		this.size = x;
	}
	
	public int getSizeX() {
		return size;
	}
	
	public int getSizeY() {
		return size;
	}
}
