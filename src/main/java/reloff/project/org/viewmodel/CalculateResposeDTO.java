/**
 * 
 */
package reloff.project.org.viewmodel;

/**
 * @author Jesús Garcia
 */
public class CalculateResposeDTO {
	
	private Double LoanAmount;
	private Double MI;
	private Double primaryPay;
	private Double maxPay;
	
	public CalculateResposeDTO() {
		super();
	}

	public CalculateResposeDTO(Double loanAmount, Double MI, Double primaryPay, Double maxPay) {
		super();
		LoanAmount = loanAmount;
		this.MI = MI;
		this.primaryPay = primaryPay;
		this.maxPay = maxPay;
	}

	public Double getLoanAmount() {
		return LoanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		LoanAmount = loanAmount;
	}

	public Double getMI() {
		return MI;
	}

	public void setMI(Double mI) {
		MI = mI;
	}

	public Double getPrimaryPay() {
		return primaryPay;
	}

	public void setPrimaryPay(Double primaryPay) {
		this.primaryPay = primaryPay;
	}

	public Double getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(Double maxPay) {
		this.maxPay = maxPay;
	}
	
	
	
}
