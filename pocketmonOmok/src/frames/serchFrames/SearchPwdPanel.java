package frames.serchFrames;

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

import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;

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
		
		//에러 메세지 레이블
		String searchErrorMsg = "<html>3분초과가 되었습니다.<br>다시 인증을 받아 주세요<br></html>";
		this.searchErrorMsgLabel = new JLabel(searchErrorMsg);
		this.searchErrorMsgLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		String searchAnswer = "<html>고객님의 이메일로 "
				           + "<br>인증번호가 발송되었습니다.<br></html>";
		this.searchAnswerMsg = new JLabel(searchAnswer);
		this.searchAnswerMsg.setForeground(SearchPwdEnum.LABELCOLOR_DEFAULT.getColor());
		
		String time = "3:00";
		this.searchTimeLabel = new JLabel(time);
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		

		//this.add(searchConfirmButton);
		
		
		//레이블 폰트 - searchIdEnum 에서 불러왔습니다.
		Font default_Font  = SearchIdEnum.LABELFONT_DEFAULT.getFont(); //일반
		Font error_FONT    = SearchIdEnum.LABELFONT_ERROR.getFont(); //에러
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
		
		
//		//레이블, 텍스트, 버튼 불러오기
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
	 
	}
//	//이름, 이메일 , 에러 메세지  레이블!!!!!! 위치 및 크기
		public void setLabelPosition() {
			//아이디라벨 위치및 크기
			this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
			//이메일 라벨 위치 및 크기
			this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
			//에러 메세지 위치 및 크기
			this.searchErrorMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
			//
			this.searchAnswerMsg.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
			//3분 타임 라인 위치 및 크기
			this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
			this.add(searchIdLabel);
			this.add(searchemailLabel);
			this.add(searchErrorMsgLabel);
			this.add(searchAnswerMsg);
			this.add(searchTimeLabel);
			this.searchIdLabel.setOpaque(false);
	    }
		
//	// 이름 이메일, 인증 텍스트필드!!!!!!!!!!!!!! 위치 및 크기
	    public void setTextFieldPosition() {
	    	//아이디 텍스트 필드
	    	this.searchIdTextField.setBounds(SearchPwdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
	    	//이메일 입력창 
	    	this.searchemailTextField.setBounds(SearchPwdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	    	//인증 번호 입력창
	    	this.searchConfirmTextField.setBounds(SearchPwdEnum.SEARCH_CONFIRM_TEXTFIELD.getRectangle());
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
	    				new File("resources/yesno/certify.kor.png")).getScaledInstance(
	    						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
	    						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
	    					Image.SCALE_AREA_AVERAGING))
	    		);
	    	this.searchConfirmButton.setBounds(SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle()); 
	    	this.add(searchConfirmButton);    	 
	    }   
	    
	    public SearchPwdFrame getSearchPwdMain() {
	    	return searchPwdFrame;
	    }
	    
	}

