package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserPositionEnum;

public class UserPersonalInfoDAO {
	// DTO 에 ID가 입력되어 전달되면 DTO 에 ID와 PW가 담겨 되돌려집니다. 없을경우 NULL 이 반환됩니다.
	public UserPersonalInfoDTO checkIDMatchesPW(UserPersonalInfoDTO personalDTO) {
		// try-catch문에서 선언하여 사용시 finally 와 스코프가 달라 에러 발생. 미리 선언해준다.
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		// DB Pool 없으면 생성, 있으면 인스턴스 가져오기
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_LOGIN);
		userPersonalInfo.setServerAction(ServerActionEnum.LOGIN_SUCCESS);
		try {
			// 연결 요청
			connection = dbPool.getConnection();
			
			// sql 문
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID, USER_PASSWD ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=? AND USER_PASSWD=?");
			
			// 쿼리 날리고 값 가져오기
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			ps.setString(2, personalDTO.getUserPasswd());
			resultSet = ps.executeQuery();
			
			// DAO 에 가져온 값 세팅
			while(resultSet.next()) {
				userPersonalInfo.setUserID(resultSet.getString("USER_ID"));
				userPersonalInfo.setUserPasswd(resultSet.getString("USER_PASSWD"));
			}
			
			userPersonalInfo.setServerAction(userPersonalInfo.getUserID() == null ?
							ServerActionEnum.LOGIN_FAIL_INPUT_ERROR : ServerActionEnum.LOGIN_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 무조건 dbPool 의 커넥션을 free.
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// 사용자자에게 DTO 반환
		return userPersonalInfo;
	}
	
	public int getUserGender(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		int userGender = 0;
		
		try {
			// 연결 요청
			connection = dbPool.getConnection();
			
			// sql 문
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_GENDER ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			// 쿼리 날리고 값 가져오기
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			// DAO 에 가져온 값 세팅
			while(resultSet.next()) {
				userGender = resultSet.getInt("USER_GENDER");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// 사용자자에게 DAO 반환
		return userGender;
	}
	
	// 유저 이름과 이메일로 유저 ID찾기
	// UserPersonalInfoDTO에 유저 ID세팅하여 반환.
	public UserPersonalInfoDTO findUserID(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
		userPersonalInfo.setServerAction(ServerActionEnum.LOGIN_SUCCESS);
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_NAME=? AND USER_EMAIL=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserName());
			ps.setString(2, personalDTO.getUserEmail());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userPersonalInfo.setUserID(resultSet.getString("USER_ID"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userPersonalInfo;
	}
	
	// 유저 아이디와 이메일로 유저 패스워드 찾기
	// UserPersonalInfoDTO에 유저 패스워드 담아서 반환
	public UserPersonalInfoDTO findUserPW(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_PASSWD ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=? AND USER_EMAIL=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			ps.setString(2, personalDTO.getUserEmail());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userPersonalInfo.setUserID(personalDTO.getUserID());
				userPersonalInfo.setUserPasswd(resultSet.getString("USER_PASSWD"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userPersonalInfo;
	} 
}
