package enums.etc;

public enum ServerActionEnum {
	JOIN_CERTIFICATION,			// ȸ�����Խÿ� ������ȣ ������
	JOIN_SUCCESS,				// ȸ������ ����
	JOIN_FAIL,					// ȸ������ ����
	
	LOGIN_SUCCESS,				// �α��� ����
	LOGIN_FAIL_INPUT_ERROR,		// �α��� ����-�Է¿���
	LOGIN_FAIL_OVERLAP_ACCEPT,	// �α��� ����-�̹� �α����� ����
	LOGIN_DROP_USER,			// �α��� ����-Ż���� ����
	LOGIN_NEW_USER,				// ���ǿ� ���ο� ������ ������ ��

	SEARCH_PASSWD,				// ����-�н����������ÿ� �������� ������ �Է��� ������ȣ ������
	SEARCH_PASSWD_SUCCESS,		// ����-�н����������Ʈ ����
	SEARCH_PASSWD_FAIL,			// ����-�н����������Ʈ ���� 
	
	WAITING_ROOM_ENTER,			// ���������� �α��� �����Ͽ� ���� �����
	GAME_CREATEROOM_SUCCESS,	// �� ���� ����
	GAME_CREATEROOM_FAIL,		// �� ���� ����
	GAME_ROOM_ADD,				// �� ������ �ٸ� ������ ȭ�鿡 �� �߰����ֱ�
	OTHERS_UER_EXIT,			// �ٸ� ������ ���� ������
	OTHER_USER_INFO,			// �ٸ� ���� ������ �� ��
	MESSAGE_SEND_SUCCESS,		// �޼��� ���� ����
	
	ENTER_ROOM_SUCCESS_OWNER,	// �� ���� ����-���� (�Խ�Ʈ�� �濡 ������ ��)
	ENTER_ROOM_SUCCESS_GUEST,	// �� ���� ����-�Խ�Ʈ
	GAME_ROOM_LIST_MODIFY,		// �� ����Ʈ ������ �����Ǿ���.
	ENTER_PRIVATE_GAME_ROOM,	// ������ ��й濡 �����ϰ��� �� �� �������� �ش� ��й��� ������ ����
	GAME_ROOM_USER_CHATTING,	// ���ӹ濡�� ������ ä�ýõ�
	GAME_ROOM_GUEST_READY_CHECK,// �Խ�Ʈ�� ���� Ŭ���ߴٴ� ���� ������ �˾Ҵ�.
	GAME_ROOM_GUEST_READY_DECHECK,// �Խ�Ʈ�� ���� �����Ͽ���.
	GAME_ROOM_GAME_START,		// ���ӹ濡�� ���ʰ� ���ӽ�ŸƮ Ŭ��!
	GAME_ROOM_SEND_BOARD_INFO,	// ���ӹ� ���忡 ���� �� ������ �� �������� �����ش�.
	GAME_ROOM_WINNER_INFO,		// �����߿� ������ �¸��ߴ�. �¸��� ������ �ִ�.
	GAME_ROOM_EXIT_OTHER_USER,	// ���� ���ӹ��� �ٸ� ������ ���� ������.
	
	MODIFY_USER_PERSONAL_INFO,	// DB���� ������ ���������� ã�Ƽ� ��ȯ
	MODIFY_USER_DROPCHECK_FAIL,		// Ż��-�Է��� �н����尡 Ʋ���� ��
	MODIFY_USER_DROPCHECK_SUCCESS,	// �н����带 ����� �Է����� ��
	MODIFY_USER_DROPOUT_SUCCESS,	// ȸ��Ż�� ����
	MODIFY_USER_DROPOUT_FAIL,		// ȸ��Ż�� ���� 
	MODIFY_USER_FAIL,			// ��������
	MODIFY_USER_PASSWD_FAIL,
	MODIFY_USER_SUCCESS,		// ��������
	NOTHING;
}
