package reloff.project.org.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import reloff.project.org.entity.Loan_officer;

public interface Loan_officerRepository extends CrudRepository<Loan_officer, Long> {

	public List<Loan_officer> findByCompanyId(Long company_id);
}
