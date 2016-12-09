package actions.join;

import java.awt.Color;

import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.frames.JoinSizesEnum;
import frames.joinFrames.JoinFrame;
import frames.joinFrames.JoinSuccessFrame;

public class JoinServerAction {
	private JoinFrame joinFrame;
	private JoinClientAction joinClientAction;
	
	public JoinServerAction(JoinFrame joinFrame) {
		this.joinFrame = joinFrame;
		this.joinClientAction = joinFrame.getJoinAction();
	}
	
	public void joinOverlapCheck(AbstractEnumsDTO data) {
		UserPersonalInfoDTO userPersonalInfoDTO = (UserPersonalInfoDTO)data;
		String checkMsg = null;
		Color color 	= null;
		
		if(userPersonalInfoDTO.getUserID() == null) {
			checkMsg = "join����";
			color = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		
		} else {
			checkMsg = "joinID�ߺ�";
			color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		}
		
		this.joinFrame.labelSetting(
				this.joinFrame.getIdErrorLabel(), 
				color, checkMsg);
	}
	
	public void joinTry(AbstractEnumsDTO data) {
		if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
			new JoinSuccessFrame(this.joinFrame, "ȸ������ �Ϸ�:)");
			this.joinFrame.setVisible(false);
			this.joinFrame.dispose();
		
		} else {
			new JoinSuccessFrame(this.joinFrame, "���� �߻� :(");
			this.joinFrame.setVisible(false);
			this.joinFrame.dispose();
			
		}
	}
	
	public void certificationNumSuccess() {
		this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), JoinSizesEnum.LABELCOLOR_DEFAULT.getColor(), "joinMail������ġ");
		this.joinFrame.getEmailTimeLabel().setVisible(false);
		this.joinFrame.getEmailConfTextField().setEditable(false);
		this.joinFrame.getEmailAddrTextField().setEditable(false);
		this.joinFrame.getEmailIDTextField().setEditable(false);
		this.joinFrame.getEmailAddrChoice().setEnabled(false);
		this.joinFrame.getConfirmButton().setEnabled(false);
		this.joinClientAction.setEmailConfirmTime(true);
		this.joinClientAction.getTimeThread().interrupt();
		
	}
	
	public void certificationNumFail() {
		this.joinFrame.labelSetting(this.joinFrame.getEmailErrorLabel(), JoinSizesEnum.LABELCOLOR_ERROR.getColor(), "jointMail��������ġ");
	}
}