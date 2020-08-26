package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Client;
import reloff.project.org.entity.Loan_Client;
import reloff.project.org.entity.Loan_officer;

@Service
public class ClientServices {

	
	@Autowired
	private reloff.project.org.repository.ClientRepository ClientRepository;
	
	@Autowired
	private reloff.project.org.repository.Loan_ClientRepository Loan_ClientRepository;
	
	@ExceptionHandler
	public List<Client> LoadAllClient() throws Exception {
		List<Client> List = new ArrayList<Client>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBDA
		ClientRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Client getClient(Long id) throws Exception {
		Optional<Client> optinalEntity =  ClientRepository.findById(id);
		return optinalEntity.get();
	}
	
	@ExceptionHandler
	public Client findClientByEmail(String email) throws Exception {
		Optional<Client> optinalEntity =  ClientRepository.findClientByEmail(email);
		return optinalEntity.get();
	}
	
	
	@ExceptionHandler
	public void AddClient(Client Client) throws Exception {
		ClientRepository.save(Client);
	}
	
	@ExceptionHandler
	public void UpdateClient(Client Client) throws Exception {
		ClientRepository.save(Client);
	}
	
	
	@ExceptionHandler
	public void DeleteClient(Long id) throws Exception {
		ClientRepository.deleteById(id);
	}
	
	@ExceptionHandler
	public List<Client> findClientListByLoan(Long loan_id) throws Exception {
		List<Client> List = new ArrayList<Client>();
		ClientRepository.findClientByLoan(loan_id).forEach(List::add);
		return List;
	}
	
	@ExceptionHandler
	public List<Client> findClientListByRealtor(Long realtor_id) throws Exception {
		List<Client> List = new ArrayList<Client>();
		ClientRepository.findClientByRealtor(realtor_id).forEach(List::add);
		return List;
	}
	
	@Transactional
	@ExceptionHandler
	public void AddClientAndLoanClient(Client client, Loan_officer off ) throws Exception {
		ClientRepository.save(client);
		Loan_Client loan_client = new Loan_Client(off,  client);
		Loan_ClientRepository.save(loan_client);
	} 
	

	
	
}
