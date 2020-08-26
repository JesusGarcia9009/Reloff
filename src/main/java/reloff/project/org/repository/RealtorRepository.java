package reloff.project.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.Realtor;

public interface RealtorRepository extends CrudRepository<Realtor, Long> {	

//public List<Realtor> findbyCompanyId(Long company_id);
	
//	@Query(value = "SELECT \r\n" + 
//			"  realtor.*, app_user.*\r\n" + 
//			"FROM \r\n" + 
//			"  public.company, \r\n" + 
//			"  public.company_realtor, \r\n" + 
//			"  public.realtor, \r\n" + 
//			"  public.app_user\r\n" + 
//			"WHERE \r\n" + 
//			"  company.id= company_realtor.company_id AND\r\n" + 
//			"  company_realtor.realtor_id = realtor.realtor_id AND\r\n" + 
//			"  realtor.realtor_id = app_user.user_id AND\r\n" + 
//			"  company.id = :company_id", nativeQuery = true
//			)
//	List<Realtor> findByCompany(@Param("company_id") Long company_id);

	
	@Query(value = "SELECT \r\n" + 
			"  realtor.realtor_id, \r\n" + 
			"  realtor.name, \r\n" + 
			"  realtor.last_name, \r\n" + 
			"  realtor.cellphone, \r\n" + 
			"  realtor.email, \r\n" + 
			"  realtor.license_number, \r\n" + 
			"  realtor.mailing_add, \r\n" + 
			"  realtor.notes, \r\n" + 
			"  app_user.user_id, \r\n" + 
			"  app_user.enabled, \r\n" + 
			"  app_user.encryted_password, \r\n" + 
			"  app_user.name, \r\n" + 
			"  realtor.broker_company_id\r\n" + 
			"FROM \r\n" + 
			"  public.realtor, \r\n" + 
			"  public.company, \r\n" + 
			"  public.company_realtor, \r\n" + 
			"  public.app_user\r\n" + 
			"WHERE \r\n" + 
			
			"  realtor.realtor_id = company_realtor.realtor_id AND\r\n" + 
			"  company_realtor.company_id = company.id AND\r\n" + 
			"  app_user.user_id = realtor.realtor_id and company.id =  :company_id", nativeQuery = true)
		List<Realtor> findByCompanyId(@Param("company_id")Long company_id);
}
