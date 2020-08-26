SELECT distinct
  letter_config.id, 
  letter_config.deleted, 
  letter_config.loan_amount, 
  letter_config.loan_term, 
  letter_config.loan_type, 
  letter_config.location, 
  letter_config.ltv, 
  letter_config.max_pay, 
  letter_config.price
FROM 
  public.letter_config, 
  public.loan_client, 
  public.loan_client_operation, 
  public.loan_officer, 
  public.client, 
  public.operation
WHERE 
  loan_client.id = loan_client_operation.loan_client_id AND
  loan_client.loan_id = loan_officer.loan_id AND
  loan_client_operation.operation_id = operation.id AND
  client.id = loan_client.client_id AND
  operation.id = letter_config.id AND
  letter_config.deleted = false AND
  letter_config.active = true AND
  loan_officer.loan_id = 2;
