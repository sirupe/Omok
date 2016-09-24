package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public enum GameStoreEnum {
Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	GAME_STORE_PANEL_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	GAME_STORE_PANEL_HEIGHT((int)(GAME_STORE_PANEL_WIDTH.getSize() * 1.2)),	
	GAME_STORE_PANEL_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (GAME_STORE_PANEL_WIDTH.getSize() / 2 ))),
	GAME_STORE_PANEL_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (GAME_STORE_PANEL_HEIGHT.getSize() / 2))),
	
//-----------------------------------------------------------------------------------------------------
	
	//userMoneyPanel 패널크기

    STORE_USER_MONEY_PANEL_REC(new Rectangle(
          (int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.07),
          (int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.1),
          (int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.9),
          (int)(GAME_STORE_PANEL_HEIGHT.getSize() *0.3)
          )),
 //사용자 보유 금액 
    STORE_USER_MONEY_REC(new Rectangle(
          (int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.03),
          (int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.03),
          (int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.45),
          (int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.08)
          )),
 // 충전 버튼
    STORE_USER_CONFIRM_BUTTON_REC(new Rectangle(
          (int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.36),
          (int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.03),
          (int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.17),
          (int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.08)
          )),
//--------------------------------------------------------------------------------------
		//ItemChoicePanel 크기
		STORE_ITEM_CHOICE_PANEL_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.01),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.4),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.9),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.6)
		)),
		//방해하기 보유숫자
		STORE_USER_OWN_INTERRUPT_ITEM_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.1),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.5),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.15),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.05)
		)),
		
		//방해하기 아이템 위치
		STORE_USER_OWN_INTERRUPT_ITEM_BUTTON_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.03),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.05),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.41),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.23)
		)),
		
		//무르기 아이템 보유 숫자 X,Y,가로,세로
		STORE_USER_OWN_RETURN_ITEM_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.1),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 1.12),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.15),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.05)
		)),
		
		//무르기 아이템 X,Y,가로,세로
		STORE_USER_OWN_RETURN_ITEM_BUTTON_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.04),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.65),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.41),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() *0.23)
		)),
		//시간늘리기 아이템 보유숫자
		STORE_USER_OWN_TIMEEXTION_ITEM_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.4),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.5),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.15),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize()* 0.05)
		)),
		
		//시간늘리기 아이템 위치
		STORE_USER_OWN_TIMEEXTION_ITEM_BUtton_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.33),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.05),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.41),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.23)
		)),
		//스킨 뽑기 라벨
		STORE_USER_SKIN_CATCH_LABEL_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.39),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 1.14),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.25),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.05)
		)),
		//스킨 뽑기 버튼
		STORE_USER_SKIN_CATCH_BUTTON_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.33),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.65),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.41),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.23)
		)),
//-----------------------------------------------------------------------------------
		//나가기 패널
		STORE_OUT_PANEL_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.5),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 1.8),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() / 100 * 23),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() / 100 * 10)
		)),
		
		//나가기 아이콘 
		STORE_OUT_BUTTON_REC(new Rectangle(
			(int)(GAME_STORE_PANEL_POSITION_X.getSize() * 0.01),
			(int)(GAME_STORE_PANEL_POSITION_Y.getSize() * 0.01),
			(int)(GAME_STORE_PANEL_WIDTH.getSize() * 0.2),
			(int)(GAME_STORE_PANEL_HEIGHT.getSize() * 0.1)
		)),
		
		
		//일반 폰트
		// 일반색깔
		LABELCOLOR_DEFAULT(Color.gray),
		//테두리 라인
		LABEL_LINE(new MatteBorder(2,2,2,2, Color.pink));
	
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Rectangle rec;
	private EmptyBorder emptyBorder;
	private MatteBorder matteBorder;
	
	private GameStoreEnum(MatteBorder matteBorder) {
		this.matteBorder = matteBorder;
	}
	private GameStoreEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	private GameStoreEnum(int size) {
		this.size = size;
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
	public MatteBorder getMatteBorder() {
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
	private Dimension getDimension() {
		return dimension;
	}
	public int getSize() {
		return size;
	}
	
	

}
