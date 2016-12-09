package enums.etc;

public enum UserActionEnum {
	USER_JOIN_ID_OVERLAP_CHECK,	// 유저님 회원가입 중복체크중
	USER_JOIN_JOINACTION,		// 유저님 회원가입
	USER_JOIN_CERTIFICATION_CREATE,	// 유저님 회원가입중 인증중
	USER_JOIN_CERTIFICATION_CHECK,
	USER_JOIN_CERTIFICATION_SUCCESS,
	USER_JOIN_CERTIFICATION_FAIL,
	USER_SEARCH_CERTIFICATION_CHECK, // 유저님 패스워드 찾기 중 인증번호(수진)
	USER_SEARCHPW_SERCHFAIL,		// 패스워드 변경시 찾아줄 정보가 없다.
	USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER, //유저님 인증번호 비교 결과 (수진)
	USER_SEARCH_ID_EMAIL_CHECK,		// 유저님 아이디 이메일 DB에 있는지 체크
	USER_SEARCH_PASSWD,			// 유저 패스워드 재등록(수진)
	USER_LOGIN_SUCCESS,			// 유저님 로그인 성공
	USER_CREATE_ROOM,			// 유저님 방생성
	USER_CONFIRM_USERINFO,		// 유저님 다른 유저님 정보 확인하기
	USER_MESSAGE_DEFAULT,		// 유저님 일반 메세지 전송

	USER_MESSAGE_SECRET,		// 유저님 귓속말 전송
	USER_ENTER_ROOM,			// 유저님이 방 들어가신단다
	USER_PRIVATE_ROOM_ENTER,	// 유저님 비밀방 들어가고 싶으시단다
	USER_IN_GAME_ROOM_CHATTING, // 유저님 게임방 안에서 채팅하신단다
	USER_GUEST_READY_CHECK,		// 게스트유저가 레디를 체크했다.
	USER_GUEST_READY_DECHECK,	// 게스트유저가 레디 체크를 해제했다.
	USER_GAME_START,			// 드디어 방장이 게임시작을..!!
	USER_GAME_BOARD_INFO,		// 유저가 돌을 놓았다.
	USER_GAME_ROOM_EXIT,		// 유저님 게임방 나가심
	
	USER_MODIFY_GET_MY_INFO,	// 유저님 수정하기 클릭하심 (내정보 가져오기)
	USER_MODIFY_UPDATE,			// 유저님 수정 정보 업데이트
	USER_MODIFY_DROP,			// 회원탈퇴
	USER_DROP_CERTAIN;			// 유저가 탈퇴하기로...
}
