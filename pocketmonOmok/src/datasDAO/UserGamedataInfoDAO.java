package datasDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;

public class UserGamedataInfoDAO implements Serializable {
	public UserGamedataInfoDTO userGameData(UserPersonalInfoDTO personalDTO) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		UserGamedataInfoDTO userGameData = new UserGamedataInfoDTO(UserPositionEnum.POSITION_WAITING_ROOM);
		
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID, USER_GRADE, USER_WIN_COUNT, USER_SCORE ");
			sql.append("FROM USER_GAMEDATA_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, personalDTO.getUserID());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userGameData.setUserID(resultSet.getString("USER_ID"));
				userGameData.setUserGrade(resultSet.getString("USER_GRADE"));
				userGameData.setUserWinCount(resultSet.getInt("USER_WIN_COUNT"));
				userGameData.setUserScore(resultSet.getInt("USER_SCORE"));
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		
		return userGameData;
	}
}
