package frames;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.searchPwdEnum;
import enums.searchRePwdEnum;

public class SearchRePwdPanel extends JPanel {
	private JLabel searchPwdLabel;
	private JLabel searchRePwdLabel;
	private JLabel searchPwdErrorLabel;
	
	private JTextField searchPwdTextField;
	private JTextField searchRePwdTextField;
	
	private JButton searchConfirmButton;
	private Image backGround;
	
	
	public SearchRePwdPanel() throws IOException {
		this.searchPwdLabel   = new JLabel("PW");
		this.searchRePwdLabel = new JLabel("PW재입력");
		
		this.searchPwdTextField   = new JTextField(10);
		this.searchRePwdTextField = new JTextField(10);
		
		this.add(searchPwdTextField);
		this.add(searchRePwdTextField);

		
		String searchErrorMsg = "error message";
		this.searchPwdErrorLabel = new JLabel(searchErrorMsg);
		this.searchPwdErrorLabel.setForeground(searchRePwdEnum.LABELCOLOR_ERROR.getColor());
		
		//버튼 생성
				searchConfirmButton  = new JButton();
				
				searchConfirmButton.setBorderPainted(false);
				searchConfirmButton.setFocusPainted(false);
				searchConfirmButton.setContentAreaFilled(false);
				this.add(searchConfirmButton);
		
		
		
		//텍스트 필드 테두리 없애기
				searchPwdTextField.setBorder(searchRePwdEnum.LABEL_DEFAULT.getBorder());
				this.searchPwdTextField.setOpaque(true);
				searchRePwdTextField.setBorder(searchRePwdEnum.LABEL_DEFAULT.getBorder());
				this.searchRePwdTextField.setOpaque(true);
				
					//레이블 폰트
					this.searchPwdLabel.setFont(searchRePwdEnum.LABELFONT_DEFAULT.getFont());
					this.searchRePwdLabel.setFont(searchRePwdEnum.LABELFONT_DEFAULT.getFont());
				
	//				 
	//				// 텍스트 폰트
					this.searchPwdTextField.setFont(searchRePwdEnum.LABELFONT_DEFAULT.getFont());
					this.searchRePwdLabel.setFont(searchRePwdEnum.LABELFONT_DEFAULT.getFont());
					this.searchPwdErrorLabel.setFont(searchRePwdEnum.LABELFONT_ERROR.getFont());
					
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
		
//		//레이블, 텍스트, 버튼 불러오기
	             this.setLabelPosition();
			     this.setTextFieldPosition();
			     this.setButtonPosition();    
				
				this.setLayout(null);
			    this.setVisible(true);
			 
	}
	public void setLabelPosition() {
		this.searchPwdLabel.setBounds(searchRePwdEnum.SEARCH_PWD_LABEL.getRectangle());
		this.searchRePwdLabel.setBounds(searchRePwdEnum.SEARCH_REPWD_LABEL.getRectangle());
		this.searchPwdErrorLabel.setBounds(searchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.add(searchPwdLabel);
		this.add(searchRePwdLabel);
		this.add(searchPwdErrorLabel);
	}
	public void setTextFieldPosition() {
		this.searchPwdTextField.setBounds(searchRePwdEnum.SEARCH_PWD_TEXTFIELD.getRectangle());
		this.searchRePwdTextField.setBounds(searchRePwdEnum.SEARCH_REPWD_TEXTFIELD.getRectangle());
		
    	this.add(searchPwdTextField);
    	this.add(searchRePwdTextField);
	}
	public void setButtonPosition() throws IOException {
		this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);
    	
    	this.searchConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/up_up_confirm.jpg")).getScaledInstance(
    						searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
    						searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
    					Image.SCALE_AREA_AVERAGING))
    		);

    	this.searchConfirmButton.setBounds(searchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
    	
    	 
    	this.add(searchConfirmButton);
	}



}
