package frames.searchFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.findIDandPW.FindPWAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;
import frames.BasicFrame;
import omokGame.client.ClientAccept;
	
	public class SearchPwdPanel extends JPanel {
	
		private JLabel searchIdLabel;
		private JLabel searchemailLabel;
		
		private JLabel searchAnswerMsgLabel;
		private static JLabel searchTimeLabel;
		
		private JTextField searchIdTextField;
		private JTextField searchemailTextField;
		private JTextField searchConfirmTextField;
		
		private JButton searchConfirmButton; //확인 버튼
		private JButton searchCancelButton; //취소 버튼 -- > 홈으로 가는 버튼
		private JButton searchCheckButton; // 인증 버튼
		private JButton confirmCheckButton; // 인증 번호와 일치하는지 보는확인 버튼
	
	//private Image backGround;
	
		private SearchPwdFrame searchPwdFrame;
		private FindPWAction findPwdAction;
		
		
		private String emailAddr; // 인증메일
		private String idCheck;
		private boolean emailConfirmLimitTime;
		private String checkMsg;
		
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
		this.emailConfirmLimitTime = false;
	
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
		Font default_Font  = SearchIDEnum.LABELFONT_DEFAULT.getFont(); //일반
		Font error_FONT    = SearchIDEnum.LABELFONT_ERROR.getFont(); //에러
		this.searchIdLabel.setFont(default_Font);
		this.searchemailLabel.setFont(default_Font);
		
		// 텍스트 폰트
		this.searchemailTextField.setFont(default_Font);
		this.searchIdTextField.setFont(default_Font);
		this.searchConfirmTextField.setFont(default_Font);
		
		
		String searchAnswer = "";
		this.searchAnswerMsgLabel = new JLabel(searchAnswer);
		
		this.searchTimeLabel = new JLabel("3:00");
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		
		//레이블, 텍스트, 버튼 불러오기
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
		
		
		this.addKeyAction(this.searchIdTextField, "searchIdTextField");
		this.addKeyAction(this.searchemailTextField, "searchemailTextField");
		this.addKeyAction(this.searchConfirmTextField, "confirmNumberText");
		
		//TODO
		this.searchIdTextField.setText("sujin");
		this.searchemailTextField.setText("tnwls@naver.com");
		this.searchConfirmTextField.setText("000");
	}
	
	//아이디 또는 이메일 오류 알려주는 텍스트

	//초기화가 되어서 위에 있는 searchErrorMsg의 메세지를 불러온다.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------
	//인증번호가 발송 되었다면 나타나는 메세지
	public void userNumberMsg(String searchAnswer){
		this.setLayout(null);
		this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchAnswerMsgLabel.setOpaque(false);
		this.add(this.searchAnswerMsgLabel);
		
		this.searchAnswerMsgLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.searchAnswerMsgLabel.setForeground(Color.blue);
		this.searchAnswerMsgLabel.setText(searchAnswer);
	}
	
	public void userNumberMsgReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	//-----------------------------------------------------------------------------
	//3분 스레드
	public void limitTimeMsg(String time) {
		this.setLayout(null);
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.searchTimeLabel.setOpaque(false);
		this.add(this.searchTimeLabel);
		
		this.searchTimeLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.searchTimeLabel.setForeground(SearchPwdEnum.LABELCOLOR_ERROR.getColor());
		this.searchTimeLabel.setText(time);
	}
	
	public void limitTimeMsg() {
		String init = "";
		this.searchTimeLabel.setText(init);
	
	}
	//이름, 이메일 , 에러 메세지  레이블!!!!!! 위치 및 크기
	public void setLabelPosition() {
		//아이디라벨 위치및 크기
	this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
	//이메일 라벨 위치 및 크기
	this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
	//에러 메세지 위치 및 크기
	//
	this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
	//3분 타임 라인 위치 및 크기
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.add(searchIdLabel);
		this.add(searchemailLabel);
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

	}
	
	//버튼 생성
	public void setButtonPosition() throws IOException {   	

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
	this.searchCancelButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());
	this.searchCancelButton.addActionListener(this.findPwdAction); 
	
	
	//-------------------------------------------------------------------------------------------------
	//확인 버튼 -- > 누르면 비밀변경 카드레이아웃으로 간다.
	
	
	this.searchCheckButton  = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.setIconTextGap(this.searchCheckButton.getIconTextGap() - 15);    	
	this.searchCheckButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
	);
	this.searchCheckButton.setBounds(SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle()); 
	this.add(searchCheckButton); 
	//this.searchCheckButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.addActionListener(this.findPwdAction); 
	} 
	
	//====================액션처리========================================================================================================
	//취소버튼을 눌렀을 때 처리하는 메소드
	public void doCancelButton() {
		this.searchPwdFrame.doCancelButton(); //여기에서 searchPwdFrame에 접근해서 docancel실행
	}
	
	public void getChangePanel() {
		this.searchPwdFrame.doCheckButton();
		System.out.println("여기 바뀌는 채널");
	}
	
	//인증메일 보내기
	public void getCerfication() {
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
		ClientAccept clientAccpet = basicFrame.getClientAccept();
		ObjectOutputStream oos = clientAccpet.getClientOS(); 
		
		String email = this.searchemailTextField.getText();
		
		
		UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfoDTO.setUserEmail(email);
		
		System.out.println(userPersonalInfoDTO.toString() + " :  이메일");
		
		try {
			oos.writeObject(userPersonalInfoDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addKeyAction(JComponent comp, String Name) {
		comp.setName(Name);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this.findPwdAction);
		this.add(comp);	
	}
	

	
	
    public SearchPwdFrame getSearchPwdFrame() {
    	return searchPwdFrame;
    }
    public JLabel getSearchIdLabel() {
    	return searchIdLabel;
    }
    public JLabel getSearchemailLabel() {
    	return searchemailLabel;
    }
    public JLabel getSearchAnswerMsg() {
    	return searchAnswerMsgLabel;
    }
    public static JLabel getSearchTimeLabel() {
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
    public JButton getConfirmCheckButton() {
    	return confirmCheckButton;
    }
    public JButton getsearchCheckButton() {
    	return searchCheckButton;
    }
	
	
	    
	}

