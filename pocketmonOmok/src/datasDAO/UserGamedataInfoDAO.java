package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;

public class UserGamedataInfoDAO {
	public UserGamedataInfoDTO userGameData(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		UserGamedataInfoDTO userGameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
		
		//gamedata 테이블의 모든 정보와 personalinfo 테이블의 성별정보를 join 한다.
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("GAMEDATA.USER_ID, ");
			sql.append("GAMEDATA.USER_GRADE, ");
			sql.append("GAMEDATA.USER_GAME_COUNT, ");
			sql.append("GAMEDATA.USER_WIN_COUNT, ");
			sql.append("GAMEDATA.USER_SCORE, ");
			sql.append("PERSONAL.USER_GENDER ");
			sql.append("FROM USER_GAMEDATA_INFO GAMEDATA ");
			sql.append("INNER JOIN USER_PERSONAL_INFO PERSONAL ");
			sql.append("ON GAMEDATA.USER_ID=PERSONAL.USER_ID ");
			sql.append("WHERE GAMEDATA.USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userGameData.setUserID(resultSet.getString("USER_ID"));
				userGameData.setUserGrade(resultSet.getString("USER_GRADE"));
				userGameData.setUserWinCount(resultSet.getInt("USER_WIN_COUNT"));
				userGameData.setUserGameCount(resultSet.getInt("USER_GAME_COUNT"));
				userGameData.setUserScore(resultSet.getInt("USER_SCORE"));
				userGameData.setUserWaitingRoomImage(resultSet.getInt("USER_GENDER"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userGameData;
	}
	
	public UserGamedataInfoDTO getUserGrade(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		UserGamedataInfoDTO userGameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID, USER_GRADE ");
			sql.append("FROM USER_GAMEDATA_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userGameData.setUserID(resultSet.getString("USER_ID"));
				userGameData.setUserGrade(resultSet.getString("USER_GRADE"));
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userGameData;
	}
	
	public int winUserGameDataUpdate(String userID) {
		Connection connection = null;
		PreparedStatement ps  = null;
		PreparedStatement ps2 = null;
		ResultSet resultSet	  = null;
		int result = 0;
		int userScore = 0;
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT (USER_SCORE + 10) AS USER_SCORE ");
			sql.append("FROM USER_GAMEDATA_INFO WHERE USER_ID=?");
						
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, userID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userScore = resultSet.getInt("USER_SCORE");
			}
			
			StringBuffer sql2 = new StringBuffer();
			sql2.append("UPDATE USER_GAMEDATA_INFO ");
			sql2.append("SET USER_GRADE = ");
			sql2.append("CASE WHEN " + userScore + " >= 0 AND " + userScore + " < 100 THEN '초보' ");
			sql2.append("WHEN " + userScore + " >= 100 AND " + userScore + " < 200 THEN '중수' ");
			sql2.append("WHEN " + userScore + " >= 200 AND " + userScore + " < 400 THEN '고수' ");
			sql2.append("WHEN " + userScore + " >= 400 AND " + userScore + " < 800 THEN '초고수' ");
			sql2.append("WHEN " + userScore + " >= 800 AND " + userScore + " < 1600 THEN '달인' ");
			sql2.append("WHEN " + userScore + " >= 1600 AND " + userScore + " < 3200 THEN '영웅' ");
			sql2.append("WHEN " + userScore + " >= 3200 THEN '신' ");
			sql2.append("END, ");
			sql2.append("USER_GAME_COUNT=USER_GAME_COUNT+1, ");
			sql2.append("USER_WIN_COUNT=USER_WIN_COUNT+1, ");
			sql2.append("USER_SCORE=USER_SCORE+10 ");
			sql2.append("WHERE USER_ID=?");
						
			ps2 = connection.prepareStatement(sql2.toString());
			ps2.setString(1, userID);
			result = ps2.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
			dbPool.freeConnection(connection, ps2, resultSet);
		}
		
		return result;
	}
	
	public int loseUserGameDataUpdate(String userID) {
		Connection connection = null;
		PreparedStatement ps  = null;
		int result = 0;
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		try {
			connection = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE USER_GAMEDATA_INFO ");
			sql.append("SET USER_GAME_COUNT=USER_GAME_COUNT+1 ");
			sql.append("WHERE USER_ID=?");
						
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, userID);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps);
		}
		
		return result;
	}
}
