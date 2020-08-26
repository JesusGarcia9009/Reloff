package reloff.project.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Broker_Company")
public class Broker_Company {

	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
	private Long id;
	
	@NotNull(message="The name is required")
	@NotBlank(message = "The name is required.")
	@Size(min=3, max = 40, message = "The name must be {min} to {max} characters in length.")
	@Column(name = "name", length = 40, nullable = false)
	private String name;
	
	@NotNull(message="The physical_add is required")
	@NotBlank(message = "The physical_add is required.")
	@Size(min=3, max = 50, message = "The physical_add must be {min} to {max} characters in length.")
	@Column(name = "physical_add", length = 50, nullable = false)
	private String physical_add;
	
	@NotNull(message="The cellphone is required")
	@NotBlank(message = "The cellphone is required.")
	@Size(min=3, max = 50, message = "The phone must be {min} to {max} characters in length.")
	@Column(name = "phone", length = 50, nullable = false)
	private String phone;
	
	@Size(min=3, max = 500, message = "The website must be {min} to {max} characters in length.")
	@Column(name = "web_site", length = 500, nullable = true)
	private String web_site;
	
	public Broker_Company() {
		super();
	}

	public Broker_Company(Long id, String name, String physical_add, String phone, String webs) {
		super();
		this.id = id;
		this.name = name;
		this.physical_add = physical_add;
		this.phone = phone;
		this.web_site = webs;
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

	public String getPhysical_add() {
		return physical_add;
	}

	public void setPhysical_add(String physical_add) {
		this.physical_add = physical_add;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeb_site() {
		return web_site;
	}

	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
   
    @Override
    public String toString() {
        return this.name;
    }

}
