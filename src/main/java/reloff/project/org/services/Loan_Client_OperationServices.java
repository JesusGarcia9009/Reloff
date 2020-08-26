package reloff.project.org.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Loan_Client_Operation;
import reloff.project.org.entity.Operation;

@Service
public class Loan_Client_OperationServices {

	
	@Autowired
	private reloff.project.org.repository.Loan_Client_OperationRepository Loan_Client_OperationRepository;
	
	@Autowired
	private reloff.project.org.repository.OperationRepository OperationRepository;

	@ExceptionHandler
	public void AddLoan_Client_Operation(Loan_Client_Operation loan_Client_Operation) throws Exception {
		Loan_Client_OperationRepository.save(loan_Client_Operation);
	}
	
//	@ExceptionHandler
//	public Operation findOperationIdByLoan_Client(Long loan_clientId) throws Exception {
//		Operation operation = new Operation();
//		operation = Loan_Client_OperationRepository.getOperationIdbyLoan_Client(loan_clientId);
//			return operation;		
//	}
//	
	@ExceptionHandler
	public Operation findOperationByLoan_Client_Realtor(Long loan_client_id, Long company_realtor_id) throws Exception {
		Operation operation = new Operation();		
		operation = OperationRepository.getOperationIdbyLoan_Client_Realtor(loan_client_id, company_realtor_id);
			return operation;		
	}
	
	@ExceptionHandler
	public List<Loan_Client_Operation> findLCOByOperation(Operation op) throws Exception {
	    long op_id = op.getId();
		List<Loan_Client_Operation> lcoList = Loan_Client_OperationRepository.getLCOByOperation(op_id);
			return lcoList;		
	}
	
//	@ExceptionHandler
//	public Operation getcomunOperationId(java.util.List<Long> loan_clientIdList, int length, Long company_realtor_id) throws Exception {
//		Operation operation = new Operation();		
//		operation = OperationRepository.getcomunOperationId( loan_clientIdList, length, company_realtor_id);
//			return operation;		
//	}
	
	@ExceptionHandler
	public Long getcomunOperationId(java.util.List<Long> loan_clientIdList, Long company_realtor_id, int clsize) throws Exception {
		Long operation;		
		operation = OperationRepository.getcomunOperationId( loan_clientIdList, company_realtor_id, clsize);
			return operation;		
	}
	
	@ExceptionHandler
	public Long getlcOperationByList(java.util.List<Long> loan_clientIdList, Long company_realtor_id) throws Exception {
		Long operation;		
		operation = OperationRepository.getlcOperationByList( loan_clientIdList, company_realtor_id);
			return operation;		
	}
	
	@ExceptionHandler
	public Loan_Client_Operation getLCO(Long id) throws Exception {
		Optional<Loan_Client_Operation> optinalEntity =  Loan_Client_OperationRepository.findById(id);
		return optinalEntity.get();
	}
	
	@ExceptionHandler
	public Loan_Client_Operation getLCOByOperation(Long operation_id) throws Exception {
		Optional<Loan_Client_Operation> optinalEntity =  Loan_Client_OperationRepository.findByOperationId(operation_id);
		return optinalEntity.get();
	}
	
	@ExceptionHandler
	public Loan_Client_Operation findlocByLoan_Client(Long loan_clientId) throws Exception {
		Loan_Client_Operation lco = Loan_Client_OperationRepository.getlocByLoan_Client(loan_clientId);
		return lco;
	}
	
	@ExceptionHandler
	public void DeleteLCOperation(Long id) throws Exception {
		Loan_Client_OperationRepository.deleteById(id);
	}		
	
}
