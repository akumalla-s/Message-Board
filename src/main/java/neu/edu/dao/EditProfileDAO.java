package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import neu.edu.data.UserSession;
import neu.edu.database.DatabaseConnector;

public class EditProfileDAO {
	private Connection connection;
	
	public EditProfileDAO() {
	}
	public boolean updateUserData(String username, String email, String oldusername) {
		PreparedStatement pst = null;
		UserSession userSession = null;
		
		try {
			connection = DatabaseConnector.getInstance().getConnection();
			String sql = "UPDATE user SET username=? , email=? WHERE username=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, email);
			pst.setString(3, oldusername);
			int rowsUpdated = pst.executeUpdate();
			if(rowsUpdated == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public boolean updateUserPassword(String password, String username) {
		PreparedStatement pst = null;
		UserSession userSession = null;
		
		try {
			connection = DatabaseConnector.getInstance().getConnection();
			String sql = "UPDATE user SET password=MD5(?) WHERE username=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, username);
			int rowsUpdated = pst.executeUpdate();
			if(rowsUpdated == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
