package reloff.project.org.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import reloff.project.org.entity.Company_Realtor_Operation;

public interface Company_Realtor_OperationRepository extends CrudRepository<Company_Realtor_Operation, Long> {
	
	@Query(value = "SELECT public.company_realtor_operation.* FROM public.company_realtor_operation WHERE public.company_realtor_operation.company_realtor_id = :company_realtor_id AND public.company_realtor_operation.operation_id = :operation_id" , nativeQuery = true)
	public Company_Realtor_Operation getCompany_RealtorByCompanyAndRealtor(@Param("company_realtor_id") Long company_realtor_id, @Param("operation_id") Long operation_id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM public.company_realtor_operation WHERE public.company_realtor_operation.company_realtor_id= :company_realtor_id" , nativeQuery = true)
	public void DeleteByCR_Id(@Param("company_realtor_id") Long company_realtor_id);
}
