select distinct op.operation_id--, op.loan_client_id 
from public.loan_client_operation op, company_realtor_operation
		where op.operation_id = public.company_realtor_operation.operation_id AND 
                company_realtor_operation.company_realtor_id = 1
                and (select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id and 
                public.loan_client_operation.loan_client_id <> op.loan_client_id) = 0
                and op.loan_client_id in (1,2)


-- Para mas de un cliente
select distinct op.operation_id--, op.loan_client_id 
from public.loan_client_operation op, company_realtor_operation
		where op.operation_id = public.company_realtor_operation.operation_id AND 
                company_realtor_operation.company_realtor_id = 1
                and (select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id) > 1
                and (select count(*) from public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id) = 3
                and op.loan_client_id in (1,2,3)


