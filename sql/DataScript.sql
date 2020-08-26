/* SECURITY */

INSERT INTO "public"."app_user" ("user_id", "enabled", "encryted_password", "name")
VALUES (6, True, '$2a$10$IjvERyy32Gy/TH58IjY29OAUxNtYymrKzCDgiwKMuHoP1hGtvMUVi', 'joan');

INSERT INTO "public"."app_user" ("user_id", "enabled", "encryted_password", "name")
VALUES (1, True, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'realtor');

INSERT INTO "public"."app_user" ("user_id", "enabled", "encryted_password", "name")
VALUES (2, True, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'loan');


INSERT INTO "public"."role" ("role_id", "name")
VALUES (2, 'LOAN');

INSERT INTO "public"."role" ("role_id", "name")
VALUES (1, 'REALTOR');



INSERT INTO "public"."user_role" ("id", "role_id", "user_id")
VALUES (3, 2, 2);

INSERT INTO "public"."user_role" ("id", "role_id", "user_id")
VALUES (1, 1, 1);


/* BUSINESS */


INSERT INTO "public"."company" ("id", "mailing_add", "name", "nmls", "phisical_add")
VALUES (1, 'reloff@gmail.com', 'Reloff', 'nmls', 'Miami');



INSERT INTO "public"."loan_officer" ("cellphone", "email", "last_name", "mailing_add", "name", "nmls", "loan_id", "company_id")
VALUES ('fgbhfgh', 'jesusmanuel garcia@gmail.com', 'Garcia', 'fghfgh', 'Jesus', 'fghfgh', 2, 1);

INSERT INTO public.broker_company(id, name, phone, physical_add, web_site)
      values(1,'Company', '3053080000', '171 nw', 'company.com');

INSERT INTO public.realtor(
            cellphone, email, last_name, license_number, mailing_add, name, 
            notes, realtor_id, broker_company_id)
VALUES ('+5353334545', 'joannamiro@nauta.cu', 'Miro', '91060722273', 'joannamiro@nauta.cu', 'Joanna', 'notes' , 1, 1);

INSERT INTO public.realtor(
            cellphone, email, last_name, license_number, mailing_add, name, 
            notes, realtor_id, broker_company_id)
VALUES ('ghjghjghj', 'joanmiro@gmail.com', 'Miro', '91060722273', 'oanmiro@gmail.com', 'Joan', 'notes', 6, 1);


INSERT INTO public.client(id, cellphone, email, last_name, mailing_add, name)
values(1, '3053933020', 'joan@gmail.com', 'Miro', '171 nw', 'Joan');


INSERT INTO public.client(id, cellphone, email, last_name, mailing_add, name)
values(2, '3053933020', 'joanna@gmail.com', 'Miro', '171 nw', 'Joanna');

INSERT INTO public.company_realtor(id, company_id, realtor_id)
values(1, 1, 1);

INSERT INTO public.company_realtor(id, company_id, realtor_id)
values(2, 1, 6);

INSERT INTO public.loan_client(id, client_id, loan_id)
values(1, 1, 2);

INSERT INTO public.loan_client(id, client_id, loan_id)
values(2, 2, 2);

INSERT INTO public.letter_fixdata(id, conditions, finaltext, subject, deleted)
values(1, 'Comditions', 'finaltext', 'subject', FALSE);

--INSERT INTO public.letter_config(id, clients, deleted, loan_amount, loan_term, loan_type, location, ltv, max_pay, price, letter_fix_data_id)
--values(1, 'Ari', FALSE, 296000, 360, 'FHA', 'Miami', 96.5, 2550, 300000, 1)






