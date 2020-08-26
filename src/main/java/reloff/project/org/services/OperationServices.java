package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Operation;

@Service
public class OperationServices {

	
	@Autowired
	private reloff.project.org.repository.OperationRepository OperationRepository;
	
	
	@ExceptionHandler
	public List<Operation> LoadAllOperation() throws Exception {
		List<Operation> List = new ArrayList<Operation>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		OperationRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Operation> getOperation(Long id) throws Exception {
		return OperationRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddOperation(Operation Operation) throws Exception {
		OperationRepository.save(Operation);
	}
	
	@ExceptionHandler
	public void UpdateOperation(Operation Operation) throws Exception {
		OperationRepository.save(Operation);
	}
	
	@ExceptionHandler
	public void DeleteOperation(Long id) throws Exception {
		OperationRepository.deleteById(id);
	}	

	
}
