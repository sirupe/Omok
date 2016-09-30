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
	//�̹��� ȭ�� ������ ���缭 �ٲ�� �ϱ� ���� �̹��� ���
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
		
		//����̹��� ������� �ػ󵵿� ���� �����ǰ� ����
		this.reimage = ImageIO.read(new File("resources/login/background.jpg")).getScaledInstance(
					LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(),
					LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize(),
					Image.SCALE_SMOOTH);
		
		this.setContentPane(new JLabel(new ImageIcon(reimage)));   
		
		//������ ȭ�� ��� ��ġ ����
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
	
	// GameRoom �� �ᱹ �г��̹Ƿ� �г� ������ �͸�Ŭ������ �̿��Ͽ�  paintComponent �� �������̵� �ϸ�
	// ���� GameRoom �ȿ��� ���ο� �г��� �����Ͽ� ����� ����� �ʿ䰡 ���� �ȴ�. 
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

