package frames.searchFrames;

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

import actions.findIDandPW.FindPWAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;
import frames.LoginPanel;
import utility.RegexCheck;

public class SearchPwdPanel extends JPanel {

	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	
	private JLabel searchErrorMsgLabel;
	private JLabel searchAnswerMsgLabel;
	private JLabel searchTimeLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private JTextField searchConfirmTextField;
	
	private JButton searchConfirmButton;
	private JButton searchCancelButton;
	//private Image backGround;
	
	private SearchPwdFrame searchPwdFrame;
	private FindPWAction findPwdAction;
	
	
	private String emailAddr;
	
	
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
//		this.addWin

	    this.setLayout(null);
	
		//라벨 생성 TODO
		this.searchIdLabel    = new JLabel("ID");
		this.searchemailLabel = new JLabel("Email");
	
//		텍스트 필드생성
		this.searchIdTextField      = new JTextField(10);
		this.searchemailTextField   = new JTextField(10);
		this.searchConfirmTextField = new JTextField(10);
		
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		
		//this.add(searchConfirmButton);
		//레이블 폰트 - searchIdEnum 에서 불러왔습니다.
		Font default_Font  = SearchIdEnum.LABELFONT_DEFAULT.getFont(); //일반
		Font error_FONT    = SearchIdEnum.LABELFONT_ERROR.getFont(); //에러
		this.searchIdLabel.setFont(default_Font);
		this.searchemailLabel.setFont(default_Font);
		
		// 텍스트 폰트
		this.searchemailTextField.setFont(default_Font);
		this.searchIdTextField.setFont(default_Font);
		this.searchConfirmTextField.setFont(default_Font);


		//에러 메세지 레이블
		String searchErrorMsg = "<html>3분초과가 되었습니다.<br>다시 인증을 받아 주세요<br></html>";
		this.searchErrorMsgLabel = new JLabel(searchErrorMsg);
		
		String searchAnswer = "<html>고객님의 이메일로 "
				           + "<br>인증번호가 발송되었습니다.<br></html>";
		this.searchAnswerMsgLabel = new JLabel(searchAnswer);
		
		String time = "3:00";
		this.searchTimeLabel = new JLabel(time);
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		//레이블, 텍스트, 버튼 불러오기
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
	}
	
	//아이디 또는 이메일 오류 알려주는 텍스트
	public void userInfoError(String searchErrorMsg){
		this.setLayout(null);
		this.searchErrorMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchErrorMsgLabel.setOpaque(false);
		this.add(this.searchErrorMsgLabel);
		
		this.searchErrorMsgLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.searchErrorMsgLabel.setForeground(Color.RED);
		this.searchErrorMsgLabel.setText(searchErrorMsg);
	}
	//초기화가 되어서 위에 있는 searchErrorMsg의 메세지를 불러온다.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchErrorMsgLabel.setText(init);
	}
//-----------------------------------------------------------------------------------------------------------------------------------------
	//인증번호가 발송 되었다면 나타나는 메세지
	public void userNumberMsg(String searchAnswer){
		this.setLayout(null);
		this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchAnswerMsgLabel.setOpaque(false);
		this.add(this.searchAnswerMsgLabel);
		
		this.searchAnswerMsgLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.searchAnswerMsgLabel.setForeground(Color.blue);
		this.searchAnswerMsgLabel.setText(searchAnswer);
		System.out.println("ANJID..");
	}
	
	public void userNumberMsgReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	
	//이름, 이메일 , 에러 메세지  레이블!!!!!! 위치 및 크기
		public void setLabelPosition() {
			//아이디라벨 위치및 크기
			this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
			//이메일 라벨 위치 및 크기
			this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
			//에러 메세지 위치 및 크기
			this.searchErrorMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
			//
			this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
			//3분 타임 라인 위치 및 크기
			this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
			this.add(searchIdLabel);
			this.add(searchemailLabel);
			this.add(searchErrorMsgLabel);
			this.add(searchAnswerMsgLabel);
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
			this.searchConfirmButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName());
			
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
	    	this.searchConfirmButton.addActionListener(this.findPwdAction);
	    	
	    	
	    	//취소버튼
	    	this.searchCancelButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());

			this.searchCancelButton.setBorderPainted(false);
			this.searchCancelButton.setFocusPainted(false);
			this.searchCancelButton.setContentAreaFilled(false);
			
	    	this.searchCancelButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	    	this.searchCancelButton.setIcon(
	    			new ImageIcon(ImageIO.read(
	    				new File("resources/login/back.png")).getScaledInstance(
	    						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
	    						SearchPwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
	    					Image.SCALE_AREA_AVERAGING))
	    	);
	    	this.searchCancelButton.setBounds(SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle()); 
	    	this.add(searchCancelButton); 
	    	this.searchCancelButton.addActionListener(this.findPwdAction); 
	    	}  
	      
		    //취소버튼을 눌렀을 때 처리하는 메소드
		    public void doCancelButton() {
		    	this.searchPwdFrame.doCancelButton(); //여기에서 searchPwdFrame에 접근해서 docancel실행
		    }
	    
		    //인증버튼을 눌렀을때 처리하는 메소드
		    public void getCerfication() {
		    	this.emailAddr = this.getSearchemailTextField().getText();
		    	
		    	String checkMsg = null;
	    	
		    	if(this.getSearchIdTextField().getText().isEmpty() || this.getSearchemailTextField().getText().isEmpty()) {
		    		this.userInfoErrorLabelReset();
		    		this.userInfoError("아이디와 이메일 입력해주세요");
				
		    	} else if (!RegexCheck.emailRegexCheck(this.emailAddr)) {
		    		this.userInfoErrorLabelReset();
		    		checkMsg = "<html>email형식에 맞지 않습니다..<br>다시 적어주세요<br></html>";
		    		this.userInfoError(checkMsg);	
	    		
		    	} else {
		    		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW_EMAIL);
		    		//맞다면 이메일 인증 보내달라...?
		    		personalDTO.setUserID(this.getSearchIdTextField().getText());
		    		personalDTO.setUserEmail(this.getSearchemailTextField().getText());
		    		checkMsg = "<html>인증번호 발송되었습니다.<br>인증번호 적어주세요<br></html>";
		    		this.userNumberMsg(checkMsg);
		    		this.userInfoErrorLabelReset();
				
		    		this.searchPwdFrame.getCerficartion(personalDTO); //action이 보내준 것을 패널이 받아 searchPwdFrame한테 위에 메세지를 보내준다.
		    	} 
		    }
	    
	    public SearchPwdFrame getSearchPwdMain() {
	    	return searchPwdFrame;
	    }
	    public JLabel getSearchIdLabel() {
	    	return searchIdLabel;
	    }
	    public JLabel getSearchemailLabel() {
	    	return searchemailLabel;
	    }
	    public JLabel getSearchErrorMsgLabel() {
	    	return searchErrorMsgLabel;
	    }
	    public JLabel getSearchAnswerMsg() {
	    	return searchAnswerMsgLabel;
	    }
	    public JLabel getSearchTimeLabel() {
	    	return searchTimeLabel;
	    }
	    public JTextField getSearchIdTextField() {
	    	return searchIdTextField;
	    }
	    public JTextField getSearchemailTextField() {
	    	return searchemailTextField;
	    }
	    public JTextField getSearchConfirmTextField() {
	    	return searchConfirmTextField;
	    }
	    public JButton getSearchConfirmButton() {
	    	return searchConfirmButton;
	    }
	    public JButton getSearchCancelButton() {
	    	return searchCancelButton;
	    }

	    
	}

