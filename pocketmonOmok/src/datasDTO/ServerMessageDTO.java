package datasDTO;

import enums.etc.UserPositionEnum;

public class ServerMessageDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 8353390824060690129L;
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
