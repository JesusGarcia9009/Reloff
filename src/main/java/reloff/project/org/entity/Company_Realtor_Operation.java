package reloff.project.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Company_Realtor_Operation")
public class Company_Realtor_Operation {

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Company_RealtorId", nullable = false)
    private Company_Realtor Company_Realtor;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OperationId", nullable = false)
    private Operation Operation ;

	public Company_Realtor_Operation(Long id, reloff.project.org.entity.Company_Realtor company_Realtor,
			reloff.project.org.entity.Operation operation) {
		super();
		this.id = id;
		Company_Realtor = company_Realtor;
		Operation = operation;
	}

	public Company_Realtor_Operation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company_Realtor getCompany_Realtor() {
		return Company_Realtor;
	}

	public void setCompany_Realtor(Company_Realtor company_Realtor) {
		Company_Realtor = company_Realtor;
	}

	public Operation getOperation() {
		return Operation;
	}

	public void setOperation(Operation operation) {
		Operation = operation;
	}
    
    
    
    
    
}
