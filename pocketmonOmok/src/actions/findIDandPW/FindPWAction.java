package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import actions.adapters.Adapters;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import frames.serchFrames.SearchPwdPanel;
import utility.RegexCheck;

public class FindPWAction extends Adapters {
	private SearchPwdPanel searchPwdPanel;
	private String checkMsg;
	private String emailCheck;
	private String idCheck;
	private String confirmNumberText; // 인증번호저장할 곳...
	//private boolean checkNumberText;
	private String checkNumberText; //인증번호 받아오기
	
	public FindPWAction(SearchPwdPanel searchPwdPanel) {
		this.searchPwdPanel = searchPwdPanel;
		this.checkNumberText = "0";
		
		//this.searchPwdPanel.addKeyAction(this.searchPwdPanel.getSearchConfirmTextField(), "confirmNumberText");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
//취소버튼 눌렀을때 -- >홈으로
		if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CANCEL.getButtonName())) {
			//1. 취소 버튼이 눌렸다.
			//2. searchPwdFrame을 꺼준다.
			//3. 나는 panel밖에 모른다. 그렇기 때문에 panel에 있는 메소드를 실행해 줄 수 밖에 없다.
			//4. panel에서 searchFrame을 숨겨주는 메소드를 만든다.
			//5. frame에서 basic을 숨길 수 있는 호출을 하고 자기를 숨길 수 있는 호출을 한다.
			this.searchPwdPanel.doCancelButton();
			
			
//인증 버튼 눌렀을때
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRM.getButtonName())) {
			this.searchPwdPanel.getCerfication(); //프레임한테 보내준다 -- > action은 frame을 모르므로 panel가서 불러온다,
		System.out.println();
//확인 버튼을 누르고 맞으면 넘어가고 아니면 아니라고 에러메세지 띄어줌
		} else if(buttonName.equals(LoginSizesEnum.BUTTON_NAME_SEARCH_CHECK.getButtonName())) {
			//this.searchPwdPanel.getSearchPwdFrame().doCheckButton(); //재비밀번호 입력하는 패널로 넘어감
			this.idCheck = searchPwdPanel.getSearchIdTextField().getText();
			this.emailCheck = searchPwdPanel.getSearchemailTextField().getText();
			this.confirmNumberText = searchPwdPanel.getSearchConfirmTextField().getText(); //인증번호적는곳
			
			if(this.confirmNumberText.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "인증번호 입력하세요.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.emailCheck.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "이메일 입력 부탁드립니다.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.idCheck.isEmpty()) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "아이디 입력 부탁드립니다.";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			//인증번호가 맞다면 -- > 재입력으로 가기
			if(!this.confirmNumberText.equals(this.checkNumberText)) {
				this.searchPwdPanel.userInfoErrorLabelReset();
				this.checkMsg = "<html>인증번호가 틀렸습니다." 
						+"<br>다시확인해주세요!<br></html>";
	    		this.searchPwdPanel.userNumberMsg(checkMsg);
			}
			
			if(this.confirmNumberText.equals(this.checkNumberText)) {
				this.searchPwdPanel.getSearchConfirmTextField().setEditable(false);
				this.searchPwdPanel.getsearchCheckButton().setEnabled(false);
				this.searchPwdPanel.getSearchPwdFrame().doCheckButton();
				System.out.println("맞y");
			}
		}
	}
	
	
	
	public void addKeyAction(JComponent comp, String Name) {
//		EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
		comp.setName(Name);
//		comp.setBorder(border);
		comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
		comp.addKeyListener(this);
		//this.add(comp);	
	}
	
	
	

}	