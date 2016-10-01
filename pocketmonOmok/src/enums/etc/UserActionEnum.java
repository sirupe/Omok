package enums.etc;

public enum UserActionEnum {
	USER_JOIN_ID_OVERLAP_CHECK,	// 유저님 회원가입 중복체크중
	USER_JOIN_JOINACTION,		// 유저님 회원가입
	USER_JOIN_CERTIFICATION_CREATE,	// 유저님 회원가입중 인증중
	USER_JOIN_CERTIFICATION_CHECK,
	USER_JOIN_CERTIFICATION_SUCCESS,
	USER_JOIN_CERTIFICATION_FAIL,
	USER_LOGIN_SUCCESS,			// 유저님 로그인 성공
	USER_CREATE_ROOM,			// 유저님 방생성
	USER_CONFIRM_USERINFO,		// 유저님 다른 유저님 정보 확인하기
	USER_MESSAGE_DEFAULT,		// 유저님 일반 메세지 전송
	USER_FIND_ID,				//유저님 아이디 찾는중
	USER_MESSAGE_SECRET,		// 유저님 귓속말 전송
	USER_ENTER_ROOM,			// 유저님이 방 들어가신단다
	USER_PRIVATE_ROOM_ENTER,	// 유저님 비밀방 들어가고 싶으시단다
	USER_IN_GAME_ROOM_CHATTING, // 유저님 게임방 안에서 채팅하신단다
	USER_GUEST_READY_CHECK,		// 게스트유저가 레디를 체크했다.
	USER_GUEST_READY_DECHECK,	// 게스트유저가 레디 체크를 해제했다.
	USER_GAME_START,			// 드디어 방장이 게임시작을..!!
	USER_GAME_BOARD_INFO;		// 유저가 돌을 놓았다.
}
