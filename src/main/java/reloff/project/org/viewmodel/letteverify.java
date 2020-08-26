package reloff.project.org.viewmodel;

import reloff.project.org.entity.Letter_config;

public class letteverify {

	private String uniqueKey;
	
	private Letter_config letter;

	public letteverify(String uniqueKey, Letter_config letter) {
		super();
		this.uniqueKey = uniqueKey;
		this.letter = letter;
	}

	public letteverify() {
		super();
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public Letter_config getLetter() {
		return letter;
	}

	public void setLetter(Letter_config letter) {
		this.letter = letter;
	}

	
	
	
}
