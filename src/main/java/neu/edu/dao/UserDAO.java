package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;
import neu.edu.database.DatabaseConnector;

public class UserDAO {
	
	private Connection connection;

	public UserDAO() {

	}

	public ArrayList<UserRegistration> viewAllUsers(String role) {

		PreparedStatement pst = null;
		ArrayList<UserRegistration> userRegistrations = new ArrayList();;

		try {
			connection = DatabaseConnector.getInstance().getConnection();
			if(role != null) {
				pst = connection.prepareStatement("SELECT * FROM USER WHERE ROLE=(?)");
				pst.setString(1, role);
			}else {
				pst = connection.prepareStatement("SELECT * FROM USER");

			}

			ResultSet rs = pst.executeQuery();
		

			while (rs.next()) {


				String usernameFromDB = rs.getString("username");
				String emailFromDB = rs.getString("email");
				String roleFromDB = rs.getString("role");
				
				//System.out.println(usernameFromDB + " " +emailFromDB + " " + roleFromDB);

				UserRegistration userRegistration = new UserRegistration(usernameFromDB, emailFromDB, roleFromDB);
				userRegistrations.add(userRegistration);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userRegistrations;

	}

}
