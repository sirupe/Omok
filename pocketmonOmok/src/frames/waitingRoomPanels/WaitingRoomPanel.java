package frames.waitingRoomPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datasDTO.UserGamedataInfoDTO;
import enums.etc.ImageEnum;
import enums.frames.WaitingRoomSizesEnum;

public class WaitingRoomPanel extends JPanel {	
	private static int roomNumber;
	private JPanel background;
	
	private JScrollPane waitingRoomListScroll;
	private JTable waitingRoomTable;
	private JPanel waitingRoomListBackground;
	
	private JButton gamestartButton;
	private JButton createRoomButton;
	
	private JTextArea chattingOutput;
	private JTextField chattingInput;
	private JButton sendMessage;
	
	private JList<String> playerList;
	private JPanel playerListBackground;
	private JScrollPane playerListScroll;
	
	private JPanel myInfo;
	private JPanel myInfoImage;
	private JLabel userID;
	private JLabel userIDText;
	private JLabel score;
	private JLabel scoreText;
	private JLabel winningRate;
	private JLabel winningRateText;
	private JLabel point;
	private JLabel pointText;
	private JLabel level;
	private JLabel levelText;
	private JButton correct;
	
	private Map<String,ImageIcon> imageMap;
	private Vector<String> players;
	public WaitingRoomPanel() throws IOException {
		this.playerListScroll = new JScrollPane();
		//==========================채팅방&내정보==========================
		
		this.chattingOutput = new JTextArea();
		this.chattingInput  = new JTextField();
		this.sendMessage    = new JButton();
						
		this.gamestartButton  = new JButton();
		this.createRoomButton = new JButton();
			
		this.userID 	 	 = new JLabel("ID");
		this.userIDText  	 = new JLabel("여기에아이디가들어가요label");
		this.score 		 	 = new JLabel("전적");
		this.scoreText   	 = new JLabel("12345");
		this.winningRate 	 = new JLabel("승률");
		this.winningRateText = new JLabel("123123");
		this.point 		 	 = new JLabel("승점");
		this.pointText       = new JLabel("3333333");
		this.level 		 	 = new JLabel(".LV");
		this.levelText       = new JLabel();
		this.correct 	 	 = new JButton();
		
		this.roomListPosition();
			
	}
	//==========================대기방 리스트==========================

	public void roomListSetting(WaitingRoomListTable roomListModel) {
		
		//TODO	DefaultTableModel defaultTableModel = new DefaultTableModel(roomListModel.getWaitingRoomListData(), roomListModel.getWaitingRoomListColumn());
			DefaultTableModel defaultTableModel = new DefaultTableModel(roomListModel.getWaitingRoomListData(), roomListModel.getWaitingRoomListColumn());
			this.waitingRoomTable = new JTable(defaultTableModel) {
				@Override
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
				
				@Override
				//테이블 수정 금지
				public boolean isCellEditable(int row, int column){
				    return false;
				}
			};
			
			this.waitingRoomTable.getTableHeader().setFont(new Font("a으라차차", Font.BOLD, 20));//방타이틀글꼴
			this.waitingRoomTable.setFont(new Font("a으라차차",Font.BOLD,15));
			this.waitingRoomTable.setForeground(Color.WHITE);
			this.waitingRoomTable.setShowVerticalLines(false);                               //수직선을 그릴것인가
			this.waitingRoomTable.getTableHeader().setReorderingAllowed(false);              //이동불가
			this.waitingRoomTable.getTableHeader().setResizingAllowed(false);                //크기 조절 불가
			this.waitingRoomTable.setOpaque(false);
			this.waitingRoomTable.setBorder(new EmptyBorder(0, 0, 0, 0));
			this.waitingRoomTable.setBackground(new Color(0, 0, 0, 0));
			
			this.waitingRoomTable.getColumn("OX").setPreferredWidth(2);
			this.waitingRoomTable.getColumn("NO").setPreferredWidth(2);
			this.waitingRoomTable.getColumn("TITLE").setPreferredWidth(300);
			this.waitingRoomTable.getColumn("MASTER").setPreferredWidth(150);
			this.waitingRoomTable.getColumn("NUM").setPreferredWidth(20);
			this.waitingRoomTable.setRowHeight(50);
				
	}

	public void userListSetting(List<UserGamedataInfoDTO> list) throws IOException {
		System.out.println("userListSettings진입");
		ArrayList<String> usersGrade = new ArrayList<String>();
		this.players = new Vector<String>();
		for(UserGamedataInfoDTO gameData : list) {
			this.players.add(gameData.getUserID());
			usersGrade.add(gameData.getUserGrade());
		}

		
		//==========================접속자 리스트==========================
		this.imageMap = createImage(this.players, usersGrade);
		this.playerList = new JList<String>(this.players);
		this.playerList.setCellRenderer(new PlayerRenderer());
		
		this.playerListScroll.setViewportView(this.playerList);

		this.playerListScroll.setOpaque(false);
		this.playerListScroll.getViewport().setOpaque(false);
		this.playerList.setOpaque(false);
			
	}

	public void userAddSetting(UserGamedataInfoDTO newUser) throws IOException {
		this.players.add(newUser.getUserID());
		this.playerList.setListData(players);
		this.addNewUserImage(newUser.getUserID(), newUser.getUserGrade());

		for(String s : this.imageMap.keySet()) {
			System.out.println(s);
		}
				
		this.playerList.setCellRenderer(new PlayerRenderer());
		
		this.playerListScroll.setViewportView(this.playerList);

		this.playerListScroll.setOpaque(false);
		this.playerListScroll.getViewport().setOpaque(false);
		this.playerList.setOpaque(false);
	}
	
	/***************************접속자 리스트 클래스***************************/
	public class PlayerRenderer extends DefaultListCellRenderer {
		

		
		public Component getListCellRendererComponent(JList player, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(player, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setOpaque(false);
            label.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE90.getfont());
            return label;
		}
	}
	
	/***************************접속자 리스트 맵***************************/
	private Map<String, ImageIcon> createImage(Vector<String> player, ArrayList<String> grade) throws IOException {
	    System.out.println("createImage");
		Map<String, ImageIcon> map = new HashMap<>();
	    try{
    		for(int i = 0, size = player.size(); i < size; i++) {
		    	map.put(player.get(i), new ImageIcon(ImageIO.read(
            		new File(ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap().get(grade.get(i)))).getScaledInstance(
    					WaitingRoomSizesEnum.LEVEL_ICON_SIZE_WIDTH.getSize(),
    					WaitingRoomSizesEnum.LEVEL_ICON_SIZE_HEIGHT.getSize(),
    					Image.SCALE_AREA_AVERAGING))
		    	);
    		}
	    } catch (Exception e){
	    	e.printStackTrace();
	    }    
	    return map;
	}
	
	private void addNewUserImage(String player, String grade) throws IOException {
		try{
			this.imageMap.put(player, new ImageIcon(ImageIO.read(
				new File(ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap().get(grade))).getScaledInstance(
					WaitingRoomSizesEnum.LEVEL_ICON_SIZE_WIDTH.getSize(),
					WaitingRoomSizesEnum.LEVEL_ICON_SIZE_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
			);
		
		} catch (Exception e){
			e.printStackTrace();
		}    
	}

	/***************************대기실 위치***************************/
	public void roomListPosition() throws IOException {
		//방 리스트의 배경 이미지를 불러올 JPanel 생성
		this.waitingRoomListBackground = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomList.png")),
							0,
							0,
							WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH.getSize(), 
							WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		//방리스트 배경 이미지를 가져옴
		this.waitingRoomListBackground.setBounds(
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_BACKGROUND_POSITION_X.getSize(), 
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_BACKGROUND_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH.getSize(), 
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT.getSize()
		);
		
		this.waitingRoomListScroll = new JScrollPane(this.waitingRoomTable);
		this.waitingRoomListScroll.setViewportView(this.waitingRoomTable);
		
		//방리스트 위치와 크기를 가져옴
				this.waitingRoomListScroll.setBounds(
						0,0,
						WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_WIDTH.getSize(),
						WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_HEIGHT.getSize()
				);	
	
		this.waitingRoomListBackground.setLayout(null);
		this.waitingRoomListBackground.setOpaque(false);

		this.waitingRoomListScroll.setOpaque(false);
		this.waitingRoomListScroll.getViewport().setOpaque(false);
		
		/******************************************************************************/
		//채팅 입력창의 위치와 크기를 가져옴
		this.chattingOutput.setBounds(
				WaitingRoomSizesEnum.CHATTING_OUTPUT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.CHATTING_OUTPUT_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.CHATTING_OUTPUT_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.CHATTING_OUTPUT_SIZE_HEIGHT.getSize()
		);
		//채팅 출력창의 위치와 크기를 가져옴
		this.chattingInput.setBounds(
				WaitingRoomSizesEnum.CHATTING_INPUT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.CHATTING_INPUT_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.CHATTING_INPUT_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.CHATTING_INPUT_SIZE_HEIGHT.getSize()	
		);
		//메세지 버튼 위치와 크기를 가져옴
		this.sendMessage.setBounds(
				WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_POSITION_X.getSize(),
				WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_WIDTH.getSize(),
				WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_HEIGHT.getSize()
		);
		//메시지 버튼의 이미지를 불러옴
		this.sendMessage.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/send.png")).getScaledInstance(
					WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_WIDTH.getSize(),
					WaitingRoomSizesEnum.SEND_MESSAGE_BUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		
		/******************************************************************************/
		//게임시작 버튼 위치와 크기를 가져옴
		this.gamestartButton.setBounds(
				WaitingRoomSizesEnum.GAMESTART_JBUTTON_POSITION_X.getSize(), 
				WaitingRoomSizesEnum.GAMESTART_JBUTTON_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.GAMESTART_JBUTTON_WIDTH.getSize(),
				WaitingRoomSizesEnum.GAMESTART_JBUTTON_HEIGHT.getSize()
		);
		//게임시작 버튼의 이미지를 불러옴
		this.gamestartButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/_gamestart.jpg")).getScaledInstance(
					WaitingRoomSizesEnum.GAMESTART_JBUTTON_WIDTH.getSize(),
					WaitingRoomSizesEnum.GAMESTART_JBUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		/******************************************************************************/
		//방생성 버튼 위치와 크기를 가져옴
		this.createRoomButton.setBounds(
				WaitingRoomSizesEnum.CREATEROOM_JBUTTON_POSITION_X.getSize(), 
				WaitingRoomSizesEnum.CREATEROOM_JBUTTON_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.CREATEROOM_JBUTTON_WIDTH.getSize(),
				WaitingRoomSizesEnum.CREATEROOM_JBUTTON_HEIGHT.getSize()
		);
		//방생성 버튼의 이미지를 불러옴
		this.createRoomButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/_createRoom.jpg")).getScaledInstance(
					WaitingRoomSizesEnum.CREATEROOM_JBUTTON_WIDTH.getSize() ,
					WaitingRoomSizesEnum.CREATEROOM_JBUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		/******************************************************************************/
		
		this.playerListScroll.setBounds(
				WaitingRoomSizesEnum.PLAYERS_LIST_POSITION_X.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_POSITION_Y.getSize(),				
				WaitingRoomSizesEnum.PLAYERS_LIST_WIDTH.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_HEIGHT.getSize()		
		);
				
		//현재 접속중인 플래이어 배경 이미지를 불러올 JPanel 생성
		this.playerListBackground = new JPanel() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomInfo.png")),
							0,
							0,
							WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_WIDTH.getSize(), 
							WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.playerListBackground.setLayout(null);

		
		//현재 접속중인 플래이어 배경 이미지 크기와 위치
		this.playerListBackground.setBounds(
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_POSITION_X.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_WIDTH.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_HEIGHT.getSize()
		);
		
		this.playerListBackground.setOpaque(false);
		
		/******************************************************************************/

		//아이디, 아이디 텍스트
		this.userID.setBounds(
				WaitingRoomSizesEnum.MY_INFO_USERID_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_USERID_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_HEIGHT.getSize()
		);
		this.userIDText.setBounds(
				WaitingRoomSizesEnum.MY_INFO_USERID_TEXT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_TEXT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_USERID_TEXT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_TEXT_HEIGHT.getSize()
		);
		//전적, 전적 텍스트
		this.score.setBounds(
				WaitingRoomSizesEnum.MY_INFO_SCORE_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_SCORE_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_HEIGHT.getSize()
		);
		this.scoreText.setBounds(
				WaitingRoomSizesEnum.MY_INFO_SCORE_TEXT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_TEXT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_SCORE_TEXT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_TEXT_HEIGHT.getSize()
		);
		
		//승률, 승률 텍스트
		this.winningRate.setBounds(
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_HEIGHT.getSize()
		);
		this.winningRateText.setBounds(
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_TEXT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_TEXT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_TEXT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_TEXT_HEIGHT.getSize()
		);
		
		
		//승점, 승점 텍스트
		this.point.setBounds(
				WaitingRoomSizesEnum.MY_INFO_POINT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_POINT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_HEIGHT.getSize()
		);
		this.pointText.setBounds(
				WaitingRoomSizesEnum.MY_INFO_POINT_TEXT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_TEXT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_POINT_TEXT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_TEXT_HEIGHT.getSize()
		);
		
		
		//등급, 등급 텍스트 이미지
		this.level.setBounds(
				WaitingRoomSizesEnum.MY_INFO_LEVEL_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_LEVEL_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_HEIGHT.getSize()
		);
		this.levelText.setBounds(
				WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_HEIGHT.getSize()
		);
		this.levelText.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/user/usermediumgrade.png")).getScaledInstance(
					WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_WIDTH.getSize() ,
					WaitingRoomSizesEnum.MY_INFO_LEVEL_TEXT_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		
		//수정
		this.correct.setBounds(
				WaitingRoomSizesEnum.MY_INFO_CORRECT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_CORRECT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_CORRECT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_CORRECT_HEIGHT.getSize()
		);
		//수정 버튼의 이미지를 불러옴
		this.correct.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/correct.png")).getScaledInstance(
					WaitingRoomSizesEnum.MY_INFO_CORRECT_WIDTH.getSize() ,
					WaitingRoomSizesEnum.MY_INFO_CORRECT_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);


		//내정보 이미지 틀
		this.myInfoImage = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/imageframe.png")),
							0,
							0,
							WaitingRoomSizesEnum.MY_INFO_IMAGE_WIDTH.getSize(), 
							WaitingRoomSizesEnum.MY_INFO_IMAGE_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.myInfoImage.setBounds(
				WaitingRoomSizesEnum.MY_INFO_IMAGE_POSITION_X.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_IMAGE_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_IMAGE_WIDTH.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_IMAGE_HEIGHT.getSize()
		);
		myInfoImage.setOpaque(false);
		
		//나의 정보창의 배경 이미지를 불러올 JPanel 생성
		this.myInfo = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomInfo.png")),
							0,
							0,
							WaitingRoomSizesEnum.MY_INFO_WIDTH.getSize(), 
							WaitingRoomSizesEnum.MY_INFO_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		//나의 정보창의 위치와 크기를 불러옴
		this.myInfo.setBounds(
				WaitingRoomSizesEnum.MY_INFO_POSITION_X.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_WIDTH.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_HEIGHT.getSize()
		);
		
		this.myInfo.setOpaque(false);
		
		/******************************************************************************/
	
		//게임시작 버튼테두리 효과를 없애줌
		this.gamestartButton.setBorderPainted(false);
		this.gamestartButton.setContentAreaFilled(false);
		this.gamestartButton.setFocusPainted(false);
		//게임시작 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
		this.gamestartButton.setIconTextGap(this.createRoomButton.getIconTextGap() - 15);
		
		//방생성 버튼테두리 효과를 없애줌
		this.createRoomButton.setBorderPainted(false);
		this.createRoomButton.setContentAreaFilled(false);
		this.createRoomButton.setFocusPainted(false);
		//방생성 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
		this.createRoomButton.setIconTextGap(this.createRoomButton.getIconTextGap() - 15);
		
		//메시지 보내는 버튼테두리 효과를 없애줌
		this.sendMessage.setBorderPainted(false);
		this.sendMessage.setContentAreaFilled(false);
		this.sendMessage.setFocusPainted(false);
		
		//수정 버튼 테두리 효과를 없애줌
		this.correct.setBorderPainted(false);
		this.correct.setContentAreaFilled(false);
		this.correct.setFocusPainted(false);
		
		//방정보 폰트
		this.userID.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE90.getfont());
		this.score.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE100.getfont());
		this.winningRate.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE100.getfont());
		this.point.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE100.getfont());
		this.level.setFont(WaitingRoomSizesEnum.LABELFONT_SIZE90.getfont());
		
		
		
		this.setLayout(null);

		this.waitingRoomListBackground.add(waitingRoomListScroll);
		this.add(waitingRoomListBackground);
		this.add(chattingOutput);
		this.add(chattingInput);
		this.add(sendMessage);
		this.add(gamestartButton);
		this.add(createRoomButton);
		this.playerListBackground.add(playerListScroll);
		this.add(playerListBackground);
		this.add(myInfoImage);
		this.add(userID);
		this.add(userIDText);
		this.add(score);
		this.add(scoreText);
		this.add(winningRate);
		this.add(winningRateText);
		this.add(point);
		this.add(pointText);
		this.add(level);
		this.add(levelText);
		this.add(correct);
		this.add(myInfo);
		
		
	}
	
	public void updateAddRoom() {
		// 테이블모델을 얻어옴
		DefaultTableModel tableModel = (DefaultTableModel) this.waitingRoomTable.getModel();
		Object o;
		int roomNum = 0;
		
		// 방번호 구하기. 1 ~ 20 중 가장 작은 생성되지 않은 방번호를 얻어온다.
		for(int i = 1, j, size; i <= 20; i++) {
			for(j = 0, size = tableModel.getRowCount(); j <= size; j++) {
				o = tableModel.getValueAt(2, j);
				if(Integer.parseInt(o.toString()) == i) {
					break;
				}
			}
			
			if(j == size) {
				roomNum = i;
				break;
			}
			
		}
		
		

	}
	
	public void updateDeleteRoom() {
		
	}
	
	public JTable getWaitingRoomTable() {
		return waitingRoomTable;
	}
	
//	public static void main(String[] args) {
//		int[] arr = new int[] {9, 8, 7, 10, 2, 3, 4, 1};
//		Arrays.sort(arr);
//		int j = 1;
//		for(int i = 0, size = arr.length; i < size; i++) {
//			if(j == arr[i]) {
//				j++;
//			} else {
//				System.out.println("생성될 방 번호 : " + j);
//			}
//		}
//		
//		WaitingRoomPanel p = null;
//		try {
//			p = new WaitingRoomPanel();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		DefaultTableModel tableModel = (DefaultTableModel) p.getWaitingRoomTable().getModel();
//		int row = tableModel.getRowCount();
//		int[] arr2 = p.getWaitingRoomTable().getSelectedColumns();
//		System.out.println(row);
//		Object o = tableModel.getValueAt(2, 1);
//		System.out.println((String)o);
//		System.out.println(arr2.length);
//
//	}
}