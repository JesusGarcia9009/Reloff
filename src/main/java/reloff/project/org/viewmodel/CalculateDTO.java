/**
 * 
 */
package reloff.project.org.viewmodel;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jesús Garcia
 */
public class CalculateDTO {
	
	private String location;
	private Integer price;
	private Double loanAmount;
	private Double interest;
	private Double mi;
	private Double insurance;
	private Double ltv;
	private Integer loanTerm;
	private String loanType;
	private Double maxPay;
	private Double hoa;
	private Double taxes;
	
	public CalculateDTO() {
		super();
	}

	public CalculateDTO(String location, Integer price, Double loanAmount, Double interest, Double mi, Double insurance,
			Double ltv, Integer loanTerm, String loanType, Double maxPay, Double hoa, Double taxes) {
		super();
		this.location = location;
		this.price = price;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.mi = mi;
		this.insurance = insurance;
		this.ltv = ltv;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.maxPay = maxPay;
		this.hoa = hoa;
		this.taxes = taxes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getMi() {
		return mi;
	}

	public void setMi(Double mi) {
		this.mi = mi;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getLtv() {
		return ltv;
	}

	public void setLtv(Double ltv) {
		this.ltv = ltv;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(Double maxPay) {
		this.maxPay = maxPay;
	}

	public Double getHoa() {
		return hoa;
	}

	public void setHoa(Double hoa) {
		this.hoa = hoa;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}
	
	

}
