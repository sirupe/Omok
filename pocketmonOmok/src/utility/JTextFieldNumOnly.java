package utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//글자수 4글자로 제한
public class JTextFieldNumOnly extends PlainDocument {
	private int limit;
	
	// JTextFiled 에 글자수제한 설정이 불가능하기 때문에
	// PlainDocument 를 상속받아 필요 메소드를 구현한 클래스를 사용한다.
	public JTextFieldNumOnly(int limit) {
		this.limit = limit;
	}
	
	// 이 메소드가 PlainDocument 로부터 오버라이드 해야 하는 메소드.
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null) {
			return;
		}
		
		// 글자수 제한ㄴ을 둘 때
//		if(getLength() + str.length() <= limit) {
//			super.insertString(offs, str, a);
//		}
		
		// 숫자만 입력받고자 할 때
		if(str.charAt(0) >= '0' && str.charAt(0) <= '9') {
			super.insertString(offs, str, a);
		}
	}
	
	
}
