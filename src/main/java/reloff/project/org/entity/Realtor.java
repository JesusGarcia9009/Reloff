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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Realtor")
@PrimaryKeyJoinColumn(name="realtor_Id")
public class Realtor extends AppUser
{
	
	@Column(name = "name", length = 40, nullable = false)
	@NotNull(message="The name is required")
	@NotBlank(message = "The name is required.")
	@Size(min=3, max = 40, message = "The name must be {min} to {max} characters in length.")
	@Pattern(regexp = "^[a-zA-Z]+$", message="The name can only have letters")
	private String name;
	
	@Column(name = "last_name", length = 40, nullable = false)
	@NotNull(message="The last_name is required")
	@NotBlank(message = "The last_name is required.")
	@Size(min=3, max = 40, message = "The lastname must be {min} to {max} characters in length.")
	@Pattern(regexp = "^[a-zA-Z]+$", message="The name can only have letters")
	private String last_name;
	
	@Column(name = "email", length = 50, nullable = false)
	@NotNull(message="The email is required")
	@NotBlank(message = "The email is required.")
	@Size(min=5, max = 50, message = "The email must be {min} to {max} characters in length.")
	private String email;
	
	@Column(name = "mailing_add", length = 500, nullable = true)
	//@NotNull(message="The mailing_add is required")
	//@NotBlank(message = "The mailing_add is required.")
	//@Size(min=3, max = 500, message = "The mailing_add must be {min} to {max} characters in length.")
	private String mailing_add;
	
	@Column(name = "cellphone", length = 20, nullable = false)
	@NotNull(message="The cellphone is required")
	@NotBlank(message = "The cellphone is required.")
	@Size(min=3, max = 20, message = "The cellphone must be {min} to {max} characters in length.")
	private String  cellphone;
	
	@Column(name = "license_number", length = 20, nullable = false)
	@NotNull(message="The license number is required")
	@NotBlank(message = "The license number is required.")
	@Size(min=3, max = 20, message = "The license number must be {min} to {max} characters in length.")
	private String license_number;
	
	@Column(name = "notes", length = 500, nullable = true)
	@NotNull(message="The notes is required")
	@NotBlank(message = "The notes is required.")
	@Size(min=3, max = 500, message = "The notes must be {min} to {max} characters in length.")
	private String notes;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "realtor")
    private List<Company_Realtor> Company_Realtor_List = new ArrayList<Company_Realtor>();
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BrokerCompanyId", nullable = true)
	private Broker_Company broker_company;
	
	public Realtor() {
		super();
	}

	public Realtor(String name, String last_name, String email, String mailing_add, String cellphone,
			String license_number, String notes, Broker_Company broker_company) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.mailing_add = mailing_add;
		this.cellphone = cellphone;
		this.license_number = license_number;
		this.notes = notes;
		this.broker_company = broker_company;
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

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Broker_Company getBroker_company() {
		return broker_company;
	}

	public void setBroker_company(Broker_Company broker_company) {
		this.broker_company = broker_company;
	}

	public List<Company_Realtor> getCompany_Realtor_List() {
		return Company_Realtor_List;
	}

	public void setCompany_Realtor_List(List<Company_Realtor> company_Realtor_List) {
		Company_Realtor_List = company_Realtor_List;
	}

}
