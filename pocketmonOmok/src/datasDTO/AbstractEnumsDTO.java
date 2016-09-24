package datasDTO;

import java.io.Serializable;

import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

// 유저의 현재 위치를 알려줄 인덱스 클래스 (추상)
@SuppressWarnings("serial")
public class AbstractEnumsDTO implements Serializable {
	private UserPositionEnum position;
	private UserActionEnum userAction;
	private ServerActionEnum serverAction;
	
	public AbstractEnumsDTO(UserPositionEnum position) {
		this.position = position;
		System.out.println("포지션 등록 : " + position);
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
		System.out.println("포지션 등록 : " + position);
	}
	
	public void setUserAction(UserActionEnum userAction) {
		this.userAction = userAction;
		System.out.println("유저액션 등록 : " + userAction);
	}
	
	public void setServerAction(ServerActionEnum serverAction) {
		this.serverAction = serverAction;
		System.out.println("서버액션 등록 : " + serverAction);
	}
}
