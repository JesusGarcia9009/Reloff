package reloff.project.org.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.viewmodel.CalculateDTO;
import reloff.project.org.viewmodel.CalculateResposeDTO;

@Service
public class CalculateServices {

	@Autowired
	private Letter_configServices letterConfigServices;

	@ExceptionHandler
	public CalculateResposeDTO calculate(CalculateDTO calculateDTO) throws Exception {

		double loanAmount = calculateLoanAmount(calculateDTO);
		loanAmount = Math.ceil(loanAmount);
		
		double MI = calculateMI(calculateDTO);

		double primaryPay = letterConfigServices.calculateMonthlyPayment(calculateDTO.getLoanAmount(),
				calculateDTO.getLoanTerm(), calculateDTO.getInterest());

		double maxPay = letterConfigServices.maxPaid(primaryPay, calculateDTO.getTaxes(), calculateDTO.getInsurance(),
				calculateDTO.getHoa(), MI);
		
		maxPay = Math.ceil(maxPay);
		
		return new CalculateResposeDTO( loanAmount, MI, primaryPay, maxPay) ;
	}
	
	
	private double calculateLoanAmount(CalculateDTO dto) {
		double result = -1 ;
		if (dto.getLoanType().equals("FHA")) {
			result = dto.getPrice() * ((dto.getLtv() / 100) + 0.0175);
		}

		if (dto.getLoanType().equals("Conventional")) {
			result = dto.getPrice() * ((dto.getLtv() / 100));
		}
		return result;
	}
	
	private double calculateMI(CalculateDTO dto) {
		return (dto.getPrice() * ((dto.getLtv() / 100)) * (dto.getMi() / 100)) / 12;
	}

}
