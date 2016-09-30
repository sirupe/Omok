package enums.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
// �¼�(���)

public enum LoginFrameSizesEnum {

	SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
	     
	//�α��� ������ ���� ����
	LOGIN_FRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.67)),
	//�α��� ������ ���� ����
	LOGIN_FRAME_SIZE_HEIGHT((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),

	//�α��� �������� X��ġ
	LOGIN_FRAME_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (LOGIN_FRAME_SIZE_WIDTH.getSize() / 2))),
	//�α��� �������� Y��ġ
	LOGIN_FRAME_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2)) - (LOGIN_FRAME_SIZE_HEIGHT.getSize() / 2)),	
	
	//ID�̹����� X��ġ
	LOGIN_RESOURCE_ID_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.37)),
	//ID�̹����� Y��ġ
	LOGIN_RESOURCE_ID_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.34)),
		
	//PW�̹����� X��ġ
	LOGIN_RESOURCE_PASSWORD_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.368)),
	//PW�̹����� Y��ġ
	LOGIN_RESOURCE_PASSWORD_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.40)),
	
	//�α��� ��ư�� X��ġ
	LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.46)),
	//�α��� ��ư�� Y��ġ
	LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.55)),
	
	//ȸ������(sign up) �̹�����  X��ġ
	LOGIN_RESOURCE_JOIN_BUTTON_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.37)),
	//ȸ������(sign up) �̹����� Y��ġ
	LOGIN_RESOURCE_JOIN_BUTTON_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.49)),
	
	//���̵�ã��(forgot ID) �̹����� X��ġ
	LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.455)),
	//���̵�ã��(forgot ID) �̹����� Y��ġ
	LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.49)),
	
	//��й�ȣã��(forgot PW) �̹����� X��ġ
	LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.54)),
	//��й�ȣã��(forgot PW) �̹����� Y��ġ
	LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.49)),
	
	//���̵� �Է�â �ʵ��� X��ġ
	LOGIN_RESOURCE_ID_FIELD_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.44)),
	//���̵� �Է�â �ʵ��� Y��ġ
	LOGIN_RESOURCE_ID_FIELD_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.35)),
	
	//�н����� �Է�â �ʵ��� X��ġ
	LOGIN_RESOURCE_PW_FIELD_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.44)),
	//�н����� �Է�â �ʵ��� Y��ġ
	LOGIN_RESOURCE_PW_FIELD_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.41)),
	
	//�α��� ȭ��â��  �����ܵ��� ���� ũ��
	LOGIN_ICON_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.07)),
	//�α��� ȭ��â�� �����ܵ��� ���� ũ��
	LOGIN_ICON_HEIGHT((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.08)),
	
	//ID, PW �̹����� ���� ũ��
	SIZE_LABEL_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	//ID, PW �̹����� ���� ũ��
	SIZE_LABEL_HEIGHT((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	
	//ID, PW �Է�â�� ���� ũ��
	SIZE_TEXT_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.15)),
	//ID, PW �Է�â�� ���� ũ��
	SIZE_TEXT_HEIGHT((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.035)),
	
	//ȸ������, ���̵�ã��, ��й�ȣã�� ������ ũ�� ���� ũ��
	ICON_SIZE_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.07)),
	//ȸ������, ���̵�ã��, ��й�ȣã�� ������ ũ�� ���� ũ��
	ICON_SIZE_HEIGHT((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.03)),
	
	//�α��� ���н� ���� �˸� �ؽ�Ʈ ǥ�� X��ġ
	LOGIN_FAIL_TEXT_POSITION_X((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.444)),
	//�α��� ���н� ���� �˸� �ؽ�Ʈ ǥ�� Y��ġ
	LOGIN_FAIL_TEXT_POSITION_Y((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.455)),
	//�α��� ���н� ���� �˸� �ؽ�Ʈ ǥ�� ���� ũ��
	LOGIN_FAIL_TEXT_WIDTH((int)(LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.2)),
	//�α��� ���н� ���� �˸� �ؽ�Ʈ ǥ�� ���� ũ��
	LOGIN_FAIL_TEXT_HEIGTH((int)(LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.02)),
	
	//============================================================================================
	
	LOGIN_ACCESS_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.2)),
	LOGIN_ACCESS_SIZE_HEIGHT((int)(SCREEN_SIZE.getDimension().getHeight() * 0.27)),
	
	LOGIN_ACCESS_FRAME_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (LOGIN_ACCESS_SIZE_WIDTH.getSize() / 2))),
	LOGIN_ACCESS_FRAME_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 2))),
	
	LOGIN_ACCESS_LABEL_WIDTH(300),
	LOGIN_ACCESS_LABEL_HEIGTH(100),
	
	LOGIN_ACCESS_LABEL_POSITION_X((int)(LOGIN_ACCESS_SIZE_WIDTH.getSize() / 4.2)),
	LOGIN_ACCESS_LABEL_POSITION_Y((int)(LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 6)),
	
	LOGIN_ACCESS_OK_BUTTON_WIDTH(100),
	LOGIN_ACCESS_OK_BUTTON_HEIGHT(50),
	
	LOGIN_ACCESS_OK_BUTTON_POSITION_X((int)(LOGIN_ACCESS_SIZE_WIDTH.getSize() / 2.66)),
	LOGIN_ACCESS_OK_BUTTON_POSITION_Y((int)(LOGIN_ACCESS_SIZE_HEIGHT.getSize() / 2)),
	
	BUTTON_NAME_SIGNUP("signup"),
	BUTTON_NAME_SEARCHID("searchID"),
	BUTTON_NAME_SEARCHPW("searchPW");
	
	private String buttonName;
	private int size;
	private Dimension dimension;
	
	private LoginFrameSizesEnum() {}
	
	private LoginFrameSizesEnum(int x) {
	   this.size = x;
	}
	
	private LoginFrameSizesEnum(Dimension dimension) {
	   this.dimension = dimension;
	}
	
	private LoginFrameSizesEnum(String buttonName) {
		this.buttonName = buttonName;
	}
	
	public int getSize() {
	   return size;
	}
	
	public Dimension getDimension() {
	   return dimension;
	}
	
	public String getButtonName() {
		return buttonName;
	}
	
}