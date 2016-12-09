package frames.gameRoom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
import javax.swing.JScrollBar;
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
	
	private JPanel gameBoardPanel;	// 오목판
	private JButton[][] gameBoardButtons;
	private int[][] gameBoard;
	private int x;
	private int y;
	
	private JPanel omokStonePanel;	// 돌을 놓을 패널
	
	private JPanel timeLimitPanel;	// 시간제한 표시
	private JProgressBar timeBar;
	private JLabel timeLabel;
	private Integer time;
	private boolean isThreadStart;
	
	private JPanel userImagePanel;	// 유저이미지
	private JPanel gameMenuPanel;	// 게임메뉴 및 아이템
	
	private JLabel rightUserId;     // 유저 아이디
	private JLabel leftUserId;
	private JLabel rightUserLevel;  // 유저 레벨
	private JLabel leftUserLevel;  
	
	private JPanel chattingPanel;	// 채팅
	private JTextField chattingField;
	private JTextArea chattingArea;
	
	private JLabel leftUser;
	private JLabel rightUser;
	private JButton[] menuButtons;
	
	private GameRoomClientAction gameRoomAction;
	private GameRoomInfoVO gameRoomInfo;
	private StonePositionCheck stonePositionCheck;
	private boolean isStoneClickCheck;
	private boolean isGameIngCheck;
	
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
		
		this.time = 30;
		this.timeBar = new JProgressBar(0, 30);
		this.timeBar.setValue(time);
		this.timeBar.setBounds(GameRoomEnum.GAME_TIMELIMIT_PROGRESS_RECT.getRect());
		
		
		this.timeLabel = new JLabel("0:" + time.toString());
		this.timeLabel.setBounds(GameRoomEnum.GAME_TIMELIMIT_TIMELABEL_RECT.getRect());
		this.timeLabel.setForeground(Color.red);
		this.timeLabel.setFont(GameRoomEnum.GAME_TIMELABEL_FONT.getFont());
		
		//시간이 점점 줄어든다. 
		
		this.timeLimitPanel.add(timeLabel);
		this.timeLimitPanel.add(timeBar);
		this.add(this.timeLimitPanel);
	} // 시간제한 표시 패널
	
	// 유저이미지 세팅
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
		
		//유저 이미지 하단 아이디 세팅
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
		
		//유저 이미지 하단 이미지 세팅
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
			if(this.menuButtons[i].getName().equals("exit")) {
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
		this.chattingField.setEditable(true);
		this.chattingField.setName("chattingField");
		this.chattingField.addActionListener(this.gameRoomAction);
		
		JScrollPane scroll = new JScrollPane(chattingArea);
		scroll.setBounds(GameRoomEnum.GAME_SCROLL_PANE_RECT.getRect());
		scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar src = (JScrollBar)e.getSource();
				src.setValue(src.getMaximum());
			}
		});
		
		this.chattingPanel.add(scroll);
		this.chattingPanel.add(chattingField);
		this.add(this.chattingPanel);
	} // 채팅 패널

	// 게임방에 접속한 유저의 정보 세팅
	public void setEnterUserInfo(UserInGameRoomDTO inGameUserInfo) {
		// 레디버튼 안에 마우스 진입시 색깔이 바뀌어아 하며 사용자가 클릭했다면 더이상 변경되지 말아야 하므로 상태값을 boolean으로 주었다.
		// 그 boolean 값을 유저가 방에 들어오면 일단 무조건 true 로 만들어준다.
		this.gameRoomInfo = inGameUserInfo.getGameRoomInfo();
		this.thisUserID = this.basicFrame.getUserID();
		try {
			Map<String, String> gradeImageMap = ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap();
			String imageDir = null;
			this.myGradeImage = this.getUserLevelIcon(gradeImageMap.get(inGameUserInfo.getUserGameData().getUserGrade()));
			// 오너인 경우
			if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getOwner())) {
				imageDir 		 = ImageEnum.GAMEROOM_START.getImageDir();
				this.myImage 	 = GetResources.getImageIcon(inGameUserInfo.getUserGameData().getUserWaitingRoomImage(), 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height);
				this.leftUser.setIcon(myImage);
				this.leftUserId.setText(this.thisUserID);
				this.leftUserLevel.setIcon(myGradeImage);
				
				this.otherUserID = this.gameRoomInfo.getGuest();
			// 게스트인 경우
			} else if(this.thisUserID.equals(inGameUserInfo.getGameRoomInfo().getGuest())) {
				this.chattingField.setEditable(true);
				imageDir = ImageEnum.GAMEROOM_READY.getImageDir();
				this.rightUser.setIcon(GetResources.getImageIcon(inGameUserInfo.getUserGameData().getUserWaitingRoomImage(), 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height));
						
				this.rightUserId.setText(this.thisUserID);
				this.rightUserLevel.setIcon(myGradeImage);
				
				String ownerImageDir = inGameUserInfo.getOwnerGender() == 1 ? 
						ImageEnum.GAMEROOM_MALE_IMAGE.getImageDir() : ImageEnum.GAMEROOM_FEMALE_IMAGE.getImageDir();
				
				// 레디버튼에 액션등록!!!
				this.menuButtons[0].setName("ready");
				this.menuButtons[0].addMouseListener(this.gameRoomAction);
				
				this.otherUserID = this.gameRoomInfo.getOwner();
				this.leftUser.setIcon(this.getUserImageIcon(ownerImageDir));
				this.leftUserId.setText(this.otherUserID);
				this.leftUserLevel.setIcon(GetResources.getImageIcon(
						gradeImageMap.get(inGameUserInfo.getOtherGameData().getUserGrade()), 
						GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().width, 
						GameRoomEnum.GAME_USERLEVEL_LEFT_IMAGE_RECT.getRect().height));
				this.myImage 	 = GetResources.getImageIcon(inGameUserInfo.getUserGameData().getUserWaitingRoomImage(), 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
						GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height);
						
//						inGameUserInfo.getUserGameData().getUserWaitingRoomImage();
			}
			this.addTextNotice("게임방에 입장하였습니다.");
			this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	// 오너의 경우 방에 유저가 들어왔을 때 실행됨
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
		this.addTextNotice(this.gameRoomInfo.getGuest() + "님께서 입장하셨습니다.");
	}
	
	// 채팅 입력시 서버로 전송
	public void chattingInfoSendServer() {
		if(!this.chattingField.getText().equals("")) {
			UserMessageVO userMessage = new UserMessageVO(UserPositionEnum.POSITION_GAME_ROOM);
			userMessage.setUserAction(UserActionEnum.USER_IN_GAME_ROOM_CHATTING);
			userMessage.setUserID(this.thisUserID);
			userMessage.setTargetID(this.otherUserID);
			userMessage.setMessage(this.chattingField.getText());
			this.basicFrame.sendDTO(userMessage);
		}
	}
	
	// 채팅에 관련한 서버에서의 응답
	public void chattingAreaSetting(AbstractEnumsDTO data) {
		this.chattingField.setText("");
		UserMessageVO messageVO = (UserMessageVO)data;
		this.chattingArea.setText(this.chattingArea.getText() + "\n" + messageVO.getMessage());
	}
	
	// 메뉴 영역 밖으로 마우스 포인터가 빠짐
	public void changeButtonImageMouseOut(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_CH.getImageDir()));
		} else if(buttonName.equals("ready") && this.gameRoomAction.getReadyCheck() == 0) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_READY.getImageDir()));
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
	public void changeButtonImageMouseIn(String buttonName) {
		if(buttonName.equals("start")) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START_GO.getImageDir()));
		} else if(buttonName.equals("ready") && this.gameRoomAction.getReadyCheck() == 0) {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_READY_BLUE.getImageDir()));
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
		String imageDir = check ? ImageEnum.GAMEROOM_READY_CH.getImageDir() : ImageEnum.GAMEROOM_READY.getImageDir() ;
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
			imageDir = ImageEnum.GAMEROOM_START_CH.getImageDir();
			this.menuButtons[0].addMouseListener(this.gameRoomAction);
		} else {
			imageDir = ImageEnum.GAMEROOM_START.getImageDir();
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
	// 기권버튼의 액션을 활성화시킨다.
	// 서버에서 회신이 오면 게스트도 이 메소드를 생성하며 레디 버튼에 같은 액션을 실행한다.
	public void gameStart() {
		this.isGameIngCheck = true;
		// 이미지를 초기화하고 액션삭제
		String imageDir = this.getBasicFrame().getUserID().equals(this.gameRoomInfo.getOwner()) ?
				ImageEnum.GAMEROOM_START_BATTILING.getImageDir() : ImageEnum.GAMEROOM_START_BATTILING.getImageDir();
		
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		this.menuButtons[2].removeMouseListener(this.gameRoomAction);
		this.chattingArea.setText(this.chattingArea.getText() + "\n<게임을 시작합니다.>");
		
		GetResources.soundPlay(SoundEnum.GAME_START_SOUND.getSound());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 오너 먼저 시작 (흑돌)
		if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
			this.thisUserTurn(SoundEnum.GAME_BLACK_TURN.getSound());
		}
	}
	
	// 서버에서 정보 도착했을 때 작동
	// 상대방 턴이 종료된 후 서버로 정보가 전송되었을 때
	// 서버가 그 정보를 해당 방의 유저들에게 전송해줄 때 이 메소드로 들어오게 된다.
	public void boardSettingAndMyTurnStart(AbstractEnumsDTO data) {
		GameBoardVO gameBoardVO = (GameBoardVO)data;
		this.gameBoard = gameBoardVO.getGameBoard();
		this.stoneImageSetting(gameBoardVO.getX(), gameBoardVO.getY());
		// 서버에서 보내준 정보 중 내가 현재 턴인 유저라면
		// (내가 현재 턴인 유저가 아니라면 무시 ^^)
		if(this.thisUserID.equals(gameBoardVO.getNowTurnUser())) {
			// 그런데 내가 오너라면
			if(this.thisUserID.equals(this.gameRoomInfo.getOwner())) {
				this.thisUserTurn(SoundEnum.GAME_BLACK_TURN.getSound());
			// 내가 게스트라면
			} else {
				this.thisUserTurn(SoundEnum.GAME_WHITE_TURN.getSound());
			}
			
		// 상대방의 턴에는 상대방 돌의 소리를 낼 수 있게끔 else 처리.
		} else {
			GetResources.soundPlay(this.thisUserID.equals(this.gameRoomInfo.getOwner()) ? 
					SoundEnum.GAME_WHITE_TURN.getSound() : SoundEnum.GAME_BLACK_TURN.getSound());
			this.chattingArea.setText(this.chattingArea.getText() + "\n" + this.otherUserID + " 님의 턴");
		}
	}

	// 현재 유저 턴이라면 쓰레드를 멈추는 플래그를 true로 바꿔주고
	// 시간을 세어주는 쓰레드를 구동시키고
	// 사운드를 내보내주고 게임패널에 액션리스너를 등록시켜준다.
	public void thisUserTurn(String soundDir) {
		this.chattingArea.setText(this.chattingArea.getText() + "\n" + this.thisUserID + " 님의 턴");
		this.isThreadStart = true;
		this.gameBoardPanelAddAction();
		GetResources.soundPlay(soundDir);
		this.timeLimitThread();
	}

	// 게임보드에 액션을 추가한다.
	public void gameBoardPanelAddAction() {
		for(int i = 0, iSize = this.gameBoardButtons.length; i < iSize; i++) {
			for(int j = 0, jSize = this.gameBoardButtons[0].length; j < jSize; j++) {
				if(this.gameBoard[i][j] == 0) {
					this.gameBoardButtons[i][j].addMouseListener(this.gameRoomAction);
				}
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
	
	// 게임보드에 이미지를 추가한다. TODO 착수점을 표시하고 싶으시다.
	public void stoneImageSetting(int x, int y) {
		for (int i = 0, iLen = this.gameBoard.length; i < iLen; i++) {
			for (int j = 0, jLen = gameBoard[i].length; j < jLen; j++) {
				String imageDir = null;
				if(this.gameBoard[i][j] == 1) {
					imageDir = ImageEnum.GAMEROOM_STONE_ROUND_PIKA.getImageDir();
					this.gameBoardButtons[i][j].setIcon(this.getStoneImageIcon(imageDir));
					if(i == x && j == y) {
						this.gameBoardButtons[x][y].setIcon(this.getStoneImageIcon(ImageEnum.GAMEROOM_STONE_ROUND_PIKA_CLICK.getImageDir()));	
					}
				} else if(this.gameBoard[i][j] == 2) {
					imageDir = ImageEnum.GAMEROOM_STONE_ROUND_KOBOOK.getImageDir();
					this.gameBoardButtons[i][j].setIcon(this.getStoneImageIcon(imageDir));
					if(i == x && j == y) {
						this.gameBoardButtons[x][y].setIcon(this.getStoneImageIcon(ImageEnum.GAMEROOM_STONE_ROUND_KOBOOK_CLICK.getImageDir()));					
						
					}
				}
			}
		}
	}
	
	// 턴이 종료되면 (유저가 돌을 놓으면)
	// 돌을 놓은 자리에 게임보드 배열값을 바꿔주고
	// 시간 쓰레드를 종료시켜주고
	// 게임보드 배열값에 따라 게임보드 이미지를 바꿔준다.
	public void turnEnd(int x, int y) {
		GetResources.soundPlay(SoundEnum.GAME_STONE_DROP.getSound());
		this.gameBoard[x][y] = this.thisUserID.equals(this.gameRoomInfo.getOwner()) ? 1 : 2;
		this.x = x;
		this.y = y;
		this.isThreadStart = false;
		this.stoneImageSetting(x, y);
	}
	
	// 게임 도중 기권버튼이 클릭되면 실행됨.
	public void clickWithDraw() {
		if(new ConfirmDialog(this.basicFrame, "기권하시겠습니까?").isYesNoCheck()) {
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

	// 한 유저가 이긴 경우 게임이 종료된다.
	// 이긴 유저가 접속자와 같다면 승리하셨습니다 메세지를 띄워주고 진 유저가 같다면 패배하셨습니다 메세지 띄워준다.
	// for문을 돌면 돌이 일정하게 사라지지 않기 대문에 setVisible 먼저 보이지 않게 한 후 for문으로 작업하고 다시 true를 준다.
	// 초기화 해야 할 값이 있다면 초기화 하고 게스트 유저의 레디를 해제해준다.
	public void gameEnd(AbstractEnumsDTO data) {
		this.isThreadStart = false;
		this.isGameIngCheck = false;
		GameBoardVO gameBoardVO = (GameBoardVO)data;
		this.gameBoard = gameBoardVO.getGameBoard();
		this.x = gameBoardVO.getX();
		this.y = gameBoardVO.getY();

		if(gameBoardVO.getWinUser().equals(this.thisUserID)) {
			new GameEndDialog(this.basicFrame,"승리하셨습니다 :D");
		} else {
			this.stoneImageSetting(x, y);
			new GameEndDialog(this.basicFrame, "패배하였습니다 :<");
		}
		
		this.timeBar.setValue(0);
		this.stoneInit();
		
		this.x = 0;
		this.y = 0;
		
		if(this.thisUserID.equals(this.gameRoomInfo.getGuest())) {
			this.menuButtons[0].addMouseListener(this.gameRoomAction);
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_READY.getImageDir()));
			this.gameRoomAction.setReadyCheck(0);
		} else {
			this.menuButtons[0].setIcon(this.getButtonImageIcon(ImageEnum.GAMEROOM_START.getImageDir()));
		}
		
		this.menuButtons[2].addMouseListener(this.gameRoomAction);
	}
	
	// 놓였던 돌 초기화
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
	
	// 나가기 버튼을 눌렀을 때 
	public void exitGame() {
		// 뜨는 팝업창에서 Yes 가 눌린 경우
		if(new ConfirmDialog(this.basicFrame, "게임방을 나가시겠습니까?").isYesNoCheck()) {
			GameRoomInfoVO gameRoomInfo = new GameRoomInfoVO(UserPositionEnum.POSITION_GAME_ROOM);
			gameRoomInfo.setUserAction(UserActionEnum.USER_GAME_ROOM_EXIT);
			gameRoomInfo.setRoomNumber(this.gameRoomInfo.getRoomNumber());
			gameRoomInfo.setRoomName(this.gameRoomInfo.getRoomName());
			gameRoomInfo.setPwd(this.gameRoomInfo.getPwd());
			
			// 게임방에 2명이 모두 들어온 경우
			if(this.gameRoomInfo.getPersonNum() == 2) {
				gameRoomInfo.setPersons(1);
				gameRoomInfo.setOwner(this.otherUserID);
				
				// 게임방 패널의 유저이미지와 돌 이미지 초기화
				this.initUserGameRoomInfo();
				this.stoneInit();
			
			// 게임방에 혼자 있었던 경우
			} else {
				gameRoomInfo.setOwner(null);
				this.initUserGameRoomInfo();
				gameRoomInfo.setPersons(0);
			}
			// 서버에게 내가 방을 나가겠다는 정보를 보낸다. 
			this.basicFrame.sendDTO(gameRoomInfo);
			
			// 대기실로 나감.
			this.basicFrame.showWaitingRoom();
		}
	}
	
	// 게임방 초기화
	public void initUserGameRoomInfo() {
		this.leftUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));
		this.rightUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));
		this.timeBar.setValue(time);
		this.timeLabel.setText("0:30");
		this.chattingArea.setText("");
		this.rightUserId.setText(null);
		this.rightUserLevel.setIcon(null);
		this.leftUserId.setText(null);
		this.leftUserLevel.setIcon(null);
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		this.menuButtons[1].removeMouseListener(this.gameRoomAction);
	}
	
	// 다른 유저가 방을 나가면 서버에서 내려준 바뀐 방정보를 내 게임방정보에 저장하고
	// 접속자 이미지를 내 이미지와 빈 이미지로 변경하고
	// 메뉴버튼 첫번쨰를 시작버튼으로 바꾸고 등록된 액션을 삭제한다.
	public void otherUserExitGame(AbstractEnumsDTO data) {
		this.addTextNotice(otherUserID + " 님이 방을 나갔습니다.");
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

		this.rightUser.setIcon(this.getUserImageIcon(ImageEnum.GAMEROOM_DEFALT_USER_IMAGE.getImageDir()));
		this.rightUserId.setText(null);
		this.rightUserLevel.setIcon(null);

		String imageDir = ImageEnum.GAMEROOM_START.getImageDir();
		this.menuButtons[0].setIcon(this.getButtonImageIcon(imageDir));
		this.menuButtons[0].setName(GameRoomEnum.GAME_BUTTONNAME_OWNER.getButtonName()[0]);
		this.menuButtons[0].removeMouseListener(this.gameRoomAction);
		this.menuButtons[0].removeActionListener(this.gameRoomAction);
	}
	
	public void timeLimitThread() {//TODO
		new Thread() {
			// 30초동안 1초 1초 줄어든다. 시간부분과 프로그레스바를 줄어드는 시간에 맞추어 변경시킨다.
			// 시간이 모두 지나거나 유저가 게임보드에 돌을 놓으면(isThreadStart가 false가 되면)
			// 현재 유저의 마우스리스너를 삭제하고 현재 게임보드 정보를 서버로 전송하며 턴을 종료한다.
			// 시간이 시작하면 클릭이 가능하고 턴이 종료되면 불가능하게 하는 플래그 설정
			// 기권이 자신의 턴에만 가능하게 하기 위해 쓰레드 시작시 이벤트 등록하고 쓰레드 종료시 이벤트를 제거한다.
			@Override
			public void run() {
				isStoneClickCheck = true;
				menuButtons[1].addMouseListener(gameRoomAction);
				int i = 0;
				for(i = time; i >= 0 && isThreadStart; i--) {
					timeBar.setValue(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeLabel.setText("0:" + String.valueOf(i > 9 ? i : "0" + i));
				}
				timeBar.setValue(0);
				timeLabel.setText("0:00");
				menuButtons[1].removeMouseListener(gameRoomAction);
				// 쓰레드가 끝나면 (시간이 다 갔거나 유저가 돌을 놓았거나) - turnEnd 에서 false처리가 되면
				// 화면에 등록된 액션들을 삭제한다. (클릭이 불가능하게 만듦)
				gameBoardPanelDeleteAction();
				
				// 쓰레드 종료보다 게임종료가 먼저 일어났는지 판단하여 아래 결과를 실행한다.
				// 게임종료가 먼저 일어났다면 서버에 두 번 요청을 보낼 수 있기 때문.
				// (기권 등의 상황)
				if(isGameIngCheck) {
					// 게임보드VO 에 현재 진행한 유저와 다음턴의 유저와 게임보드에 놓인 돌 정보와 이긴 유저 정보를 담아 보낸다.
					// (현재 유저의 돌의 위치를 탐색하여 5개가 이어져서 놓여졌는지 판단한다.)
					GameBoardVO gameBoardVO = new GameBoardVO(UserPositionEnum.POSITION_GAME_ROOM);
					gameBoardVO.setUserAction(UserActionEnum.USER_GAME_BOARD_INFO);
					gameBoardVO.setNowTurnUser(thisUserID);
					gameBoardVO.setNextTurnUser(gameRoomInfo.getOwner().equals(thisUserID) ? gameRoomInfo.getGuest() : gameRoomInfo.getOwner());
					gameBoardVO.setGameBoard(gameBoard);
					gameBoardVO.setX(x);
					gameBoardVO.setY(y);
					
					if(i > 0) {
						gameBoardVO.setWinUser(
							stonePositionCheck.stoneDiagonalLeftCheck(x, y, gameBoard) < 5 ? 
								(stonePositionCheck.stoneDiagonalRightCheck(x, y, gameBoard) < 5 ? 
									(stonePositionCheck.stoneHeightCheck(x, y, gameBoard) < 5 ? 
										(stonePositionCheck.stoneWidthCheck(x, y, gameBoard) < 5 ? null : thisUserID) 
									: thisUserID) 
								: thisUserID) 
							: thisUserID);
					}
					
					if(gameBoardVO.getWinUser() != null) {
						gameBoardVO.setLoseUser(otherUserID);
					}
					basicFrame.sendDTO(gameBoardVO);
				}
				isGameIngCheck = true;
			}
		}.start();
	}
	
	// 유저 이미지 세팅
	public ImageIcon getUserImageIcon(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().width, 
				GameRoomEnum.GAME_USERIMAGE_LEFT_RECT.getRect().height);
	}
	
	// 유저 레벨 이미지 세팅
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
	
	// 버튼 이미지 세팅
	public ImageIcon getButtonImageIcon(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().width, 
				GameRoomEnum.GAME_BUTTON_SIZE_RECT.getRect().height);
	}
	
	// 돌 이미지 세팅
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
	
	public void setStoneClickCheck(boolean isStoneClickCheck) {
		this.isStoneClickCheck = isStoneClickCheck;
	}
	
	public boolean isStoneClickCheck() {
		return isStoneClickCheck;
	}
}