package enums.etc;

public enum ServerActionEnum {
	JOIN_CERTIFICATION,			// 회원가입시에 인증번호 보내기
	JOIN_SUCCESS,				// 회원가입 성공
	JOIN_FAIL,					// 회원가입 실패
	LOGIN_SUCCESS,				// 로그인 성공
	LOGIN_FAIL_INPUT_ERROR,		// 로그인 실패-입력오류
	LOGIN_FAIL_OVERLAP_ACCEPT,	// 로그인 실패-이미 로그인한 유저
	LOGIN_NEW_USER,				// 대기실에 새로운 유저가 들어왔을 때
	WAITING_ROOM_ENTER,			// 현재유저가 로그인 성공하여 대기실 입장시
	GAME_CREATEROOM_SUCCESS,	// 방 생성 성공
	GAME_CREATEROOM_FAIL,		// 방 생성 실패
	GAME_ROOM_ADD,				// 방 생성시 다른 유저들 화면에 방 추가해주기
	OTHER_USER_EXIT,			// 다른 유저가 방을 나갔음
	OTHER_USER_INFO,			// 다른 유저 정보를 볼 때
	MESSAGE_SEND_SUCCESS,
	NOTHING;
}
