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
@Table(name = "Loan_Client_Operation")
public class Loan_Client_Operation {

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Loan_ClientId", nullable = false)
    private Loan_Client Loan_Client;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OperationId", nullable = false)
    private Operation Operation ;

	public Loan_Client_Operation(Long id, reloff.project.org.entity.Loan_Client loan_Client,
			reloff.project.org.entity.Operation operation) {
		super();
		this.id = id;
		Loan_Client = loan_Client;
		Operation = operation;
	}
	
	public Loan_Client_Operation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Loan_Client getLoan_Client() {
		return Loan_Client;
	}

	public void setLoan_Client(Loan_Client loan_Client) {
		Loan_Client = loan_Client;
	}

	public Operation getOperation() {
		return Operation;
	}

	public void setOperation(Operation operation) {
		Operation = operation;
	}

    
    
    
		
}



