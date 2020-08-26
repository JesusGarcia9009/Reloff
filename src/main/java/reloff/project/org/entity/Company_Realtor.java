package reloff.project.org.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Company_Realtor")
public class Company_Realtor {
	
	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY) // Ambos son ManyToOne pq esta relacion esta en la tabla intermedia y se relaciona asi con cada una de las tablas.
    @JoinColumn(name = "realtor_Id", nullable = false)
    private Realtor realtor;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_Id", nullable = false)
    private Company company;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "Company_Realtor")
    private List<Company_Realtor_Operation> Company_Realtor_Operation_List = new ArrayList<Company_Realtor_Operation>();

	public Company_Realtor(Long id, Company company, Realtor realtor) {
		super();
		this.id = id;
		this.company = company;
		this.realtor = realtor;
	}
	
	public Company_Realtor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Realtor getRealtor() {
		return realtor;
	}

	public void setRealtor(Realtor realtor) {
		this.realtor = realtor;
	}

	public List<Company_Realtor_Operation> getCompany_Realtor_Operation_List() {
		return Company_Realtor_Operation_List;
	}

	public void setCompany_Realtor_Operation_List(List<Company_Realtor_Operation> company_Realtor_Operation_List) {
		Company_Realtor_Operation_List = company_Realtor_Operation_List;
	}
    
    

}
