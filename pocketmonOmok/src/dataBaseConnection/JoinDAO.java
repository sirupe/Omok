package dataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datas.UserPersonalInfoDTO;

public class JoinDAO {
	// ID 중복체크. 리턴이 0이면 사용 가능, 1이면 이미 존재하는 ID.
	public int checkOverlapID(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		
		// DB Pool 없으면 생성, 있으면 인스턴스 가져오기
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		int result = 0;
		try {
			connection = dbPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_ID ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);

			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 무조건 dbPool 의 커넥션을 free.
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(new JoinDAO().checkOverlapID("test2"));
	}
}
