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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//@Component
@Entity
@Table(name = "Client")
public class Client 
{

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 36, nullable = false)
	@NotNull(message="The name is required")
	@NotBlank(message = "The name is required.")
	@Size(min=3, max = 40, message = "The name must be {min} to {max} characters in length.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="The name can only have letters and spaces")
	private String name;
	
	@Column(name = "last_name", length = 36, nullable = false)
	@NotNull(message="The last_name is required")
	@NotBlank(message = "The last_name is required.")
	@Size(min=3, max = 40, message = "The lastname must be {min} to {max} characters in length.")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="The name can only have letters and spaces")
	private String last_name;
	
	@Column(name = "email", length = 50, nullable = false)
	@NotNull(message="The email is required")
	@NotBlank(message = "The email is required.")
	@Size(min=5, max = 50, message = "The email must be {min} to {max} characters in length.")
	@Email(message = "Must have an email format")
	private String email;
	
	@Column(name = "mailing_add", length = 500, nullable = true)
	//@NotNull(message="The mailing_add is required")
	//@NotBlank(message = "The mailing_add is required.")
	//@Size(min=3, max = 500, message = "The mailing_add must be {min} to {max} characters in length.")
	private String mailing_add;
	
	@Column(name = "cellphone", length = 20, nullable = false)
	@NotNull(message="The cellphone is required")
	@NotBlank(message = "The cellphone is required.")
	@Size(min=3, max = 40, message = "The cellphone must be {min} to {max} characters in length.")
	private String  cellphone;	
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "Client")
    private List<Loan_Client> Loan_ClientList = new ArrayList<Loan_Client>();

	public Client() {
		super();
	}

	public Client(Long id, String name, String last_name, String email, String mailing_add, String cellphone) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.mailing_add = mailing_add;
		this.cellphone = cellphone;
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

	public List<Loan_Client> getLoan_ClientList() {
		return Loan_ClientList;
	}

	public void setLoan_ClientList(List<Loan_Client> loan_ClientList) {
		Loan_ClientList = loan_ClientList;
	}	
	
	
	
}
