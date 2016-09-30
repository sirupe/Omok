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
			checkMsg = "join성공";
			color = JoinSizesEnum.LABELCOLOR_DEFAULT.getColor();
		
		} else {
			checkMsg = "joinID중복";
			color = JoinSizesEnum.LABELCOLOR_ERROR.getColor();
		}
		
		this.joinFrame.labelSetting(
				this.joinFrame.getIdErrorLabel(), 
				color, checkMsg);
	}
	
	public void joinTry(AbstractEnumsDTO data) {
		if(data.getServerAction() == ServerActionEnum.JOIN_SUCCESS) {
			new JoinSuccessFrame(this.joinFrame, "회원가입 완료:)");
			this.joinFrame.setVisible(false);
			this.joinFrame.dispose();
		
		} else {
			new JoinSuccessFrame(this.joinFrame, "오류 발생 :(");
			this.joinFrame.setVisible(false);
			this.joinFrame.dispose();
			
		}
	}
	
	public void cercificationNumber(AbstractEnumsDTO data) {
		this.joinFrame.getJoinAction().setCertificationNumber(((UserPersonalInfoDTO)data).getCertificationNumber());
	}	
}