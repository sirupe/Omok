package datasDTO;

import enums.etc.UserPositionEnum;

public class UserStoreSkinInfoDTO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 876410358296226255L;

	private String userID;
	private int skinNyaong;
    private int skinLeesanghaeC;
    private int skinZammanbo;
    private int skinCcobugi;
    private int skinPairy;
    private int skinPicachu;
    private int skinPurin;
    
    public UserStoreSkinInfoDTO(UserPositionEnum position) {
    	super(position);
    }
    
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getSkinNyaong() {
		return skinNyaong;
	}

	public void setSkinNyaong(int skinNyaong) {
		this.skinNyaong = skinNyaong;
	}

	public int getSkinLeesanghaeC() {
		return skinLeesanghaeC;
	}

	public void setSkinLeesanghaeC(int skinLeesanghaeC) {
		this.skinLeesanghaeC = skinLeesanghaeC;
	}

	public int getSkinZammanbo() {
		return skinZammanbo;
	}

	public void setSkinZammanbo(int skinZammanbo) {
		this.skinZammanbo = skinZammanbo;
	}

	public int getSkinCcobugi() {
		return skinCcobugi;
	}

	public void setSkinCcobugi(int skinCcobugi) {
		this.skinCcobugi = skinCcobugi;
	}

	public int getSkinPairy() {
		return skinPairy;
	}

	public void setSkinPairy(int skinPairy) {
		this.skinPairy = skinPairy;
	}

	public int getSkinPicachu() {
		return skinPicachu;
	}

	public void setSkinPicachu(int skinPicachu) {
		this.skinPicachu = skinPicachu;
	}

	public int getSkinPurin() {
		return skinPurin;
	}

	public void setSkinPurin(int skinPurin) {
		this.skinPurin = skinPurin;
	}
    
}
