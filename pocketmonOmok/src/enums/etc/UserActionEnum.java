package enums.etc;

public enum UserActionEnum {
	USER_JOIN_ID_OVERLAP_CHECK,	// ������ ȸ������ �ߺ�üũ��
	USER_JOIN_JOINACTION,		// ������ ȸ������
	USER_JOIN_CERTIFICATION_CREATE,	// ������ ȸ�������� ������
	USER_JOIN_CERTIFICATION_CHECK,
	USER_JOIN_CERTIFICATION_SUCCESS,
	USER_JOIN_CERTIFICATION_FAIL,
	USER_LOGIN_SUCCESS,			// ������ �α��� ����
	USER_CREATE_ROOM,			// ������ �����
	USER_CONFIRM_USERINFO,		// ������ �ٸ� ������ ���� Ȯ���ϱ�
	USER_MESSAGE_DEFAULT,		// ������ �Ϲ� �޼��� ����
	USER_FIND_ID,				//������ ���̵� ã����
	USER_MESSAGE_SECRET,		// ������ �ӼӸ� ����
	USER_ENTER_ROOM,			// �������� �� ���Ŵܴ�
	USER_PRIVATE_ROOM_ENTER,	// ������ ��й� ���� �����ôܴ�
	USER_IN_GAME_ROOM_CHATTING, // ������ ���ӹ� �ȿ��� ä���ϽŴܴ�
	USER_GUEST_READY_CHECK,		// �Խ�Ʈ������ ���� üũ�ߴ�.
	USER_GUEST_READY_DECHECK,	// �Խ�Ʈ������ ���� üũ�� �����ߴ�.
	USER_GAME_START,			// ���� ������ ���ӽ�����..!!
	USER_GAME_BOARD_INFO;		// ������ ���� ���Ҵ�.
}
