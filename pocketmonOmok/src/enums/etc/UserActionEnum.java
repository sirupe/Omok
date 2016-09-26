package enums.etc;

public enum UserActionEnum {
	USER_JOIN_ID_OVERLAP_CHECK,	// 유저님 회원가입 중복체크중
	USER_JOIN_JOINACTION,		// 유저님 회원가입
	USER_JOIN_CERTIFICATION,	// 유저님 회원가입중 인증중
	USER_LOGIN_SUCCESS,			// 유저님 로그인 성공
	USER_CREATE_ROOM,			// 유저님 방생성
	USER_CONFIRM_USERINFO,		// 유저님 다른 유저님 정보 확인하기
	USER_MESSAGE_DEFAULT,		// 유저님 일반 메세지 전송
	USER_MESSAGE_SECRET;
}
