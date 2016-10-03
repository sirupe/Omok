package datasDTO;

import java.sql.Date;

import enums.etc.UserPositionEnum;

// ���� �������� DTO (Data Transfer Object)
public class UserPersonalInfoDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 2662828056554396523L;
	
	private String userID;			// ���� ���̵�
	private String userPasswd;		// ���� ��й�ȣ
	private String userName;		// ���� �̸�
	private int userGender;			// ���� ����
	private String userBirth;		// ���� ����
	private String userEmail;		// ���� �̸���
	private String userPhoneNumber;	// ���� �ڵ�����ȣ
	private Date userJoinDate;		// ���� ��������
	private String certificationNumber;
	private boolean isEmailSuccess;
	private boolean isCertificationNumber;
	private int userCount;
	


	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public boolean isEmailSuccess() {
		return isEmailSuccess;
	}

	public void setEmailSuccess(boolean isEmailSuccess) {
		this.isEmailSuccess = isEmailSuccess;
	}

	public boolean isCertificationNumber() {
		return isCertificationNumber;
	}

	public void setCertificationNumber(boolean isCertificationNumber) {
		this.isCertificationNumber = isCertificationNumber;
	}                            

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

	@Override
	public String toString() {
		return "UserPersonalInfoDTO [userID=" + userID + ", userPasswd=" + userPasswd + ", userName=" + userName
				+ ", userGender=" + userGender + ", userBirth=" + userBirth + ", userEmail=" + userEmail
				+ ", userPhoneNumber=" + userPhoneNumber + ", userJoinDate=" + userJoinDate + ", certificationNumber="
				+ certificationNumber + ", isEmailSuccess=" + isEmailSuccess + ", isCertificationNumber="
				+ isCertificationNumber + ", userCount=" + userCount +  "]";
	}
	
}
