package datasDTO;

import java.io.IOException;

import enums.etc.ImageEnum;
import enums.etc.UserPositionEnum;

// ���� ���ӵ����� ���� DTO (Data Transfer Object)
public class UserGamedataInfoDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 1767280013075444699L;
	
	private String userID;		// �������̵�
	private String userGrade;	// �������
	private int userGameCount;	// ���� �� ���Ӽ�
	private int userWinCount;	// ���� �̱� ���Ӽ�
	private int userScore;		// ���� ����
	private double userWinRate;	// ���� �·� (DTO���� �Ѱ��Ӽ��� �̱���Ӽ��� �·��� ����Ͽ� ������
	private String userWaitingRoomImage;	// ���� ���� �̹���
	
	public UserGamedataInfoDTO(UserPositionEnum position) {
		super(position);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public int getUserGameCount() {
		return userGameCount;
	}

	public void setUserGameCount(int userGameCount) {
		this.userGameCount = userGameCount;
	}

	public int getUserWinCount() {
		return userWinCount;
	}

	public void setUserWinCount(int userWinCount) {
		this.userWinCount = userWinCount;
	}

	public double getUserWinRate() {
		return userWinRate;
	}

	public void setUserWinRate() {
		double rate = ((double)this.userWinCount / this.userGameCount) * 100;
		this.userWinRate = ((int)(rate * 100)) / 100.0;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public String getUserWaitingRoomImage() {
		return userWaitingRoomImage;
	}

	public void setUserWaitingRoomImage(int gender) throws IOException {
		this.userWaitingRoomImage = gender == 1 ? ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
	}
}
