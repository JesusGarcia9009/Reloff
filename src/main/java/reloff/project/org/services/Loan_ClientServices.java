package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Client;
import reloff.project.org.entity.Loan_Client;
import reloff.project.org.entity.Loan_officer;

@Service
public class Loan_ClientServices {

	
	@Autowired
	private reloff.project.org.repository.Loan_ClientRepository Loan_ClientRepository;
	
	@ExceptionHandler
	public List<Loan_Client> LoadAllLoan_Client() throws Exception {
		List<Loan_Client> List = new ArrayList<Loan_Client>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		Loan_ClientRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Loan_Client> getLoan_Client(Long id) throws Exception {
		return Loan_ClientRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddLoan_Client(Client client, Loan_officer off ) throws Exception {
		Loan_Client loan_client = new Loan_Client(off,  client);
		Loan_ClientRepository.save(loan_client);
	}
	
	@ExceptionHandler
	public void UpdateLoan_Client(Loan_Client Loan_Client) throws Exception {
		Loan_ClientRepository.save(Loan_Client);
	}
	
	@ExceptionHandler
	public void DeleteLoan_Client(Long id) throws Exception {
		Loan_ClientRepository.deleteById(id);
	}
	
	@ExceptionHandler
	public void DeleteLoan_ClientByIdClient(Long client_id) throws Exception {
		Loan_ClientRepository.DeleteByClient(client_id);
	}
	
	@ExceptionHandler
	public void DeleteLoan_ClientByLoanAndClient(Long loan_id, Long client_id) throws Exception {
		Loan_ClientRepository.DeleteByLoanAndClient(loan_id, client_id);;
	}
	
	@ExceptionHandler
	public Loan_Client getLoan_ClientByClientAndLoan(Long idLoan, Long idClient) throws Exception {
		return Loan_ClientRepository.getLoan_ClientByClientAndLoan(idLoan, idClient);
	}
	
	@ExceptionHandler
	public List<Loan_Client> getLoan_ClientByClienList(Long loan_id, List<Long> clientIdList) throws Exception {
		return Loan_ClientRepository.getLoan_ClientByClientList(loan_id, clientIdList);
	}
	
	
	
	
}
