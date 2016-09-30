package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import com.sun.glass.ui.Size;

public enum SearchPwdEnum {
	Screen_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	
	SEARCH_PWD_FRAME_WIDTH((int)(Screen_SIZE.getDimension().getWidth() * 0.25)),
	SEARCH_PWD_FRAME_HEIGHT((int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.9)),	
	SEARCH_PWD_FRAME_POSITION_X((int)((Screen_SIZE.getDimension().getWidth() / 2) - (SEARCH_PWD_FRAME_WIDTH.getSize() / 2 ))),
	SEARCH_PWD_FRAME_POSITION_Y((int)((Screen_SIZE.getDimension().getHeight() / 2) - (SEARCH_PWD_FRAME_HEIGHT.getSize() / 2))),
	

//�̸�, �̸��� ���̺�
   SEARCH_ID_LABEL(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.1),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.1),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.2),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.2)
         )),
   SEARCH_EMAIL_LABEL(new Rectangle(
         
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.1),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.23),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.3),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.2)
         )),
//// ���� ���̺� -- 3���ʰ��޼���
   SEARCH_ERROR_LABEL(new Rectangle(
         
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.12),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.75),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 13),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.13)
         )),

//// �̸� �ؽ�Ʈ �ʵ� x,y, ���� ���� 
   SEARCH_ID_TEXTFIELD(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.23),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.17),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.4),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.08)
         )),
   // �̸��� �ؽ�Ʈ �ʵ� x,y, ���� ���� 
   SEARCH_EMAIL_TEXTFIELD(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.23),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.3),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.4),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.08)
         )),
   // ����  �ؽ�Ʈ �ʵ� x,y, ���� ���� 
   SEARCH_CONFIRM_TEXTFIELD(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.23),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.44),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.4),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.08)
         )),
   
   // �̸��� ������ȣ�� �߼۵Ǿ��ٴ� �ؽ�Ʈ �ʵ�   x,y, ���� ���� 
   SEARCH_ANSWER_LABEL(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.16),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.6),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 13),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.13)
         )),
   
   //3�� ���ѽð� ���̺�  x,y, ���� ���� 
   SEARCH_Time_LABEL(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize()  * 0.51),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.4),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.15),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.15)
         )),
   
//   //���� ��ư ����
   SEARCH_CONFIRM_BUTTON(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.1),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 0.43),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.15),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.1)
         )),
   
   //��� ��ư ����
   SEARCH_CANCEL_BUTTON(new Rectangle(
         (int)(SEARCH_PWD_FRAME_POSITION_X.getSize() * 0.23),
         (int)(SEARCH_PWD_FRAME_POSITION_Y.getSize() * 1),
         (int)(SEARCH_PWD_FRAME_WIDTH.getSize() * 0.18),
         (int)(SEARCH_PWD_FRAME_HEIGHT.getSize() * 0.15)
         )),

   //�Ϲ� ��Ʈ
   LABELFONT_DEFAULT(new Font("a��������",Font.BOLD,16)),
   //���� �޼��� ��Ʈ
   LABELFONT_ERROR(new Font("a��������",Font.BOLD,18)),

	//���� �޼�������
		LABELCOLOR_ERROR(Color.red),
	// �Ϲݻ���
		LABELCOLOR_DEFAULT(Color.BLUE);
	
	private Dimension dimension;
	private int size;
	private Color color;
	private Font font;
	private Rectangle rec;
	
	
	private SearchPwdEnum(Rectangle rec) {
		this.rec = rec;
	}
	private SearchPwdEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	private SearchPwdEnum(int size) {
		this.size = size;
	}
	private SearchPwdEnum(Color color) {
		this.color = color;
	}
	private SearchPwdEnum(Font font) {
		this.font = font;
	}


   public Rectangle getRectangle() {
      return rec;
   }
   public Dimension getDimension() {
      return dimension;
   }
   public int getSize() {
      return size;
   }
   public Color getColor() {
      return color;
   }
   public Font getFont() {
      return font;
   }

}