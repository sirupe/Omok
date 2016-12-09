package enums.etc;

public enum UserActionEnum {
	USER_JOIN_ID_OVERLAP_CHECK,	// ������ ȸ������ �ߺ�üũ��
	USER_JOIN_JOINACTION,		// ������ ȸ������
	USER_JOIN_CERTIFICATION_CREATE,	// ������ ȸ�������� ������
	USER_JOIN_CERTIFICATION_CHECK,
	USER_JOIN_CERTIFICATION_SUCCESS,
	USER_JOIN_CERTIFICATION_FAIL,
	USER_SEARCH_CERTIFICATION_CHECK, // ������ �н����� ã�� �� ������ȣ(����)
	USER_SEARCHPW_SERCHFAIL,		// �н����� ����� ã���� ������ ����.
	USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER, //������ ������ȣ �� ��� (����)
	USER_SEARCH_ID_EMAIL_CHECK,		// ������ ���̵� �̸��� DB�� �ִ��� üũ
	USER_SEARCH_PASSWD,			// ���� �н����� ����(����)
	USER_LOGIN_SUCCESS,			// ������ �α��� ����
	USER_CREATE_ROOM,			// ������ �����
	USER_CONFIRM_USERINFO,		// ������ �ٸ� ������ ���� Ȯ���ϱ�
	USER_MESSAGE_DEFAULT,		// ������ �Ϲ� �޼��� ����

	USER_MESSAGE_SECRET,		// ������ �ӼӸ� ����
	USER_ENTER_ROOM,			// �������� �� ���Ŵܴ�
	USER_PRIVATE_ROOM_ENTER,	// ������ ��й� ���� �����ôܴ�
	USER_IN_GAME_ROOM_CHATTING, // ������ ���ӹ� �ȿ��� ä���ϽŴܴ�
	USER_GUEST_READY_CHECK,		// �Խ�Ʈ������ ���� üũ�ߴ�.
	USER_GUEST_READY_DECHECK,	// �Խ�Ʈ������ ���� üũ�� �����ߴ�.
	USER_GAME_START,			// ���� ������ ���ӽ�����..!!
	USER_GAME_BOARD_INFO,		// ������ ���� ���Ҵ�.
	USER_GAME_ROOM_EXIT,		// ������ ���ӹ� ������
	
	USER_MODIFY_GET_MY_INFO,	// ������ �����ϱ� Ŭ���Ͻ� (������ ��������)
	USER_MODIFY_UPDATE,			// ������ ���� ���� ������Ʈ
	USER_MODIFY_DROP,			// ȸ��Ż��
	USER_DROP_CERTAIN;			// ������ Ż���ϱ��...
}
