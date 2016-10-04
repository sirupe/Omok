package frames.waitingRoom;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import actions.waitingRoom.WaitingRoomActionListeners;
import enums.frames.CreateGameRoomEnum;
import enums.frames.SearchIDEnum;

public class CreateGameRoomFrame extends JFrame {
	private static final long serialVersionUID = 36363454L;
	
	private JLabel createRoomNameLabel;
	private JLabel createRoomPwdLabel;
	
	private JTextField createRoomNameText;
	private JTextField createRoomPwdText;
		
	private JButton createRoomConfirmButton;
	private JButton createRoomCancelButton;
	
	private JRadioButton roomCreateOpen;
	private JRadioButton roomCreatePrivate;
	private ButtonGroup roomCreateGroup;
	
	private Image backGround;
	
	private WaitingRoomActionListeners waitingRoomAction;
	private WaitingRoomPanel waitingRoomPanel;
	
	public CreateGameRoomFrame(WaitingRoomActionListeners waitingRoomAction, WaitingRoomPanel waitingRoomPanel) throws IOException {
		this.waitingRoomAction = waitingRoomAction;
		this.waitingRoomPanel  = waitingRoomPanel;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this.waitingRoomAction);
		
		//���̸�, ���й�ȣ �� ����
		this.createRoomNameLabel = new JLabel("���̸�");
		this.createRoomPwdLabel  = new JLabel("���й�ȣ");
		
		//���̸�, ���й�ȣ �ؽ�Ʈ ����
		this.createRoomNameText = new JTextField(10);
		this.createRoomPwdText  = new JTextField(10);
		// ���� - ��й�ȣ �Է� �⺻�� �Ұ�
		this.createRoomPwdText.setEditable(false);
		
		//���� ������� ���� �ڽ�
		this.roomCreateGroup   = new ButtonGroup();
		this.roomCreateOpen = new JRadioButton("������");
		this.roomCreatePrivate    = new JRadioButton("��й�");
		// ���� - �⺻�� ������ ����
		this.roomCreateOpen.setSelected(true);
		this.roomCreateGroup.add(this.roomCreateOpen);
		this.roomCreateGroup.add(this.roomCreatePrivate);
		
		
		//��ư ����
		//Ȯ�� ��ư
		createRoomConfirmButton  = new JButton();
		createRoomConfirmButton.setBorderPainted(false);
		createRoomConfirmButton.setFocusPainted(false);
		createRoomConfirmButton.setContentAreaFilled(false);
		
		//��� ��ư
		createRoomCancelButton  = new JButton();
		createRoomCancelButton.setBorderPainted(false);
		createRoomCancelButton.setFocusPainted(false);
		createRoomCancelButton.setContentAreaFilled(false);
		
		
		
		//���ȭ��	

		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchIDEnum.SEARCHFRAME_SIZE_WIDTH.getSize(),
				SearchIDEnum.SEARCHFRAME_SIZE_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
				
		this.setBounds(
				CreateGameRoomEnum.GAMEROOM_CREATE_FRAME_POSITION_X.getSize(),
				CreateGameRoomEnum.GAMEROOM_CREATE_FRAME_POSITION_Y.getSize(),
				CreateGameRoomEnum.GAMEROOM_CREATE_FRAME_SIZE_WIDTH.getSize(),
				CreateGameRoomEnum.GAMEROOM_CREATE_FRAME_SIZE_HEIGHT.getSize()
		);
		
		//���̺� ��Ʈ -- searchIdEnum ���� �ҷ���
		this.createRoomNameLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());	
		this.createRoomPwdLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		
		
		this.createRoomNameText.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.createRoomPwdText.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		
		this.roomCreateOpen.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.roomCreatePrivate.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		//���̺� ����
		this.createRoomNameLabel.setForeground(Color.black);
		this.createRoomPwdLabel.setForeground(Color.black);
				
		this.setLabelPosition();
		this.setTextPosition();
		this.setButtonPosition();
		this.setRadioPosition();
		
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	//========================================================================================================
	//�� ��ġ
	public void setLabelPosition() {
		this.createRoomNameLabel.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_NAME_LABEL.getRectangle());
		this.createRoomPwdLabel.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_PWD_NAME_LABEL.getRectangle());
		
		this.add(createRoomPwdLabel);
		this.add(createRoomNameLabel);
	}
	
	//�ؽ�Ʈ�ʵ� ��ġ
	public void setTextPosition() {
		this.createRoomNameText.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_NAME_TEXT.getRectangle());
		this.createRoomPwdText.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_PWD_TEXT.getRectangle());
		this.add(createRoomNameText);
		this.add(createRoomPwdText);
		
	}
	//========================================================================================================
	//Ȯ��, ��� ��ư �޼ҵ�
	public void setButtonPosition() throws IOException {
		//Ȯ�� ��ư
		this.createRoomConfirmButton.setIconTextGap(this.createRoomConfirmButton.getIconTextGap() - 15);
    	this.createRoomConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/confirm.jpg")).getScaledInstance(
    						CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON.getRectangle().width,
    						CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
    	this.createRoomConfirmButton.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CONFIRM_BUTTON.getRectangle());
    	
    //��ҹ�ư
    	this.createRoomCancelButton.setIconTextGap(this.createRoomCancelButton.getIconTextGap() - 15);
    	this.createRoomCancelButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/reset.jpg")).getScaledInstance(
    						CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CANCEL_BUTTON.getRectangle().width,
    						CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CANCEL_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
    	this.createRoomCancelButton.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_CANCEL_BUTTON.getRectangle());
    	
    	this.add(createRoomConfirmButton);
    	this.add(createRoomCancelButton);
    	
    	this.waitingRoomPanel.addAction(this.createRoomCancelButton, "createRoomCancelButton");
    	this.waitingRoomPanel.addAction(this.createRoomConfirmButton, "createRoomConfirmButton");
    	this.waitingRoomPanel.addItemAction(this.roomCreatePrivate, "roomCreatePrivate");
	}
	//========================================================================================================
	public void setRadioPosition() throws IOException {
		//��������� �ڽ�
    	this.roomCreatePrivate.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_OPEN_RADIO.getRectangle());
    	this.roomCreateOpen.setBounds(CreateGameRoomEnum.GAMEROOM_CREATE_ROOM_PRIVATE_RADIO.getRectangle());
    	
    	
    	this.roomCreatePrivate.setOpaque(false);
    	this.roomCreateOpen.setOpaque(false);
    	this.add(roomCreateOpen);
		this.add(roomCreatePrivate);
	}
	//========================================================================================================
	
	public JTextField getCreateRoomPwdText() {
		return createRoomPwdText;
	}
	
	public JTextField getCreateRoomNameText() {
		return createRoomNameText;
	}
}
