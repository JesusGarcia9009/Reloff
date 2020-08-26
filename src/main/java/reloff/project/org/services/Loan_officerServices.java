package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Loan_officer;

@Service
public class Loan_officerServices {

	
	@Autowired
	private reloff.project.org.repository.Loan_officerRepository Loan_officerRepository;
	
	@ExceptionHandler
	public List<Loan_officer> LoadAllLoan_officer() throws Exception {
		List<Loan_officer> List = new ArrayList<Loan_officer>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		Loan_officerRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public List<Loan_officer> LoadAllLoan_officerByCompany(Long company_id) throws Exception {
		List<Loan_officer> List = new ArrayList<Loan_officer>();
		Loan_officerRepository.findByCompanyId(company_id).forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Loan_officer getLoan_officer(Long id) throws Exception {
		Optional<Loan_officer> optinalEntity =  Loan_officerRepository.findById(id);
		return optinalEntity.get();
	}
	
	@ExceptionHandler
	public void AddLoan_officer(Loan_officer Loan_officer) throws Exception {
		Loan_officerRepository.save(Loan_officer);
	}
	
	@ExceptionHandler
	public void UpdateLoan_officer(Loan_officer Loan_officer) throws Exception {
		Loan_officerRepository.save(Loan_officer);
	}
	
	@ExceptionHandler
	public void DeleteLoan_officer(Long id) throws Exception {
		Loan_officerRepository.deleteById(id);
	}
	
	
}
