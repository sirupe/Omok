package dataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datas.UserPersonalInfoDTO;
import enums.UserPositionEnum;

public class LoginDAO {
	
	public UserPersonalInfoDTO checkIDMatchesPW(String id) {
		// try-catch문에서 선언하여 사용시 finally 와 스코프가 달라 에러 발생. 미리 선언해준다.
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_LOGIN);
		
		// DB Pool 없으면 생성, 있으면 인스턴스 가져오기
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		try {
			// 연결 요청
			connection = dbPool.getConnection();
			
			// sql 문
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_ID, USER_PASSWD ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			// 쿼리 날리고 값 가져오기
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			// DAO 에 가져온 값 세팅
			while(resultSet.next()) {
				userPersonalInfo.setUserID(resultSet.getString("USER_ID"));
				userPersonalInfo.setUserPasswd(resultSet.getString("USER_PASSWD"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 무조건 dbPool 의 커넥션을 free.
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// 사용자자에게 DAO 반환
		return userPersonalInfo;
	}
}
