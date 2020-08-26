package reloff.project.org.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Loan_officer")
@PrimaryKeyJoinColumn(name="loan_Id")
public class Loan_officer extends AppUser
{
	@Column(name = "name", length = 36, nullable = false)
	private String name;
	
	@Column(name = "last_name", length = 36, nullable = false)
	private String last_name;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "mailing_add", length = 75, nullable = false)
	private String mailing_add;
	
	@Column(name = "cellphone", length = 20, nullable = false)
	private String  cellphone;
	
	@Column(name = "nmls", length = 50, nullable = false)
	private String  nmls;	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CompanyId")
	private Company company;	
	
	
	////esto es para
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Loan_officer")
    private List<Loan_Client> Loan_ClientList = new ArrayList<Loan_Client>();

	public Loan_officer() {
		super();
	}

	public Loan_officer(String name, String last_name, String email, String mailing_add,
			String cellphone, String nmls, Company company) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.mailing_add = mailing_add;
		this.cellphone = cellphone;
		this.nmls = nmls;
		this.company = company;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Loan_Client> getLoan_ClientList() {
		return Loan_ClientList;
	}

	public void setLoan_ClientList(List<Loan_Client> loan_ClientList) {
		Loan_ClientList = loan_ClientList;
	}
	
	
}
