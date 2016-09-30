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
			this.loginPanel.loginFail("���̵�, �н����� �����Դϴ�.");
		} else {
			this.loginPanel.loginFailLabelReset();
			this.loginPanel.loginFail("�̹� �������� ���̵��Դϴ�.");
		}
	}
	
	public void loginSuccess(AbstractEnumsDTO data) {
		UserPersonalInfoDTO userPersonalDTO = (UserPersonalInfoDTO)data;
		// ���Ƿ� �̵��ϰڴٴ� ������ ��� ������ ���� (������ �̵����� �ʴ´�.)
		this.loginPanel.getBasicFrame().setUserID(userPersonalDTO.getUserID());
		userPersonalDTO.setPosition(UserPositionEnum.POSITION_WAITING_ROOM);
		userPersonalDTO.setUserAction(UserActionEnum.USER_LOGIN_SUCCESS);
		this.loginPanel.getBasicFrame().sendDTO(userPersonalDTO);
	}
	
}
