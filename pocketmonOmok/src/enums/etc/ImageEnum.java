package enums.etc;

public enum ImageEnum {
//BasieFrame 이미지-----------------------------------------------------
	BASIC_BACKGROUND("resources/background/background.png"),
//Loginpanel 이미지-----------------------------------------------------
	LOGINPANEL_LOGIN("resources/login/login.jpg"),
//GameRoom 이미지들------------------------------------------------------	
	GAMEROOM_MALE_IMAGE("resources/gameRoom/userImageMale.png"),
	GAMEROOM_FEMALE_IMAGE("resources/gameRoom/userImageFemale.png"),
	GAMEROOM_DEFALTUSER_IMAGE("resources/gameRoom/logo.png"),
	
	
	GAMEROOM_MENU_IMAGES_OWNER(new String[] {
			"resources/gameroom/start.png",			// gamestart
			"resources/gameroom/alone.png",			// 혼자하기
			"resources/gameroom/giveup.png",			// 기권
			"resources/gameroom/itemgameout.png",	// 나가기
			"resources/gameroom/itemunity.png",		// 돌색 통일시키기
			"resources/gameroom/itemtimeplus.png",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameRoom/gamecoin.png"		// 상점
	}),
	
	GAMEROOM_MENU_IMAGES_GUEST(new String[] {
			"resources/gameroom/ready.png",			// ready
			"resources/gameroom/alone.png",			// 혼자하기
			"resources/gameroom/giveup.png",			// 기권
			"resources/gameroom/itemgameout.png",	// 나가기
			"resources/gameroom/itemunity.png",		// 돌색 통일시키기
			"resources/gameroom/itemtimeplus.png",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameroom/gamecoin.png"		// 상점
	}),
	
	GAMEROOM_STONE_CHARMANDER("resources/omokball/charmander.png"),
//WaitingRoom 이미지들-------------------------------------------------------
	WAITINGROOM_ENTER_POSSIBLE_IMAGE("resources/waitroom/waitingvacancy.jpg"),
	WAITINGROOM_ENTER_PRIVATE_IMAGE("resources/waitroom/waitingprivate.jpg"),
	WAITINGROOM_ENTER_FULL_IMAGE("resources/waitroom/watingfull.jpg");
	

	private String imageDir;
	private String[] images;
	
	private ImageEnum(String image) {
		this.imageDir = image;
	}
	
	private ImageEnum(String[] images) {
		this.images = images;
	}
	
	public String getImageDir() {
		return imageDir;
	}
	
	public String[] getImages() {
		return images;
	}
}
