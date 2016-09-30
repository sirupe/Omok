package utility;

import java.util.regex.Pattern;
// ����
public class RegexCheck {
	// �̸��� ����üũ. ���Ŀ� ������ true, ���Ŀ� ���� ������ false return
	// (���̵�@����Ʈ.com �� ����)
	public static boolean emailRegexCheck(String email) {
		String regex = "^[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		return Pattern.matches(regex, email);
	}

	// (����Ʈ.com ���� üũ)
	public static boolean emailDomainRegexCheck(String domain) {
		String regex = "^[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		return Pattern.matches(regex, domain);
	}
	
	// ���̵� ����üũ. ���Ŀ� ������ true, ���Ŀ� ���� ������ false return
	// (������+���� ������ ����. �����ڸ����δ� �����ϳ� ���ڸ����δ� �Ұ����ϴ�.)
	public static boolean idRegexCheck(String id) {
		String regex = "^[a-zA-Z0-9]*$";
		boolean result = Pattern.matches(regex, id);
		if(Pattern.matches("^[0-9]*$", id)) {
			result = false;
		}
		return result;
	}
	
	// ��й�ȣ ����üũ. ���Ŀ� ������ true, ���Ŀ� ���� ������ false return
	// (������ + ���� + Ư������ 1�� �̻� ����. 6~16 �� �̳��� ���ڼ��� true.
	public static boolean passwdRegexCheck(String passwd) {
		String regex = "^(?=.*[~`!@%$%\\^&*()-])(?=.*[0-9])(?=.*[a-zA-Z]).{6,16}$";
		return Pattern.matches(regex, passwd);
	}
	
	// �̸� ����üũ. ���Ŀ� ������ true, ���Ŀ� ���� ������ false return
	// (�ѱ۸� true ��ȯ.)
	public static boolean nameRegecCheck(String name) {
		String regex = "^([��-����-�R])*$";
		return Pattern.matches(regex, name);
	}
}
