package frames;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import enums.searchPwdEnum;
import enums.searchRePwdEnum;

public class SearchChangePanel extends JPanel {
	private Image backGround;
	private JLabel changeConfirmLabel;
	
	private JButton changeConfirmButton;

	public SearchChangePanel() throws IOException {
		
		String searchChange ="<html>비밀번호 변경이 완료 되었습니다."
							+ "<br>다시 로그인해주세요!<br></html>";
		this.changeConfirmLabel = new JLabel(searchChange);
		this.changeConfirmLabel.setForeground(searchRePwdEnum.LABELCOLOR_ERROR.getColor());
		
		//확인 버튼 생성 -- > 확인 누르면 login 화면으로
		changeConfirmButton  = new JButton();
		
		changeConfirmButton.setBorderPainted(false);
		changeConfirmButton.setFocusPainted(false);
		changeConfirmButton.setContentAreaFilled(false);
		this.add(changeConfirmButton);
		
		//레이블 폰트
		Font default_Font  = searchRePwdEnum.LABELFONT_DEFAULT.getFont(); //일반
		this.changeConfirmLabel.setFont(default_Font);
		
		//배경화면	
		backGround = ImageIO.read(new File("resources/signUp/backg.png")).getScaledInstance(
				searchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				searchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		this.add(new JLabel(new ImageIcon(backGround)));

		this.setBounds(
			searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
			searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
			searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
			searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
);
		this.setLabelPosition();
		this.setButtonPosition();
		
		this.setLayout(null);
	    this.setVisible(true);    
	}
	//레이블 위치 
	public void setLabelPosition() {
		this.changeConfirmLabel.setBounds(searchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle());
		this.add(changeConfirmLabel);
	}
	
	// 버튼 이미지 입히기  - Enum은 searchRePwdPanel에서 추가해서 씀
	public void setButtonPosition() throws IOException {
		this.changeConfirmButton.setIconTextGap(this.changeConfirmButton.getIconTextGap() - 15);
		this.changeConfirmButton.setBounds(searchRePwdEnum.SEARCH_REPWD_TEXTFIELD.getRectangle());
		
    	this.changeConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/confirm.jpg")).getScaledInstance(
    						searchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle().width,
    						searchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle().height,
    					Image.SCALE_AREA_AVERAGING))
    		);

    	this.changeConfirmButton.setBounds(searchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
    	
    	 
    	this.add(changeConfirmButton);
    	
	}
	
	
}
