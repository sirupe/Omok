package frames.searchFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

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
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;
import utility.RegexCheck;
	
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
	Font default_Font  = SearchIdEnum.LABELFONT_DEFAULT.getFont(); //일반
	Font error_FONT    = SearchIdEnum.LABELFONT_ERROR.getFont(); //에러
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
		
		this.searchAnswerMsgLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
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
		
		this.searchTimeLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
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
	}
	
	//인증버튼을 눌렀을때 처리하는 메소드
	public void getCerfication() {
		this.emailAddr = this.getSearchemailTextField().getText();
		this.idCheck   = this.getSearchIdTextField().getText();
		this.checkMsg = null;

		if(!RegexCheck.emailRegexCheck(this.emailAddr) &&this.idCheck.isEmpty() && this.emailAddr.isEmpty()) {
		this.userNumberMsgReset();
		checkMsg = "<html>email형식에 맞지 않습니다..<br>다시 적어주세요<br></html>";
		this.userNumberMsg(checkMsg);
		
		} 
		if(RegexCheck.emailRegexCheck(this.emailAddr)) {
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW_EMAIL);
		//맞다면 이메일 인증 보내달라...?
			personalDTO.setUserID(this.idCheck);
			personalDTO.setUserEmail(this.emailAddr);
			
			System.out.println("여기는들어오나안오나... 들어와라 제발");
			this.searchPwdFrame.getCerficartion(personalDTO); //action이 보내준 것을 패널이 받아 searchPwdFrame한테 위에 메세지를 보내준다.
			
			//3분 쓰레드 ..
			this.userNumberMsgReset();
			checkMsg = "<html>인증번호 발생.<br>인증번호 적어주세요<br></html>";
			this.userNumberMsg(checkMsg);
	
	
	new Thread() {
		@Override
		public void run() {
			StringBuffer time = new StringBuffer();
			
		for(int i = 0; i >= 0; --i) {
			for(int j = (i >= 3) ? 0 : 59; j >= 0; j--) {
				time.delete(0, time.length());
				time.append(i);
				time.append(" : ");
		time.append(j < 10 ? "0" +  j : j);
		
		SearchPwdPanel.getSearchTimeLabel().setText(time.toString());
		try {
			Thread.sleep(1000);
			if(emailConfirmLimitTime) {
				this.interrupt();
				//userNumberMsg().setEditable(false);
					}
				} catch (InterruptedException e) {
					break;
				}
			}
			if(emailConfirmLimitTime) {
				return;
			}
		}
	SearchPwdPanel.getSearchTimeLabel().setVisible(false);
	String checkMsg = "<html>인증번호가 맞지 않습니다..<br>다시 확인바랍니다.<br></html>";
	SearchPwdPanel.this.userNumberMsg(checkMsg);
	//인증번호 초기화 한다.
	SearchPwdPanel.this.searchConfirmButton = null;
		    	}
		   }.start();
		    	}
		    }
	
	
	public void addKeyAction(JComponent comp, String Name) {
//		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(Name);
//		comp.setBorder(border);
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

