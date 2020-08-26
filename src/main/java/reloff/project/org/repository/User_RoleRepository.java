package reloff.project.org.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.User_Role;


public interface User_RoleRepository extends CrudRepository<User_Role, Long> {
	
	@Query(value = "SELECT  \r\n" + 
			"user_role.id, \r\n" +
			"user_role.role_id, \r\n" + 
			 "user_role.user_id \r\n" +  
			 "FROM \r\n" + 
			 "public.user_role \r\n" + 
	         "WHERE\r\n" + 
	         "user_role.user_id = :user_id", nativeQuery = true)
	User_Role findByUser_Id(@Param("user_id") Long user_id);
	
}
