package frames;

import java.awt.CardLayout;
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
import javax.swing.JPanel;

import client.ClientAccept;
import datas.UserPositionIndex;
import enums.LoginFrameSizesEnum;
// 태성
import enums.UserPositionEnum;

@SuppressWarnings("serial")
public class BasicFrame extends JFrame implements Serializable{
	//이미지 화면 비율에 맞춰서 바뀌게 하기 위해 이미지 사용
	private Image reimage;
	private CardLayout cardLayout;
	private LoginPanel loginPanel;
	private JPanel waitingRoomPanel;
	
	private ClientAccept clientAccept;
	
	public BasicFrame(ClientAccept clientAccept) throws IOException {
		this.clientAccept = clientAccept;
		
		//배경이미지 모니터의 해상도에 따라 조절되게 설정
		this.reimage = ImageIO.read(new File("resources/login/background.jpg")).getScaledInstance(
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(),
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize(),
					Image.SCALE_SMOOTH);
		
		this.setContentPane(new JLabel(new ImageIcon(reimage)));   
		
		//프레임 화면 출력 위치 설정
		this.setBounds(   
					LoginFrameSizesEnum.LOGIN_FRAME_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_FRAME_POSITION_Y.getSize(),
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), 
					LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize()
		);
		
		this.loginPanel = new LoginPanel(this);
		this.loginPanel.setOpaque(false);
		this.cardLayout = new CardLayout();
		
		this.waitingRoomPanel = new WaitingroomPanel();
		
		this.gameExit();
		this.setLayout(this.cardLayout);
		this.add("loginPanel", this.loginPanel);
		this.add("waitingRoomPanel", this.waitingRoomPanel);
		this.setTitle("Login");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void gameExit() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("인덱스를 전송합니다.");
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
	
	public void inWaitingRoom() {
		this.cardLayout.show(this.getContentPane(), "waitingRoomPanel");
	}

	public ObjectOutputStream getClientOS() {
		return this.clientAccept.getClientOS();
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
}