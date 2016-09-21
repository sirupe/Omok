package enums.etc;

public enum UtilityEnums {
	SENDER_EMAIL_ADDRESS("jijungsinkim@gmail.com"),
	SENDER_EMAIL_PASSWD("dpswpflsjtm"),
	SENDER_EMAIL_SUBJECT("포켓몬 오목에서 발송한 인증번호입니다."),
	SENDER_EMAIL_PORT("465");
	
	private String str;
	
	private UtilityEnums() {}
	
	private UtilityEnums(String str) {
		this.str = str;
	}
		
	public String getStr() {
		return str;
	}
}