package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import neu.edu.data.UserSession;
import neu.edu.database.DatabaseConnector;

public class DeleteUserDAO {
	private Connection connection;
	public boolean deleteUser(String username) {
		PreparedStatement pst = null;
		UserSession userSession = null;
		
		try {
			connection = DatabaseConnector.getInstance().getConnection();
			String sql = "DELETE FROM USER WHERE USERNAME = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			int rowsUpdated = pst.executeUpdate();
			if(rowsUpdated == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
