package reloff.project.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Letter_fixdata")
public class Letter_FixData {
	
	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "subject", length = 500, nullable = false) //parrafo introductorio muelero
	private String subject;
	
	@Column(name = "conditions", length = 500, nullable = false) //Specific conditions
	private String conditions;
	
	@Column(name = "finaltext", length = 500, nullable = false) //Parrafo final muelero
	private String finaltext;
	
	@Column(name = "deleted", nullable = false)
	private boolean deleted;
	
	/*@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "Letter_fixdata")
    private Letter_config Letter_config;	*/

	public Letter_FixData() {
		super();
	}

	public Letter_FixData(Long id, String subject, String conditions, String finaltext, boolean deleted) {
		super();
		this.id = id;
		this.subject = subject;
		this.conditions = conditions;
		this.finaltext = finaltext;
		this.deleted = deleted;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getFinaltext() {
		return finaltext;
	}

	public void setFinaltext(String finaltext) {
		this.finaltext = finaltext;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
