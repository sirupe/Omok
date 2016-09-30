package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import utility.RegexCheck;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private String checkMsg;
	
	private String emailTextCheck;
	private String idTextCheck;
	private String confirmNumberCheck;
	
	
	private String idText;
	private String emailText;
	private String confirmNumberText;
	
//	private boolean emailCheck;
//	private boolean idCheck;
//	
//	private boolean confirmNumberCheck;
	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
		this.idTextCheck = "sujin";
		this.emailTextCheck = "tnwls1@naver.com";
		this.confirmNumberCheck = "0";
//		this.emailCheck         = false;
//		this.idCheck            = false;
//		this.confirmNumberCheck = false;  
		
		
	}
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			this.searchPwdPanel.doCancelButton();
			System.out.println(this.searchPwdPanel.getSearchPwdFrame());
			System.out.println("취소");
		//인증버튼
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.searchPwdPanel.getCerfication();
			System.out.println("인증버튼");
		//확인버튼	
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			this.idText = searchPwdPanel.getSearchIdTextField().getText();
			this.emailText  = searchPwdPanel.getSearchemailTextField().getText();
			this.confirmNumberText = searchPwdPanel.getSearchConfirmTextField().getText();
			
			if(this.idText.isEmpty()) {
				this.checkMsg = "아이디 입력 오류.";
				this.searchPwdPanel.userNumberMsg(checkMsg);
				System.out.println("아이디");
			} else if(this.emailText.isEmpty() && !RegexCheck.emailRegexCheck(emailText)) {
				this.checkMsg = "이메일 입력오류.";
				System.out.println("이메일");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} else if(this.confirmNumberText.isEmpty() && !this.confirmNumberCheck.equals(confirmNumberText)) {
				this.checkMsg = "인증번호 입력오류.";
				this.searchPwdPanel.userNumberMsg(checkMsg);
				System.out.println("인증번호");
			} else {
				this.searchPwdPanel.getSearchPwdFrame().doCheckButton();
				
				
				
			}
		}
	}
	//실시간으로 에러 메세지 송출
		public void keyReleased(KeyEvent e) {
			String inFo = e.getSource().toString();
			//키리스너에 들어오는 것을 받아 문자열로 반환하여 inFo에 저장
			this.idText = searchPwdPanel.getSearchIdTextField().getText();
			this.emailText= searchPwdPanel.getSearchemailTextField().getText();
			
			if(inFo.contains("searchIdTextField")) {
					this.idCheck();
					System.out.println("여기ㅠㅠ 아이디");
			
			} else if (inFo.contains("searchemailTextField")) {
				this.emailCheck();
			}    
		}
		
		public void idCheck() {
			if(!this.idTextCheck.equals(idText)) {
				checkMsg = "<html>입력한정보가 일치하지 않습니다." 
						+"<br>다시확인하세여<br></html>";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(!RegexCheck.idRegexCheck(idText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "<html>영소문자, 특수문자구분" 
						+"<br>다시확인하세여<br></html>";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.idTextCheck.equals(idText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "확인!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
//				this.searchPwdPanel.userNumberMsgReset();
			}
			
		}
		public void emailCheck() {
			if(this.emailText.isEmpty()) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "이메일 작성해주세요.";
				System.out.println("여기는나오나");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(!RegexCheck.emailRegexCheck(emailText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "이메일 형식 체크해주세요!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.emailTextCheck.equals(emailText) ){
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "확인!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}
		}
		
		public void ConfirmCheck() {
			if(this.confirmNumberText.isEmpty()) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "인증번호 써주세요";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}if(this.confirmNumberCheck.equals(confirmNumberText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "인증번호가 일치하지 않습니다.";
				System.out.println("여기가 문제세요???ㅆㅂ.");
				this.searchPwdPanel.userNumberMsg(checkMsg);
			} if (this.confirmNumberCheck.equals(confirmNumberText)) {
				this.searchPwdPanel.userNumberMsgReset();
				checkMsg = "확인!";
				this.searchPwdPanel.userNumberMsg(checkMsg);
			}
		}
}


