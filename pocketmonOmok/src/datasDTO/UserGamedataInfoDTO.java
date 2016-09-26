package datasDTO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import enums.etc.ImageEnum;
import enums.etc.UserPositionEnum;
import enums.frames.WaitingRoomSizesEnum;

// 유저 게임데이터 저장 DTO (Data Transfer Object)
public class UserGamedataInfoDTO extends AbstractEnumsDTO {
	private String userID;		// 유저아이디
	private String userGrade;	// 유저등급
	private int userGameCount;	// 유저 총 게임수
	private int userWinCount;	// 유저 이긴 게임수
	private int userScore;		// 유저 점수
	private double userWinRate;	// 유저 승률 (DTO에서 총게임수와 이긴게임수로 승률을 계산하여 저장함
	private ImageIcon userImage;	// 유저 이미지

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

	public ImageIcon getUserImage() {
		return userImage;
	}

	public void setUserImage(int gender) throws IOException {
		String imageEnum = gender == 1 ? ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
		
		this.userImage = new ImageIcon(ImageIO.read(
			new File(imageEnum)).getScaledInstance(
				WaitingRoomSizesEnum.USER_INFO_VIEW_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.USER_INFO_VIEW_SIZE_HEIGHT.getSize(),
				Image.SCALE_AREA_AVERAGING)
		);
	}
}
