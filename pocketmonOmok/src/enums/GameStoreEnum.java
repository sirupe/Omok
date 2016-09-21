package enums;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum GameStoreEnum {
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	GAME_STORE_PANEL_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAME_STORE_PANEL_HEIGHT((int)(GAME_STORE_PANEL_WIDTH.getSize() * 1.2)),	
	GAME_STORE_PANEL_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAME_STORE_PANEL_WIDTH.getSize() / 2 ))),
	GAME_STORE_PANEL_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAME_STORE_PANEL_HEIGHT.getSize() / 2))),
	
//-----------------------------------------------------------------------------------------------------
	
	//userMoneyPanel 패널크기
		STORE_USER_MONEY_PANEL_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 3),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 15),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 100),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 10)
				)),
	//사용자 보유 금액 
		STORE_USER_MONEY_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 1),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 3),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 55),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 8)
				)),
	// 충전 버튼
		STORE_USER_CONFIRM_BUTTON_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 43),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 3),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 20),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 8)
				)),
//--------------------------------------------------------------------------------------
		
		//ItemChoicePanel 크기
		STORE_ITEM_CHOICE_PANEL_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 3),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 80),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 100),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 60)
				)),
		//방해하기 보유숫자
		STORE_USER_OWN_INTERRUPT_ITEM_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 10),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 100),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 15),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 5)
				)),
		
		//방해하기 아이템 위치
				STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC(new Rectangle(
						(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 3),
						(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 5),
						(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 41),
						(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 23)
						)),
		
		//무르기 아이템 보유 숫자 X,Y,가로,세로
		STORE_USER_OWN_RETURN_ITEM_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 10),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 216),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 15),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 5)
				)),
		
		//무르기 아이템 X,Y,가로,세로
				STORE_USER_OWN_RETURN_ITEM_BUTTON_REC(new Rectangle(
						(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 3),
						(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 122),
						(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 41),
						(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 23)
						)),
		//시간늘리기 아이템 보유숫자
		STORE_USER_OWN_TIMEEXTION_ITEM_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 40),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 100),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 15),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 5)
				)),
		
		//시간늘리기 아이템 보유숫자
		STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 33),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 5),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 41),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 23)
				)),
				
		
		
		
//-----------------------------------------------------------------------------------
		//나가기 패널
		STORE_OUT_PANEL_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 50),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 330),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 23),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 10)
				)),
		
		//나가기 아이콘 
		STORE_OUT_BUTTON_REC(new Rectangle(
				(int)(GAME_STORE_PANEL_POSITION_X.getSize() / 100 * 1),
				(int)(GAME_STORE_PANEL_POSITION_Y.getSize() / 100 * 1),
				(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 20),
				(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 8)
				)),
		
		
		//일반 폰트
		LABELFONT_DEFAULT(new Font("a으라차차",Font.BOLD,17)),
		// 일반색깔
		LABELCOLOR_DEFAULT(Color.gray),
		//텍스트 테두리 없애기
		LABEL_DEFAULT(new EmptyBorder(0,0,0,0));
	
	
	private Dimension dimension;
	private int size;
	private Font font;
	private Color color;
	private Rectangle rec;
	private EmptyBorder emptyBorder;
	
	private GameStoreEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private GameStoreEnum(int size) {
		this.size = size;
	}
	private GameStoreEnum(Font font) {
		this.font = font;
	}
	private GameStoreEnum(Color color) {
		this.color = color;
	}
	private GameStoreEnum(Rectangle rec) {
		this.rec = rec;
	}
	private GameStoreEnum(EmptyBorder emptyBorder) {
		this.emptyBorder = emptyBorder;
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
