package enums.etc;

public enum ServerIPEnum {
	SERVER_IP("192.168.43.185"),
	SERVER_PORT(2345);
	
	public String serverIP;
	public int serverPort;
	
	private ServerIPEnum() {}
	private ServerIPEnum(String ip) {
		this.serverIP = ip;
	}
	
	private ServerIPEnum(int port) {
		this.serverPort = port;
	}
	
	public String getServerIP() {
		return serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}	
}
