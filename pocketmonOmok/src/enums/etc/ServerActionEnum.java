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
	MESSAGE_SEND_SUCCESS,		// 메세지 전송 성공
	ENTER_ROOM_SUCCESS_OWNER,	// 방 입장 성공-방장 (게스트가 방에 들어왔을 때)
	ENTER_ROOM_SUCCESS_GUEST,	// 방 입장 성공-게스트
	ENTER_ROOM_SUCCESS_LIST,	// 방 입장 성공-대기실에 있는 사람들 방목록 변경
	ENTER_PRIVATE_GAME_ROOM,	// 유저가 비밀방에 입장하고자 할 때 서버에서 해당 비밀방의 정보를 전송
	
	NOTHING;
}
