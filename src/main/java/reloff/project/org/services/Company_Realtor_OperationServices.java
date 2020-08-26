package reloff.project.org.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Company_Realtor_Operation;
import reloff.project.org.repository.Company_Realtor_OperationRepository;


@Service
public class Company_Realtor_OperationServices {

	
	@Autowired
	private Company_Realtor_OperationRepository Company_Realtor_OperationRepository;
	
	@ExceptionHandler
	public Company_Realtor_Operation getCompanyRealtorOperationByCompanyAndRealtor(Long company_realtor_id , Long operation_id) throws Exception {
		Company_Realtor_Operation cro = new Company_Realtor_Operation();
		if(Company_Realtor_OperationRepository.getCompany_RealtorByCompanyAndRealtor(company_realtor_id , operation_id) != null)			
		return Company_Realtor_OperationRepository.getCompany_RealtorByCompanyAndRealtor(company_realtor_id , operation_id);
		else
			return cro;
	}
	
	@ExceptionHandler
	public void AddCompany_Realtor_Operation(Company_Realtor_Operation Company_Realtor_Operation) throws Exception {
		Company_Realtor_OperationRepository.save(Company_Realtor_Operation);
	}
	
	@ExceptionHandler
	public void DeleteCompany_Realtor_Operation(long CRO_Id) throws Exception {
		Company_Realtor_OperationRepository.DeleteByCR_Id(CRO_Id);
	}
	
	
}
