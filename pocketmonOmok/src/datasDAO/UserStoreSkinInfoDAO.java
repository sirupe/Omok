package datasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datasDTO.UserStoreSkinInfoDTO;
import enums.etc.UserPositionEnum;

public class UserStoreSkinInfoDAO {
	public UserStoreSkinInfoDTO getUserSkinInfo(String id) {
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet resultSet   = null;
		
		DBConnectionPool dbPool = DBConnectionPool.getInstance();
		
		UserStoreSkinInfoDTO userSkinInfo = new UserStoreSkinInfoDTO(UserPositionEnum.POSITION_STORE);
		
		try {
			connection = dbPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("USER_ID, ");
			sql.append("SKIN_NYAONG, ");
			sql.append("SKIN_LEESANGHAEC, ");
			sql.append("SKIN_ZAMMANBO, ");
			sql.append("SKIN_CCOBUGI, ");
			sql.append("SKIN_PAIRY, ");
			sql.append("SKIN_PICACHU, ");
			sql.append("SKIN_PURIN ");
			sql.append("FROM USER_STORE_SKIN_INFO ");
			sql.append("WHERE USER_ID=?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				userSkinInfo.setUserID(resultSet.getString("USER_ID"));
				userSkinInfo.setSkinZammanbo(resultSet.getInt("SKIN_ZAMMANBO"));
				userSkinInfo.setSkinPurin(resultSet.getInt("SKIN_PURIN"));
				userSkinInfo.setSkinPicachu(resultSet.getInt("SKIN_PICACHU"));
				userSkinInfo.setSkinPairy(resultSet.getInt("SKIN_PAIRY"));
				userSkinInfo.setSkinNyaong(resultSet.getInt("SKIN_NYAONG"));
				userSkinInfo.setSkinLeesanghaeC(resultSet.getInt("SKIN_LEESANGHAEC"));
				userSkinInfo.setSkinCcobugi(resultSet.getInt("SKIN_CCOBUGI"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbPool.freeConnection(connection, ps, resultSet);
		}
		return userSkinInfo;
	}
}