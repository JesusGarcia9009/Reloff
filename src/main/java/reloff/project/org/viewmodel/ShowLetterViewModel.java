package reloff.project.org.viewmodel;

import java.util.ArrayList;
import java.util.List;

import reloff.project.org.entity.Letter_config;


public class ShowLetterViewModel {
	
	private List<String> clientsName = new ArrayList<String>();
	
	private Letter_config letter;

	//constructor general
	public ShowLetterViewModel(List<String> clientsName, Letter_config letter) {
		super();
		this.clientsName = clientsName;
		this.letter = letter;
	}
	
	//constructor vacio
	public ShowLetterViewModel() {
	}

	public Letter_config getLetter() {
		return letter;
	}

	public void setLetter(Letter_config letter) {
		this.letter = letter;
	}

	public List<String> getClientsName() {
		return clientsName;
	}

	public void setClientsName(List<String> clientsName) {
		this.clientsName = clientsName;
	}

	
    	
}
