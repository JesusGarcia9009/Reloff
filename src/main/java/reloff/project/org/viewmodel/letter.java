package reloff.project.org.viewmodel;


public class letter {
	
	private Long id;
	
	private String subject;
	
	private String conditions;
	
	private String finaltext;
	
	private boolean deleted;
	
	//realtor
	private Long realtorid;
	
	private String realtorname;
	
	private String realtorlast_name;
	
	//client
	private Long clientid;
	
	private String clientname;
	
	private String clientlast_name;
	
	private String clientemail;
	
	private String clientmailing_add;
	
	private String  clientcellphone;

	public letter(Long id, String subject, String conditions, String finaltext, boolean deleted, Long realtorid,
			String realtorname, String realtorlast_name, Long clientid, String clientname, String clientlast_name,
			String clientemail, String clientmailing_add, String clientcellphone) {
		super();
		this.id = id;
		this.subject = subject;
		this.conditions = conditions;
		this.finaltext = finaltext;
		this.deleted = deleted;
		this.realtorid = realtorid;
		this.realtorname = realtorname;
		this.realtorlast_name = realtorlast_name;
		this.clientid = clientid;
		this.clientname = clientname;
		this.clientlast_name = clientlast_name;
		this.clientemail = clientemail;
		this.clientmailing_add = clientmailing_add;
		this.clientcellphone = clientcellphone;
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

	public Long getRealtorid() {
		return realtorid;
	}

	public void setRealtorid(Long realtorid) {
		this.realtorid = realtorid;
	}

	public String getRealtorname() {
		return realtorname;
	}

	public void setRealtorname(String realtorname) {
		this.realtorname = realtorname;
	}

	public String getRealtorlast_name() {
		return realtorlast_name;
	}

	public void setRealtorlast_name(String realtorlast_name) {
		this.realtorlast_name = realtorlast_name;
	}

	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getClientlast_name() {
		return clientlast_name;
	}

	public void setClientlast_name(String clientlast_name) {
		this.clientlast_name = clientlast_name;
	}

	public String getClientemail() {
		return clientemail;
	}

	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}

	public String getClientmailing_add() {
		return clientmailing_add;
	}

	public void setClientmailing_add(String clientmailing_add) {
		this.clientmailing_add = clientmailing_add;
	}

	public String getClientcellphone() {
		return clientcellphone;
	}

	public void setClientcellphone(String clientcellphone) {
		this.clientcellphone = clientcellphone;
	}
	

	
}
