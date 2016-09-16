package enums;

public enum ImageEnum {
	LOGINPANEL_LOGIN("resources/login/login.jpg"),
	
	
	GAMEROOM_MALE_IMAGE("resources/gameRoom/userImageMale.png"),
	GAMEROOM_FEMALE_IMAGE("resources/gameRoom/userImageFemale.png"),
	GAMEROOM_DEFALTUSER_IMAGE("resources/gameRoom/logo.png"),
	
	
	GAMEROOM_MENU_IMAGES_OWNER(new String[] {
			"resources/gameroom/test1",				// gamestart
			"resources/gameroom/test2",				// 혼자하기
			"resources/gameroom/test3",				// 기권
			"resources/gameroom/test4",				// 나가기
			"resources/gameroom/itemunity.jpg",		// 돌색 통일시키기
			"resources/gameroom/itemtimeadd.jpg",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameRoom/gamecoin.png"		// 상점
	}),
	
	GAMEROOM_MENU_IMAGES_GUEST(new String[] {
			"resources/gameroom/test5",				// ready
			"resources/gameroom/test2",				// 혼자하기
			"resources/gameroom/test3",				// 기권
			"resources/gameroom/test4",				// 나가기
			"resources/gameroom/itemunity.jpg",		// 돌색 통일시키기
			"resources/gameroom/itemtimeplus.jpg",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameRoom/gamecoin.png"		// 상점
	});
	
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
}
