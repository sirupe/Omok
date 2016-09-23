package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource.MatteBorderUIResource;

public enum GamePayRoomEnum {
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	GAME_PAY_ROOM_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.22)),
	GAME_PAY_ROOM_HEIGHT((int)(GAME_PAY_ROOM_WIDTH.getSize() * 1)),	
	GAME_PAY_ROOM_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAME_PAY_ROOM_WIDTH.getSize() / 2 ))),
	GAME_PAY_ROOM_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAME_PAY_ROOM_HEIGHT.getSize() / 2))),
	//===========================================================================
	//아이템 크기
	GAME_ROOM_PAY_ITEM_LABEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 18),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 10),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 35),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 35)
			)),
	
	//기본 1000원
	GAME_ROOM_PAY_BASICMONEY_LABEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 1),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 70),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 20),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 15)
			)),
	// x 
	GAME_ROOM_PAY_DIV_LABEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 14),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 71),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 20),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 13)
			)),

	//==
	GAME_ROOM_PAY_EQUAL_LABEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 35),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 76),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 5),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 5)
			)),
	//총금액 계산
	GAME_ROOM_PAY_TOTALMONEY_LABEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 40),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 73),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 28),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 10)
			)),
//==========Panel크기==================================================
	GAME_ROOM_AMOUNT_PANEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 18),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 72),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 27),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 12)
			)),
	
	GAME_ROOM_USER_AMOUNT_PANEL(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 1),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 1),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 15),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 10)
			)),
	
//=====================================================================	
	// 결제 버튼
	GAME_ROOM_PAY_BUTTON(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 18),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 105),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 16),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 10)
			)),
	
	
	
	//취소 버튼
	GAME_ROOM_PAY_CANCEL_BUTTON(new Rectangle(
			(int)(GAME_PAY_ROOM_POSITION_X.getSize() / 100 * 30),
			(int)(GAME_PAY_ROOM_POSITION_Y.getSize() / 100 * 105),
			(int)(GAME_PAY_ROOM_WIDTH.getSize() / 100 * 16),
			(int)(GAME_PAY_ROOM_HEIGHT.getSize() / 100 * 10)
			)),
	
	
	
	
	//일반 폰트
	LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,22)),
	// 일반색깔
	LABELCOLOR_DEFAULT(Color.gray),
	//텍스트 테두리 없애기
	LABEL_DEFAULT(new EmptyBorder(0,0,0,0)),
	
	LABEL_LINE(new MatteBorder(0,0,0, 0, Color.pink));

	private Dimension dimension;
	private int size;
	private Font font;
	private Color color;
	private Rectangle rec;
	private EmptyBorder emptyBorder;
	private MatteBorder matteBorder;
	
	private GamePayRoomEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private GamePayRoomEnum(int size) {
		this.size = size;
	}
	private GamePayRoomEnum(Font font) {
		this.font = font;
	}
	private GamePayRoomEnum(Color color) {
		this.color = color;
	}
	private GamePayRoomEnum(Rectangle rec) {
		this.rec = rec;
	}
	private GamePayRoomEnum(EmptyBorder emptyBorder) {
		this.emptyBorder = emptyBorder;
	}
	private GamePayRoomEnum(MatteBorder matteBorder) {
		this.matteBorder = matteBorder;
	}
	public MatteBorder getMatterBorder() {
		return matteBorder;
	}
	public EmptyBorder getEmptyBorder() {
		return emptyBorder;
	}
	public Rectangle getRectangle() {
		return rec;
	}
	public Color getColor() {
		return color;
	}
	public Font getFont() {
		return font;
	}
	private Dimension getDimension() {
		return dimension;
	}
	public int getSize() {
		return size;
	}
	
}
