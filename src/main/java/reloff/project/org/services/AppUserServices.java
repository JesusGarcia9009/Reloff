package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.AppUser;


@Service
public class AppUserServices {

	
	@Autowired
	private reloff.project.org.repository.AppUserRepository AppUserRepository;
	
	@ExceptionHandler
	public List<AppUser> LoadAllAppUser()  throws Exception {
		List<AppUser> List = new ArrayList<AppUser>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		AppUserRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<AppUser> getAppUser(Long id)  throws Exception {
		return AppUserRepository.findById(id);
	}
	
	@ExceptionHandler
	public Optional<AppUser> getAppUserByName(String name)  throws Exception {
		return AppUserRepository.findByUserName(name);
	}
		
	@ExceptionHandler
	public void AddRAppUser(AppUser AppUser) throws Exception {
		AppUserRepository.save(AppUser);
	}
	
	@ExceptionHandler
	public void SaveAndUpdateAppUser(AppUser AppUser) throws Exception {
		AppUserRepository.save(AppUser);
	}
	
	@ExceptionHandler
	public void DeleteAppUser(Long id) throws Exception {
		AppUserRepository.deleteById(id);
	}
	
	
}
