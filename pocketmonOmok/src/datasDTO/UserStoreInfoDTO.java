package datasDTO;

import enums.etc.UserPositionEnum;

public class UserStoreInfoDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = -4081016818898218349L;
	
	private String userID;
	private int itemColorMatching;
	private int itemIncreaseTime;
	private int itemReturn;
	private int itemMoney;
	
	public UserStoreInfoDTO(UserPositionEnum position) {
		super(position);
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public int getItemColorMatching() {
		return itemColorMatching;
	}
	
	public void setItemColorMatching(int itemColorMatching) {
		this.itemColorMatching = itemColorMatching;
	}
	
	public int getItemIncreaseTime() {
		return itemIncreaseTime;
	}
	
	public void setItemIncreaseTime(int itemIncreaseTime) {
		this.itemIncreaseTime = itemIncreaseTime;
	}
	
	public int getItemReturn() {
		return itemReturn;
	}
	
	public void setItemReturn(int itemReturn) {
		this.itemReturn = itemReturn;
	}
	
	public int getItemMoney() {
		return itemMoney;
	}
	
	public void setItemMoney(int itemMoney) {
		this.itemMoney = itemMoney;
	}
}
