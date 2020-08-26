package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.AppRole;


@Service
public class RoleServices {

	
	@Autowired
	private reloff.project.org.repository.RoleRepository RoleRepository;
	
	@ExceptionHandler
	public List<AppRole> LoadAllRole()  throws Exception {
		List<AppRole> List = new ArrayList<AppRole>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		RoleRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<AppRole> getRole(Long id)  throws Exception {
		return RoleRepository.findById(id);
	}
	
	@ExceptionHandler
	public AppRole getRoleByName(String roleName)  throws Exception {
		return RoleRepository.findByRoleName(roleName);
	}
		
	@ExceptionHandler
	public void AddRole(AppRole Role) throws Exception {
		RoleRepository.save(Role);
	}
	
	@ExceptionHandler
	public void SaveAndUpdateRole(AppRole Role) throws Exception {
		RoleRepository.save(Role);
	}
	
	@ExceptionHandler
	public void DeleteRole(Long id) throws Exception {
		RoleRepository.deleteById(id);
	}
	
	
}
