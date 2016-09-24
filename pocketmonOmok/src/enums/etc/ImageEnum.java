package enums.etc;

import java.util.HashMap;
import java.util.Map;

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

	WAITINGROOM_USER_GRADE_IMAGE_MAP(userGradeImageMap()),
	WAITINGROOM_ROOM_ENTERCHECK_IMAGE_MAP(enterRoomCheckImage());
	
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
	
	private static Map<String, String> enterRoomCheckImage() {
		Map<String, String> images = new HashMap<String, String>();
		images.put("입장가능", "resources/waitingRoom/admission.png");
		images.put("비밀방", "resources/waitingRoom/private.png");
		images.put("입장불가", "resources/waitingRoom/NoAdmittance.png");
		
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
