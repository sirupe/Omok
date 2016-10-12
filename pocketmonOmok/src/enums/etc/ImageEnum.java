package enums.etc;

import java.util.HashMap;
import java.util.Map;

public enum ImageEnum {
//BasieFrame 이미지-----------------------------------------------------
	BASIC_BACKGROUND("resources/background/background.png"),
//Loginpanel 이미지-----------------------------------------------------
	LOGINPANEL_LOGIN("resources/login/login.jpg"),
	LOGINPANEL_SIGHUP("resources/login/signup.png"),
	LOGINPANEL_SEARCHID("resources/login/forgotID.png"),
	LOGINPANEL_SEARCHPW("resources/login/forgotPW.png"),
	LOGINPANEL_LOGIN_HOVER("resources/login/gamestartIconchange.png"),
	LOGINPANEL_SIGNUP_HOVER("resources/login/signupYellow.png"),
	LOGINPANEL_SEARCHID_HOVER("resources/login/forgotIDYellow.png"),
	LOGINPANEL_SEARCHPW_HOVER("resources/login/forgotPWYellow.png"),
//WaitingRoom 이미지들---------------------------------------------------
	WAITINGROOM_MYINFO_MODIFY("resources/waitingroom/correctInfo.png"),
	WAITINGROOM_ENTER_POSSIBLE("resources/waitingRoom/admission.png"),
	WAITINGROOM_ENTER_PRIVATE("resources/waitingRoom/private.png"),
	WAITINGROOM_ENTER_IMPOSSIBLE("resources/waitingRoom/NoAdmittance.png"),
	WAITINGROOM_USER_GRADE_IMAGE_MAP(userGradeImageMap()),
//GameRoom 이미지들------------------------------------------------------	
//뒤에 "CH" 가 붙은것은 마우스를 버튼위에 올렸을때 변하는 것 입니다.
	GAMEROOM_READY("resources/gameRoom/ready.png"),
	GAMEROOM_READY_CH("resources/gameRoom/ready_ch.png"),
	GAMEROOM_READY_BLUE("resources/gameRoom/ready_blue.png"),
	GAMEROOM_EXIT("resources/gameRoom/exit.png"),
	GAMEROOM_EXIT_CH("resources/gameRoom/exit_ch.png"),
	GAMEROOM_GIVEUP("resources/gameRoom/giveup.png"),
	GAMEROOM_GIVEUP_CH("resources/gameRoom/giveup_ch.png"),
	GAMEROOM_START("resources/gameRoom/start.png"),
	//게스트가 레디버튼을 눌렀을때
	GAMEROOM_START_GO("resources/gameRoom/start_go.png"),
	//방장이 스타트 버튼위에 마우스를 올렸을때
	GAMEROOM_START_CH("resources/gameRoom/start_ch.png"),
	//게임이 시작되면 게스트의 레디버튼과 방장의 스타트 버튼이 
	//비활성화 되면서 BATTLING 이미지로 바뀌어요
	GAMEROOM_START_BATTILING("resources/gameRoom/battling.png"),
	
	GAMEROOM_BOARD_IMAGE("resources/gameRoom/gameBoard.png"),
	GAMEROOM_MALE_IMAGE("resources/gameRoom/userImageMale.png"),
	GAMEROOM_FEMALE_IMAGE("resources/gameRoom/userImageFemale.png"),
	GAMEROOM_DEFALT_USER_IMAGE("resources/gameRoom/logo.png"),
	
	GAMEROOM_SHOP_GRAY("resources/gameRoom/gamecoin_gray.png"),
	GAMEROOM_ALONE_GRAY("resources/gameRoom/alone_gray.png"),
	GAMEROOM_ITEM_ALL_SAME_GRAY("resources/gameRoom/itemunity_gray.png"),
	GAMEROOM_ITEM_TIME_PLUS_GRAY("resources/gameRoom/itemtimeplus_gray.png"),
	GAMEROOM_ITEM_RETURN_GRAY("resources/gameRoom/gamereturn_gray.png"),
	GAMEROOM_GIVE_UP_GRAY("resources/gameRoom/giveup_gray.png"),
	
	GAMEROOM_SHOP_COLOR("resources/gameRoom/gamecoin.png"),
	GAMEROOM_ALONE_COLOR("resources/gameRoom/alone.png"),
	GAMEROOM_ITEM_ALL_SAME_COLOR("resources/gameRoom/itemunity.png"),
	GAMEROOM_ITEM_TIME_PLUS_COLOR("resources/gameRoom/itemtimeplus.png"),
	GAMEROOM_ITEM_RETURN_COLOR("resources/gameRoom/gamereturn.png"),
	GAMEROOM_ITEM_GIVE_UP_COLOR("resources/gameRoom/giveup.png"),
	

	GAMEROOM_MENU_IMAGES_GRAY_OWNER(new String[] {
			GAMEROOM_START.getImageDir(),				// gamestart
			GAMEROOM_GIVEUP.getImageDir(),				// 기권
			GAMEROOM_EXIT.getImageDir(),				// 나가기
			GAMEROOM_ITEM_ALL_SAME_GRAY.getImageDir(),	// 돌색 통일시키기
			GAMEROOM_ITEM_TIME_PLUS_GRAY.getImageDir(),	// 시간추가
			GAMEROOM_ITEM_RETURN_GRAY.getImageDir(),	// 턴되돌리기
			GAMEROOM_SHOP_GRAY.getImageDir(),			// 상점
	}),
	
	GAMEROOM_MENU_IMAGES_COLOR_OWNER(new String[] {
			GAMEROOM_START_CH.getImageDir(),			// gamestart
			GAMEROOM_GIVEUP_CH.getImageDir(),			// 기권
			GAMEROOM_EXIT_CH.getImageDir(),				// 나가기
			GAMEROOM_ITEM_ALL_SAME_COLOR.getImageDir(),	// 돌색 통일시키기
			GAMEROOM_ITEM_TIME_PLUS_COLOR.getImageDir(),// 시간추가
			GAMEROOM_ITEM_RETURN_COLOR.getImageDir(),	// 턴되돌리기
			GAMEROOM_SHOP_COLOR.getImageDir(),			// 상점
	}),
	

	GAMEROOM_MENU_IMAGES_GRAY_GUEST(new String[] {
			GAMEROOM_READY.getImageDir(),				// ready
			GAMEROOM_GIVEUP.getImageDir(),				// 기권
			GAMEROOM_EXIT.getImageDir(),				// 나가기
			GAMEROOM_ITEM_ALL_SAME_GRAY.getImageDir(),	// 돌색 통일시키기
			GAMEROOM_ITEM_TIME_PLUS_GRAY.getImageDir(),	// 시간추가
			GAMEROOM_ITEM_RETURN_GRAY.getImageDir(),	// 턴되돌리기
			GAMEROOM_SHOP_GRAY.getImageDir(),			// 상점
	}),
	
	GAMEROOM_MENU_IMAGES_COLOR_GUEST(new String[] {
			GAMEROOM_READY_CH.getImageDir(),			// ready
			GAMEROOM_GIVEUP_CH.getImageDir(),			// 기권
			GAMEROOM_EXIT_CH.getImageDir(),				// 나가기
			GAMEROOM_ITEM_ALL_SAME_COLOR.getImageDir(),	// 돌색 통일시키기
			GAMEROOM_ITEM_TIME_PLUS_COLOR.getImageDir(),// 시간추가
			GAMEROOM_ITEM_RETURN_COLOR.getImageDir(),	// 턴되돌리기
			GAMEROOM_SHOP_COLOR.getImageDir(),			// 상점
	}),
	
	//TODO
	GAMEROOM_TEST_IMAGE_LEVEL("resources/user/usermediumgrade.png"),
	
	GAMEROOM_STONE_CHARMANDER("resources/omokball/charmander.png"),
	GAMEROOM_STONE_PIKACHU("resources/omokball/pikachu.png"),
	GAMEROOM_STONE_ROUND_PIKA("resources/omokball/pika.png"),
	GAMEROOM_STONE_ROUND_PIKA_CLICK("resources/omokball/pica_click.png"),
	GAMEROOM_STONE_ROUND_KOBOOK("resources/omokball/kobook.png"),
	GAMEROOM_STONE_ROUND_KOBOOK_CLICK("resources/omokball/koboogi_click.png");
	
	

	
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
