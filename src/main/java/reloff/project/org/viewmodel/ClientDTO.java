package reloff.project.org.viewmodel;

public class ClientDTO {
	
	private Long idCliente;
	
	private String fullName;
	
	private boolean selected;

	
	public ClientDTO(Long idCliente, String fullName, boolean selected) {
		super();
		this.idCliente = idCliente;
		this.fullName = fullName;
		this.selected = selected;
	}

	public ClientDTO() {
		super();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	

}
