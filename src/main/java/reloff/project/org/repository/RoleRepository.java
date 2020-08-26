package reloff.project.org.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.AppRole;


public interface RoleRepository extends CrudRepository<AppRole, Long> {

	@Query(value = "SELECT  \r\n" + 
			 "role.role_id, \r\n" + 
			 "role.name \r\n" +  
			 "FROM \r\n" + 
			 "public.role \r\n" + 
	         "WHERE\r\n" + 
	         "role.name = :roleName", nativeQuery = true)
	AppRole findByRoleName(@Param("roleName") String roleName);

}
