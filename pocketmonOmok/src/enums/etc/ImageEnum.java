package enums.etc;

import java.util.HashMap;
import java.util.Map;

import oracle.net.aso.i;

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
			"resources/gameroom/test1.png",			// gamestart
			"resources/gameroom/test2.png",			// 혼자하기
			"resources/gameroom/test3.png",			// 기권
			"resources/gameroom/itemgameout.png",	// 나가기
			"resources/gameroom/itemunity.png",		// 돌색 통일시키기
			"resources/gameroom/itemtimeplus.png",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameRoom/gamecoin.png"		// 상점
	}),
	
	GAMEROOM_MENU_IMAGES_GUEST(new String[] {
			"resources/gameroom/test5.png",			// ready
			"resources/gameroom/test2.png",			// 혼자하기
			"resources/gameroom/test3.png",			// 기권
			"resources/gameroom/itemgameout.png",	// 나가기
			"resources/gameroom/itemunity.jpg",		// 돌색 통일시키기
			"resources/gameroom/itemtimeplus.png",	// 시간추가
			"resources/gameroom/gamereturn.png",	// 턴되돌리기
			"resources/gameroom/gamecoin.png"		// 상점
	}),
	
	GAMEROOM_STONE_CHARMANDER("resources/omokball/charmander.png"),
//WaitingRoom 이미지들-------------------------------------------------------
	WAITINGROOM_ENTER_POSSIBLE_IMAGE("resources/waitroom/waitingvacancy.jpg"),
	WAITINGROOM_ENTER_PRIVATE_IMAGE("resources/waitroom/waitingprivate.jpg"),
	WAITINGROOM_ENTER_FULL_IMAGE("resources/waitroom/watingfull.jpg"),
	
	WAITINGROOM_USER_GRADE_IMAGE_MAP(userGradeImageMap());
	
	private static Map<String, String> userGradeImageMap() {
		Map<String, String> images = new HashMap<String, String>();
		images.put("초보", "resources/user/userbegining.png");
		images.put("중수", "resources/user/usermediumgrade.png");
		images.put("고수", "resources/user/userhigh.png");
		images.put("초고수", "resources/user/usermorehigh.png");
		images.put("달인", "resources/user/usermaster.png");
		images.put("영웅", "resources/user/userhero.png");
		images.put("신", "resources/user/usertop.png");
		
		return images;
	}
//-------------------------------------------------------------------------	
	
	private String imageDir;
	private String[] images;
	private Map<String, String> map;
	
	private ImageEnum(String image) {
		this.imageDir = image;
	}
	
	private ImageEnum(String[] images) {
		this.images = images;
	}
	
	private ImageEnum(Map<String, String> map) {
		this.map = map;
	}
	
	public String getImageDir() {
		return imageDir;
	}
	
	public String[] getImages() {
		return images;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
}
