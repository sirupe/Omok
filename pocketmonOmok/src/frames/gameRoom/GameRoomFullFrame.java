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
		
		//방 확인 내용출력 라벨
		this.RoomConfirmLabel = new JLabel("<html>현재 빈방이 없습니다..<br>방을 만들어 주세요<br></html>");
		//폰트 searchIdEnum 에서 가져옴
		this.RoomConfirmLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.RoomConfirmLabel.setForeground(GameRoomFullEnum.LABELCOLOR_DEFAULT.getColor());
		
		//확인버튼생성
		this.RoomConfirmButton = new JButton();
		RoomConfirmButton.setBorderPainted(false);
		RoomConfirmButton.setFocusPainted(false);
		RoomConfirmButton.setContentAreaFilled(false);
		
		
		
		//배경화면	
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
		//화면 불러오기
		this.getLabelFrame();
		this.getButtonFrame();
		
		this.setLayout(null);
	    this.setTitle("");
	    this.setVisible(true);
	    this.setResizable(false);	
	}
	//만들려는 방의 상태 라벨 ㅁ생성
	public void getLabelFrame() {
		this.RoomConfirmLabel.setBounds(GameRoomFullEnum.GAMEROOM_CONFIRM_LABEL.getRectangle());
		this.add(RoomConfirmLabel);
	}
	
	//확인 버튼
	public void getButtonFrame() throws IOException {
		
		// 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
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
