package reloff.project.org.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import reloff.project.org.entity.AppUser;


public interface AppUserRepository extends CrudRepository<AppUser, Long> {

	public Optional<AppUser> findByUserName(String name);	
}
