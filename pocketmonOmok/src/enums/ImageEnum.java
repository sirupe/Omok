package enums;

public enum ImageEnum {
	LOGINPANEL_LOGIN("resources/login/login.jpg");
	
	private String imageDir;
	
	private ImageEnum(String image) {
		this.imageDir = image;
	}
	
	public String getImageDir() {
		return imageDir;
	}
}
