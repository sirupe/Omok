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
	
	public JoinServerAction(JoinFrame joinFrame) {
		this.joinFrame = joinFrame;
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
	
	public void cercificationNumber(AbstractEnumsDTO data) {
		this.joinFrame.getJoinAction().setCertificationNumber(((UserPersonalInfoDTO)data).getCertificationNumber());
	}	
}