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

import datasDTO.AbstractEnumsDTO;
import datasDTO.UserInGameRoomDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.LoginFrameSizesEnum;
import enums.frames.LoginSizesEnum;

import frames.gameRoom.GameRoomPanel;
import frames.joinFrames.JoinFrame;
import frames.searchFrames.SearchIdFrame;
import frames.searchFrames.SearchPwdFrame;
import frames.searchFrames.SearchPwdPanel;
import frames.waitingRoom.WaitingRoomPanel;
import omokGame.client.ClientAccept;

@SuppressWarnings("serial")
public class BasicFrame extends JFrame implements Serializable{
	//이미지 화면 비율에 맞춰서 바뀌게 하기 위해 이미지 사용
	private Image reimage;
	private CardLayout cardLayout;
	private LoginPanel loginPanel;
	private WaitingRoomPanel waitingRoomPanel;
	private GameRoomPanel gameRoomPanel;
	private JoinFrame joinFrame;
	private SearchPwdFrame searchPwdFrame;
	private SearchIdFrame searchIdFrame;
	
	private ClientAccept clientAccept;
	
	private String userID;
	
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

		this.waitingRoomPanel = new WaitingRoomPanel(this) {
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
			}
		};
	
		
		
		this.gameExit();
		this.newGameRoomPanel();
		this.setLayout(this.cardLayout);

		this.add("loginPanel", this.loginPanel);
		this.add("waitingRoomPanel", this.waitingRoomPanel);
		this.add("gameRoomPanel", this.gameRoomPanel);

		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void gameExit() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_EXIT);
				personalDTO.setUserID(userID);
				try {
					clientAccept.getClientOS().writeObject(personalDTO);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				e.getWindow().setVisible(false);
			}
		
		});
	}
	
	// GameRoom 도 결국 패널이므로 패널 생성시 익명클래스를 이용하여  paintComponent 를 오버라이드 하면
	// 굳이 GameRoom 안에서 새로운 패널을 생성하여 배경을 깔아줄 필요가 없게 된다. 
	public void newGameRoomPanel() throws IOException {
		this.gameRoomPanel = new GameRoomPanel(this) {
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
	
	public void sendDTO(AbstractEnumsDTO dto) {
		this.clientAccept.sendDTO(dto);
	}
	
	public void showWaitingRoom() {
		this.cardLayout.show(this.getContentPane(), "waitingRoomPanel");
	}
	
	public void showGameRoom(UserInGameRoomDTO inGameUserInfo) {
		this.gameRoomPanel.setEnterUserInfo(inGameUserInfo);
		this.cardLayout.show(this.getContentPane(), "gameRoomPanel");
	}

	public void newJoinFrame() throws IOException {
		this.joinFrame = new JoinFrame(this);
	}
	public void newSearchPwdFrame() throws IOException {
		this.searchPwdFrame = new SearchPwdFrame(this);
	}
	
	public void newSearchIdFrame() throws IOException {
		this.searchIdFrame = new SearchIdFrame(this);
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public SearchPwdFrame getSearchPwdFrame() {
		return searchPwdFrame;
	}	
	
	public ObjectOutputStream getClientOS() {
		return this.clientAccept.getClientOS();
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public JoinFrame getJoinFrame() {
		return joinFrame;
	}
	
	public SearchIdFrame getSearchIdFrame() {
		return searchIdFrame;
	}
	
	public WaitingRoomPanel getWaitingRoomPanel() {
		return waitingRoomPanel;
	}

	public GameRoomPanel getGameRoomPanel() {
		return gameRoomPanel;
	}

	public ClientAccept getClientAccept() {
		return clientAccept;
	}
}

