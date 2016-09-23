package datasDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

public class JoinDAO implements Serializable {
	// ID 중복체크. 리턴이 0이면 사용 가능, 1이면 이미 존재하는 ID.
	public UserPersonalInfoDTO checkOverlapID(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		
		// DB Pool 없으면 생성, 있으면 인스턴스 가져오기
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		ResultSet resultSet = null;
		UserPersonalInfoDTO resultDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_JOIN);
		resultDTO.setUserAction(UserActionEnum.USER_JOIN_ID_OVERLAP_CHECK);
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());

			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				resultDTO.setUserID(resultSet.getString("USER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 무조건 dbPool 의 커넥션을 free.
			dbPool.freeConnection(connection, ps);
		}
		
		return resultDTO;
	}
	
	public int userJoin(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		int result = 0;
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO USER_PERSONAL_INFO ( ");
			sql.append("USER_ID, ");
			sql.append("USER_PASSWD, ");
			sql.append("USER_NAME, ");
			sql.append("USER_GENDER, ");
			sql.append("USER_BIRTH, ");
			sql.append("USER_EMAIL, ");
			sql.append("USER_JOINDATE ");
			if(personalDTO.getUserPhoneNumber() == null) {
				sql.append(") VALUES ( ");
				sql.append("?, ?, ?, ?, ?, ?, sysdate )");				
				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, personalDTO.getUserID());
				ps.setString(2, personalDTO.getUserPasswd());
				ps.setString(3, personalDTO.getUserName());
				ps.setInt	(4, personalDTO.getUserGender());
				ps.setString(5, personalDTO.getUserBirth());
				ps.setString(6, personalDTO.getUserEmail());

			} else {
				sql.append("USER_PHONENUMBER, ");
				sql.append(") VALUES ( ");
				sql.append("?, ?, ?, ?, ?, ?, sysdate, ? )");
				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, personalDTO.getUserID());
				ps.setString(2, personalDTO.getUserPasswd());
				ps.setString(3, personalDTO.getUserName());
				ps.setInt	(4, personalDTO.getUserGender());
				ps.setString(5, personalDTO.getUserBirth());
				ps.setString(6, personalDTO.getUserEmail());
				ps.setString(7, personalDTO.getUserPhoneNumber());
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
}