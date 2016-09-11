package client;

import java.io.IOException;

import dataBaseConnection.DBConnectionPool;
import dataBaseConnection.LoginDAO;
import datas.UserPersonalInfoDTO;

// 클라이언트 실행
public class ClientMain {
	public static void main(String[] args) throws Exception {
		try {
			new ClientAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		DBConnectionPool dbcp = DBConnectionPool.getInstance();
//		
//		LoginDAO loginDAO = new LoginDAO();
//		UserPersonalInfoDTO userPersonalInfo = loginDAO.checkIDMatchesPW("imcts");
//		
//		System.out.println(userPersonalInfo.getUserID());
//		System.out.println(userPersonalInfo.getUserPasswd());
	}
	
	
}
