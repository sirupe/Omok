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
	private JPanel backGround;
	private JLabel changeConfirmLabel;
	
	private JButton changeConfirmButton;

	public SearchChangePanel() {
		String searchChange = "비밀번호 변경이 완료 되었습니다. /n" +
								"다시 로그인 해주세요";
		this.changeConfirmLabel = new JLabel(searchChange);
		this.changeConfirmLabel.setForeground(searchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		changeConfirmButton  = new JButton();
		
		changeConfirmButton.setBorderPainted(false);
		changeConfirmButton.setFocusPainted(false);
		changeConfirmButton.setContentAreaFilled(false);
		this.add(changeConfirmButton);
		
		//레이블 폰트
		Font default_Font  = searchPwdEnum.LABELFONT_DEFAULT.getFont(); //일반
		this.changeConfirmLabel.setFont(default_Font);
		
//		//레이블, 텍스트, 버튼 불러오기
        this.setLabelPosition();
	     try {
			this.setButtonPosition();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	     
//	     this.add(this.searchPwdPanel);
//	     this.setLayout(new CardLayout());
	
	}
	public void setLabelPosition() {
		
	}
	public void setButtonPosition() throws IOException {
		this.changeConfirmButton.setIconTextGap(this.changeConfirmButton.getIconTextGap() - 15);
    	
    	this.changeConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/up_up_confirm.jpg")).getScaledInstance(
    						searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
    						searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
    					Image.SCALE_AREA_AVERAGING))
    		);

    	this.changeConfirmButton.setBounds(searchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
    	
    	 
    	this.add(changeConfirmButton);
	}
	
}
