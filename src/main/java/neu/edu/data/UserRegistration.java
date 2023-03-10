package neu.edu.data;

import neu.edu.data.UserRegistration.Role;

public class UserRegistration {
	
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;
	private String username;
	private String password;
	private Role role;
	
	public enum Role {
		
		ADMIN("admin"), USER("user");
		
		private final String roleName;

		Role(String roleName) {
			this.roleName = roleName;
		}
		
		public String getRoleName() {
			return roleName;
		}

	}
	
	public UserRegistration(String firstname, String lastname, String mobile, String email, String username,
			String password, Role role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserRegistration() {

	}
	
	public UserRegistration(String username, String password, String email, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public UserRegistration(String username, String email, String role) {

		this.username = username;
		this.email = email;
		if("admin".equals(role)) {
			this.role = Role.ADMIN;
		}else if("user".equals(role)) {
			this.role = Role.USER;
		}
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}
}
