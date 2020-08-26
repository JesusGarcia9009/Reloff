package reloff.project.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.Letter_config;


public interface Letter_configRepository extends CrudRepository<Letter_config, Long> {

	
	@Query(value = "SELECT DISTINCT\r\n" +
			 "letter_config.id, \r\n" +
			 "letter_config.deleted, \r\n" +
			 "letter_config.loan_amount, \r\n" +
			 "letter_config.loan_term, \r\n" +
			 "letter_config.loan_type, \r\n" +
			 "letter_config.location, \r\n" +
			 "letter_config.ltv, \r\n" +
			 "letter_config.max_pay, \r\n" +
			 "letter_config.price, \r\n" +
			 "letter_config.hoa, \r\n" +
			 "letter_config.taxes, \r\n" +
			 "letter_config.letter_fix_data_id, \r\n" +
			 "letter_config.operation_id, \r\n" +
			 "letter_config.active \r\n" +
			// "loan_client_operation.loan_client_id, \r\n" +
			// "loan_client.id as client_id \r\n" +
			// "operation.id as operation_id\r\n" +
			"FROM \r\n" +
			 "public.letter_config, \r\n" +
			 "public.loan_client, \r\n" +
			 "public.loan_client_operation, \r\n" +
			 "public.loan_officer, \r\n" +
			 "public.client, \r\n" +
			 "public.operation\r\n" +
			"WHERE \r\n" +
			  "loan_client.id = loan_client_operation.loan_client_id AND\r\n" +
			  "loan_client.loan_id = loan_officer.loan_id AND\r\n" +
			  "loan_client_operation.operation_id = operation.id AND\r\n" +
			  "client.id = loan_client.client_id AND\r\n" +
			  "operation.id = letter_config.operation_id AND\r\n" +
			  "letter_config.deleted is not true AND\r\n" +
			  "loan_officer.loan_id = :loan_id" , nativeQuery = true )
	List<Letter_config> findByLoan(@Param("loan_id") Long loan_id);
	
	@Query(value = "SELECT DISTINCT\r\n" +
			 "letter_config.id, \r\n" +
			 "letter_config.deleted, \r\n" +
			 "letter_config.loan_amount, \r\n" +
			 "letter_config.loan_term, \r\n" +
			 "letter_config.loan_type, \r\n" +
			 "letter_config.location, \r\n" +
			 "letter_config.interest, \r\n" +
			 "letter_config.mi, \r\n" +
			 "letter_config.insurance, \r\n" +
			 "letter_config.ltv, \r\n" +
			 "letter_config.max_pay, \r\n" +
			 "letter_config.price, \r\n" +
			 "letter_config.hoa, \r\n" +
			 "letter_config.taxes, \r\n" +
			 "letter_config.letter_fix_data_id, \r\n" +
			 "letter_config.operation_id, \r\n" +
			 "letter_config.active, \r\n" +
			 "letter_config.unique_key \r\n" + 
			// "loan_client_operation.loan_client_id, \r\n" +
			// "loan_client.id as client_id \r\n" +
			// "operation.id as operation_id\r\n" +
			"FROM \r\n" +
			 "public.letter_config, \r\n" +
			 "public.loan_client, \r\n" +
			 "public.loan_client_operation, \r\n" +
			 "public.loan_officer, \r\n" +
			 "public.client, \r\n" +
			 "public.operation\r\n" +
			"WHERE \r\n" +
			  "loan_client.id = loan_client_operation.loan_client_id AND\r\n" +
			  "loan_client.loan_id = loan_officer.loan_id AND\r\n" +
			  "loan_client_operation.operation_id = operation.id AND\r\n" +
			  "client.id = loan_client.client_id AND\r\n" +
			  "operation.id = letter_config.operation_id AND\r\n" +
			  "letter_config.deleted is not true AND\r\n" +
			  //"letter_config.active = true AND\r\n" +
			  "loan_officer.loan_id = :loan_id" , nativeQuery = true )
	List<Letter_config> findActiveByLoan(@Param("loan_id") Long loan_id);
	
	@Query(value = "SELECT DISTINCT\r\n" + 
			  "letter_config.id, \r\n" + 
			  "letter_config.deleted, \r\n" + 
			  "letter_config.loan_amount, \r\n" + 
			  "letter_config.loan_term, \r\n" + 
			  "letter_config.loan_type, \r\n" + 
			  "letter_config.location, \r\n" + 
			  "letter_config.ltv, \r\n" + 
			  "letter_config.max_pay, \r\n" + 
			  "letter_config.price, \r\n" + 
			  "letter_config.hoa, \r\n" + 
			  "letter_config.letter_fix_data_id, \r\n" + 
			  "letter_config.taxes \r\n" + 
			  //--loan_client_operation.loan_client_id, 
			  //--loan_client.id as client_id--, 
			  //--operation.id as operation_id
			"FROM \r\n" + 
			  "public.letter_config, \r\n" + 
			  "public.company_realtor, \r\n" + 
			  "public.company_realtor_operation, \r\n" + 
			  "public.realtor, \r\n" + 
			  "public.operation\r\n" + 
			  //--public.company
			"WHERE \r\n" +
			//--company_realtor.company_id = company.id AND
			"company_realtor.realtor_id = realtor.realtor_id AND \r\n" + 
			"company_realtor_operation.company_realtor_id = company_realtor.id AND \r\n" + 
			"company_realtor_operation.operation_id = operation.id AND \r\n" + 
			"operation.letter_config_id = letter_config.id AND \r\n" + 
			"letter_config.deleted = false AND \r\n" + 
			"realtor.realtor_id = :realtor_id", nativeQuery = true )
			List<Letter_config> findByRealtor(@Param("realtor_id") Long realtor_id);
			
			@Query(value = "SELECT DISTINCT\r\n" + 
					  "letter_config.id, \r\n" +
					  "letter_config.deleted, \r\n" + 
					  "letter_config.loan_amount, \r\n" + 
					  "letter_config.loan_term, \r\n" + 
					  "letter_config.loan_type, \r\n" + 
					  "letter_config.location, \r\n" + 
					  "letter_config.interest, \r\n" +
					  "letter_config.mi, \r\n" +
					  "letter_config.insurance, \r\n" +
					  "letter_config.ltv, \r\n" + 
					  "letter_config.max_pay, \r\n" + 
					  "letter_config.price, \r\n" + 
					  "letter_config.hoa, \r\n" + 
					  "letter_config.letter_fix_data_id, \r\n" + 
					  "letter_config.taxes, \r\n" + 
					  "letter_config.active, \r\n" + 
					  "letter_config.operation_id, \r\n" + 
					  "letter_config.unique_key \r\n" + 
					  //--loan_client_operation.loan_client_id, 
					  //--loan_client.id as client_id--, 
					  //--operation.id as operation_id
					"FROM \r\n" + 
					  "public.letter_config, \r\n" + 
					  "public.company_realtor, \r\n" + 
					  "public.company_realtor_operation, \r\n" + 
					  "public.realtor, \r\n" + 
					  "public.operation\r\n" + 
					  //--public.company
					"WHERE \r\n" +
					//--company_realtor.company_id = company.id AND
					"company_realtor.realtor_id = realtor.realtor_id AND \r\n" + 
					"company_realtor_operation.company_realtor_id = company_realtor.id AND \r\n" + 
					"company_realtor_operation.operation_id = operation.id AND \r\n" + 
					"operation.id = letter_config.operation_id AND \r\n" + 
					"letter_config.deleted = false AND \r\n" + 
					//"letter_config.active = true AND \r\n" + 
					"realtor.realtor_id = :realtor_id", nativeQuery = true )
					List<Letter_config> findActiveByRealtor(@Param("realtor_id") Long realtor_id);
					
					@Query(value = "SELECT \r\n" +
							 "letter_config.id, \r\n" +
							 "letter_config.deleted, \r\n" +
							 "letter_config.loan_amount, \r\n" +
							 "letter_config.loan_term, \r\n" +
							 "letter_config.loan_type, \r\n" +
							 "letter_config.location, \r\n" +
							 "letter_config.interest, \r\n" +
							 "letter_config.mi, \r\n" +
							 "letter_config.insurance, \r\n" +
							 "letter_config.ltv, \r\n" +
							 "letter_config.max_pay, \r\n" +
							 "letter_config.price, \r\n" +
							 "letter_config.hoa, \r\n" +
							 "letter_config.taxes, \r\n" +
							 "letter_config.letter_fix_data_id, \r\n" +
							 "letter_config.operation_id, \r\n" +
							 "letter_config.active, \r\n" +
							 "letter_config.unique_key \r\n" + 
							// "loan_client_operation.loan_client_id, \r\n" +
							// "loan_client.id as client_id \r\n" +
							// "operation.id as operation_id\r\n" +
							"FROM \r\n" +
							 "public.letter_config, \r\n" +
							 "public.loan_client, \r\n" +
							 "public.loan_client_operation, \r\n" +
							 "public.loan_officer, \r\n" +
							 "public.client, \r\n" +
							 "public.operation\r\n" +
							"WHERE \r\n" +
							  "loan_client.id = loan_client_operation.loan_client_id AND\r\n" +
							  "loan_client.loan_id = loan_officer.loan_id AND\r\n" +
							  "loan_client_operation.operation_id = operation.id AND\r\n" +
							  "client.id = loan_client.client_id AND\r\n" +
							  "operation.id = letter_config.operation_id AND\r\n" +
							  "letter_config.deleted is not true AND\r\n" +
							  "letter_config.active = true AND\r\n" +
							  "letter_config.operation_id = :operation_id" , nativeQuery = true )
					List<Letter_config> findActiveByOperation(@Param("operation_id") Long operation_id);
					
			public Letter_config findByUniqueKey(String uniqueKey);	
}
