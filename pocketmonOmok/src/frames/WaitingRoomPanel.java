package frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.LoginFrameSizesEnum;
import enums.WaitingRoomSizesEnum;

public class WaitingRoomPanel extends JPanel implements ActionListener {	
	private JPanel background;
	
	private JScrollPane waitingRoomListScroll;
	private JLabel waitingRoomInfo;
	private JList<String> waitingRoomList;
	private Vector<String> waitingRoomListVector;
	private JPanel waitingRoomListBackground;
	
	private JButton gamestartButton;
	private JButton createRoomButton;
	
	private JTextArea chattingOutput;
	private JTextField chattingInput;
	private JButton sendMessage;
	
	private JList<String> playerList;
	private Vector<String> playerListVector;
	private JPanel playerListBackgrounnd;
	
	private JPanel myInfo;
	private JPanel myInfoImage;
	private JLabel userID;
	private JLabel score;
	private JLabel winningRate;
	private JLabel point;
	private JLabel level;
	private JButton correct;
	

	public WaitingRoomPanel() throws IOException {	
		
		this.waitingRoomListVector = new Vector<String>();
		this.waitingRoomInfo = new JLabel(" OX " + "  NO  "
				+ "                 TITLE                 "
				+ "     MASTER     " + "NUMBER");
		this.waitingRoomList = new JList<>(this.waitingRoomListVector);
		this.waitingRoomListVector.add("1111111111111111111111111번");
		this.waitingRoomListVector.add("2222222222222222222222222번");
		
		this.chattingOutput = new JTextArea();
		this.chattingInput = new JTextField();
		this.sendMessage = new JButton();
				

		this.gamestartButton = new JButton();
		this.createRoomButton = new JButton();
		
		this.playerListVector = new Vector<String>();
		this.playerList = new JList<>(this.playerListVector);
		this.playerListVector.add("shin");
		this.playerListVector.add("yeon");
		this.playerListVector.add("jong");
		
		this.userID = new JLabel("아이디");
		this.score = new JLabel("전적");
		this.winningRate = new JLabel("승률");
		this.point = new JLabel("승점");
		this.level = new JLabel("등급");
		this.correct = new JButton();
		
		this.roomListPosition();
	}
	

	public void roomListPosition() throws IOException {
		
		//방리스트 정보의 위치와 크기를 가져옴
		this.waitingRoomInfo.setBounds(
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_HEIGHT.getSize()
		);
		/******************************************************************************/
		//방리스트 위치와 크기를 가져옴
		this.waitingRoomList.setBounds(
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_SIZE_HEIGHT.getSize()
		);	
		
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
		
		waitingRoomList.setOpaque(false);
		waitingRoomListBackground.setOpaque(false);
		
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
		//현재 접속중인 플레이어 리스트의 크기와 위치
		this.playerList.setBounds(
				WaitingRoomSizesEnum.PLAYERS_LIST_POSITION_X.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_WIDTH.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_HEIGHT.getSize()		
		);
		//현재 접속중인 플래이어 배경 이미지를 불러올 JPanel 생성
		this.playerListBackgrounnd = new JPanel() {
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
		//현재 접속중인 플래이어 배경 이미지 크기와 위치
		this.playerListBackgrounnd.setBounds(
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_POSITION_X.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_WIDTH.getSize(),
				WaitingRoomSizesEnum.PLAYERS_LIST_BACKGROUND_HEIGHT.getSize()
		);
		playerList.setOpaque(false);
		playerListBackgrounnd.setOpaque(false);
		
		/******************************************************************************/
		//아이디
		this.userID.setBounds(
				WaitingRoomSizesEnum.MY_INFO_USERID_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_USERID_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_USERID_HEIGHT.getSize()
		);
		//전적
		this.score.setBounds(
				WaitingRoomSizesEnum.MY_INFO_SCORE_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_SCORE_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_SCORE_HEIGHT.getSize()
		);
		
		//승률
		this.winningRate.setBounds(
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_WINNINGRATE_HEIGHT.getSize()
		);
		
		//승점
		this.point.setBounds(
				WaitingRoomSizesEnum.MY_INFO_POINT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_POINT_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_POINT_HEIGHT.getSize()
		);
		
		//등급
		this.level.setBounds(
				WaitingRoomSizesEnum.MY_INFO_LEVEL_POSITION_X.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_POSITION_Y.getSize(), 
				WaitingRoomSizesEnum.MY_INFO_LEVEL_WIDTH.getSize(),
				WaitingRoomSizesEnum.MY_INFO_LEVEL_HEIGHT.getSize()
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
		myInfo.setOpaque(false);
		
		
		
		
		
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
		this.waitingRoomInfo.setForeground(Color.WHITE);
		this.waitingRoomInfo.setFont(new Font("a으라차차", Font.BOLD, 18));
		this.userID.setFont(new Font("a으라차차", Font.BOLD, 18));
		this.score.setFont(new Font("a으라차차", Font.BOLD, 18));
		this.winningRate.setFont(new Font("a으라차차", Font.BOLD, 18));
		this.point.setFont(new Font("a으라차차", Font.BOLD, 18));
		this.level.setFont(new Font("a으라차차", Font.BOLD, 18));
		
		
		
		this.setLayout(null);
		
		
		
		this.add(waitingRoomInfo);
		this.add(waitingRoomList);
		this.add(waitingRoomListBackground);
		this.add(chattingOutput);
		this.add(chattingInput);
		this.add(sendMessage);
		this.add(gamestartButton);
		this.add(createRoomButton);
		this.add(playerList);
		this.add(playerListBackgrounnd);
		this.add(myInfoImage);
		this.add(userID);
		this.add(score);
		this.add(winningRate);
		this.add(point);
		this.add(level);
		this.add(correct);
		this.add(myInfo);

	}

	private void waitingRoomInfoFont(Font font) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			
	}
}
