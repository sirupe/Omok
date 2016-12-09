package actions.login;

import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import frames.LoginPanel;

public class LoginServerAction {
	private LoginPanel loginPanel;
	
	public LoginServerAction(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
	public void loginFail(String errMsg) {		
		this.loginPanel.loginFailLabelReset();
		this.loginPanel.loginFail(errMsg);
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