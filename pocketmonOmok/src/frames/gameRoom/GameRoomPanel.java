package frames.gameRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import actions.gameRoom.GameRoomClientAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserMessageVO;
import enums.etc.ImageEnum;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.GameRoomEnum;
import frames.BasicFrame;

@SuppressWarnings("serial")
public class GameRoomPanel extends JPanel {
	private BasicFrame basicFrame;
	private String thisUserID;
	
	private JPanel gameBoardPanel;	// ������
	private JPanel omokStonePanel;	// ���� ���� �г�
	private JPanel timeLimitPanel;	// �ð����� ǥ��
	private JPanel userImagePanel;	// �����̹���
	private JPanel gameMenuPanel;	// ���Ӹ޴� �� ������
	
	private JPanel chattingPanel;	// ä��
	private JTextField chattingField;
	private JTextArea chattingArea;
	
	private Thread timeLimitThread;
	
	private JLabel leftUser;
	private JLabel rightUser;
	private JButton[] menuButtons;
	
	private GameRoomClientAction gameRoomAction;
	private GameRoomInfoVO gameRoomInfo;
	
	public GameRoomPanel(BasicFrame basicFrame) throws IOException {
		this.gameRoomAction = new GameRoomClientAction(this);
		
		this.basicFrame = basicFrame;
		this.setLayout(null);
		
		this.omokStonePanel = new JPanel();
		this.timeLimitPanel = new JPanel();
		this.userImagePanel = new JPanel();
		this.gameMenuPanel  = new JPanel();
		this.chattingPanel  = new JPanel();

		this.setGameBoard();
		this.setUserImage();
		this.setInGameMenuButtons();
		this.setInGameChattingArea();
		this.setTimeLimit();
		this.setStonePanel();
			
	} // ������
	
	public void setStonePanel() {
		this.omokStonePanel.setLayout(null);
		this.omokStonePanel.setOpaque(false);
		JButton[][] stonesLocation = new JButton[15][];

		for(int i = 0, iSize = stonesLocation.length; i < iSize; i++) {
			stonesLocation[i] = new JButton[15];
			for(int j = 0, jSize = stonesLocation.length; j < jSize; j++) {
				stonesLocation[i][j] = new JButton();
				stonesLocation[i][j].setBounds(new Rectangle(
						j * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						i * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height
				));
				
				stonesLocation[i][j].setContentAreaFilled(false);
				stonesLocation[i][j].setFocusPainted(false);
				stonesLocation[i][j].setBorderPainted(false);
				this.omokStonePanel.add(stonesLocation[i][j]);
			}
		}
//		try {
//			stonesLocation[7][7].setIcon(
//					new ImageIcon(
//						ImageIO.read(new File(ImageEnum.GAMEROOM_STONE_CHARMANDER.getImageDir())).getScaledInstance(
//							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
//							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height, 
//							Image.SCALE_AREA_AVERAGING)
//					));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		this.omokStonePanel.setBounds(GameRoomEnum.GAME_STONEPANEL_RECT.getRect());
	}
	
	
	public void setGameBoard() {
		this.gameBoardPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File(ImageEnum.GAMEROOM_BOARD_IMAGE.getImageDir())), 
						0, 
						0, 
						GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect().width, 
						GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect().height, 
						this
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.gameBoardPanel.setLayout(null);
		this.gameBoardPanel.setBounds(GameRoomEnum.GAME_BOARD_PANEL_RECT.getRect());
		
		this.gameBoardPanel.add(this.omokStonePanel);
		this.add(this.gameBoardPanel);
	} // ������ �г�
	
	
	public void setTimeLimit() {
		this.timeLimitPanel.setLayout(null);
		this.timeLimitPanel.setOpaque(false);
		this.timeLimitPanel.setBounds(GameRoomEnum.GAME_TIMELIMIT_PANEL_RECT.getRect());
		
		Integer time = 20;
		JProgressBar timeBar = new JProgressBar(0, 30);
		timeBar.setValue(time);
		timeBar.setBounds(GameRoomEnum.GAME_TIMELIMIT_PROGRESS_RECT.getRect());
		
		
		JLabel timeLabel = new JLabel("0:" + time.toString());
		timeLabel.setBounds(GameRoomEnum.GAME_TIMELIMIT_TIMELABEL_RECT.getRect());
		timeLabel.setForeground(Color.red);
		timeLabel.setFont(GameRoomEnum.GAME_TIMELABEL_FONT.getFont());
		
		this.timeLimitThread = new Thread() {
			@Override
			public void run() {
				for(int i = time; i >= 0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeBar.setValue(i);
					timeLabel.setText("0:" + String.valueOf(i));
				}
			}
		};
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // �ð����� ǥ�� �г�
	
	
	public void setUserImage() throws IOException {
		this.userImagePanel.setBounds(GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect());
		this.userImagePanel.setLayout(null);
			
		this.leftUser = new JLabel();
		this.leftUser.setIcon(new ImageIcon(ImageIO.read(
			new File(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir())).getScaledInstance(
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height, 
				Image.SCALE_AREA_AVERAGING)));
		this.leftUser.setBounds(GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect());
		this.leftUser.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.userImagePanel.add(this.leftUser);
		
		this.rightUser = new JLabel();
		this.rightUser.setIcon(new ImageIcon(ImageIO.read(
			new File(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir())).getScaledInstance(
				GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect().height,
				Image.SCALE_AREA_AVERAGING)));		
		this.rightUser.setBounds(GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect());
		this.rightUser.setBorder(BorderFactory.createLineBorder(Color.blue, 6));
		this.userImagePanel.add(this.rightUser);
		
		this.userImagePanel.add(this.leftUser);
		this.userImagePanel.add(this.rightUser);
		this.add(this.userImagePanel);
		
	} // �����̹��� �г�
	
	
	public void setInGameMenuButtons() {
		this.gameMenuPanel.setLayout(null);
		this.gameMenuPanel.setOpaque(false);
		this.gameMenuPanel.setBounds(GameRoomEnum.GAME_MENU_PANEL_RECT.getRect());
		String[] buttonsName = GameRoomEnum.GAME_BUTTONNAME_OWNER.getButtonName();
		String[] buttonsDir = ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_OWNER.getImages();
		this.menuButtons = new JButton[buttonsName.length];
		
		for(int i = 0, size = buttonsName.length; i < size; i++) {
			try {
				this.menuButtons[i] = new JButton(
					new ImageIcon(
						ImageIO.read(new File(buttonsDir[i])).getScaledInstance(
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height, 
							Image.SCALE_AREA_AVERAGING)
					)
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(i < 4) {
				this.menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * i),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().y,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
			} else {
				this.menuButtons[i].setBounds(new Rectangle(
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().x + (GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width * (i - 4)),
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
						GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height
				));
				
			}
			this.menuButtons[i].setName(buttonsName[i]);
			this.menuButtons[i].setBorderPainted(false);
			this.menuButtons[i].setContentAreaFilled(false);
			this.menuButtons[i].setFocusPainted(false);
			if(!this.menuButtons[i].getName().equals("start")) {
				this.menuButtons[i].addMouseListener(this.gameRoomAction);
			}
			this.gameMenuPanel.add(this.menuButtons[i]);
		}
		
		this.add(this.gameMenuPanel);
	} // ���Ӹ޴� ��  ������ �г�
	
	
	public void setInGameChattingArea() {
		this.chattingPanel.setLayout(null);
		this.chattingPanel.setOpaque(false);
		this.chattingPanel.setBounds(GameRoomEnum.GAME_CHATTING_PANEL_RECT.getRect());
		
		
		this.chattingArea = new JTextArea();
		this.chattingArea.setLineWrap(true);
		this.chattingArea.setFont(GameRoomEnum.GAME_CHATTING_FONT.getFont());
		this.chattingArea.setEditable(false);
		
		this.chattingField = new JTextField();
		this.chattingField.setBounds(GameRoomEnum.GAME_CHATTINGFIELD_RECT.getRect());
		this.chattingField.setFont(GameRoomEnum.GAME_CHATTING_FONT.getFont());
		this.chattingField.setEditable(false);
		this.chattingField.setName("chattingField");
		this.chattingField.addActionListener(this.gameRoomAction);
		
		JScrollPane scroll = new JScrollPane(chattingArea);
		scroll.setBounds(GameRoomEnum.GAME_SCROLL_PANE_RECT.getRect());
		
		this.chattingPanel.add(scroll);
		this.chattingPanel.add(chattingField);
		this.add(this.chattingPanel);
	} // ä�� �г�

	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		this.gameRoomInfo = inGameUserInfo.getGameRoomInfo();
		this.thisUserID = this.basicFrame.getUserID();
		try {
			System.out.println("�����Դٰ�");
			String imageDir = null;
			
			// ������ ���
			if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
				imageDir = ImageEnum.GAMEROOM_START_GRAY.getImageDir();
				this.leftUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
			
			// ������ ���
			} else if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getGuest())) {
				this.chattingField.setEditable(true);
				imageDir = ImageEnum.GAMEROOM_READY_GRAY.getImageDir();
				this.rightUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
				
				String ownerImageDir = inGameUserInfo.getOwnerGender() == 1 ? 
						ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
				
				// �����ư�� �׼ǵ��!!!
				this.menuButtons[0].setName("ready");
				this.menuButtons[0].addMouseListener(this.gameRoomAction);
				
				this.leftUser.setIcon(this.getUserImageIcon(ownerImageDir));
			}
			
			this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ������ ��� �濡 ������ ������ �� �����
	public void ownerFrameModify(UserInGameRoomDTO userInGameRoomDTO) {
		this.gameRoomInfo = userInGameRoomDTO.getGameRoomInfo();
		this.chattingField.setEditable(true);
		
		String imageDir = userInGameRoomDTO.getGuestGender() == 1 ? 
				ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
		
		this.rightUser.setIcon(this.getUserImageIcon(imageDir));
	}
	
	// ä�� �Է½� ������ ����
	public void chattingInfoSendServer() {
		UserMessageVO userMessage = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
		userMessage.setUserAction(UserActionEnum.USER_IN_GAME_ROOM_CHATTING);
		userMessage.setUserID(this.thisUserID);
		userMessage.setTargetID(this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
				this.gameRoomInfo.getGuest() : this.gameRoomInfo.getOwner());
		userMessage.setMessage(this.chattingField.getText());
		this.basicFrame.sendDTO(userMessage);
	}
	
	// ä�ÿ� ������ ���������� ����
	public void chattingAreaSetting(AbstractEnumsDTO data) {
		this.chattingField.setText("");
		UserMessageVO messageVO = (UserMessageVO)data;
		this.chattingArea.setText(this.chattingArea.getText() + "\n" + messageVO.getMessage());
	}
	
	// �޴� ���� ������ ���콺 �����Ͱ� ����
	public void changeButtonGrayImage(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_COLOR.getImageDir()));
		} else {
			String[] buttonImages = this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
					ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_OWNER.getImages() : ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_GUEST.getImages();
			// ���� Ȥ�� �����ư ����
			for(int i = 1, size = this.menuButtons.length; i < size; i++) {
				if(this.menuButtons[i].getName().equals(buttonName)) {
					this.menuButtons[i].setIcon(this.getButtonImageIcon(buttonImages[i]));
					break;
				}
			}
			
		}
	}
	
	// �޴� ���� ������ ���콺 �����Ͱ� ����
	public void changeButtonColorImage(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_LAST_COLOR.getImageDir()));
		} else {
			String[] buttonImages = this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
					ImageEnum.GAMEROOM_MENU_IMAGES_COLOR_OWNER.getImages() : ImageEnum.GAMEROOM_MENU_IMAGES_COLOR_GUEST.getImages();
			// ���� Ȥ�� �����ư ����
			for(int i = 1, size = this.menuButtons.length; i < size; i++) {
				if(this.menuButtons[i].getName().equals(buttonName)) {
					this.menuButtons[i].setIcon(this.getButtonImageIcon(buttonImages[i]));
					
					break;
				}
			}
		}
	}
	
	// ���� ��ư�� Ŭ�� �̺�Ʈ�� ������
	public void changeGameReadyButton(boolean check) {
		String imageDir = check ? ImageEnum.GAMEROOM_READY_COLOR.getImageDir() : ImageEnum.GAMEROOM_READY_GRAY.getImageDir() ;
		UserActionEnum userAction = check ? UserActionEnum.USER_GUEST_READY_CHECK : UserActionEnum.USER_GUEST_READY_DECHECK ;
	
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		
		// ������ ���ۿ� ��ü�� ���� ����� �ʿ� ������ ������ �� �Խ�Ʈ�� ���� �ߴٴ� ������ ������ �����Ѵ�.
		GameRoomInfoVO gameRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomVO.setUserAction(userAction);
		gameRoomVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomVO.setOwner(this.gameRoomInfo.getOwner());
		this.basicFrame.sendDTO(gameRoomVO);	
	}
	
	// �Խ�Ʈ�� �����ư�� ������� ������ ��ŸƮ��ư�� ������ �ٲ��.
	
	//TODO
	// �Խ�Ʈ�� ���� ������ �� �������� ������ ������ ������ ��ŸƮ ��ư ������ �ٲ��ش�. (��ŸƮ ��ư Ŭ���� �����ϰ� ��.)
	public void changeStartGuestReadyCheck(AbstractEnumsDTO data) {
		String imageDir = null;
		if(data.getServerAction() == ServerActionEnum.GAME_ROOM_GUEST_READY_CHECK) {
			imageDir = ImageEnum.GAMEROOM_START_COLOR.getImageDir();
			this.menuButtons[0].addMouseListener(this.gameRoomAction);
		} else {
			imageDir = ImageEnum.GAMEROOM_START_GRAY.getImageDir();
			this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		}
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
	}
	
	// ���� �����Ѵٴ� ������ �������� ������.
	public void buttonRemoveAction() {
		GameRoomInfoVO gameRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfoVO.setUserAction(UserActionEnum.USER_GAME_START);
		gameRoomInfoVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomInfoVO.setOwner(this.gameRoomInfo.getOwner());
		
		this.basicFrame.sendDTO(gameRoomInfoVO);
		
	}
	
	
	// ���ʰ� ���ӽ��� ��ư�� ������ [0]��° ���� ��ư(����)�� �̹����� �ʱ�ȭ�ϰ� �׼��� �����Ѵ�.
	// �������� ȸ���� ���� �Խ�Ʈ�� �� �޼ҵ带 �����ϸ� ���� ��ư�� ���� �׼��� �����Ѵ�.
	public void gameStart() {
		String imageDir = this.getBasicFrame().getUserID().equals(this.gameRoomInfo.getOwner()) ?
				ImageEnum.GAMEROOM_START_GRAY.getImageDir() : ImageEnum.GAMEROOM_READY_GRAY.getImageDir();
		
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}

	public ImageIcon getUserImageIcon(String imageDir) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(
						new File(imageDir)).getScaledInstance(
							GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
							GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height, 
							Image.SCALE_AREA_AVERAGING)
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
	
	public ImageIcon getButtonImageIcon(String imageDir) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(
						new File(imageDir)).getScaledInstance(
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width,
							GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height, 
							Image.SCALE_AREA_AVERAGING)
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
}