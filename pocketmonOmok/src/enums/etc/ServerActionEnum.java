package enums.etc;

public enum ServerActionEnum {
	JOIN_CERTIFICATION,			// ȸ�����Խÿ� ������ȣ ������
	JOIN_SUCCESS,				// ȸ������ ����
	JOIN_FAIL,					// ȸ������ ����
	LOGIN_SUCCESS,				// �α��� ����
	LOGIN_FAIL_INPUT_ERROR,		// �α��� ����-�Է¿���
	LOGIN_FAIL_OVERLAP_ACCEPT,	// �α��� ����-�̹� �α����� ����
	LOGIN_NEW_USER,				// ���ǿ� ���ο� ������ ������ ��
	WAITING_ROOM_ENTER,			// ���������� �α��� �����Ͽ� ���� �����
	GAME_CREATEROOM_SUCCESS,	// �� ���� ����
	GAME_CREATEROOM_FAIL,		// �� ���� ����
	GAME_ROOM_ADD,				// �� ������ �ٸ� ������ ȭ�鿡 �� �߰����ֱ�
	OTHER_USER_EXIT,			// �ٸ� ������ ���� ������
	OTHER_USER_INFO,			// �ٸ� ���� ������ �� ��
	MESSAGE_SEND_SUCCESS,		// �޼��� ���� ����
	ENTER_ROOM_SUCCESS_OWNER,	// �� ���� ����-���� (�Խ�Ʈ�� �濡 ������ ��)
	ENTER_ROOM_SUCCESS_GUEST,	// �� ���� ����-�Խ�Ʈ
	ENTER_ROOM_SUCCESS_LIST,	// �� ���� ����-���ǿ� �ִ� ����� ���� ����
	ENTER_PRIVATE_GAME_ROOM,	// ������ ��й濡 �����ϰ��� �� �� �������� �ش� ��й��� ������ ����
	
	NOTHING;
}
