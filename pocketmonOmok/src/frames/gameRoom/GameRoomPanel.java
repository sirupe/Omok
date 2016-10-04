package frames.gameRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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
import datasDTO.GameBoardVO;
import datasDTO.GameRoomInfoVO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserMessageVO;
import enums.etc.ImageEnum;
import enums.etc.ServerActionEnum;
import enums.etc.SoundEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.GameRoomEnum;
import frames.BasicFrame;
import utility.GetResources;

@SuppressWarnings("serial")
public class GameRoomPanel extends JPanel {
	private BasicFrame basicFrame;
	private String thisUserID;
	private String otherUserID;
	private ImageIcon myImage;
	private ImageIcon myGradeImage;
	
	private JPanel gameBoardPanel;	// ������
	private JButton[][] gameBoardButtons;
	private int[][] gameBoard;
	private int x;
	private int y;
	
	private JPanel omokStonePanel;	// ���� ���� �г�
	
	private JPanel timeLimitPanel;	// �ð����� ǥ��
	private JProgressBar timeBar;
	private JLabel timeLabel;
	private Integer time;
	private boolean isThreadStart;
	
	private JPanel userImagePanel;	// �����̹���
	private JPanel gameMenuPanel;	// ���Ӹ޴� �� ������
	
	private JLabel rightUserId;     // ���� ���̵�
	private JLabel leftUserId;
	private JLabel rightUserLevel;  // ���� ����
	private JLabel leftUserLevel;  
	
	private JPanel chattingPanel;	// ä��
	private JTextField chattingField;
	private JTextArea chattingArea;
	
	private JLabel leftUser;
	private JLabel rightUser;
	private JButton[] menuButtons;
	
	private GameRoomClientAction gameRoomAction;
	private GameRoomInfoVO gameRoomInfo;
	private StonePositionCheck stonePositionCheck;
	
	public GameRoomPanel(BasicFrame basicFrame) {
		this.gameRoomAction = new GameRoomClientAction(this);
		this.stonePositionCheck = new StonePositionCheck();
		this.basicFrame 	= basicFrame;
		this.isThreadStart  = false;
		
		this.omokStonePanel = new JPanel();
		this.timeLimitPanel = new JPanel();
		this.userImagePanel = new JPanel();
		this.gameMenuPanel  = new JPanel();
		this.chattingPanel  = new JPanel();

		this.setLayout(null);
		this.setGameBoard();
		this.setUserImage();
		this.setInGameMenuButtons();
		this.setInGameChattingArea();
		this.setTimeLimit();
		this.setStonePanel();
			
	} // ������
	
	// ���� �� �г� ����
	public void setStonePanel() {
		this.omokStonePanel.setLayout(null);
		this.omokStonePanel.setOpaque(false);
		this.gameBoardButtons = new JButton[15][];
		this.gameBoard 		= new int[15][];
		for(int i = 0, iSize = gameBoardButtons.length; i < iSize; i++) {
			this.gameBoardButtons[i] = new JButton[15];
			this.gameBoard[i] = new int[15];
			for(int j = 0, jSize = gameBoardButtons.length; j < jSize; j++) {
				this.gameBoardButtons[i][j] = new JButton();
				this.gameBoard[i][j] = 0;
				this.gameBoardButtons[i][j].setBounds(new Rectangle(
						j * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						i * GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
						GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height
				));
				
				this.gameBoardButtons[i][j].setName(i + "," + j);
				this.gameBoardButtons[i][j].setContentAreaFilled(false);
				this.gameBoardButtons[i][j].setFocusPainted(false);
				this.gameBoardButtons[i][j].setBorderPainted(false);
				this.omokStonePanel.add(this.gameBoardButtons[i][j]);
			}
		}
		this.omokStonePanel.setBounds(GameRoomEnum.GAME_STONEPANEL_RECT.getRect());
	}
	
	// ���Ӻ��� �г� ����
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
	
	// ���ѽð� �г� ����
	public void setTimeLimit() {
		this.timeLimitPanel.setLayout(null);
		this.timeLimitPanel.setOpaque(false);
		this.timeLimitPanel.setBounds(GameRoomEnum.GAME_TIMELIMIT_PANEL_RECT.getRect());
		
		this.time = 30;
		this.timeBar = new JProgressBar(0, 30);
		timeBar.setValue(time);
		timeBar.setBounds(GameRoomEnum.GAME_TIMELIMIT_PROGRESS_RECT.getRect());
		
		
		this.timeLabel = new JLabel("0:" + time.toString());
		timeLabel.setBounds(GameRoomEnum.GAME_TIMELIMIT_TIMELABEL_RECT.getRect());
		timeLabel.setForeground(Color.red);
		timeLabel.setFont(GameRoomEnum.GAME_TIMELABEL_FONT.getFont());
		
		//�ð��� ���� �پ���. 
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // �ð����� ǥ�� �г�
	
	// �����̹��� ����
	public void setUserImage() {
		this.userImagePanel.setBounds(GameRoomEnum.GAME_USERIMAGE_PANEL_RECT.getRect());
		this.userImagePanel.setLayout(null);
			
		this.leftUser = new JLabel();
		this.leftUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));
		this.leftUser.setBounds(GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect());
		this.leftUser.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.userImagePanel.add(this.leftUser);
		
		this.rightUser = new JLabel();
		this.rightUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));		
		this.rightUser.setBounds(GameRoomEnum.GAME_USERIMAGE_RIGHT_RECT.getRect());
		this.rightUser.setBorder(BorderFactory.createLineBorder(Color.blue, 6));
		this.userImagePanel.add(this.rightUser);
		
		//���� �̹��� �ϴ� ���̵� ����
		this.rightUserId = new JLabel();
		this.rightUserId.setBounds(GameRoomEnum.GAME_USERID_RIGHT_LABEL_RECT.getRect());
		this.rightUserId.setFont(GameRoomEnum.GAMEROOM_USERID_FONT.getFont());
		this.rightUserId.setForeground(GameRoomEnum.GAMEROOM_USERIF_FONT_COLOR.getColor());
		this.userImagePanel.add(this.rightUserId);
		
		this.leftUserId = new JLabel();
		this.leftUserId.setBounds(GameRoomEnum.GAME_USERID_LEFT_LABEL_RECT.getRect());
		this.leftUserId.setForeground(GameRoomEnum.GAMEROOM_USERIF_FONT_COLOR.getColor());
		this.leftUserId.setFont(GameRoomEnum.GAMEROOM_USERID_FONT.getFont());
		this.userImagePanel.add(this.leftUserId);
		
		//���� �̹��� �ϴ� �̹��� ����
		this.rightUserLevel = new JLabel();
		this.rightUserLevel.setBounds(GameRoomEnum.GAME_USERLEVEL_RIGHT_IMAGE_RECT.getRect());
		this.userImagePanel.add(this.rightUserLevel);
		
		this.leftUserLevel = new JLabel();
		this.leftUserLevel.setBounds(GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect());
		this.userImagePanel.add(this.leftUserLevel);
				
		this.userImagePanel.add(this.leftUser);
		this.userImagePanel.add(this.rightUser);
		this.userImagePanel.setOpaque(false);
		this.add(this.userImagePanel);
		
	} // �����̹��� �г�
	
	// �޴���ư ����
	public void setInGameMenuButtons() {
		this.gameMenuPanel.setLayout(null);
		this.gameMenuPanel.setOpaque(false);
		this.gameMenuPanel.setBounds(GameRoomEnum.GAME_MENU_PANEL_RECT.getRect());
		String[] buttonsName = GameRoomEnum.GAME_BUTTONNAME_OWNER.getButtonName();
		String[] buttonsDir = ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_OWNER.getImages();
		this.menuButtons = new JButton[buttonsName.length];
		
		for(int i = 0, size = buttonsName.length; i < size; i++) {
			this.menuButtons[i] = new JButton(this.getButtonImageIcon(buttonsDir[i]));
			
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
			if(this.menuButtons[i].getName().equals("exit")) {
				this.menuButtons[i].addMouseListener(this.gameRoomAction);
			}
			this.gameMenuPanel.add(this.menuButtons[i]);
		}
		this.add(this.gameMenuPanel);
	} // ���Ӹ޴� ��  ������ �г�
	
	// ä�ÿ��� ����
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

	// ���ӹ濡 ������ ������ ���� ����
	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		this.gameRoomInfo = inGameUserInfo.getGameRoomInfo();
		this.thisUserID = this.basicFrame.getUserID();
		try {
			Map<String, String> gradeImageMap = ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap();
			String imageDir = null;
			this.myGradeImage = this.getUserLevelIcon(gradeImageMap.get(inGameUserInfo.getUserGameData().getUserGrade()));
			// ������ ���
			if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
				imageDir 		 = ImageEnum.GAMEROOM_START.getImageDir();
				this.leftUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
				this.leftUserId.setText(this.thisUserID);
				this.leftUserLevel.setIcon(myGradeImage);
				
				this.otherUserID = this.gameRoomInfo.getGuest();
				this.myImage 	 = inGameUserInfo.getUserGameData().getUserGameRoomImage();
			// �Խ�Ʈ�� ���
			} else if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getGuest())) {
				this.chattingField.setEditable(true);
				imageDir = ImageEnum.GAMEROOM_READY.getImageDir();
				this.rightUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
				this.rightUserId.setText(this.thisUserID);
				this.rightUserLevel.setIcon(myGradeImage);
				
				String ownerImageDir = inGameUserInfo.getOwnerGender() == 1 ? 
						ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
				
				// �����ư�� �׼ǵ��!!!
				this.menuButtons[0].setName("ready");
				this.menuButtons[0].addMouseListener(this.gameRoomAction);
				
				this.otherUserID = this.gameRoomInfo.getOwner();
				this.leftUser.setIcon(this.getUserImageIcon(ownerImageDir));
				this.leftUserId.setText(this.otherUserID);
				this.leftUserLevel.setIcon(GetResources.getImageIcon(
						gradeImageMap.get(inGameUserInfo.getOtherGameData().getUserGrade()), 
						GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().width, 
						GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().height));
				this.myImage 	 = inGameUserInfo.getUserGameData().getUserGameRoomImage();
			}
			this.addTextNotice("���ӹ濡 �����Ͽ����ϴ�.");
			this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ������ ��� �濡 ������ ������ �� �����
	public void ownerFrameModify(UserInGameRoomDTO userInGameRoomDTO) {
		Map<String, String> gradeImageMap = ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap();
		
		this.gameRoomInfo = userInGameRoomDTO.getGameRoomInfo();
		this.chattingField.setEditable(true);
		this.otherUserID = this.gameRoomInfo.getGuest();
		String imageDir = userInGameRoomDTO.getGuestGender() == 1 ? 
				ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
		
		this.rightUser.setIcon(this.getUserImageIcon(imageDir));
		this.rightUserId.setText(this.otherUserID);
		this.rightUserLevel.setIcon(GetResources.getImageIcon(
				gradeImageMap.get(userInGameRoomDTO.getOtherGameData().getUserGrade()), 
				GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().width, 
				GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().height));
		this.addTextNotice(this.gameRoomInfo.getGuest() + "�Բ��� �����ϼ̽��ϴ�.");
	}
	
	// ä�� �Է½� ������ ����
	public void chattingInfoSendServer() {
		UserMessageVO userMessage = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
		userMessage.setUserAction(UserActionEnum.USER_IN_GAME_ROOM_CHATTING);
		userMessage.setUserID(this.thisUserID);
		userMessage.setTargetID(this.otherUserID);
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
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_CH.getImageDir()));
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
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_GO.getImageDir()));
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
		String imageDir = check ? ImageEnum.GAMEROOM_READY_CH.getImageDir() : ImageEnum.GAMEROOM_READY.getImageDir() ;
		UserActionEnum userAction = check ? UserActionEnum.USER_GUEST_READY_CHECK : UserActionEnum.USER_GUEST_READY_DECHECK ;
	
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		
		// ������ ���ۿ� ��ü�� ���� ����� �ʿ� ������ ������ �� �Խ�Ʈ�� ���� �ߴٴ� ������ ������ �����Ѵ�.
		GameRoomInfoVO gameRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomVO.setUserAction(userAction);
		gameRoomVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomVO.setOwner(this.gameRoomInfo.getOwner());
		this.basicFrame.sendDTO(gameRoomVO);	
	}
		
	// �Խ�Ʈ�� ���� ������ �� �������� ������ ������ ������ ��ŸƮ ��ư ������ �ٲ��ش�. (��ŸƮ ��ư Ŭ���� �����ϰ� ��.)
	public void changeStartGuestReadyCheck(AbstractEnumsDTO data) {
		String imageDir = null;
		if(data.getServerAction() == ServerActionEnum.GAME_ROOM_GUEST_READY_CHECK) {
			imageDir = ImageEnum.GAMEROOM_START_CH.getImageDir();
			this.menuButtons[0].addMouseListener(this.gameRoomAction);
		} else {
			imageDir = ImageEnum.GAMEROOM_START.getImageDir();
			this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		}
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
	}
	
	// ���� �����Ѵٴ� ������ �������� ������. 
	public void startReadyButtonRemoveAction() {
		GameRoomInfoVO gameRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfoVO.setUserAction(UserActionEnum.USER_GAME_START);
		gameRoomInfoVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomInfoVO.setOwner(this.gameRoomInfo.getOwner());
		
		this.basicFrame.sendDTO(gameRoomInfoVO);
	}
	
	// ���ʰ� ���ӽ��� ��ư�� ������ [0]��° ���� ��ư(����)�� �̹����� �ʱ�ȭ�ϰ� �׼��� �����Ѵ�.
	// ��ǹ�ư�� �׼��� Ȱ��ȭ��Ų��.
	// �������� ȸ���� ���� �Խ�Ʈ�� �� �޼ҵ带 �����ϸ� ���� ��ư�� ���� �׼��� �����Ѵ�.
	public void gameStart() {
		// �̹����� �ʱ�ȭ�ϰ� �׼ǻ���
		String imageDir = this.getBasicFrame().getUserID().equals(this.gameRoomInfo.getOwner()) ?
				ImageEnum.GAMEROOM_START_BATTILING.getImageDir() : ImageEnum.GAMEROOM_START_BATTILING.getImageDir();
		
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		this.menuButtons[1].addMouseListener(this.gameRoomAction);
		this.chattingArea.setText(this.chattingArea.getText() + "\n<������ �����մϴ�.>");
		
		GetResources.soundPlay(SoundEnum.GAME_START_SOUND.getSound());
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// ���� ���� ���� (�浹)
		if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
			this.thisUserTurn(SoundEnum.GAME_BLACK_TURN.getSound());
		}
	}
	
	// �������� ���� �������� �� �۵�
	// ���� ���� ����� �� ������ ������ ���۵Ǿ��� ��
	// ������ �� ������ �ش� ���� �����鿡�� �������� �� �� �޼ҵ�� ������ �ȴ�.
	public void boardSettingAndMyTurnStart(AbstractEnumsDTO data) {
		GameBoardVO gameBoardVO = (GameBoardVO)data;
		this.gameBoard = gameBoardVO.getGameBoard();
		this.stoneImageSetting(x, y);
		// �������� ������ ���� �� ���� ���� ���� �������
		// (���� ���� ���� ������ �ƴ϶�� ���� ^^)
		if(this.thisUserID.equals(gameBoardVO.getNowTurnUser())) {
			// �׷��� ���� ���ʶ��
			if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
				this.thisUserTurn(SoundEnum.GAME_BLACK_TURN.getSound());
			// ���� �Խ�Ʈ���
			} else {
				this.thisUserTurn(SoundEnum.GAME_WHITE_TURN.getSound());
			}
			
		// ������ �Ͽ��� ���� ���� �Ҹ��� �� �� �ְԲ� else ó��.
		} else {
			GetResources.soundPlay(this.thisUserID.equals(this.gameRoomInfo.getOwner()) ? 
					SoundEnum.GAME_WHITE_TURN.getSound() : SoundEnum.GAME_BLACK_TURN.getSound());
		}
	}

	// ���� ���� ���̶�� �����带 ���ߴ� �÷��׸� true�� �ٲ��ְ�
	// �ð��� �����ִ� �����带 ������Ű��
	// ���带 �������ְ� �����гο� �׼Ǹ����ʸ� ��Ͻ����ش�.
	public void thisUserTurn(String soundDir) {
		this.isThreadStart = true;
		this.gameBoardPanelAddAction();
		GetResources.soundPlay(soundDir);
		this.timeLimitThread();
	}

	// ���Ӻ��忡 �׼��� �߰��Ѵ�.
	public void gameBoardPanelAddAction() {
		for(int i = 0, iSize = this.gameBoardButtons.length; i < iSize; i++) {
			for(int j = 0, jSize = this.gameBoardButtons[0].length; j < jSize; j++) {
				if(this.gameBoard[i][j] == 0) {
					this.gameBoardButtons[i][j].addMouseListener(this.gameRoomAction);
				}
			}
		}
	}
	
	// ���Ӻ��忡�� �׼��� �����Ѵ�.
	public void gameBoardPanelDeleteAction() {
		for(int i = 0, iSize = this.gameBoardButtons.length; i < iSize; i++) {
			for(int j = 0, jSize = this.gameBoardButtons[0].length; j < jSize; j++) {
				this.gameBoardButtons[i][j].removeMouseListener(this.gameRoomAction);
			}
		}
	}
	
	// ���Ӻ��忡 �̹����� �߰��Ѵ�. TODO �������� ǥ���ϰ� �����ô�.
	public void stoneImageSetting(int x, int y) {
		for (int i = 0, iLen = this.gameBoard.length; i < iLen; i++) {
			for (int j = 0, jLen = gameBoard[i].length; j < jLen; j++) {
				String imageDir = null;
				if(this.gameBoard[i][j] == 1) {
					imageDir = ImageEnum.GAMEROOM_STONE_ROUND_PIKA.getImageDir();
					this.gameBoardButtons[i][j].setIcon(this.getStoneImageIcon(imageDir));
				} else if(this.gameBoard[i][j] == 2) {
					imageDir = ImageEnum.GAMEROOM_STONE_ROUND_KOBOOK.getImageDir();
					this.gameBoardButtons[i][j].setIcon(this.getStoneImageIcon(imageDir));
				}
			}
		}
		
		this.gameBoardButtons[x][y].setBorder(BorderFactory.createLineBorder(Color.red, 100));
	}
	
	// ���� ����Ǹ� (������ ���� ������)
	// ���� ���� �ڸ��� ���Ӻ��� �迭���� �ٲ��ְ�
	// �ð� �����带 ��������ְ�
	// ���Ӻ��� �迭���� ���� ���Ӻ��� �̹����� �ٲ��ش�.
	public void turnEnd(int x, int y) {
		GetResources.soundPlay(SoundEnum.GAME_STONE_DROP.getSound());
		this.gameBoard[x][y] = this.thisUserID.equals(this.gameRoomInfo.getOwner()) ? 1 : 2;
		this.x = x;
		this.y = y;
		this.isThreadStart = false;
		this.stoneImageSetting(x, y);
	}
	
	// ���� ���� ��ǹ�ư�� Ŭ���Ǹ� �����.
	public void clickWithDraw() {
		if(new ConfirmDialog(this.basicFrame, "����Ͻðڽ��ϱ�?").isYesNoCheck()) {
			GameBoardVO gameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
			gameBoardVO.setUserAction(UserActionEnum.USER_GAME_BOARD_INFO);
			gameBoardVO.setLoseUser(this.thisUserID);
			gameBoardVO.setWinUser(this.otherUserID);
			gameBoardVO.setGameBoard(this.gameBoard);
			gameBoardVO.setNowTurnUser(this.thisUserID);
			gameBoardVO.setNextTurnUser(this.otherUserID);
			
			this.basicFrame.sendDTO(gameBoardVO);
		}
	}

	// �� ������ �̱� ��� ������ ����ȴ�.
	// �̱� ������ �����ڿ� ���ٸ� �¸��ϼ̽��ϴ� �޼����� ����ְ� �� ������ ���ٸ� �й��ϼ̽��ϴ� �޼��� ����ش�.
	// for���� ���� ���� �����ϰ� ������� �ʱ� �빮�� setVisible ���� ������ �ʰ� �� �� for������ �۾��ϰ� �ٽ� true�� �ش�.
	// �ʱ�ȭ �ؾ� �� ���� �ִٸ� �ʱ�ȭ �ϰ� �Խ�Ʈ ������ ���� �������ش�.
	public void gameEnd(AbstractEnumsDTO data) {
		GameBoardVO gameBoardVO = (GameBoardVO)data;
		this.gameBoard = gameBoardVO.getGameBoard();
		this.x = gameBoardVO.getX();
		this.y = gameBoardVO.getY();

		if(gameBoardVO.getWinUser().equals(this.thisUserID)) {
			new GameEndDialog(this.basicFrame,"�¸��ϼ̽��ϴ� :D");
		} else {
			this.stoneImageSetting(x, y);
			new GameEndDialog(this.basicFrame, "�й��Ͽ����ϴ� :<");
		}
		
		this.stoneInit();
		
		this.x = 0;
		this.y = 0;
		
		this.menuButtons[1].removeMouseListener(this.gameRoomAction);
		
		if(this.thisUserID.equals(this.gameRoomInfo.getGuest())) {
			this.menuButtons[0].addMouseListener(this.gameRoomAction);
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_READY.getImageDir()));
		} else {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START.getImageDir()));
		}
		
	}
	
	// ������ �� �ʱ�ȭ
	public void stoneInit() {
		this.omokStonePanel.setVisible(false);
		
		for (int i = 0, iLen = gameBoard.length; i < iLen; i++) {
			for (int j = 0, jLen = gameBoard[0].length; j < jLen; j++) {
				this.gameBoard[i][j] = 0;
				this.gameBoardButtons[i][j].setIcon(null);
			}
		}
		
		this.omokStonePanel.setVisible(true);
	}
	
	// ������ ��ư�� ������ �� TODO RoomExit
	public void exitGame() {
		// �ߴ� �˾�â���� Yes �� ���� ���
		if(new ConfirmDialog(this.basicFrame, "���ӹ��� �����ðڽ��ϱ�?").isYesNoCheck()) {
			GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
			gameRoomInfo.setUserAction(UserActionEnum.USER_GAME_ROOM_EXIT);
			gameRoomInfo.setRoomNumber(this.gameRoomInfo.getRoomNumber());
			gameRoomInfo.setRoomName(this.gameRoomInfo.getRoomName());
			
			// ���ӹ濡 2���� ��� ���� ���
			if(this.gameRoomInfo.getPersonNum() == 2) {
				gameRoomInfo.setPersons(1);
				// ������ ���
				if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
					gameRoomInfo.setOwner(this.otherUserID);
				
				// �Խ�Ʈ�� ���
				} else {
					gameRoomInfo.setOwner(this.otherUserID);
				}
					
				// ���ӹ� �г��� �����̹����� �� �̹��� �ʱ�ȭ
				this.setUserImage();
				this.stoneInit();
			
			// ���ӹ濡 ȥ�� �־��� ���
			} else {
				gameRoomInfo.setOwner(null);
				this.setUserImage();
				gameRoomInfo.setPersons(0);
			}
			// �������� ���� ���� �����ڴٴ� ������ ������. 
			this.basicFrame.sendDTO(gameRoomInfo);
			
			// ���Ƿ� ����.
			this.basicFrame.showWaitingRoom();
		}
	}
	
	// �ٸ� ������ ���� ������ �������� ������ �ٲ� �������� �� ���ӹ������� �����ϰ�
	// ������ �̹����� �� �̹����� �� �̹����� �����ϰ�
	// �޴���ư ù������ ���۹�ư���� �ٲٰ� ��ϵ� �׼��� �����Ѵ�.
	public void otherUserExitGame(AbstractEnumsDTO data) {
		this.addTextNotice(otherUserID + " ���� ���� �������ϴ�.");
		this.otherUserID = null;
		
		GameRoomInfoVO gameRoomInfo = (GameRoomInfoVO)data;
		this.gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		this.gameRoomInfo.setGuest(null);
		this.gameRoomInfo.setOwner(this.thisUserID);
		this.gameRoomInfo.setPersons(1);
		this.gameRoomInfo.setRoomName(gameRoomInfo.getRoomName());
		this.gameRoomInfo.setRoomNumber(gameRoomInfo.getRoomNumber());
		
		this.leftUser.setIcon(this.myImage);
		this.leftUserId.setText(this.thisUserID);
		this.leftUserLevel.setIcon(this.myGradeImage);
		// TODO ���Ⱑ �ȵ�
		this.rightUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));
		this.rightUserId.setText(null);
		this.rightUserLevel.setIcon(null);

		String imageDir = ImageEnum.GAMEROOM_START.getImageDir();
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].setName(GameRoomEnum.GAME_BUTTONNAME_OWNER.getButtonName()[0]);
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
	}
	
	public void timeLimitThread() {
		new Thread() {
			@Override
			public void run() {
				// 30�ʵ��� 1�� 1�� �پ���. �ð��κа� ���α׷����ٸ� �پ��� �ð��� ���߾� �����Ų��.
				// �ð��� ��� �����ų� ������ ���Ӻ��忡 ���� ������(isThreadStart�� false�� �Ǹ�)
				// ���� ������ ���콺�����ʸ� �����ϰ� ���� ���Ӻ��� ������ ������ �����ϸ� ���� �����Ѵ�.
				for(int i = time; i >= 0 && isThreadStart; i--) {
					timeBar.setValue(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeLabel.setText("0:" + String.valueOf(i));
				}
				timeBar.setValue(0);
				timeLabel.setText("0:00");
				// �����尡 ������ (�ð��� �� ���ų� ������ ���� ���Ұų�) - turnEnd ���� falseó���� �Ǹ�
				// ȭ�鿡 ��ϵ� �׼ǵ��� �����Ѵ�. (Ŭ���� �Ұ����ϰ� ����)
				gameBoardPanelDeleteAction();
				// ���Ӻ���VO �� ���� ������ ������ �������� ������ ���Ӻ��忡 ���� �� ������ �̱� ���� ������ ��� ������.
				// (���� ������ ���� ��ġ�� Ž���Ͽ� 5���� �̾����� ���������� �Ǵ��Ѵ�.)
				GameBoardVO gameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
				gameBoardVO.setUserAction(UserActionEnum.USER_GAME_BOARD_INFO);
				gameBoardVO.setNowTurnUser(thisUserID);
				gameBoardVO.setNextTurnUser(gameRoomInfo.getOwner().equals(thisUserID) ? gameRoomInfo.getGuest() : gameRoomInfo.getOwner());
				gameBoardVO.setGameBoard(gameBoard);
				gameBoardVO.setX(x);
				gameBoardVO.setY(y);
				gameBoardVO.setWinUser(
					stonePositionCheck.stoneDiagonalLeftCheck(x, y, gameBoard) < 5 ? 
						(stonePositionCheck.stoneDiagonalRightCheck(x, y, gameBoard) < 5 ? 
							(stonePositionCheck.stoneHeightCheck(x, y, gameBoard) < 5 ? 
								(stonePositionCheck.stoneWidthCheck(x, y, gameBoard) < 5 ? null : thisUserID) 
							: thisUserID) 
						: thisUserID) 
					: thisUserID);
				if(gameBoardVO.getWinUser() != null) {
					gameBoardVO.setLoseUser(otherUserID);
				}
				basicFrame.sendDTO(gameBoardVO);
			}
		}.start();
	}
	
	// ���� �̹��� ����
	public ImageIcon getUserImageIcon(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height);
	}
	
	// ���� ���� �̹��� ����
	public ImageIcon getUserLevelIcon(String imageDir) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(
				new File(imageDir)).getScaledInstance(
					GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().width, 
					GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().height, 
					Image.SCALE_AREA_AVERAGING)
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
	
	// ��ư �̹��� ����
	public ImageIcon getButtonImageIcon(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width, 
				GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height);
	}
	
	// �� �̹��� ����
	public ImageIcon getStoneImageIcon(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width, 
				GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height);
	}	

	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
	
	public void setThreadStart(boolean isThreadStart) {
		this.isThreadStart = isThreadStart;
	}
	
	public void addTextNotice(String message) {
		this.chattingArea.setText(this.chattingArea.getText() + "\n" + message);
	}
}