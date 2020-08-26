package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.User_Role;


@Service
public class User_RoleServices {

	
	@Autowired
	private reloff.project.org.repository.User_RoleRepository User_RoleRepository;
	
	@ExceptionHandler
	public List<User_Role> LoadAllUser_Role()  throws Exception {
		List<User_Role> List = new ArrayList<User_Role>();
		
		//el forEach es una referencia del metodo add a la lista
		//es lo mismo que recorrer la lista entera y agregarla 
		//asi se hace con expresiones LAMBA
		User_RoleRepository.findAll().forEach(List::add);
		
		return List;
	}
	
	@ExceptionHandler
	public Optional<User_Role> getUser_Role(Long id)  throws Exception {
		return User_RoleRepository.findById(id);
	}
	
	@ExceptionHandler
	public User_Role getUser_RoleByUser_Id(Long user_id)  throws Exception {
		return User_RoleRepository.findByUser_Id(user_id);
	}
	
	@ExceptionHandler
	public void AddUser_Role(User_Role user_role) throws Exception {
		User_RoleRepository.save(user_role);
	}
	
	@ExceptionHandler
	public void SaveAndUpdateUser_Role(User_Role user_role) throws Exception {
		User_RoleRepository.save(user_role);
	}
	
	@ExceptionHandler
	public void DeleteUser_Role(Long id) throws Exception {
		User_RoleRepository.deleteById(id);
	}
	
	
}
