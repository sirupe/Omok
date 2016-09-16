package frames;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.WaitingRoomSizesEnum;

public class WaitingRoomPanel extends JPanel implements ActionListener {
	private JPanel background;
	private JLabel waitingRoomInfo;
	private JList<String> waitingRoomList;
	private Vector<String> vector;
	private JButton gamestartButton;
	private JButton createRoomButton;
	private JTextArea chattingOutput;
	private JTextField chattingInput;
	
	public WaitingRoomPanel() throws IOException {	
		
		this.vector = new Vector<String>();
		this.waitingRoomInfo = new JLabel(" OX " + "  NO  "
				+ "                 TITLE                 "
				+ "     MASTER     " + "NUMBER");
		this.waitingRoomList = new JList<>(this.vector);
		this.vector.add("1111111111111111111111111번");
		this.vector.add("2222222222222222222222222번");
		this.chattingOutput = new JTextArea();
		this.chattingInput = new JTextField();
		this.gamestartButton = new JButton();
		this.createRoomButton = new JButton();
		this.roomListPosition();
	}

	
	
	public void roomListPosition() throws IOException {
		
		this.waitingRoomList.setBackground(Color.BLUE);

		//방리스트 정보의 위치와 크기를 가져옴
		this.waitingRoomInfo.setBounds(
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_INFO_HEIGHT.getSize()
		);
		
		//방리스트 위치와 크기를 가져옴
		this.waitingRoomList.setBounds(
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_LIST_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_ROOM_SIZE_HEIGHT.getSize()
		);
		
		//채팅 입력창의 위치와 크기를 가져옴
		this.chattingOutput.setBounds(
				WaitingRoomSizesEnum.WAITING_CHATTING_OUTPUT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_OUTPUT_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_OUTPUT_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_OUTPUT_SIZE_HEIGHT.getSize()
		);
		//채팅 출력창의 위치와 크기를 가져옴
		this.chattingInput.setBounds(
				WaitingRoomSizesEnum.WAITING_CHATTING_INPUT_POSITION_X.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_INPUT_POSITION_Y.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_INPUT_SIZE_WIDTH.getSize(),
				WaitingRoomSizesEnum.WAITING_CHATTING_INPUT_SIZE_HEIGHT.getSize()	
		);
		
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
		
		
		//방정보 폰트
		this.waitingRoomInfo.setForeground(Color.WHITE);
		this.waitingRoomInfo.setFont(new Font("a으라차차", Font.BOLD, 18));
		
		this.setLayout(null);
		
		this.add(waitingRoomInfo);
		this.add(waitingRoomList);
		this.add(chattingOutput);
		this.add(chattingInput);
		this.add(gamestartButton);
		this.add(createRoomButton);

	}

	private void waitingRoomInfoFont(Font font) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
	}
}
