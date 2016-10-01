package frames.gameRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

@SuppressWarnings("serial")
public class GameRoomPanel extends JPanel {
	private BasicFrame basicFrame;
	private String thisUserID;
	private String otherUserID;
	
	private JPanel gameBoardPanel;	// 오목판
	private JButton[][] gameBoardButtons;
	private int[][] gameBoard;
	
	private JPanel omokStonePanel;	// 돌을 놓을 패널
	private JPanel timeLimitPanel;	// 시간제한 표시
	private JPanel userImagePanel;	// 유저이미지
	private JPanel gameMenuPanel;	// 게임메뉴 및 아이템
	
	private JPanel chattingPanel;	// 채팅
	private JTextField chattingField;
	private JTextArea chattingArea;
	
	private Thread timeLimitThread;
	private boolean isThreadStart;
	
	private JLabel leftUser;
	private JLabel rightUser;
	private JButton[] menuButtons;
	
	private GameRoomClientAction gameRoomAction;
	private GameRoomInfoVO gameRoomInfo;
	
	public GameRoomPanel(BasicFrame basicFrame) throws IOException {
		this.gameRoomAction = new GameRoomClientAction(this);		
		this.basicFrame 	= basicFrame;
		this.isThreadStart  = true;
		
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
			
	} // 생성자
	
	// 오목 돌 패널 세팅
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
	
	// 게임보드 패널 세팅
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
	
	// 제한시간 패널 세팅
	public void setTimeLimit() {
		this.timeLimitPanel.setLayout(null);
		this.timeLimitPanel.setOpaque(false);
		this.timeLimitPanel.setBounds(GameRoomEnum.GAME_TIMELIMIT_PANEL_RECT.getRect());
		
		Integer time = 30;
		JProgressBar timeBar = new JProgressBar(0, 30);
		timeBar.setValue(time);
		timeBar.setBounds(GameRoomEnum.GAME_TIMELIMIT_PROGRESS_RECT.getRect());
		
		
		JLabel timeLabel = new JLabel("0:" + time.toString());
		timeLabel.setBounds(GameRoomEnum.GAME_TIMELIMIT_TIMELABEL_RECT.getRect());
		timeLabel.setForeground(Color.red);
		timeLabel.setFont(GameRoomEnum.GAME_TIMELABEL_FONT.getFont());
		
		//시간이 점점 줄어든다.
		this.timeLimitThread = new Thread() {
			@Override
			public void run() {
				// 30초동안 1초 1초 줄어든다. 시간부분과 프로그레스바를 줄어드는 시간에 맞추어 변경시킨다.
				// 시간이 모두 지나거나 유저가 게임보드에 돌을 놓으면(isThreadStart가 false가 되면)
				// 현재 유저의 마우스리스너를 삭제하고 현재 게임보드 정보를 서버로 전송하며 턴을 종료한다.
				for(int i = time; i >= 0 && isThreadStart; i--) {
					System.out.println("쓰레드 돌고있따..." + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeBar.setValue(i);
					timeLabel.setText("0:" + String.valueOf(i));
				}
				gameBoardPanelDeleteAction();
				
				GameBoardVO gameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
				gameBoardVO.setUserAction(UserActionEnum.USER_GAME_BOARD_INFO);
				gameBoardVO.setNowTurnUser(thisUserID);
				gameBoardVO.setNextTurnUser(gameRoomInfo.getOwner().equals(thisUserID) ? gameRoomInfo.getOwner() : gameRoomInfo.getGuest());
				gameBoardVO.setGameBoard(gameBoard);
				
				basicFrame.sendDTO(gameBoardVO);
			}
		};
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // 시간제한 표시 패널
	
	// 유저이미지 세팅
	public void setUserImage() throws IOException {
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
		
		this.userImagePanel.add(this.leftUser);
		this.userImagePanel.add(this.rightUser);
		this.add(this.userImagePanel);
		
	} // 유저이미지 패널
	
	// 메뉴버튼 세팅
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
			if(!this.menuButtons[i].getName().equals("start")) {
				this.menuButtons[i].addMouseListener(this.gameRoomAction);
			}
			this.gameMenuPanel.add(this.menuButtons[i]);
		}
		
		this.add(this.gameMenuPanel);
	} // 게임메뉴 및  아이템 패널
	
	// 채팅영역 세팅
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

	// 게임방에 접속한 유저의 정보 세팅
	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		this.gameRoomInfo = inGameUserInfo.getGameRoomInfo();
		this.thisUserID = this.basicFrame.getUserID();
		try {
			String imageDir = null;
			
			// 오너인 경우
			if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
				imageDir = ImageEnum.GAMEROOM_START_GRAY.getImageDir();
				this.leftUser.setIcon(inGameUserInfo.getUserGameData().getUserGameRoomImage());
			
			// 게스트인 경우
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
				this.otherUserID = this.gameRoomInfo.getOwner();
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
		userMessage.setTargetID(this.otherUserID);
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
	public void startReadyButtonRemoveAction() {
		GameRoomInfoVO gameRoomInfoVO = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
		gameRoomInfoVO.setUserAction(UserActionEnum.USER_GAME_START);
		gameRoomInfoVO.setGuest(this.gameRoomInfo.getGuest());
		gameRoomInfoVO.setOwner(this.gameRoomInfo.getOwner());
		
		this.basicFrame.sendDTO(gameRoomInfoVO);
	}
	
	// 오너가 게임시작 버튼을 누르면 [0]번째 방의 버튼(시작)의 이미지를 초기화하고 액션을 삭제한다.
	// 서버에서 회신이 오면 게스트도 이 메소드를 생성하며 레디 버튼에 같은 액션을 실행한다.
	public void gameStart() {
		// 이미지를 초기화하고 액션삭제
		String imageDir = this.getBasicFrame().getUserID().equals(this.gameRoomInfo.getOwner()) ?
				ImageEnum.GAMEROOM_START_GRAY.getImageDir() : ImageEnum.GAMEROOM_READY_GRAY.getImageDir();
		
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		
		this.chattingArea.setText(this.chattingArea.getText() + "\n<게임을 시작합니다.>");
		
		this.SoundPlay(SoundEnum.GAME_START_SOUND.getSound());
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 오너 먼저 시작 (흑돌)
		if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
			this.thisUserTurn(SoundEnum.GAME_BLACK_TURN.getSound());
		} else {
			this.thisUserTurn(SoundEnum.GAME_WHITE_TURN.getSound());
		}
	}
	
	public void thisUserTurn(String soundDir) {
		System.out.println("thisUserTurn");
		this.gameBoardPanelAddAction();
		this.SoundPlay(soundDir);
		this.timeLimitThread.start();
	}

//	public void guestTurn() {
//		this.gameBoardPanelAddAction();
//		this.SoundPlay(SoundEnum.GAME_WHITE_TURN.getSound());
//		this.timeLimitThread.start();
//	}
	
	// 게임보드에 액션을 추가한다.
	public void gameBoardPanelAddAction() {
		for(int i = 0, iSize = this.gameBoardButtons.length; i < iSize; i++) {
			for(int j = 0, jSize = this.gameBoardButtons[0].length; j < jSize; j++) {
				this.gameBoardButtons[i][j].addMouseListener(this.gameRoomAction);
			}
		}
	}
	
	// 게임보드에서 액션을 삭제한다.
	public void gameBoardPanelDeleteAction() {
		for(int i = 0, iSize = this.gameBoardButtons.length; i < iSize; i++) {
			for(int j = 0, jSize = this.gameBoardButtons[0].length; j < jSize; j++) {
				this.gameBoardButtons[i][j].removeMouseListener(this.gameRoomAction);
			}
		}
	}
	
	public void stoneImageSetting(int x, int y) {
		for (int i = 0, iLen = this.gameBoard.length; i < iLen; i++) {
			for (int j = 0, jLen = gameBoard[i].length; j < jLen; j++) {
				this.gameBoardButtons[i][j].setIcon(this.getButtonImageIcon(this.gameBoard[i][j] == 1 ? 
					ImageEnum.GAMEROOM_STONE_PIKACHU.getImageDir() : ImageEnum.GAMEROOM_STONE_CHARMANDER.getImageDir()
				));
			}
		}
	}
	
	// 돌을 놓으면 게임보드 위치 세팅한 후 쓰레드 종료시키러 떠난다.(턴 종료) TODO
	public void turnEnd(int x, int y) {
		System.out.println("turnEnd");
		this.gameBoard[x][y] = this.thisUserID.equals(this.gameRoomInfo.getOwner()) ? 3 : 4;
		this.isThreadStart = false;
	}
	
	// 유저 이미지 세팅
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
	
	// 버튼 이미지 세팅
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
	
	// 돌 이미지 세팅
	public ImageIcon getStoneImageIcon(String imageDir) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(
						ImageIO.read(new File(imageDir)).getScaledInstance(
							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().width,
							GameRoomEnum.GAME_STONE_LOCATION_RECT.getRect().height, 
							Image.SCALE_AREA_AVERAGING)
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
	
	// 사운드 세팅
	public void SoundPlay(String soundDir) {
		try {
			AudioInputStream gameStartSound = AudioSystem.getAudioInputStream(new File(soundDir));
			Clip clip = AudioSystem.getClip();
			
			clip.stop();
			clip.open(gameStartSound);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
	
	public void setThreadStart(boolean isThreadStart) {
		this.isThreadStart = isThreadStart;
	}
}