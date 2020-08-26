select distinct op.operation_id from public.loan_client_operation op, company_realtor_operation
where op.operation_id = public.company_realtor_operation.operation_id
and (select count(*) from  public.loan_client_operation where public.loan_client_operation.operation_id = op.operation_id 
and loan_client_id in (1,2,3)) = 3 AND company_realtor_operation.company_realtor_id = 1 --and op.loan_client_id in (1, 2, 3)


--select count(*) from  public.loan_client_operation where public.loan_client_operation.operation_id = 1 
--and public.loan_client_operation.loan_client_id in (1, 2, 3)