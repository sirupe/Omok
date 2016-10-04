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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
	
	// ���� ������ ����� ���� ���� ��������
	public UserPersonalInfoDTO getUserPersonalInfo(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;

		// DB Pool ������ ����, ������ �ν��Ͻ� ��������
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserPersonalInfoDTO userPersonalInfo = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
		try {
			// ���� ��û
			connection = dbPool.getConnection();
			
			// sql ��
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("USER_ID, ");
			sql.append("USER_NAME, ");
			sql.append("USER_GENDER, ");
			sql.append("USER_BIRTH, ");
			sql.append("USER_EMAIL, ");
			sql.append("USER_PHONENUMBER ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			// ���� ������ �� ��������
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			// DAO �� ������ �� ����
			while(resultSet.next()) {
				userPersonalInfo.setUserID(resultSet.getString("USER_ID"));
				userPersonalInfo.setUserName(resultSet.getString("USER_NAME"));
				userPersonalInfo.setUserGender(resultSet.getInt("USER_GENDER"));
				userPersonalInfo.setUserBirth(resultSet.getString("USER_BIRTH"));
				userPersonalInfo.setUserEmail(resultSet.getString("USER_EMAIL"));
				userPersonalInfo.setUserPhoneNumber(resultSet.getString("USER_PHONENUMBER"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ������ dbPool �� Ŀ�ؼ��� free.
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		// ����ڿ��� DTO ��ȯ
		return userPersonalInfo;
	}
	
	//�� ���� ����
	public int updateUserInfo(UserPersonalInfoDTO personalDTO) {
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
			sql.append("SET USER_NAME=?, ");
			sql.append("USER_GENDER=?, ");
			sql.append("USER_BIRTH=?, ");
			sql.append("USER_EMAIL=?, ");
			sql.append("USER_PHONENUMBER=? ");
			sql.append("WHERE USER_ID=?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserName());
			ps.setInt(2, personalDTO.getUserGender());
			ps.setString(3, personalDTO.getUserBirth());
			ps.setString(4, personalDTO.getUserEmail());
			ps.setString(5, personalDTO.getUserPhoneNumber());
			ps.setString(6, personalDTO.getUserID());
			result = ps.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
	
	//���� Ż��ó��. db�����͸� �������� �ʰ� ���°��� �����Ѵ�.
	public int userDropOut(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		int result = 0;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE USER_PERSONAL_INFO ");
			sql.append("SET USER_DROP_OUT=0 ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
	
	// ȸ��Ż��� ���� ���� �˻�
	public int loginDropUserCheck(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		int result = 0;
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_DROP_OUT ");
			sql.append("FROM USER_PERSONAL_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				result = resultSet.getInt("USER_DROP_OUT");
			}	
		} catch (Exception e) {
			e.printStackTrace();		
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return result;
	}
	
}