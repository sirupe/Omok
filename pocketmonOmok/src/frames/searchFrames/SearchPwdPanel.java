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
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginPanelEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;
import frames.BasicFrame;
import omokGame.client.ClientAccept;
	
@SuppressWarnings("serial")
public class SearchPwdPanel extends JPanel {

	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	
	private JLabel searchAnswerMsgLabel;
	private JLabel searchTimeLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private JTextField searchConfirmTextField;
	
	private JButton searchConfirmButton; //인증 버튼
	private JButton searchCancelButton; //취소 버튼 -- > 홈으로 가는 버튼
	private JButton searchCheckButton; // 확인버튼
	private JButton checkNumberButton; // 인증 번호와 일치하는지 보는확인 버튼
	
	
	private SearchPwdFrame searchPwdFrame;
	private FindPWAction findPwdAction;
	
	private boolean isEmailConfirmLimitTime;
	private boolean isConfirmNumberSuccess;
	
	
	
	public SearchPwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		
		this.searchPwdFrame = searchPwdFrame;
		this.findPwdAction = new FindPWAction(this);
		
		
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
		this.searchTimeLabel.setVisible(false);
		
		//레이블, 텍스트, 버튼 불러오기
		this.setLabelPosition();
		this.setTextFieldPosition();
		this.setButtonPosition();
		this.userInfoErrorLabelReset(); 
		this.userNumberMsgReset();
		
		
		this.addKeyAction(this.searchIdTextField, "searchIdTextField");
		this.addKeyAction(this.searchemailTextField, "searchemailTextField");
		this.addKeyAction(this.searchConfirmTextField, "confirmNumberText");
		this.searchConfirmTextField.setVisible(false);
	}
	
	//초기화가 되어서 위에 있는 searchErrorMsg의 메세지를 불러온다.
	public void userInfoErrorLabelReset() {
		String init = "";
		this.searchAnswerMsgLabel.setText(init);
	}
	
	
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
	
	//이름, 이메일 , 에러 메세지  레이블 위치 및 크기
	public void setLabelPosition() {
		
	//아이디라벨 위치및 크기
	this.searchIdLabel.setBounds(SearchPwdEnum.SEARCH_ID_LABEL.getRectangle());
	
	//이메일 라벨 위치 및 크기
	this.searchemailLabel.setBounds(SearchPwdEnum.SEARCH_EMAIL_LABEL.getRectangle());
	
	//에러 메세지 위치 및 크기
	this.searchAnswerMsgLabel.setBounds(SearchPwdEnum.SEARCH_ANSWER_LABEL.getRectangle());
	
	//3분 타임 라인 위치 및 크기
		this.searchTimeLabel.setBounds(SearchPwdEnum.SEARCH_Time_LABEL.getRectangle());
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		this.add(searchAnswerMsgLabel);
		this.add(searchTimeLabel);
		this.searchIdLabel.setOpaque(false);
	}
	
	//이름 이메일, 인증 텍스트필드 위치 및 크기
	public void setTextFieldPosition() {
		
	//아이디 텍스트 필드
	this.searchIdTextField.setBounds(SearchPwdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
	
	//이메일 입력창 
	this.searchemailTextField.setBounds(SearchPwdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	
	//인증 번호 입력창
		this.searchConfirmTextField.setBounds(SearchPwdEnum.SEARCH_CONFIRM_TEXTFIELD.getRectangle());
		this.add(searchIdTextField);
		this.add(searchemailTextField);
		
	} // 생성자 끝
	
	//버튼 생성 -- 인증버튼
	public void setButtonPosition() throws IOException {   	
	
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
	this.searchConfirmButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName());
	
	this.add(searchConfirmButton);
	this.getSearchConfirmButton().setVisible(true);
	
	this.searchConfirmButton.addActionListener(this.findPwdAction);
	
	
	//인증 버튼을 누른 후 바뀌는 확인 버튼
	this.checkNumberButton  = new JButton();
	
	this.checkNumberButton.setBorderPainted(false);
	this.checkNumberButton.setFocusPainted(false);
	this.checkNumberButton.setContentAreaFilled(false);
	
	this.checkNumberButton.setIconTextGap(this.checkNumberButton.getIconTextGap() - 15);    	
	this.checkNumberButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
		);
	
	this.checkNumberButton.setBounds(SearchPwdEnum.SEARCH_CONFIRM_CHECK_BUTTON.getRectangle()); 
	this.checkNumberButton.setName(LoginPanelEnum.BUTTON_NAVE_CONFIRM_NUMBER.getButtonName());
	
	this.add(checkNumberButton);
	this.checkNumberButton.setVisible(false);
	
	this.checkNumberButton.addActionListener(this.findPwdAction);
	
	
	//취소버튼
	this.searchCancelButton  = new JButton();
	
	this.searchCancelButton.setBorderPainted(false);
	this.searchCancelButton.setFocusPainted(false);
	this.searchCancelButton.setContentAreaFilled(false);
	
	this.searchCancelButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);    	
	this.searchCancelButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/myData/reset.Kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
	);
	
	this.searchCancelButton.setBounds(SearchPwdEnum.SEARCH_CANCEL_BUTTON.getRectangle()); 
	this.add(searchCancelButton); 
	
	this.searchCancelButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName());
	this.searchCancelButton.addActionListener(this.findPwdAction); 
	
	
	//확인 버튼 -- > 누르면 비밀변경 카드레이아웃으로 간다.
	
	this.searchCheckButton  = new JButton();
	this.searchCheckButton.setIconTextGap(this.searchCheckButton.getIconTextGap() - 15);    	
	this.searchCheckButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/forgotPW/confirm.kor.png")).getScaledInstance(
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().width,
						SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle().height,
					Image.SCALE_AREA_AVERAGING))
			);
	
	this.searchCheckButton.setBorderPainted(false);
	this.searchCheckButton.setFocusPainted(false);
	this.searchCheckButton.setContentAreaFilled(false);
	
	this.searchCheckButton.setBounds(SearchPwdEnum.SEARCH_CHECK_BUTTON.getRectangle()); 
	this.add(searchCheckButton); 
	this.searchCheckButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName());
	this.searchCheckButton.addActionListener(this.findPwdAction); 
	} 
	
	//====================액션처리========================================================================================================
	
	//취소버튼을 눌렀을 때 처리하는 메소드
	public void doCancelButton() {
		this.searchPwdFrame.doCancelButton(); //여기에서 searchPwdFrame에 접근해서 docancel실행
	}
	
	public void getChangePanel(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		
		if(data.getUserCount() == 0) {
			this.getSearchIdTextField().setEditable(true);
			this.userNumberMsg("<html>등록된 아이디가 없습니다.." 
					+"<br>아이디 확인하세요<br></html>");
			
		} else {
			this.searchPwdFrame.doCheckButton();
		}
	}
	
	//인증메일 보내기
	public void getCerfication() {
		this.searchConfirmButton.setEnabled(false);
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
	
		String email = this.searchemailTextField.getText();
		String id    = this.searchIdTextField.getText();
		
		UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfoDTO.setUserAction(UserActionEnum.USER_SEARCH_CERTIFICATION_CHECK);
		userPersonalInfoDTO.setUserEmail(email);
		userPersonalInfoDTO.setUserID(id);
		
		basicFrame.sendDTO(userPersonalInfoDTO);
	}
	
	//이메일 인증번호 발송 성공 시 발생하는 메소드
	public void emailSuccess(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		System.out.println(data.isEmailSuccess());
		if(data.isEmailSuccess()) {
			this.getSearchemailTextField().setEditable(false);
			this.userNumberMsg("<html>인증번호 발송되었습니다." 
					+"<br>인증번호 입력 바랍니다.<br></html>");
			
			JButton btn;
			btn = this.searchConfirmButton;
			btn.setVisible(false);
			
			btn = this.checkNumberButton;
			btn.setVisible(true);
			
			this.searchConfirmTextField.setVisible(true);
			this.timeLimit();
		} else {
			this.userNumberMsg("이메일 발송 실패");
			this.searchemailTextField.setText(null);
			this.getSearchemailTextField().setEditable(true);
			this.searchConfirmTextField.setText(null);
		}
	
	}
	
	//3분스레드 메소드
	public void timeLimit() {
		this.searchTimeLabel.setVisible(true);
	
		new Thread(() -> {
			StringBuffer time = new StringBuffer();
			
			for(int i = 2; i >= 0; --i) {
				
				for(int j = (i >= 3) ? 0 : 59 ; j >= 0; j--) {
					time.delete(0, time.length());
					time.append(i);
					time.append(" : ");
					time.append(j < 10 ? "0" +  j : j);
			
					this.searchTimeLabel.setText(time.toString());
			
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
							
					}
				}
			}
	
			//시간이 지난 후 카운트 삭제
			this.searchTimeLabel.setText("");
			this.searchTimeLabel.setVisible(false);
			this.searchConfirmButton.setEnabled(true);
			
			//인증번호 초기화 한다.
			this.searchConfirmTextField.setText("");
			this.searchConfirmTextField.setVisible(false);
			this.checkNumberButton.setVisible(false);
			this.searchConfirmButton.setVisible(true); 
			
			if(this.isEmailConfirmLimitTime) {
				return;
			}
			
			//스레드가 완전히 정상종료 됐을때에는 실행하는거. 
			this.userNumberMsg("<html>인증메일을 발송하세요.</html>");
			this.checkNumberButton.setVisible(false);
			this.searchConfirmButton.setVisible(true); 
		}).start();
	}
	
	//확인 버튼 누를시 발생하는 메소드 -- > 인증번호이 입력된 후 발생 되는 메소드
	public void confirmNumberCheck() {
		String certificationNumber = getSearchConfirmTextField().getText();
		
		//TODO
		System.out.println(certificationNumber + " : 이건 사용자가 쓴 번호");
		
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
		ClientAccept clientAccpet = basicFrame.getClientAccept();
		ObjectOutputStream oos = clientAccpet.getClientOS(); 
		
		UserPersonalInfoDTO userPersonalInfoDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfoDTO.setUserAction(UserActionEnum.USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER);
		userPersonalInfoDTO.setCertificationNumber(certificationNumber);
	
		try {
			oos.writeObject(userPersonalInfoDTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//확인을 누를때 인증 번호가 맞을때 
	public void confirmNumberSuccess(AbstractEnumsDTO userPosition) {
		UserPersonalInfoDTO data = (UserPersonalInfoDTO) userPosition;
		
		if(data.isCertificationNumber()) {
			this.userNumberMsg("<html>인증완료!!" 
					+"<br>비밀번호다시설정해주세요.<br></html>");
			this.checkNumberButton.setVisible(false);
			this.searchConfirmTextField.setEditable(false);
			
			//시간이 지난 후 카운트 삭제
			this.searchTimeLabel.setText("");
			this.searchTimeLabel.setVisible(false);
			
			//인증번호 초기화 한다.
			this.searchConfirmTextField.setVisible(false);
			this.isEmailConfirmLimitTime = true;
			this.isConfirmNumberSuccess = true;
			return;
			
		} else {
			this.getSearchemailTextField().setEditable(false);
			
			JButton btn;
			btn = this.getSearchConfirmButton();
			btn.setVisible(false);
			
			btn = this.getCheckNumberButton();
			btn.setVisible(true);
			this.searchConfirmTextField.setVisible(true);
			this.userNumberMsg("<html>인증번호일치하지 않습니다." 
								+"<br>다시설정해주세요.<br></html>");
			this.isConfirmNumberSuccess = false;
			return;
		}
	}
	
	public void userInfoSearchFail() {
		this.userNumberMsg("일치하는 정보가 없습니다.");
		this.searchConfirmButton.setEnabled(true);
	}
	
	//인증이 다된 후 아이디 이메일 검사하는 곳
	public void checkIdEmail() {
		BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();
		ClientAccept clientAccpet = basicFrame.getClientAccept();
		ObjectOutputStream oos = clientAccpet.getClientOS();
		
		String email = this.searchemailTextField.getText();
		String id	 = this.searchIdTextField.getText();
		
	
		this.searchIdTextField.setEditable(false);
		
		UserPersonalInfoDTO userPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
	
		userPersonalDTO.setUserAction(UserActionEnum.USER_SEARCH_ID_EMAIL_CHECK);
		userPersonalDTO.setUserID(id);
		userPersonalDTO.setUserEmail(email);			
		
		try {
			oos.writeObject(userPersonalDTO);
		} catch (Exception e) {
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
	
	public JButton getSearchConfirmButton() { //인증버튼
		return searchConfirmButton;
	}
	
	public JButton getSearchCancelButton() { //취소버튼
		return searchCancelButton;
	}
	
	public JButton getCheckNumberButton() { //인증번호와 맞는지 보는 확인버튼
		return checkNumberButton;
	}
	
	public JButton getsearchCheckButton() { //마지막 확인 버튼
	    	return searchCheckButton;
	    }
	    
	public void setEmailConfirmLimitTime(boolean isEmailConfirmLimitTime) {
		this.isEmailConfirmLimitTime = isEmailConfirmLimitTime;
	}
	
	public boolean isEmailConfirmLimitTime() {
		return isEmailConfirmLimitTime;
	}

	public boolean isConfirmNumberSuccess() {
		return isConfirmNumberSuccess;
	}

	public void setConfirmNumberSuccess(boolean isConfirmNumberSuccess) {
		this.isConfirmNumberSuccess = isConfirmNumberSuccess;
	}


	
}

