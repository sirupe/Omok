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
	
	private JPanel gameBoardPanel;	// 오목판
	private JPanel omokStonePanel;	// 돌을 놓을 패널
	private JPanel timeLimitPanel;	// 시간제한 표시
	private JPanel userImagePanel;	// 유저이미지
	private JPanel gameMenuPanel;	// 게임메뉴 및 아이템
	
	private JPanel chattingPanel;	// 채팅
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
			
	} // 생성자
	
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
	} // 오목판 패널
	
	
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
	} // 시간제한 표시 패널
	
	
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
		
	} // 유저이미지 패널
	
	
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
	} // 게임메뉴 및  아이템 패널
	
	
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
	} // 채팅 패널

	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		this.gameRoomInfo = inGameUserInfo.getGameRoomInfo();
		this.thisUserID = this.basicFrame.getUserID();
		try {
			System.out.println("고객들어왔다고");
			String imageDir = null;
			
			// 오너인 경우
			if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
				imageDir = ImageEnum.GAMEROOM_START_GRAY.getImageDir();
				this.leftUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
			
			// 유저인 경우
			} else if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getGuest())) {
				this.chattingField.setEditable(true);
				imageDir = ImageEnum.GAMEROOM_READY_GRAY.getImageDir();
				this.rightUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
				
				String ownerImageDir = inGameUserInfo.getOwnerGender() == 1 ? 
						ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
				
				// 레디버튼에 액션등록!!!
				this.menuButtons[0].setName("ready");
				this.menuButtons[0].addMouseListener(this.gameRoomAction);
				
				this.leftUser.setIcon(this.getUserImageIcon(ownerImageDir));
			}
			
			this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 오너의 경우 방에 유저가 들어왔을 때 실행됨
	public void ownerFrameModify(UserInGameRoomDTO userInGameRoomDTO) {
		this.gameRoomInfo = userInGameRoomDTO.getGameRoomInfo();
		this.chattingField.setEditable(true);
		
		String imageDir = userInGameRoomDTO.getGuestGender() == 1 ? 
				ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
		
		this.rightUser.setIcon(this.getUserImageIcon(imageDir));
	}
	
	// 채팅 입력시 서버로 전송
	public void chattingInfoSendServer() {
		UserMessageVO userMessage = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
		userMessage.setUserAction(UserActionEnum.USER_IN_GAME_ROOM_CHATTING);
		userMessage.setUserID(this.thisUserID);
		userMessage.setTargetID(this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
				this.gameRoomInfo.getGuest() : this.gameRoomInfo.getOwner());
		userMessage.setMessage(this.chattingField.getText());
		this.basicFrame.sendDTO(userMessage);
	}
	
	// 채팅에 관련한 서버에서의 응답
	public void chattingAreaSetting(AbstractEnumsDTO data) {
		this.chattingField.setText("");
		UserMessageVO messageVO = (UserMessageVO)data;
		this.chattingArea.setText(this.chattingArea.getText() + "\n" + messageVO.getMessage());
	}
	
	// 메뉴 영역 밖으로 마우스 포인터가 빠짐
	public void changeButtonGrayImage(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_COLOR.getImageDir()));
		} else {
			String[] buttonImages = this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
					ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_OWNER.getImages() : ImageEnum.GAMEROOM_MENU_IMAGES_GRAY_GUEST.getImages();
			// 시작 혹은 레디버튼 제외
			for(int i = 1, size = this.menuButtons.length; i < size; i++) {
				if(this.menuButtons[i].getName().equals(buttonName)) {
					this.menuButtons[i].setIcon(this.getButtonImageIcon(buttonImages[i]));
					break;
				}
			}
			
		}
	}
	
	// 메뉴 영역 안으로 마우스 포인터가 진입
	public void changeButtonColorImage(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_LAST_COLOR.getImageDir()));
		} else {
			String[] buttonImages = this.gameRoomInfo.getOwner().equals(this.thisUserID) ? 
					ImageEnum.GAMEROOM_MENU_IMAGES_COLOR_OWNER.getImages() : ImageEnum.GAMEROOM_MENU_IMAGES_COLOR_GUEST.getImages();
			// 시작 혹은 레디버튼 제외
			for(int i = 1, size = this.menuButtons.length; i < size; i++) {
				if(this.menuButtons[i].getName().equals(buttonName)) {
					this.menuButtons[i].setIcon(this.getButtonImageIcon(buttonImages[i]));
					
					break;
				}
			}
		}
	}
	
	// 레디 버튼에 클릭 이벤트가 들어오면
	public void changeGameReadyButton(boolean check) {
		String imageDir = check ? ImageEnum.GAMEROOM_READY_COLOR.getImageDir() : ImageEnum.GAMEROOM_READY_GRAY.getImageDir() ;
		UserActionEnum userAction = check ? UserActionEnum.USER_GUEST_READY_CHECK : UserActionEnum.USER_GUEST_READY_DECHECK ;
	
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		
		// 데이터 전송용 객체를 새로 만들어 필요 정보를 저장한 후 게스트가 레디를 했다는 정보를 서버로 전송한다.
		GameRoomInfoVO gameRoomVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomVO.setUserAction(userAction);
		gameRoomVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomVO.setOwner(this.gameRoomInfo.getOwner());
		this.basicFrame.sendDTO(gameRoomVO);	
	}
	
	// 게스트가 레디버튼을 눌러대면 오너의 스타트버튼의 색깔이 바뀐다.
	
	//TODO
	// 게스트가 레디를 눌렀을 때 서버에서 정보가 들어오면 오너의 스타트 버튼 색깔을 바꿔준다. (스타트 버튼 클릭이 가능하게 함.)
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
	
	// 게임 시작한다는 정보를 서버에게 보낸다.
	public void buttonRemoveAction() {
		GameRoomInfoVO gameRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfoVO.setUserAction(UserActionEnum.USER_GAME_START);
		gameRoomInfoVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomInfoVO.setOwner(this.gameRoomInfo.getOwner());
		
		this.basicFrame.sendDTO(gameRoomInfoVO);
		
	}
	
	
	// 오너가 게임시작 버튼을 누르면 [0]번째 방의 버튼(시작)의 이미지를 초기화하고 액션을 삭제한다.
	// 서버에서 회신이 오면 게스트도 이 메소드를 생성하며 레디 버튼에 같은 액션을 실행한다.
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