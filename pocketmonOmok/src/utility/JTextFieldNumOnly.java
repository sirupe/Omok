package utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//���ڼ� 4���ڷ� ����
public class JTextFieldNumOnly extends PlainDocument {
	private int limit;
	
	// JTextFiled �� ���ڼ����� ������ �Ұ����ϱ� ������
	// PlainDocument �� ��ӹ޾� �ʿ� �޼ҵ带 ������ Ŭ������ ����Ѵ�.
	public JTextFieldNumOnly(int limit) {
		this.limit = limit;
	}
	
	// �� �޼ҵ尡 PlainDocument �κ��� �������̵� �ؾ� �ϴ� �޼ҵ�.
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null) {
			return;
		}
		
		// ���ڼ� ���Ѥ��� �� ��
//		if(getLength() + str.length() <= limit) {
//			super.insertString(offs, str, a);
//		}
		
		// ���ڸ� �Է¹ް��� �� ��
		if(str.charAt(0) >= '0' && str.charAt(0) <= '9') {
			super.insertString(offs, str, a);
		}
	}
	
	
}
