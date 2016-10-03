package enums.etc;

import java.util.HashMap;
import java.util.Map;

public enum ImageEnum {
//BasieFrame �̹���-----------------------------------------------------
	BASIC_BACKGROUND("resources/background/background.png"),
//Loginpanel �̹���-----------------------------------------------------
	LOGINPANEL_LOGIN("resources/login/login.jpg"),
	LOGINPANEL_SIGHUP("resources/login/signup.png"),
	LOGINPANEL_SEARCHID("resources/login/forgotID.png"),
	LOGINPANEL_SEARCHPW("resources/login/forgotPW.png"),
	LOGINPANEL_LOGIN_HOVER("resources/login/gamestartIconchange.png"),
	LOGINPANEL_SIGNUP_HOVER("resources/login/signup.png"),
	LOGINPANEL_SEARCHID_HOVER("resources/login/forgotID.png"),
	LOGINPANEL_SEARCHPW_HOVER("resources/login/forgotPW.png"),
//WaitingRoom �̹�����---------------------------------------------------
	WAITINGROOM_MYINFO_MODIFY("resources/waitingroom/correctInfo.png"),
	WAITINGROOM_ENTER_POSSIBLE("resources/waitingRoom/admission.png"),
	WAITINGROOM_ENTER_PRIVATE("resources/waitingRoom/private.png"),
	WAITINGROOM_ENTER_IMPOSSIBLE("resources/waitingRoom/NoAdmittance.png"),
	WAITINGROOM_USER_GRADE_IMAGE_MAP(userGradeImageMap()),
//gameRoom �̹����� �����Ѱ��Դϴ�.
//�ڿ� "CH" �� �������� ���콺�� ��ư���� �÷����� ���ϴ� �� �Դϴ�.
	GAMEROOM_READY("resources/gameRoom/ready.png"),
	GAMEROOM_READY_CH("resources/gameRoom/ready_ch.png"),
	GAMEROOM_EXIT("resources/gameRoom/exit.png"),
	GAMEROOM_EXIT_CH("resources/gameRoom/exit_ch.png"),
	GAMEROOM_GIVEUP("resources/gameRoom/giveup.png"),
	GAMEROOM_GIVEUP_CH("resources/gameRoom/giveup_ch.png"),
	GAMEROOM_START("resources/gameRoom/start.png"),
	//�Խ�Ʈ�� �����ư�� ��������
	GAMEROOM_START_GO("resources/gameRoom/start_go.png"),
	//������ ��ŸƮ ��ư���� ���콺�� �÷�����
	GAMEROOM_START_CH("resources/gameRoom/start_ch.png"),
	//������ ���۵Ǹ� �Խ�Ʈ�� �����ư�� ������ ��ŸƮ ��ư�� 
	//��Ȱ��ȭ �Ǹ鼭 BATTLING �̹����� �ٲ���
//	GAMEROOM_START_BATTILING("resources/gameRoom/battling.png"),
	GAMEROOM_START_BATTILING("resources/gameRoom/battling.png"),
	
//GameRoom �̹�����------------------------------------------------------	
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
//			GAMEROOM_ALONE_GRAY.getImageDir(),			// ȥ���ϱ�
			GAMEROOM_GIVEUP.getImageDir(),				// ���
			GAMEROOM_EXIT.getImageDir(),				// ������
			GAMEROOM_ITEM_ALL_SAME_GRAY.getImageDir(),	// ���� ���Ͻ�Ű��
			GAMEROOM_ITEM_TIME_PLUS_GRAY.getImageDir(),	// �ð��߰�
			GAMEROOM_ITEM_RETURN_GRAY.getImageDir(),	// �ϵǵ�����
			GAMEROOM_SHOP_GRAY.getImageDir(),			// ����
	}),
	
	GAMEROOM_MENU_IMAGES_COLOR_OWNER(new String[] {
			GAMEROOM_START_CH.getImageDir(),			// gamestart
//			GAMEROOM_ALONE_COLOR.getImageDir(),			// ȥ���ϱ�
			GAMEROOM_GIVEUP_CH.getImageDir(),			// ���
			GAMEROOM_EXIT_CH.getImageDir(),				// ������
			GAMEROOM_ITEM_ALL_SAME_COLOR.getImageDir(),	// ���� ���Ͻ�Ű��
			GAMEROOM_ITEM_TIME_PLUS_COLOR.getImageDir(),// �ð��߰�
			GAMEROOM_ITEM_RETURN_COLOR.getImageDir(),	// �ϵǵ�����
			GAMEROOM_SHOP_COLOR.getImageDir(),			// ����
	}),
	

	GAMEROOM_MENU_IMAGES_GRAY_GUEST(new String[] {
			GAMEROOM_READY.getImageDir(),				// ready
//			GAMEROOM_ALONE_GRAY.getImageDir(),			// ȥ���ϱ�
			GAMEROOM_GIVEUP.getImageDir(),				// ���
			GAMEROOM_EXIT.getImageDir(),				// ������
			GAMEROOM_ITEM_ALL_SAME_GRAY.getImageDir(),	// ���� ���Ͻ�Ű��
			GAMEROOM_ITEM_TIME_PLUS_GRAY.getImageDir(),	// �ð��߰�
			GAMEROOM_ITEM_RETURN_GRAY.getImageDir(),	// �ϵǵ�����
			GAMEROOM_SHOP_GRAY.getImageDir(),			// ����
	}),
	
	GAMEROOM_MENU_IMAGES_COLOR_GUEST(new String[] {
			GAMEROOM_READY_CH.getImageDir(),			// ready
//			GAMEROOM_ALONE_COLOR.getImageDir(),			// ȥ���ϱ�
			GAMEROOM_GIVEUP_CH.getImageDir(),			// ���
			GAMEROOM_EXIT_CH.getImageDir(),				// ������
			GAMEROOM_ITEM_ALL_SAME_COLOR.getImageDir(),	// ���� ���Ͻ�Ű��
			GAMEROOM_ITEM_TIME_PLUS_COLOR.getImageDir(),// �ð��߰�
			GAMEROOM_ITEM_RETURN_COLOR.getImageDir(),	// �ϵǵ�����
			GAMEROOM_SHOP_COLOR.getImageDir(),			// ����
	}),
	
	//TODO
	GAMEROOM_TEST_IMAGE_LEVEL("resources/user/usermediumgrade.png"),
	
	
	GAMEROOM_STONE_CHARMANDER("resources/omokball/charmander.png"),
	GAMEROOM_STONE_PIKACHU("resources/omokball/pikachu.png");
	

	
	private static Map<String, String> userGradeImageMap() {
		Map<String, String> images = new HashMap<String, String>();
		images.put("�ʺ�", "resources/user/userbegining.png");
		images.put("�߼�", "resources/user/usermediumgrade.png");
		images.put("���", "resources/user/userhigh.png");
		images.put("�ʰ��", "resources/user/usermorehigh.png");
		images.put("����", "resources/user/usermaster.png");
		images.put("����", "resources/user/userhero.png");
		images.put("��", "resources/user/usertop.png");
		
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
