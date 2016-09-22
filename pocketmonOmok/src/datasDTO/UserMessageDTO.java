package datasDTO;

import java.io.Serializable;

import enums.etc.UserPositionEnum;

public class UserMessageDTO extends AbstractEnumsDTO implements Serializable {
	private String userID;
	private String message;
	private String targetID;
	
	public UserMessageDTO(UserPositionEnum position) {
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