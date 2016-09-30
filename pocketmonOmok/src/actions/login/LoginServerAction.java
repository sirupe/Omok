package actions.login;

import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.LoginPanel;

public class LoginServerAction {
	private LoginPanel loginPanel;
	
	public LoginServerAction(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
	public void loginFail(AbstractEnumsDTO data) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		
		if(userPersonalDTO.getServerAction() == ServerActionEnum.LOGIN_FAIL_INPUT_ERROR) {
			this.loginPanel.loginFailLabelReset();
			this.loginPanel.loginFail("아이디, 패스워드 오류입니다.");
		} else {
			this.loginPanel.loginFailLabelReset();
			this.loginPanel.loginFail("이미 접속중인 아이디입니다.");
		}
	}
	
	public void loginSuccess(AbstractEnumsDTO data) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		// 대기실로 이동하겠다는 정보를 담아 서버에 전송 (실제로 이동하진 않는다.)
		this.loginPanel.getBasicFrame().setUserID(userPersonalDTO.getUserID());
		userPersonalDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
		userPersonalDTO.setUserAction(UserActionEnum.USER_LOGIN_SUCCESS);
		this.loginPanel.getBasicFrame().sendDTO(userPersonalDTO);
	}
	
}
