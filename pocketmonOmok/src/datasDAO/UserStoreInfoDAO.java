package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserStoreInfoDTO;
import enums.etc.UserPositionEnum;

public class UserStoreInfoDAO {
	public UserStoreInfoDTO getUserStoreInfo(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserStoreInfoDTO userStoreInfo = new UserStoreInfoDTO(UserPositionEnum.POSITION_GAME_ROOM);
		
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("USER_ID, ");
			sql.append("ITEM_COLOR_MATCHING, ");
			sql.append("ITEM_INCREASE_TIME, ");
			sql.append("ITEM_RETURN, ");
			sql.append("ITEM_MONEY ");
			sql.append("FROM USER_STORE_INFO ");
			sql.append("WHERE USER_ID=?");
			
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userStoreInfo.setUserID(resultSet.getString("USER_ID"));
				userStoreInfo.setItemReturn(resultSet.getInt("ITEM_RETURN"));
				userStoreInfo.setItemIncreaseTime(resultSet.getInt("ITEM_INCREASE_TIME"));
				userStoreInfo.setItemColorMatching(resultSet.getInt("ITEM_COLOR_MATCHING"));
				userStoreInfo.setItemMoney(resultSet.getInt("ITEM_MONEY"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		return userStoreInfo;
	}

}
