package frames.gameRoom;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.GameRoomFullEnum;
import enums.frames.GameStoreEnum;
import enums.frames.SearchIdEnum;

public class GameRoomFullFrame extends JFrame {
	private JLabel RoomConfirmLabel;
	private JButton RoomConfirmButton;
	private Image backGround;
	
	public GameRoomFullFrame() throws IOException {
		
		//�� Ȯ�� ������� ��
		this.RoomConfirmLabel = new JLabel("<html>���� ����� �����ϴ�..<br>���� ����� �ּ���<br></html>");
		//��Ʈ searchIdEnum ���� ������
		this.RoomConfirmLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.RoomConfirmLabel.setForeground(GameRoomFullEnum.LABELCOLOR_DEFAULT.getColor());
		
		//Ȯ�ι�ư����
		this.RoomConfirmButton = new JButton();
		RoomConfirmButton.setBorderPainted(false);
		RoomConfirmButton.setFocusPainted(false);
		RoomConfirmButton.setContentAreaFilled(false);
		
		
		
		//���ȭ��	
		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchIdEnum.SEARCHFRAME_SIZE_WIDTH.getSize(),
				SearchIdEnum.SEARCHFRAME_SIZE_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
				
		this.setBounds(
				GameRoomFullEnum.GAMEROOMFULLFRAME_POSITION_X.getSize(),
				GameRoomFullEnum.GAMEROOMFULLFRAME_POSITION_Y.getSize(),
				GameRoomFullEnum.GAMEROOMFULLFRAME_SIZE_WIDTH.getSize(),
				GameRoomFullEnum.GAMEROOMFULLFRAME_SIZE_HEIGHT.getSize()
		);
		//ȭ�� �ҷ�����
		this.getLabelFrame();
		this.getButtonFrame();
		
		this.setLayout(null);
	    this.setTitle("");
	    this.setVisible(true);
	    this.setResizable(false);	
	}
	//������� ���� ���� �� ������
	public void getLabelFrame() {
		this.RoomConfirmLabel.setBounds(GameRoomFullEnum.GAMEROOM_CONFIRM_LABEL.getRectangle());
		this.add(RoomConfirmLabel);
	}
	
	//Ȯ�� ��ư
	public void getButtonFrame() throws IOException {
		
		// ��ư�̹��� ©���°� �̹��� �����̵����� �ذ�����
    	this.RoomConfirmButton.setIconTextGap(this.RoomConfirmButton.getIconTextGap() - 15);
    	

    	this.RoomConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/confirm.jpg")).getScaledInstance(
    						GameRoomFullEnum.GAMEROOM_CONFIRM_BUTTON.getRectangle().width,
    						GameRoomFullEnum.GAMEROOM_CONFIRM_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
    	this.RoomConfirmButton.setBounds(GameRoomFullEnum.GAMEROOM_CONFIRM_BUTTON.getRectangle());
    	this.add(RoomConfirmButton);    	 
    }
	public static void main(String[] args) throws IOException {
		new GameRoomFullFrame();

	}

}
