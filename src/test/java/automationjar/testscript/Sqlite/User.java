package automationjar.testscript.Sqlite.copy;

public class User {
	private int UserId;
	private String Name;
	private String AcountType;
	private String Password;
	
	public int getUserId() {
		return UserId;
	}

	public String getName() {
		return Name;
	}

	public String getAcountType() {
		return AcountType;
	}

	public String getPassword() {
		return Password;
	}


	
	public User(int id, String name, String acountType, String password) {
		super();
		UserId = id;
		Name = name;
		AcountType = acountType;
		Password = password;
	}

	
}
