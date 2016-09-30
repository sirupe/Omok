package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public enum WaitingRoomSizesEnum {
	//�α���â�� x,y ��
	WAITINGROOM_LABEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01)),
	WAITINGROOM_LABEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	//���� ����,���� ����
	WAITING_ROOM_LIST_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//������  x,y ��ġ
	WAITING_ROOM_LIST_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.01)),
	WAITING_ROOM_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//���� ��� ����, ���� ����
	WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.7)),
	WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.52)),
	//���� ��� x,y ��ġ
	WAITING_ROOM_LIST_BACKGROUND_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	WAITING_ROOM_LIST_BACKGROUND_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.06)),
	
	//���� O��X ���� ���� ����
	ROOMLIST_STATUS_SIZE_WIDTH((int)(WAITING_ROOM_LIST_SIZE_WIDTH.getSize() * 0.03)),
	ROOMLIST_STATUS_SIZW_HEIGHT((int)(WAITING_ROOM_LIST_SIZE_HEIGHT.getSize() * 0.05)),
	
	//==================================CHATTING==================================
	//ä�� ���â ����,���� ���� 
	CHATTING_OUTPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.8)),
	CHATTING_OUTPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.28)),
	//ä�� ���â x,y ��ġ
	CHATTING_OUTPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	CHATTING_OUTPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.59)),
		
	//ä�� �Է�â ����, ���� ����
	CHATTING_INPUT_SIZE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.38)),
	CHATTING_INPUT_SIZE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//ä�� �Է�â x,y ��ġ
	NOTICE_TEXTFIELD_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize()),
	NOTICE_TEXTFIELD_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),

	CHATTING_INPUT_POSITION_X(WAITINGROOM_LABEL_POSITION_X.getSize() + NOTICE_TEXTFIELD_WIDTH.getSize()),
	CHATTING_INPUT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	//�޽��� ������ ��ư ����, ���� ����
	SEND_MESSAGE_BUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.08)),
	SEND_MESSAGE_BUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//�޽��� ������ ��ư x,y ��ġ
	SEND_MESSAGE_BUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.49)),
	SEND_MESSAGE_BUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.87)),
	
	//==================================BUTTONS==================================
	// ���� ���� �����ϱ� ���� ����
	MODIFYINFO_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	MODIFYINFO_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	// ���� ���� �����ϱ� x, y
	MODIFYINFO_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	MODIFYINFO_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	//����� ��ư ����, ���� ����
	GAMESTART_JBUTTON_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	GAMESTART_JBUTTON_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.1)),
	//����� x,y ��ġ
	GAMESTART_JBUTTON_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.58)),
	GAMESTART_JBUTTON_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.74)),
	
	//==================================PLAYER LIST==================================
	//������â ����, ���� ����
	PLAYERS_LIST_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.24)),
	PLAYERS_LIST_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() *0.48)),
	//������â x,y ��ġ
	PLAYERS_LIST_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.014)),
	PLAYERS_LIST_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	
	
	//������â ��� ����, ���� ����
	PLAYERS_LIST_BACKGROUND_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.27)),
	PLAYERS_LIST_BACKGROUND_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() *0.54)),
	//������â  ���x,y ��ġ
	PLAYERS_LIST_BACKGROUND_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.72)),
	PLAYERS_LIST_BACKGROUND_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	
	//��� ������ ����, ���� ����
	LEVEL_ICON_SIZE_WIDTH((int)(PLAYERS_LIST_WIDTH.getSize() * 0.12)),
	LEVEL_ICON_SIZE_HEIGHT((int)(PLAYERS_LIST_HEIGHT.getSize() * 0.08)),
	
	//==================================MY INFO==================================
	//������ �̹��� ����, ���� ���� 
	MY_INFO_IMAGE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.13)),
	MY_INFO_IMAGE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.2)),
	//������ �̹��� x,y ��ġ
	MY_INFO_IMAGE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.74)),
	MY_INFO_IMAGE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.6)),

	//������ ���̵� ����, ���� ����
	MY_INFO_USERID_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.03)),
	MY_INFO_USERID_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	//������ ���̵� x,y ��ġ
	MY_INFO_USERID_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.75)),
	MY_INFO_USERID_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.81)),
	//������ ���̵� �ؽ�Ʈâ ����, ���� ����
	MY_INFO_USERID_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.18)),
	MY_INFO_USERID_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.03)),
	//������ ���̵� �ؽ�Ʈâ x,y ��ġ
	MY_INFO_USERID_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.78)),
	MY_INFO_USERID_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.811)),
	
	//������ ��� ����, ���� ����
	MY_INFO_LEVEL_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_LEVEL_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ���
	MY_INFO_LEVEL_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.81)),
	MY_INFO_LEVEL_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.855)), 
	//������ ��� �ؽ�Ʈ�̹��� ����, ���� ����
	MY_INFO_LEVEL_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_LEVEL_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ��� �ؽ�Ʈ �̹��� x,y ��ġ
	MY_INFO_LEVEL_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.75)),
	MY_INFO_LEVEL_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.85)),
	
	//������ ��������, ���� ����
	MY_INFO_SCORE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_SCORE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ����
	MY_INFO_SCORE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_SCORE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	//������ ���� �ؽ�Ʈ ����, ���� ����
	MY_INFO_SCORE_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_SCORE_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ���� �ؽ�Ʈ x,y ��ġ
	MY_INFO_SCORE_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_SCORE_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.62)),
	
	
	//������ �·� ����, ���� ����
	MY_INFO_WINNINGRATE_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_WINNINGRATE_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ �·�
	MY_INFO_WINNINGRATE_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_WINNINGRATE_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.67)),
	
	//������ �·� �ؽ�Ʈ ����, ���� ����
	MY_INFO_WINNINGRATE_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_WINNINGRATE_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ �·� �ؽ�Ʈ x,y ��ġ
	MY_INFO_WINNINGRATE_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_WINNINGRATE_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.67)),
	
	//������ ���� ����, ���� ����
	MY_INFO_POINT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_POINT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ����
	MY_INFO_POINT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize()  * 0.87)),
	MY_INFO_POINT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.72)),
	
	//������ ���� �ؽ�Ʈ ����, ���� ����
	MY_INFO_POINT_TEXT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.06)),
	MY_INFO_POINT_TEXT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ���� �ؽ�Ʈ x,y ��ġ
	MY_INFO_POINT_TEXT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.91)),
	MY_INFO_POINT_TEXT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.72)),
	
	
	//������ ��������, ���� ����
	MY_INFO_CORRECT_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.05)),
	MY_INFO_CORRECT_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.05)),
	//������ ����
	MY_INFO_CORRECT_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.92)),
	MY_INFO_CORRECT_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.85)),
	
	
	//������ ����, ���� ����
	MY_INFO_WIDTH((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.27)),
	MY_INFO_HEIGHT((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.36)),
	//������ x,y ��ġ
	MY_INFO_POSITION_X((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize() * 0.72)),
	MY_INFO_POSITION_Y((int)(LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize() * 0.58)),

	USER_INFO_VIEW_SIZE_X((int)(MY_INFO_IMAGE_WIDTH.getSize() * 0.05)),
	USER_INFO_VIEW_SIZE_Y((int)(MY_INFO_IMAGE_HEIGHT.getSize() * 0.05)),
	USER_INFO_VIEW_SIZE_WIDTH((int)(MY_INFO_IMAGE_WIDTH.getSize() * 0.9)),
	USER_INFO_VIEW_SIZE_HEIGHT((int)(MY_INFO_IMAGE_HEIGHT.getSize() * 0.9)),
	
	//==================================FONTSIZE&COLOR==================================
	
	FONTCOLOR_ERROR(Color.red),
	FONTCOLOR_DEFAULT(Color.green),
	
	
	LABELFONT_SIZE60(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 60)),
	LABELFONT_SIZE70(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 70)),
	LABELFONT_SIZE80(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 80)),
	LABELFONT_SIZE90(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 90)),
	LABELFONT_SIZE100(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 100)),
	LABELFONT_SIZE130(new Font("a��������", Font.BOLD, LoginFrameSizesEnum.SCREEN_SIZE.getDimension().width / 130));
	
	
	
	
	
	private int size;
	private Font font;
	private Color color;
	private Dimension dimension;
	
	private WaitingRoomSizesEnum() {}
	

	
	//()<- �̾ȿ� �� �͵��� �������ε� �̰��� ���������� �ٲ��ֱ����� 
	private WaitingRoomSizesEnum(int x) {
		this.size = x;
	}
	private WaitingRoomSizesEnum(Font font) {
		this.font = font;
	}
	private WaitingRoomSizesEnum(Color color) {
		this.color = color;
	}
	private WaitingRoomSizesEnum(Dimension dimension) {
		this.dimension = dimension;
	}
	
	
	public int getSize() {
		return size;
	}
	public Font getfont() {
		return font;
	}
	public Color getColot() {	
		return color;
	}
	public Dimension getDimension() {
		return dimension;
	}
	

}
