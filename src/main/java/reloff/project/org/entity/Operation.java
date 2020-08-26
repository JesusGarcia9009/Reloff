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
@Table(name = "Operation") 
public class Operation {

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 36, nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Operation")
    private List<Loan_Client_Operation> Loan_Client_Operation = new ArrayList<Loan_Client_Operation>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Operation")
    private List<Company_Realtor_Operation> Company_Realtor_Operation = new ArrayList<Company_Realtor_Operation>();
    
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "operation")
    private List<Letter_config> Letter_config_List = new ArrayList<Letter_config>();

	public Operation(Long id, String name, List<reloff.project.org.entity.Loan_Client_Operation> loan_Client_Operation,
			List<reloff.project.org.entity.Company_Realtor_Operation> company_Realtor_Operation,
			List<Letter_config> letter_config_List) {
		super();
		this.id = id;
		this.name = name;
		Loan_Client_Operation = loan_Client_Operation;
		Company_Realtor_Operation = company_Realtor_Operation;
		Letter_config_List = letter_config_List;
	}



	public Operation() {
		super();
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

	public List<Loan_Client_Operation> getLoan_Client_Operation() {
		return Loan_Client_Operation;
	}

	public void setLoan_Client_Operation(List<Loan_Client_Operation> loan_Client_Operation) {
		Loan_Client_Operation = loan_Client_Operation;
	}

	public List<Company_Realtor_Operation> getCompany_Realtor_Operation() {
		return Company_Realtor_Operation;
	}

	public void setCompany_Realtor_Operation(List<Company_Realtor_Operation> company_Realtor_Operation) {
		Company_Realtor_Operation = company_Realtor_Operation;
	}
	
	public List<Letter_config> getLetter_config_List() {
		return Letter_config_List;
	}

	public void setLetter_config_List(List<Letter_config> letter_config_List) {
		Letter_config_List = letter_config_List;
	}
    
    
}
