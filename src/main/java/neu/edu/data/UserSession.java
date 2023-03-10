package neu.edu.data;

import neu.edu.data.UserRegistration.Role;

public class UserSession {
	
	private String username;
	private String email;
	private UserRegistration.Role role;
	private String currentPage;
	
	public UserSession() {
		// TODO Auto-generated constructor stub
	}
	
	public UserSession(String username, String email, String role) {
		super();
		this.username = username;
		this.email = email;
		
		if("admin".equals(role)) {
			this.role = Role.ADMIN;
		}else if("user".equals(role)) {
			this.role = Role.USER;
		}
	}

	public UserSession(String username, String email, UserRegistration.Role role, String currentPage) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.currentPage = currentPage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRegistration.Role getRole() {
		return role;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}


}
