package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;

public class UserPersonalInfoDAO {
	// DTO �� ID�� �ԷµǾ� ���޵Ǹ� DTO �� ID�� PW�� ��� �ǵ������ϴ�. ������� NULL �� ��ȯ�˴ϴ�.
	public UserPersonalInfoDTO checkIDMatchesPW(UserPersonalInfoDTO personalDTO) {
		// try-catch������ �����Ͽ� ���� finally �� �������� �޶� ���� �߻�. �̸� �������ش�.
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		// DB Pool ������ ����, ������ �ν��Ͻ� ��������
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_LOGIN);
		userPersonalInfo.setServerAction(ServerActionEnum.LOGIN_SUCCESS);
		try {
			// ���� ��û
			connection = dbPool.getConnection();
			
			// sql ��
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID, USER_PASSWD ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=? AND USER_PASSWD=?");
			
			// ���� ������ �� ��������
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			ps.setString(2, personalDTO.getUserPasswd());
			resultSet = ps.executeQuery();
			
			// DAO �� ������ �� ����
			while(resultSet.next()) {
				userPersonalInfo.setUserID(resultSet.getString("USER_ID"));
				userPersonalInfo.setUserPasswd(resultSet.getString("USER_PASSWD"));
			}
			
			userPersonalInfo.setServerAction(userPersonalInfo.getUserID() == null ?
							ServerActionEnum.LOGIN_FAIL_INPUT_ERROR : ServerActionEnum.LOGIN_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ������ dbPool �� Ŀ�ؼ��� free.
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// ������ڿ��� DTO ��ȯ
		return userPersonalInfo;
	}
	
	public int getUserGender(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		int userGender = 0;
		
		try {
			// ���� ��û
			connection = dbPool.getConnection();
			
			// sql ��
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_GENDER ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			// ���� ������ �� ��������
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			// DAO �� ������ �� ����
			while(resultSet.next()) {
				userGender = resultSet.getInt("USER_GENDER");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// ������ڿ��� DAO ��ȯ
		return userGender;
	}
	
	// ���� �̸��� �̸��Ϸ� ���� IDã��
	// UserPersonalInfoDTO�� ���� ID�����Ͽ� ��ȯ.
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
	
	// ���� ���̵�� �̸��Ϸ� ���� �н����� ã��
	// UserPersonalInfoDTO�� ���� �н����� ��Ƽ� ��ȯ
	public UserPersonalInfoDTO findUserPW(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfo.setUserAction(UserActionEnum.USER_SEARCH_ID_EMAIL_CHECK);
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(USER_ID) AS COUNT ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=? AND USER_EMAIL=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			ps.setString(2, personalDTO.getUserEmail());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("DAO ���� ����");
				userPersonalInfo.setUserCount(resultSet.getInt("COUNT"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userPersonalInfo;
	}
	
	// ���� �н����� ������ �����մϴ�. 1�� ��ȯ�ϸ� ���������� ��ȯ�� ��, 0�� ��ȯ�ϸ� ������ �� ���Դϴ�.
	public int updateUserPasswd(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		int result = 0;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
		userPersonalInfo.setUserAction(UserActionEnum.USER_SEARCH_PASSWD);
		
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE USER_PERSONAL_INFO ");
			sql.append("SET USER_PASSWD=? ");
			sql.append("WHERE USER_ID=? ");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserPasswd());
			ps.setString(2, personalDTO.getUserID());
			
			result = ps.executeUpdate();
			
			System.out.println(userPersonalInfo.getUserID() + " : ���̵�");
			System.out.println(userPersonalInfo.getUserPasswd() + ": ��й�ȣ");
			System.out.println(result + " : ��� ���� ����");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
}
