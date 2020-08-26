package reloff.project.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Letter_config")
public class Letter_config {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "location", length = 500, nullable = false) // Posible locación de la carta
	@NotNull(message = "The Location is required")
	@NotBlank(message = "The Location is required.")
	@Size(min = 3, max = 500, message = "The location must be {min} to {max} characters in length.")
	private String location;

	@Column(name = "uniqueKey", length = 250)
	private String uniqueKey;

	@Column(name = "price", length = 500, nullable = false) // Precio de venta de la casa
	@NotNull(message="The price is required")
	private Integer price;

	@Column(name = "loanAmount", length = 500) // Valor del préstamo
	private Double loanAmount;

	@Column(name = "interest", length = 500, nullable = false) // Valor del interest
	@NotNull(message = "The Interest is required")
	@Min(value = 0, message="The minimum interest is 0")
	private Double interest;

	@Column(name = "mi", length = 500) // Valor del interest
	@NotNull(message = "The MI can not be NULL")
	@Min(value = 0, message="The minimum MI is 0")
	private Double mi;

	@Column(name = "insurance", length = 500, nullable = false) // Valor del interest
	@NotNull(message = "The Insurance is required")
	@Min(value = 0, message="The minimum Insurance is 0")
	private Double insurance;

	@Column(name = "ltv", length = 500, nullable = false) // ???
	@NotNull(message = "The LTV is required")
	@Min(value = 0, message="The minimum LTV is 0")
	private Double ltv;

	@Column(name = "loanTerm", length = 500, nullable = false) // Tiempo por el que se va a otorgar el préstamo
	@NotNull(message = "The Loan Term is required")
	private Integer loanTerm;

	@Column(name = "loanType", length = 500) // Tipo de préstamo
	@NotNull(message = "The Loan type is required")
	@NotBlank(message = "The Loan type is required.")
	private String loanType;

	@Column(name = "maxPay", length = 500, nullable = false) // Máximo a pagar por mes (Mortgage)
	@NotNull(message = "The MaxPayment is required")
	@Min(value = 0 , message="The minimum Max Pay is 0")
	private Double maxPay;

	@Column(name = "hoa", length = 500) // Comunidad
	@NotNull(message = "The hoa is required")
	@Min(value = 0, message="The minimum HOA is 0")
	private Double hoa;

	@Column(name = "taxes", length = 500, nullable = false) // Comunidad
	@NotNull(message = "The taxes are required")
	@Min(value = 0, message="The minimum taxes is 0")
	private Double taxes;

	@Column(name = "deleted")
	private boolean deleted;

	@Column(name = "active") // Ultima carta creada
	private boolean active;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LetterFixDataId") // El @JoinColumn se le pone a la tabla que guarda el ID de la otra. Ej: Aquí
											// esta tiene el id de Letter_FixData
	private Letter_FixData letter_fixdata;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OperationId")
	private Operation operation;

	public Letter_config() {
		super();
	}

	public Letter_config(Long id, String location, String uniqueKey, Integer price, Double loanAmount, Double interest,
			Double mi, Double insurance, Double ltv, Integer loanTerm, String loanType, Double maxPay, Double hoa,
			Double taxes, boolean deleted, boolean active, Letter_FixData letter_fixdata, Operation operation) {
		super();
		this.id = id;
		this.location = location;
		this.uniqueKey = uniqueKey;
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
		this.deleted = deleted;
		this.active = active;
		this.letter_fixdata = letter_fixdata;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Letter_FixData getLetter_fixdata() {
		return letter_fixdata;
	}

	public void setLetter_fixdata(Letter_FixData letter_fixdata) {
		this.letter_fixdata = letter_fixdata;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}
