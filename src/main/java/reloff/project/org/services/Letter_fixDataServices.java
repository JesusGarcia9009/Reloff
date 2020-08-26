package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.Letter_FixData;

@Service
public class Letter_fixDataServices {

	
	@Autowired
	private reloff.project.org.repository.Letter_fixDataRepository Letter_fixDataRepository;
	
	
	@ExceptionHandler
	public List<Letter_FixData> LoadAllLetter_fixData() throws Exception {
		List<Letter_FixData> List = new ArrayList<Letter_FixData>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		Letter_fixDataRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<Letter_FixData> getLetter_FixData(Long id) throws Exception {
		return Letter_fixDataRepository.findById(id);
	}
	
	@ExceptionHandler
	public void AddLetter_FixData(Letter_FixData fixData) throws Exception {
		Letter_fixDataRepository.save(fixData);
	}
	
	@ExceptionHandler
	public void UpdateLetter_FixData(Letter_FixData fixData) throws Exception {
		Letter_fixDataRepository.save(fixData);
	}
	
	@ExceptionHandler
	public void DeleteLetter_FixData(Long id) throws Exception {
		Letter_fixDataRepository.deleteById(id);
	}	

	
}
