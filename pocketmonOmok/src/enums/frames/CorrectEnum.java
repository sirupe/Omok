package enums.frames;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public enum CorrectEnum {
	 
	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	//������ ũ��, ��ġ
	FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	FRAME_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getWidth() * 0.25)),
	FRAME_SIZE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (FRAME_SIZE_WIDTH.getSize() / 2 ))),
	FRAME_SIZE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (FRAME_SIZE_HEIGHT.getSize() / 2 ))),
	
	/*************************pwd�Է�������*************************/
	//pwd������ ũ�� ��ġ
	PWD_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//pwd�Է� �� ��ġ, ũ��
	PWD_TEXT_LABEL_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.16 ),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.2),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.3),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)),
	
	//pwd �Է¶� ��ġ, ũ��
	PWD_INPUT_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.43),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.255),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.4),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.13)
	)),
	
	//pwd�����޽��� ��ġ, ũ��
	PWD_ERROR_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.27),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.4),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	//pwd Ȯ�� ��ư ��ġ, ũ��
	PWD_CONFIRM_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.25),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	//pwd ��� ��ư ��ġ, ũ��
	PWD_RESET_RECT(new Rectangle(
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.54),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(PWD_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(PWD_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************������ �Ϸ�Ǿ����ϴ�.*************************/
	
	//�����Ϸ� ������ ũ�� ��ġ
	CORRECT_COMPLETE_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//���� �Ϸ� �ؽ�Ʈ ��ġ, ũ��
	CORRECT_COMPLETE_TEXT_SIZE_RECT(new Rectangle(
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.21 ),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.7),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)),                                            
	//Ȯ�� ��ư ��ġ, ũ��                               
	CORRECT_COMPLETE_BUTTON_SIZE_RECT(new Rectangle(
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.4 ),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************���� Ż���Ͻðڽ��ϱ�?*************************/
	
	//Ż�� ������
	DROPOUT_FRAME_SIZE_RECT(new Rectangle(
			(int)(FRAME_SIZE_POSITION_X.getSize() * 0.9 ),
			(int)(FRAME_SIZE_POSITION_Y.getSize() * 0.5),
			(int)(FRAME_SIZE_WIDTH.getSize() * 0.98),
			(int)(FRAME_SIZE_HEIGHT.getSize()  * 0.7)
	)),
	
	//"Ż���Ͻðڽ��ϱ� "�� ��ġ, ũ��
	DROPOUT_TEXT_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.15 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.8),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	//Ż�� Ȯ�� ��ư ��ġ,ũ��
	DROPOUT_CONFIRM_BUTTON_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.25),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	//Ż�� ��� ��ư ��ġ,ũ��
	DROPOUT_RESET_BUTTON_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.54),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	
	/*************************Ż��Ϸ�*************************/
	
	//Ż�� �Ϸ� ������ ������ == ���� Ż���Ͻðڽ��ϱ� ������ ������
	//"Ż��Ϸ� "�� ��ġ, ũ��
	DROPOUT_COMPLETE_TEXT_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.37 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.24),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.5),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.23)
	)), 
	
	// Ȯ�� ��ư ��ġ,ũ��
	DROPOUT_COMPLETE_BUTTON_SIZE_RECT(new Rectangle(
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.4 ),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height * 0.6),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().width * 0.21),
			(int)(DROPOUT_FRAME_SIZE_RECT.getRect().height  * 0.15)
	)),
	/*************************���̵�ã�� ���*************************/
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
	
	/*************************ȸ������ �Ϸ�*************************/
	//"ȸ������ �Ϸ� "�� ��ġ, ũ��
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
