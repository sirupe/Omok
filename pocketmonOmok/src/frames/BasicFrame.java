package frames;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.ClientAccept;
import datas.UserPositionIndex;
import enums.LoginFrameSizesEnum;
import enums.LoginSizesEnum;
// 태성
import enums.UserPositionEnum;

@SuppressWarnings("serial")
public class BasicFrame extends JFrame implements Serializable{
	//이미지 화면 비율에 맞춰서 바뀌게 하기 위해 이미지 사용
	private Image reimage;
	private CardLayout cardLayout;
	private LoginPanel loginPanel;
	private WaitingRoomPanel waitingRoomPanel;
	private GameRoomPanel gameRoomPanel;
	
	private ClientAccept clientAccept;
	
	
	
	public BasicFrame(ClientAccept clientAccept) throws IOException {
		this.clientAccept = clientAccept;
		
		//배경이미지 모니터의 해상도에 따라 조절되게 설정
		this.reimage = ImageIO.read(new File("resources/login/background.jpg")).getScaledInstance(
					LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(),
					LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize(),
					Image.SCALE_SMOOTH);
		
		this.setContentPane(new JLabel(new ImageIcon(reimage)));   
		
		//프레임 화면 출력 위치 설정
		this.setBounds(   
					LoginSizesEnum.LOGIN_FRAME_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_FRAME_POSITION_Y.getSize(),
					LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), 
					LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize()
		);
		
		this.loginPanel = new LoginPanel(this);
		this.loginPanel.setOpaque(false);
		this.cardLayout = new CardLayout();

		this.waitingRoomPanel = new WaitingRoomPanel(){
			@Override
			protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			try {
				g.drawImage(ImageIO.read(
					new File("resources/login/blackhole.png")), 
					0, 
					0,
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(),
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize(),
					this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}};
	
		
		
		this.gameExit();
		this.newGameRoomPanel();
		this.setLayout(this.cardLayout);
//		this.add("loginPanel", this.loginPanel);

		this.add("waitingRoomPanel", this.waitingRoomPanel);
	//	this.add("gameRoomPanel", this.gameRoomPanel);

		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void gameExit() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UserPositionIndex index = new UserPositionIndex(UserPositionEnum.POSITION_EXIT);
				try {
					clientAccept.getClientOS().writeObject(index);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				e.getWindow().setVisible(false);
			}
		
		});
	}
	
	// GameRoom 도 결국 패널이므로 패널 생성시 익명클래스를 이용하여  paintComponent 를 오버라이드 하면
	// 굳이 GameRoom 안에서 새로운 패널을 생성하여 배경을 깔아줄 필요가 없게 된다. 
	public void newGameRoomPanel() {
		this.gameRoomPanel = new GameRoomPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/background/background.png")), 
						0, 
						0, 
						LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize(),
						this
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public void inWaitingRoom() {
		this.cardLayout.show(this.getContentPane(), "waitingRoomPanel");
	}

	public ObjectOutputStream getClientOS() {
		return this.clientAccept.getClientOS();
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public static void main(String[] args) {
		try {
			new BasicFrame(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}