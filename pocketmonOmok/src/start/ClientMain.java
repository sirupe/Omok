package start;

import java.io.IOException;

import client.ClientAccept;

public class ClientMain {
	public static void main(String[] args) {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
