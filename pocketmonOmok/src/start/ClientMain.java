package start;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import client.ClientAccept;

// 클라이언트 실행
public class ClientMain implements Serializable {
	public static void main(String[] args) throws Exception {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		
	}
}
