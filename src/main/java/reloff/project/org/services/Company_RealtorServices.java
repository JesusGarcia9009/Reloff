package reloff.project.org.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Company_Realtor;
import reloff.project.org.repository.Company_RealtorRepository;


@Service
public class Company_RealtorServices {

	
	@Autowired
	private Company_RealtorRepository Company_RealtorRepository;
	
	@ExceptionHandler
	public Company_Realtor getCompanyByCompanyAndRealtor(Long idCompany , Long idRealtor) throws Exception { //bateo cuando sea null
		return Company_RealtorRepository.getCompany_RealtorByCompanyAndRealtor(idCompany , idRealtor);
	}
	
	@ExceptionHandler
	public void AddCompany_Realtor(Company_Realtor Company_Realtor) throws Exception {
		Company_RealtorRepository.save(Company_Realtor);
	}
	
	@ExceptionHandler
	public void DeleteCompany_Realtor(long company_id, long realtor_id) throws Exception {
		Company_RealtorRepository.DeleteByCR(company_id, realtor_id);
	}
	
}
