package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import enums.frames.searchPwdEnum;

public class SearchPwdPanel extends JPanel {

	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	private JLabel searchErrorMsgLabel;
	private JLabel searchAnswerMsg;
	private JLabel searchTimeLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private JTextField searchConfirmTextField;
	
	private JButton searchConfirmButton;
	//private Image backGround;
	
	private SearchPwdFrame searchPwdFrame;
	
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.setLayout(null);
		this.searchPwdFrame = searchPwdFrame;	     
	     
		//라벨 생성 TODO
		this.searchIdLabel    = new JLabel("ID");
		this.searchemailLabel = new JLabel("Email");
	
//		텍스트 필드생성
		this.searchIdTextField      = new JTextField(10);
		this.searchemailTextField   = new JTextField(10);
		this.searchConfirmTextField = new JTextField(10);
		
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		
//		//에러 메세지 레이블
		String searchErrorMsg = "<html>3분초과가 되었습니다.<br>다시 인증을 받아 주세요<br></html>";
		this.searchErrorMsgLabel = new JLabel(searchErrorMsg);
		this.searchErrorMsgLabel.setForeground(searchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		String searchAnswer = "<html>고객님의 이메일로 "
				           + "<br>인증번호가 발송되었습니다.<br></html>";
		this.searchAnswerMsg = new JLabel(searchAnswer);
		this.searchAnswerMsg.setForeground(searchPwdEnum.LABELCOLOR_DEFAULT.getColor());
		
		String time = "3:00";
		this.searchTimeLabel = new JLabel(time);
		this.searchTimeLabel.setForeground(searchPwdEnum.LABELCOLOR_ERROR.getColor());
		

		//this.add(searchConfirmButton);
		
		
		//레이블 폰트
		Font default_Font  = searchPwdEnum.LABELFONT_DEFAULT.getFont(); //일반
		Font error_FONT    = searchPwdEnum.LABELFONT_ERROR.getFont(); //에러
		this.searchIdLabel.setFont(default_Font);
		this.searchemailLabel.setFont(default_Font);
		this.searchErrorMsgLabel.setFont(error_FONT);
//		
//		// 텍스트 폰트
		this.searchemailTextField.setFont(default_Font);
		this.searchIdTextField.setFont(default_Font);
		this.searchConfirmTextField.setFont(default_Font);
		
		this.searchErrorMsgLabel.setFont(error_FONT);
		this.searchAnswerMsg.setFont(error_FONT);
		
//		
//		//텍스트 필드 테두리 없애기
		EmptyBorder emptyBorder = searchPwdEnum.LABEL_DEFAULT.getBorder();
		searchIdTextField.setBorder(emptyBorder);
		//this.searchIdTextField.setOpaque(true);
		searchemailTextField.setBorder(emptyBorder);
		//this.searchemailTextField.setOpaque(true);
		searchConfirmTextField.setBorder(emptyBorder);
		//this.searchConfirmTextField.setOpaque(true);
		
		
//		//레이블, 텍스트, 버튼 불러오기
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
	 
	}
//	//이름, 이메일 , 에러 메세지  레이블!!!!!! 위치 및 크기
		public void setLabelPosition() {
			this.searchIdLabel.setBounds(searchPwdEnum.SEARCH_ID_LABEL.getRectangle());
			this.searchemailLabel.setBounds(searchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
			this.searchErrorMsgLabel.setBounds(searchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
			this.searchAnswerMsg.setBounds(searchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
			this.searchTimeLabel.setBounds(searchPwdEnum.SEARCH_Time_LABEL.getRectangle());
			this.add(searchIdLabel);
			this.add(searchemailLabel);
			this.add(searchErrorMsgLabel);
			this.add(searchAnswerMsg);
			this.add(searchTimeLabel);
			this.searchIdLabel.setOpaque(false);
	    }
		
//	// 이름 이메일, 인증 텍스트필드!!!!!!!!!!!!!! 위치 및 크기
	    public void setTextFieldPosition() {
	    	this.searchIdTextField.setBounds(searchPwdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
	    	this.searchemailTextField.setBounds(searchPwdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	    	this.searchConfirmTextField.setBounds(searchPwdEnum.SEARCH_CONFIRM_TEXTFIELD.getRectangle());
	    	this.add(searchIdTextField);
	    	this.add(searchemailTextField);
	    	this.add(searchConfirmTextField);
	    }
	
	    public void setButtonPosition() throws IOException {   	
			//버튼 생성
			this.searchConfirmButton  = new JButton();
			
			this.searchConfirmButton.setBorderPainted(false);
			this.searchConfirmButton.setFocusPainted(false);
			this.searchConfirmButton.setContentAreaFilled(false);
	    	this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	    	this.searchConfirmButton.setIcon(
	    			new ImageIcon(ImageIO.read(
	    				new File("resources/signUp/up_up_confirm.jpg")).getScaledInstance(
	    						searchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
	    						searchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
	    					Image.SCALE_AREA_AVERAGING))
	    		);
	    	this.searchConfirmButton.setBounds(searchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle()); 
	    	this.add(searchConfirmButton);    	 
	    }   
	    
	    public SearchPwdFrame getSearchPwdMain() {
	    	return searchPwdFrame;
	    }
	    
	}

