package client;

import java.io.IOException;

public class ClientMain {
	public static void main(String[] args) {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
