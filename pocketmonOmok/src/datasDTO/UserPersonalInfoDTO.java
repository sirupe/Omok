package datasDTO;

import java.sql.Date;

import enums.etc.UserPositionEnum;

// 유저 개인정보 DTO (Data Transfer Object)
public class UserPersonalInfoDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 2662828056554396523L;
	
	private String userID;			// 유저 아이디
	private String userPasswd;		// 유저 비밀번호
	private String userName;		// 유저 이름
	private int userGender;			// 유저 성별
	private String userBirth;		// 유저 생일
	private String userEmail;		// 유저 이메일
	private String userPhoneNumber;	// 유저 핸드폰번호
	private Date userJoinDate;		// 유저 가입일자
	private String certificationNumber;
	
	public UserPersonalInfoDTO(UserPositionEnum position) {
		super(position);
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getUserPasswd() {
		return userPasswd;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getUserGender() {
		return userGender;
	}
	
	public String getUserBirth() {
		return userBirth;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	
	public Date getUserJoinDate() {
		return userJoinDate;
	}
	
	public String getCertificationNumber() {
		return certificationNumber;
	}
}
