package FeaturesMain;

public class User {
	private String name;
	private String id;
	private String role;
	private String status;
	private String password;

	// constructor for backward compatibility (originally i didn't have a password
	// field)
	public User(String name, String id, String role, String status) {
		this(name, id, role, status, "default_password");
	}

	// Constructor with password
	public User(String name, String id, String role, String status, String password) {
		this.name = name;
		this.id = id;
		this.role = role;
		this.status = status;
		this.password = password;
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for ID
	public String getID() {
		return id;
	}

	// Setter for ID
	public void setID(String id) {
		this.id = id;
	}

	// Getter for role
	public String getRole() {
		return role;
	}

	// Setter for role
	public void setRole(String role) {
		this.role = role;
	}

	// Getter for status
	public String getStatus() {
		return status;
	}

	// Setter for status
	public void setStatus(String status) {
		this.status = status;
	}

	// Override toString to include status
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", role=" + role + ", status=" + status + "]";
	}

	public Object getPassword() {
		return password;
	}
}
