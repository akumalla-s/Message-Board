package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserRegistration.Role;
import neu.edu.database.DatabaseConnector;

public class UserRegistrationDAO {
	
	private Connection connection;
	public UserRegistrationDAO() {
		// TODO Auto-generated constructor stub
	}

	public boolean createUser(UserRegistration userRegistration) {
		PreparedStatement pst = null;
		
		try {
			connection = DatabaseConnector.getInstance().getConnection();
			String sql = "INSERT INTO user (username, first_name, last_name, mobile, password, email, role) VALUES (?, ?, ?, ?, MD5(?), ?, ?)";
			pst = connection.prepareStatement(sql);
			
			Role role = userRegistration.getRole();
			
			pst.setString(1, userRegistration.getUsername());
			pst.setString(2, userRegistration.getFirstname());
			pst.setString(3, userRegistration.getLastname());
			pst.setString(4, userRegistration.getMobile());
			pst.setString(5, userRegistration.getPassword());
			pst.setString(6, userRegistration.getEmail());
			pst.setString(7, role.getRoleName());			

            // Execute the prepared statement
            int rowsInserted = pst.executeUpdate();
            System.out.println(rowsInserted + " rows inserted");
            if(rowsInserted == 1) {
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
