package datasDTO;

import java.io.Serializable;

import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

// 유저의 현재 위치를 알려줄 인덱스 클래스 (추상)
@SuppressWarnings("serial")
public class AbstractEnumsDTO implements Serializable {
	private UserPositionEnum position; //receiver가 어디로 보낼건지를 판단하죠?
	private UserActionEnum userAction; //server 메소드에서 뭐할지를 판단하죠 ?ㄴ
	private ServerActionEnum serverAction;
	
	public AbstractEnumsDTO(UserPositionEnum position) {
		this.position = position;
	}
	
	public UserPositionEnum getPosition() {
		return position;
	}
	
	public UserActionEnum getUserAction() {
		return userAction;
	}
	
	public ServerActionEnum getServerAction() {
		return serverAction;
	}

	public void setPosition(UserPositionEnum position) {
		this.position = position;
	}
	
	public void setUserAction(UserActionEnum userAction) {
		this.userAction = userAction;
	}
	
	public void setServerAction(ServerActionEnum serverAction) {
		this.serverAction = serverAction;
	}

	
}
