package start;

import java.io.IOException;

import server.client.ClientAccept;

public class ClientMain {
	public static void main(String[] args)  {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
