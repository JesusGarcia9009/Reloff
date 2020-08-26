package reloff.project.org.viewmodel;

public class landingViewModel {
	
	private String fName;
	private String lName;
	private String company;
	private String email;
	private String mailAdd;
	private String phoneNo;
	private String nmls;
	private Integer type;
	
	public landingViewModel() {
	};
	
	public landingViewModel(String fName, String lName, String company, String email, String mailAdd, String phoneNo,
			String nmls, Integer type) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.company = company;
		this.email = email;
		this.mailAdd = mailAdd;
		this.phoneNo = phoneNo;
		this.nmls = nmls;
		this.type = type;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNmls() {
		return nmls;
	}

	public void setNmls(String nmls) {
		this.nmls = nmls;
	}

	public String getMailAdd() {
		return mailAdd;
	}

	public void setMailAdd(String mailAdd) {
		this.mailAdd = mailAdd;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}	
		

}
