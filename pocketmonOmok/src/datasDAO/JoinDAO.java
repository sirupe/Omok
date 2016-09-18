package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserPersonalInfoDTO;
import enums.UserPositionEnum;

public class JoinDAO {
	// ID 중복체크. 리턴이 0이면 사용 가능, 1이면 이미 존재하는 ID.
	public UserPersonalInfoDTO checkOverlapID(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		
		// DB Pool 없으면 생성, 있으면 인스턴스 가져오기
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		ResultSet resultSet = null;
		UserPersonalInfoDTO resultDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		try {
			connection = dbPool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT USER_ID ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());

			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				resultDTO.setUserID(resultSet.getString("USER_ID"));
				System.out.println(resultSet.getString("USER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 무조건 dbPool 의 커넥션을 free.
			dbPool.freeConnection(connection, ps);
		}
		
		return resultDTO;
	}
}