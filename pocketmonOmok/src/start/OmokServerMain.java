package start;

import java.io.IOException;

import omokGameServer.OmokServer;

public class OmokServerMain {
	public static void main(String[] args) throws IOException {
		new OmokServer().gameServerOn();
	}
}