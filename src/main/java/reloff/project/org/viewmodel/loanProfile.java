package reloff.project.org.viewmodel;

public class loanProfile {

	
	private String name;
	
	private String last_name;
	
	private String email;
	
	private String mailing_add;
	
	private String  cellphone;
	
	private String  nmls;	
	
	private Long userId;
	 
	private String userName;
 
	private String oldPassword;
    
	private String newPassword;
    
	private String confirmNewPassword;

	public loanProfile(String name, String last_name, String email, String mailing_add, String cellphone, String nmls,
			Long userId, String userName, String oldPassword, String newPassword, String confirmNewPassword) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.mailing_add = mailing_add;
		this.cellphone = cellphone;
		this.nmls = nmls;
		this.userId = userId;
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public loanProfile() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailing_add() {
		return mailing_add;
	}

	public void setMailing_add(String mailing_add) {
		this.mailing_add = mailing_add;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getNmls() {
		return nmls;
	}

	public void setNmls(String nmls) {
		this.nmls = nmls;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	
}
