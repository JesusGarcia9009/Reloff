package reloff.project.org.viewmodel;

public class userProfile {
	
	public Long userId;

	private String userName;
	 
	private String oldPassword;
    
	private String newPassword;
    
	private String confirmNewPassword;

	public userProfile(Long userId, String userName, String oldPassword, String newPassword,
			String confirmNewPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public userProfile() {
		super();
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
