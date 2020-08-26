package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Realtor;

@Service
public class RealtorServices {

	
	@Autowired
	private reloff.project.org.repository.RealtorRepository RealtorRepository;
	
	@ExceptionHandler
	public List<Realtor> LoadAllRealtor() throws Exception {
		List<Realtor> List = new ArrayList<Realtor>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		RealtorRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Realtor> getRealtor(Long id) throws Exception {
		return RealtorRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddRealtor(Realtor Realtor) throws Exception {
		RealtorRepository.save(Realtor);
	}
	
	@ExceptionHandler
	public void UpdateRealtor(Realtor Realtor) throws Exception {
		RealtorRepository.save(Realtor);
	}
	
	@ExceptionHandler
	public void DeleteRealtor(Long id) throws Exception {
		RealtorRepository.deleteById(id);
	}

	
	@ExceptionHandler
	public List<Realtor> findRealtorListByCompany(Long company_id) throws Exception {
		List<Realtor> List = new ArrayList<Realtor>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		RealtorRepository.findByCompanyId(company_id).forEach(List::add);		
		return List;
	}
	
	
}
