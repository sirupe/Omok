package datas;

import enums.UserPositionEnum;

// 유저의 현재 위치를 알려줄 인덱스 클래스 (추상)
public abstract class UserPositionIndex {
	UserPositionEnum position;
	
	public UserPositionIndex(UserPositionEnum position) {
		this.position = position;
	}

	public UserPositionEnum getPosition() {
		return position;
	}
	
	
}
