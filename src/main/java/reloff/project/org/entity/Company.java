package reloff.project.org.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {

	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 36, nullable = false)
	private String name;
	
	@Column(name = "phisical_add", length = 50, nullable = false)
	private String phisical_add;
	
	@Column(name = "mailing_add", length = 50, nullable = false)
	private String mailing_add;
	
	@Column(name = "nmls", length = 500, nullable = false)
	private String nmls;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    private List<Loan_officer> Loan_officer_List = new ArrayList<Loan_officer>();
    
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    private List<Company_Realtor> Company_Realtor_List = new ArrayList<Company_Realtor>();
	
	public Company() {
		super();
	}

	public Company(Long id, String name, String phisical_add, String mailing_add, String nmls) {
		super();
		this.id = id;
		this.name = name;
		this.phisical_add = phisical_add;
		this.mailing_add = mailing_add;
		this.nmls = nmls;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhisical_add() {
		return phisical_add;
	}

	public void setPhisical_add(String phisical_add) {
		this.phisical_add = phisical_add;
	}

	public String getMailing_add() {
		return mailing_add;
	}

	public void setMailing_add(String mailing_add) {
		this.mailing_add = mailing_add;
	}

	public String getNmls() {
		return nmls;
	}

	public void setNmls(String nmls) {
		this.nmls = nmls;
	}

	
	public List<Loan_officer> getLoan_officer_List() {
		return Loan_officer_List;
	}

	public void setLoan_officer_List(List<Loan_officer> loan_officer_List) {
		Loan_officer_List = loan_officer_List;
	}

	public List<Company_Realtor> getCompany_Realtor_List() {
		return Company_Realtor_List;
	}

	public void setCompany_Realtor_List(List<Company_Realtor> company_Realtor_List) {
		Company_Realtor_List = company_Realtor_List;
	}

	
}
