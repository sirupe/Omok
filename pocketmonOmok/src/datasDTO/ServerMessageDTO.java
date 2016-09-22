package datasDTO;

import java.io.Serializable;

import enums.etc.ServerActionEnum;
import enums.etc.UserPositionEnum;

public class ServerMessageDTO extends AbstractEnumsDTO implements Serializable {
	private String serverMessage;

	public ServerMessageDTO(UserPositionEnum position) {
		super(position);
	}
	
	public void setServerMessage(String serverMessage) {
		this.serverMessage = serverMessage;
	}
	
	public String getServerMessage() {
		return serverMessage;
	}
}
