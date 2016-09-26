package enums.frames;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum CorrectEnum {
	 
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//프레임 크기, 위치
	FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	FRAME_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (FRAME_SIZE_WIDTH.getSize() / 2 ))),
	FRAME_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (FRAME_SIZE_HEIGHT.getSize() / 2 ))),
	
	/*************************pwd입력프레임*************************/
	//pwd프레임 크기 위치
	PWD_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//pwd입력 라벨 위치, 크기
	PWD_TEXT_LABEL_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.16 ),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.2),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.3),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)),
	
	//pwd 입력란 위치, 크기
	PWD_INPUT_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.43),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.255),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.4),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.13)
	)),
	
	//pwd에러메시지 위치, 크기
	PWD_ERROR_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.27),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.4),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	//pwd 확인 버튼 위치, 크기
	PWD_CONFIRM_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.25),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	//pwd 취소 버튼 위치, 크기
	PWD_RESET_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.54),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************수정이 완료되었습니다.*************************/
	
	//수정완료 프레임 크기 위치
	CORRECT_COMPLETE_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//수정 완료 텍스트 위치, 크기
	CORRECT_COMPLETE_TEXT_SIZE_RECT(new Rectangle(
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.21 ),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)),                                            
	//확인 버튼 위치, 크기                               
	CORRECT_COMPLETE_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.4 ),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************정말 탈퇴하시겠습니까?*************************/
	
	//탈퇴 프레임
	DROPOUT_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//"탈퇴하시겠습니까 "라벨 위치, 크기
	DROPOUT_TEXT_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.15 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.8),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	//탈퇴 확인 버튼 위치,크기
	DROPOUT_CONFIRM_BUTTON_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.25),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	//탈퇴 취소 버튼 위치,크기
	DROPOUT_RESET_BUTTON_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.54),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************탈퇴완료*************************/
	
	//탈퇴 완료 프레임 사이즈 == 정말 탈퇴하시겠습니까 프레임 사이즈
	//"탈퇴완료 "라벨 위치, 크기
	DROPOUT_COMPLETE_TEXT_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.37 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.5),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	// 확인 버튼 위치,크기
	DROPOUT_COMPLETE_BUTTON_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.4 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	/*************************아이디찾기 결과*************************/
	SHOW_USER_ID_LABEL_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.37 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.5),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	SHOW_USER_ID_RESULT_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.32),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.45),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.8),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	/*************************회원가입 완료*************************/
	//"회원가입 완료 "라벨 위치, 크기
	JOIN_SUCCESS_TEXT_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.3 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	));
	
	
	private Rectangle rect;
	private int size;
	private Dimension dimension;
	
	private CorrectEnum() {}
	
	//set
	private CorrectEnum(int x) {
		   this.size = x;
	}
	private CorrectEnum(Rectangle rect) {
		this.rect = rect;
	}
	private CorrectEnum(Dimension dimension) {
		  this.dimension = dimension;
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
}
