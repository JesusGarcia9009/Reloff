package reloff.project.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

	@Query(value = "SELECT \r\n" + 
			"  client.id, \r\n" + 
			"  client.cellphone, \r\n" + 
			"  client.email, \r\n" + 
			"  client.last_name, \r\n" + 
			"  client.mailing_add, \r\n" + 
			"  client.name \r\n" + 
			"FROM \r\n" + 
			"  public.loan_officer, \r\n" + 
			"  public.loan_client, \r\n" + 
			//"  public.realtor, \r\n" + 
			//"  public.realtor_client, \r\n" + 
			"  public.client\r\n" + 
			"WHERE \r\n" + 
			"  loan_officer.loan_id = loan_client.loan_id AND\r\n" + 
			"  loan_client.client_id = client.id AND\r\n" + 
			//"  realtor.realtor_id = realtor_client.realtor_id AND\r\n" + 
			//"  realtor_client.client_id = client.id and\r\n" + 
			"  loan_officer.loan_id = :loan_id", nativeQuery = true)
	List<Client> findClientByLoan(@Param("loan_id") Long loan_id);
	
	@Query(value = "SELECT "
			+ " client.id,"
			+ " client.cellphone,"
			+ " client.email,"
			+ " client.last_name,"
			+ " client.mailing_add,"
			+ " client.name " 
	+ "FROM public.realtor,"
	+ " public.company_realtor,"
	+ " public.company_realtor_operation,"
	+ " public.loan_client_operation, loan_client,"
	+ " public.client "
	+ "WHERE realtor.realtor_id = company_realtor.realtor_id"
	+ " AND company_realtor_operation.company_realtor_id = company_realtor.id"
	+ " AND loan_client_operation.operation_id = company_realtor_operation.operation_id"
	+ " AND loan_client_operation.loan_client_id = loan_client.id AND loan_client.client_id = client.id"
	+ " AND realtor.realtor_id = :realtor_id",nativeQuery = true)
	List<Client> findClientByRealtor(@Param("realtor_id") Long realtor_id);

	
	@Query(value = "SELECT "
			+ " client.id,"
			+ " client.cellphone,"
			+ " client.email,"
			+ " client.last_name,"
			+ " client.mailing_add,"
			+ " client.name " 
	+ "FROM"
	+ " public.client "
	+ "WHERE client.email = :email",nativeQuery = true)
	Optional<Client> findClientByEmail(@Param("email") String email);

	
	
	
}
