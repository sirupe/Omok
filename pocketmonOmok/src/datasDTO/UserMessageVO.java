package datasDTO;

import enums.etc.UserPositionEnum;

public class UserMessageVO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 3012637071224915121L;
	
	private String userID;
	private String message;
	private String targetID;
	
	public UserMessageVO(UserPositionEnum position) {
		super(position);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
}