package reloff.project.org.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import reloff.project.org.entity.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long> {
	
	@Query(value = "SELECT public.operation.* FROM public.loan_client_operation, company_realtor_operation, operation "
			+ "WHERE public.loan_client_operation.operation_id = public.company_realtor_operation.operation_id AND "
			+ "public.loan_client_operation.loan_client_id = :loan_clientId AND company_realtor_operation.company_realtor_id = :realtor_Id " , nativeQuery = true)
	public Operation getOperationIdbyLoan_Client_Realtor(@Param("loan_clientId") Long loan_clientId, @Param("realtor_Id") Long realtor_Id);	
	
	
	@Query(value = "select distinct op.operation_id from public.loan_client_operation op, company_realtor_operation "
			+ "where op.operation_id = public.company_realtor_operation.operation_id  AND "
			+ "(select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id) >1  AND "
			+ "(select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id) = :clsize AND "
			+ "op.loan_client_id in (:loan_clientIdList) AND "
			+ "company_realtor_operation.company_realtor_id = :company_realtor_Id " , nativeQuery = true)
	public Long getcomunOperationId(@Param("loan_clientIdList") java.util.List<Long> loan_clientIdList, @Param("company_realtor_Id") Long company_realtor_Id, @Param("clsize") int clsize);
	
	
	@Query(value = "select distinct op.operation_id from public.loan_client_operation op, company_realtor_operation "
			+ "where op.operation_id = public.company_realtor_operation.operation_id  AND "
			+ "op.loan_client_id in (:loan_clientIdList) AND "
			+ "(select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id and public.loan_client_operation.loan_client_id <> op.loan_client_id) = 0 AND "
			+ "company_realtor_operation.company_realtor_id = :company_realtor_Id " , nativeQuery = true)
	public Long getlcOperationByList(@Param("loan_clientIdList") java.util.List<Long> loan_clientIdList, @Param("company_realtor_Id") Long company_realtor_Id);
	

}
