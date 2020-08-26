package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Company;

@Service
public class CompanyServices {

	
	@Autowired
	private reloff.project.org.repository.CompanyRepository CompanyRepository;
	
	@ExceptionHandler
	public List<Company> LoadAllCompany() throws Exception {
		List<Company> List = new ArrayList<Company>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		CompanyRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Company> getCompany(Long id) throws Exception {
		return CompanyRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddCompany(Company Company) throws Exception {
		CompanyRepository.save(Company);
	}
	
	@ExceptionHandler
	public void UpdateCompany(Company Company) throws Exception {
		CompanyRepository.save(Company);
	}
	
	@ExceptionHandler
	public void DeleteCompany(Long id) throws Exception {
		CompanyRepository.deleteById(id);
	}
	
	
}
