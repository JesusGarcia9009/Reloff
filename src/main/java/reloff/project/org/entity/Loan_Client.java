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
@Table(name = "Loan_Client")
public class Loan_Client {

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_Id", nullable = false)
    private Loan_officer Loan_officer;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClientId", nullable = false)
    private Client Client;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Loan_Client")
    private List<Loan_Client_Operation> Loan_Client_Operation = new ArrayList<Loan_Client_Operation>();

	public Loan_Client( Loan_officer Loan_officer, Client Client) {
		super();
		//this.id = id;
		this.Loan_officer = Loan_officer;
		this.Client = Client;
	}
	
    
	public Loan_Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Loan_officer getLoan() {
		return Loan_officer;
	}

	public void setLoan(Loan_officer Loan_officer) {
		this.Loan_officer = Loan_officer;
	}

	public Client getClient() {
		return Client;
	}

	public void setClient(Client Client) {
		this.Client = Client;
	}


	public List<Loan_Client_Operation> getLoan_Client_Operation() {
		return Loan_Client_Operation;
	}


	public void setLoan_Client_Operation(List<Loan_Client_Operation> loan_Client_Operation) {
		Loan_Client_Operation = loan_Client_Operation;
	}
	
	
}


