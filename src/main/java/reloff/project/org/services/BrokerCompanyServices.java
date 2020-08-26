package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Broker_Company;

@Service
public class BrokerCompanyServices {

	
	@Autowired
	private reloff.project.org.repository.BrokerCompanyRepository BrokerCompanyRepository;
	
	
	@ExceptionHandler
	public List<Broker_Company> LoadAllBrokerCompany() throws Exception {
		List<Broker_Company> List = new ArrayList<Broker_Company>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		BrokerCompanyRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Broker_Company> getBrokerCompany(Long id) throws Exception {
		return BrokerCompanyRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddBrokerCompany(Broker_Company BrokerCompany) throws Exception {
		BrokerCompanyRepository.save(BrokerCompany);
	}
	
	@ExceptionHandler
	public void UpdateBrokerCompany(Broker_Company BrokerCompany) throws Exception {
		BrokerCompanyRepository.save(BrokerCompany);
	}
	
	@ExceptionHandler
	public void DeleteBrokerCompany(Long id) throws Exception {
		BrokerCompanyRepository.deleteById(id);
	}
	
	
}
