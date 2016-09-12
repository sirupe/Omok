package client;

import java.io.IOException;

import frames.LoginFrame;

// 클라이언트 실행
public class ClientMain {
	public static void main(String[] args) {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
