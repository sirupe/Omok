package enums.etc;

public enum ServerActionEnum {
	JOIN_SUCCESS("회원가입 완료"),
	JOIN_FAIL("회원가입 실패");
	
	private String serverMessage;
	
	private ServerActionEnum(){}
	
	private ServerActionEnum(String serverMessage) {
		this.serverMessage = serverMessage;
	}
	
	public String getServerMessage() {
		return serverMessage;
	}
}
