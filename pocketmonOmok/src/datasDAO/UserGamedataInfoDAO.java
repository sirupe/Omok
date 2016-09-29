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
}
