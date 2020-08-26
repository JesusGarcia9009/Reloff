package reloff.project.org.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Client;
import reloff.project.org.entity.Company_Realtor;
import reloff.project.org.entity.Company_Realtor_Operation;
import reloff.project.org.entity.Letter_FixData;
import reloff.project.org.entity.Letter_config;
import reloff.project.org.entity.Loan_Client;
import reloff.project.org.entity.Loan_Client_Operation;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Operation;
import reloff.project.org.entity.Realtor;
import reloff.project.org.entity.User_Role;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.services.ClientServices;
import reloff.project.org.services.Company_Realtor_OperationServices;
import reloff.project.org.services.Letter_configServices;
import reloff.project.org.services.Letter_fixDataServices;
import reloff.project.org.services.Loan_ClientServices;
import reloff.project.org.services.Loan_Client_OperationServices;
import reloff.project.org.services.OperationServices;
import reloff.project.org.services.RealtorServices;
import reloff.project.org.services.User_RoleServices;
import reloff.project.org.utils.EmailNewLetter;
import reloff.project.org.viewmodel.LetterOperationViewModel;
import reloff.project.org.viewmodel.ShowLetterViewModel;

@Controller
public class Letter_configController {

	@Autowired
	private Letter_configServices Letter_configServices;

	@Autowired
	private ClientServices ClientServices;

	// @Autowired
	// private Loan_officerServices Loan_officerServices;

	@Autowired
	private Loan_ClientServices Loan_ClientServices;

	@Autowired
	private reloff.project.org.services.Company_RealtorServices Company_RealtorServices;

	@Autowired
	private OperationServices OperationServices;

	@Autowired
	private Company_Realtor_OperationServices Company_Realtor_OperationServices;

	@Autowired
	private Loan_Client_OperationServices Loan_Client_OperationServices;

	@Autowired
	private RealtorServices RealtorServices;

	@Autowired
	private User_RoleServices User_RoleServices;

//	@Autowired
//	private Client client;	

	 @Autowired
	 private EmailNewLetter EmailNewLetter;

	@Autowired
	private Letter_fixDataServices Letter_fixDataServices;

	@RequestMapping(value = "/Letter_configList")
	public String Letter_configList(Model model) {
		try {
			model.addAttribute("title", "Welcome");
			model.addAttribute("message", "Reloff Dashboard");

			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();

			if (temp instanceof Loan_officer) {
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				List<Letter_config> letterListSize = Letter_configServices.findActiveLetterListByLoan(temp.getUserId());
				List<ShowLetterViewModel> letterList = Letter_configServices
						.findActiveLetterListByLoanNew(temp.getUserId());

				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantletter", letterListSize.size());
				model.addAttribute("letterList", letterList);
			}
			if (temp instanceof Realtor) {
				// Long idCompany = ((Realtor) temp).g .getId();
				List<ShowLetterViewModel> letterList = Letter_configServices
						.findActiveLetterListByRealtorNew(temp.getUserId());
				model.addAttribute("name", ((Realtor) temp).getName() + "  " + ((Realtor) temp).getLast_name());
				model.addAttribute("type", "REALTOR");
				// model.addAttribute("companyname", idCompany);
				model.addAttribute("cantletter", letterList.size());
				model.addAttribute("letterList", letterList);
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configlist";
	}

	/////////////// PRINT CARTA
	/////////////// ///////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = { "/Letter_configLetter{id}" }, method = RequestMethod.GET)
	public String Letter_configLetter(Model model, @PathVariable(required = false, name = "id") Long id) {
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();

		if (null != id) {
			try {
				// model.addAttribute("Letter_config",
				// Letter_configServices.getLetter_config(id).get());
				Client client = new Client();
				List<String> clientList = new ArrayList<String>();
				/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION
				ShowLetterViewModel lovm = new ShowLetterViewModel();
				lovm.setLetter(Letter_configServices.getLetter_config(id).get());
				List<Loan_Client_Operation> lcoList = lovm.getLetter().getOperation().getLoan_Client_Operation();
				for (int y = 0; y < lcoList.size(); y++) {
					client = lcoList.get(y).getLoan_Client().getClient();
					clientList.add(client.getName() + " " + client.getLast_name());
				}
				lovm.setClientsName(clientList);
				model.addAttribute("Letter_config", lovm);

				model.addAttribute("fix_data_subject",
						Letter_configServices.getLetter_config(id).get().getLetter_fixdata().getSubject()); // Por qué
																											// necesito
																											// especificar
																											// el get
																											// para
																											// meterlo
																											// en un
																											// span tag
																											// ???
				model.addAttribute("fix_data_conditions",
						Letter_configServices.getLetter_config(id).get().getLetter_fixdata().getConditions());
				model.addAttribute("fix_data_ftext",
						Letter_configServices.getLetter_config(id).get().getLetter_fixdata().getFinaltext());
				model.addAttribute("loan", temp.getUserId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Por qué necesito especificar el get para meterlo en un span tag ???

		} else {
			model.addAttribute("Letter_config", new Letter_config());
		}
		return "/Letter_config/Letter_configLetter";
	}

	///////// MODIFICAR
	///////// CARTA///////////////////////////////////////////////////////////////////////////////////////
	/*
	 * @RequestMapping(value = { "/Letter_configEdit{id}" }, method =
	 * RequestMethod.GET) public String Letter_configEditForm(Model
	 * model, @PathVariable(required = false, name = "id") Long id) { try { Client
	 * client = new Client(); List<String> clientList = new ArrayList<String>();
	 * AppUser temp = ((UserLogIn)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal())
	 * .getAppUser(); User_Role user_role =
	 * User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
	 * model.addAttribute("user", user_role.getAppRole().getRoleName());
	 * 
	 * /// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION ShowLetterViewModel lovm
	 * = new ShowLetterViewModel();
	 * lovm.setLetter(Letter_configServices.getLetter_config(id).get());
	 * List<Loan_Client_Operation> lcoList =
	 * lovm.getLetter().getOperation().getLoan_Client_Operation();
	 * 
	 * for (int y = 0; y < lcoList.size(); y++) { client =
	 * lcoList.get(y).getLoan_Client().getClient(); clientList.add(client.getName()
	 * + " " + client.getLast_name()); } lovm.setClientsName(clientList);
	 * model.addAttribute("Letter_config", lovm); ///
	 * -----------------------------------------------------------------------------
	 * ---------------------
	 * 
	 * } catch (Exception e) { model.addAttribute("errorMessage",
	 * "An error has occurred, please contact the system administrator");
	 * e.printStackTrace(); } return "/Letter_config/Letter_configEdit"; }
	 */

	@RequestMapping(value = { "/Letter_configEdit2{id}" }, method = RequestMethod.GET)
	public String Letter_configEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			Client client = new Client();
			List<String> clientList = new ArrayList<String>();
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			User_Role user_role = User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
			model.addAttribute("user", user_role.getAppRole().getRoleName());

			/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION

			List<Loan_Client_Operation> lcoList = Letter_configServices.getLetter_config(id).get().getOperation()
					.getLoan_Client_Operation();

			for (int y = 0; y < lcoList.size(); y++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clientList.add(client.getName() + " " + client.getLast_name());
			}
			// model.addAttribute("Letter_config", lovm);
			model.addAttribute("clientsName", clientList);
			model.addAttribute("Letter_config", Letter_configServices.getLetter_config(id).get());

			/// --------------------------------------------------------------------------------------------------

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configEdit2";
	}

	@RequestMapping(value = "/Letter_configEdit2", method = RequestMethod.POST)
	public String Letter_configEdit(@Valid @ModelAttribute("Letter_config") Letter_config Letter_config,
			BindingResult errors, Model model) {
		try { 
		if (errors.hasErrors()) {
			
			/// Re- Mapeando ////////////////////////////////////////////////
			Client client = new Client();
			List<String> clientList = new ArrayList<String>();
			
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			User_Role user_role = User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
			model.addAttribute("user", user_role.getAppRole().getRoleName());

			/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION

			List<Loan_Client_Operation> lcoList = Letter_config.getOperation().getLoan_Client_Operation();

			for (int y = 0; y < lcoList.size(); y++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clientList.add(client.getName() + " " + client.getLast_name());
			}
			// model.addAttribute("Letter_config", lovm);
			model.addAttribute("clientsName", clientList);
			model.addAttribute("Letter_config", Letter_config);
			///////////////////////////////////////////////////////////////////
			return "/Letter_config/Letter_configEdit2";
			
		} else {
			
				AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
						.getAppUser();

				if ((temp instanceof Loan_officer) || (temp instanceof Realtor)) {
					// solo temporal pq esto va a la tabla tipo de préstamo
					double loanAmount = 0;
					double MI = (Letter_config.getPrice() * ((Letter_config.getLtv() / 100))
							* (Letter_config.getMi() / 100)) / 12;

					if (Letter_config.getLoanType().equals("FHA")) {
						loanAmount = Letter_config.getPrice() * ((Letter_config.getLtv() / 100) + 0.0175);
					}

					if (Letter_config.getLoanType().equals("Conventional")) {
						loanAmount = Letter_config.getPrice() * ((Letter_config.getLtv() / 100));
					}

					Letter_config.setLoanAmount(Math.ceil(loanAmount));

					double primaryPay = Letter_configServices.calculateMonthlyPayment(Letter_config.getLoanAmount(),
							Letter_config.getLoanTerm(), Letter_config.getInterest());
					// double insurance =
					// Letter_configServices.insurance(LetterOperationViewModel.getLetter().getPrice());
					double maxPay = Letter_configServices.maxPaid(primaryPay, Letter_config.getTaxes(),
							Letter_config.getInsurance(), Letter_config.getHoa(), MI);
					maxPay = Math.ceil(maxPay);

					if (maxPay <= Letter_config.getMaxPay()) {
						Letter_configServices.UpdateLetter_config(Letter_config);

						if (temp instanceof Loan_officer) {
							Long idCompany = ((Loan_officer) temp).getCompany().getId();
							List<Letter_config> letterListSize = Letter_configServices
									.findActiveLetterListByLoan(temp.getUserId());
							List<ShowLetterViewModel> letterList = Letter_configServices
									.findActiveLetterListByLoanNew(temp.getUserId());

							model.addAttribute("name",
									((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
							model.addAttribute("type", "LOAN OFFICER");
							model.addAttribute("companyname", idCompany);
							model.addAttribute("cantletter", letterListSize.size());
							model.addAttribute("letterList", letterList);
						}
						if (temp instanceof Realtor) {
							// Long idCompany = ((Realtor) temp).g .getId();
							List<ShowLetterViewModel> letterList = Letter_configServices
									.findActiveLetterListByRealtorNew(temp.getUserId());
							model.addAttribute("name",
									((Realtor) temp).getName() + "  " + ((Realtor) temp).getLast_name());
							model.addAttribute("type", "REALTOR");
							// model.addAttribute("companyname", idCompany);
							model.addAttribute("cantletter", letterList.size());
							model.addAttribute("letterList", letterList);
						}

						model.addAttribute("successMessage", "The letter was successfully modified");

					} else {
						model.addAttribute("errorMessage",
							"An error has occurred, please review all the data involved in the calculation of the MaxPay");
                        
						/// Re- Mapeando //////////////////////////////////////
						Client client = new Client();
						List<String> clientList = new ArrayList<String>();
						User_Role user_role = User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
						model.addAttribute("user", user_role.getAppRole().getRoleName());

						/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION

						List<Loan_Client_Operation> lcoList = Letter_config.getOperation().getLoan_Client_Operation();

						for (int y = 0; y < lcoList.size(); y++) {
							client = lcoList.get(y).getLoan_Client().getClient();
							clientList.add(client.getName() + " " + client.getLast_name());
						}
						// model.addAttribute("Letter_config", lovm);
						model.addAttribute("clientsName", clientList);
						model.addAttribute("Letter_config", Letter_config);

						return "/Letter_config/Letter_configEdit2";
                        ////////////////////////////////////////////////////////
					}
				}

			}
		
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configlist";
	}

	/*
	 * @RequestMapping(value = "/Letter_configEdit", method = RequestMethod.POST)
	 * public String Letter_configEdit(Model model, ShowLetterViewModel
	 * Letter_config, Errors errors) { if (errors.hasErrors()) { return
	 * "/Letter_config/Letter_configEdit"; } try { AppUser temp = ((UserLogIn)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal())
	 * .getAppUser(); if ((temp instanceof Loan_officer) || (temp instanceof
	 * Realtor)) { // solo temporal pq esto va a la tabla tipo de préstamo double
	 * loanAmount = 0; double MI = (Letter_config.getLetter().getPrice() *
	 * ((Letter_config.getLetter().getLtv() / 100))
	 * (Letter_config.getLetter().getMi() / 100)) / 12;
	 * 
	 * if (Letter_config.getLetter().getLoanType().equals("FHA")) { loanAmount =
	 * Letter_config.getLetter().getPrice() ((Letter_config.getLetter().getLtv() /
	 * 100) + 0.0175); }
	 * 
	 * if (Letter_config.getLetter().getLoanType().equals("Conventional")) {
	 * loanAmount = Letter_config.getLetter().getPrice() *
	 * ((Letter_config.getLetter().getLtv() / 100)); }
	 * 
	 * Letter_config.getLetter().setLoanAmount(Math.ceil(loanAmount));
	 * 
	 * double primaryPay = Letter_configServices.calculateMonthlyPayment(
	 * Letter_config.getLetter().getLoanAmount(),
	 * Letter_config.getLetter().getLoanTerm(),
	 * Letter_config.getLetter().getInterest()); // double insurance = //
	 * Letter_configServices.insurance(LetterOperationViewModel.getLetter().getPrice
	 * ()); double maxPay = Letter_configServices.maxPaid(primaryPay,
	 * Letter_config.getLetter().getTaxes(),
	 * Letter_config.getLetter().getInsurance(), Letter_config.getLetter().getHoa(),
	 * MI); maxPay = Math.ceil(maxPay); if (maxPay <=
	 * Letter_config.getLetter().getMaxPay()) {
	 * Letter_configServices.UpdateLetter_config(Letter_config.getLetter());
	 * 
	 * if (temp instanceof Loan_officer) { Long idCompany = ((Loan_officer)
	 * temp).getCompany().getId(); List<Letter_config> letterListSize =
	 * Letter_configServices .findActiveLetterListByLoan(temp.getUserId());
	 * List<ShowLetterViewModel> letterList = Letter_configServices
	 * .findActiveLetterListByLoanNew(temp.getUserId());
	 * 
	 * model.addAttribute("name", ((Loan_officer) temp).getName() + "  " +
	 * ((Loan_officer) temp).getLast_name()); model.addAttribute("type",
	 * "LOAN OFFICER"); model.addAttribute("companyname", idCompany);
	 * model.addAttribute("cantletter", letterListSize.size());
	 * model.addAttribute("letterList", letterList); } if (temp instanceof Realtor)
	 * { // Long idCompany = ((Realtor) temp).g .getId(); List<ShowLetterViewModel>
	 * letterList = Letter_configServices
	 * .findActiveLetterListByRealtorNew(temp.getUserId());
	 * model.addAttribute("name", ((Realtor) temp).getName() + "  " + ((Realtor)
	 * temp).getLast_name()); model.addAttribute("type", "REALTOR"); //
	 * model.addAttribute("companyname", idCompany);
	 * model.addAttribute("cantletter", letterList.size());
	 * model.addAttribute("letterList", letterList); }
	 * 
	 * } else { model.addAttribute("errorMessage",
	 * "An error has occurred, please contact the system administrator");
	 * 
	 * } } } catch (Exception e) { model.addAttribute("errorMessage",
	 * "An error has occurred, please contact the system administrator");
	 * e.printStackTrace(); } return "/Letter_config/Letter_configlist"; }
	 */

	@RequestMapping(value = "/Letter_configDelete{id}", method = RequestMethod.GET)
	public String Letter_configDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			if (temp instanceof Loan_officer) {
				// Letter_configServices.DeleteLetter_config(id);
				Letter_config Letter_config = Letter_configServices.getLetter_config(id).get();
				Letter_config.setDeleted(true);
				Letter_config.setActive(false);

				// Letter_configServices.UpdateLetter_config(Letter_config);
				List<Letter_config> List = Letter_configServices
						.findActiveLetterListByOperation(Letter_config.getOperation().getId());

				if (List.size() == 1) { /// comprobando que sea la unica carta para esa operation
					Letter_configServices.DeleteLetter_config(id);
					OperationServices.DeleteOperation(Letter_config.getOperation().getId());
					// todo lo otro se elimina por Cascada
				} else {
					Letter_configServices.DeleteLetter_config(id);
				}
				if (temp instanceof Loan_officer) {
					Long idCompany = ((Loan_officer) temp).getCompany().getId();
					List<Letter_config> letterListSize = Letter_configServices
							.findActiveLetterListByLoan(temp.getUserId());
					List<ShowLetterViewModel> letterList = Letter_configServices
							.findActiveLetterListByLoanNew(temp.getUserId());

					model.addAttribute("name",
							((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
					model.addAttribute("type", "LOAN OFFICER");
					model.addAttribute("companyname", idCompany);
					model.addAttribute("cantletter", letterListSize.size());
					model.addAttribute("letterList", letterList);
				}
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configlist";
	}

	@RequestMapping(value = "/CreateNewLetter")
	public String Create_Letter(Model model) {

		model.addAttribute("title", "Create Letter");

		//
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();

		if (temp instanceof Loan_officer) {
			try {
				// id compañía
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				// client del loan
				List<Client> clientListLoan = ClientServices.findClientListByLoan(temp.getUserId());

				// loan de la compannia
				List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);

				LetterOperationViewModel temporal = new LetterOperationViewModel();
				temporal.setClientList(clientListLoan);
				temporal.setRealtorList(realtorList);
				Letter_config letter = new Letter_config();
				letter.setHoa(0.0);
				temporal.setLetter(letter);
				// seteo a la pagina
				model.addAttribute("viewModel", temporal);

			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
		}
		return "/Letter_config/CreateNewLetter";
	}

	// @Transactional
	@RequestMapping(value = "/CreateNewLetter", method = RequestMethod.POST)
	public String CreateNewLetter(Model model, LetterOperationViewModel LetterOperationViewModel) {
		// validar view model
		if (LetterOperationViewModel.getLetter().getLocation() == "") {
			model.addAttribute("viewModel", LetterOperationViewModel);
			model.addAttribute("locationError", "The location is required");
			return "/Letter_config/CreateNewLetter";
		}
		Boolean success = null;
		try {
			// generating integer
			String generatedString = generateString();

			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			String fha = "FHA";
			Operation operation1;

			java.util.List<Long> cList = TransformStringToLong(LetterOperationViewModel.getClientsids());
			Long op_id = null;
			double MI = 0;
			if (temp instanceof Loan_officer) {
				// solo temporal pq esto va a la tabla tipo de préstamo
				double loanAmount = 0;

				if (LetterOperationViewModel.getLetter().getLoanType().equals(fha)) {
					loanAmount = LetterOperationViewModel.getLetter().getPrice()
							* ((LetterOperationViewModel.getLetter().getLtv() / 100) + 0.0175);
					MI = ((LetterOperationViewModel.getLetter().getPrice()
							* ((LetterOperationViewModel.getLetter().getLtv() / 100)))
							* (LetterOperationViewModel.getLetter().getMi() / 100)) / 12;
				}

				if (LetterOperationViewModel.getLetter().getLoanType().equals("Conventional")) {
					loanAmount = LetterOperationViewModel.getLetter().getPrice()
							* ((LetterOperationViewModel.getLetter().getLtv() / 100));
				}

				LetterOperationViewModel.getLetter().setLoanAmount(Math.ceil(loanAmount));

				double primaryPay = Letter_configServices.calculateMonthlyPayment(
						LetterOperationViewModel.getLetter().getLoanAmount(),
						LetterOperationViewModel.getLetter().getLoanTerm(),
						LetterOperationViewModel.getLetter().getInterest());
				// double insurance =
				// Letter_configServices.insurance(LetterOperationViewModel.getLetter().getPrice());
				double maxPay = Letter_configServices.maxPaid(primaryPay,
						LetterOperationViewModel.getLetter().getTaxes(),
						LetterOperationViewModel.getLetter().getInsurance(),
						LetterOperationViewModel.getLetter().getHoa(), MI);
				maxPay = Math.ceil(maxPay);

				if (maxPay <= LetterOperationViewModel.getLetter().getMaxPay()) {
					/// partiendo de que el Loan_Client existe pq cuando inserto un cliente inserto
					/// el Loan_Client también
					// for(int i = 0; i < LetterOperationViewModel.getClientIdList().size(); i++) {
					// Long Loan_Client =
					// Loan_ClientServices.getLoan_ClientByClientAndLoan(temp.getUserId(),
					// LetterOperationViewModel.getClientIdList().get(i) ).getId();
					// lcList.add(Loan_Client);
					// }

					Company_Realtor Company_Realtor = Company_RealtorServices.getCompanyByCompanyAndRealtor(
							((Loan_officer) temp).getCompany().getId(), LetterOperationViewModel.getIdRealtor());

					long fix = 1; // esto debe salir de un combo en el view pero será para después
					Letter_FixData fixData = Letter_fixDataServices.getLetter_FixData(fix).get();

					/// Comprobando que la operación exista
					List<Loan_Client> lcList = new ArrayList<Loan_Client>();
					lcList = Loan_ClientServices.getLoan_ClientByClienList(((Loan_officer) temp).getUserId(), cList);

					List<Long> loan_clientIdList = new ArrayList<Long>();
					for (int i = 0; i < lcList.size(); i++) {
						loan_clientIdList.add(lcList.get(i).getId()); /// Llenando lista de IDs de Loan_Cliente
					}

					if (cList.size() > 1) {
						op_id = Loan_Client_OperationServices.getcomunOperationId(loan_clientIdList,
								Company_Realtor.getId(), cList.size());
					} else {
						op_id = Loan_Client_OperationServices.getlcOperationByList(loan_clientIdList,
								Company_Realtor.getId());
					}

					if (op_id != null)   
					{
						/// creación de la carta
						operation1 = OperationServices.getOperation(op_id).get();
						List<Letter_config> listaLetters = operation1.getLetter_config_List();
						int i = 0;
						boolean breaking = false;
						Letter_config temporalletter = LetterOperationViewModel.getLetter();
						while (i < listaLetters.size() && !breaking) // poniendo como no activa la anterior activa.
						{
							Letter_config temporalActive = listaLetters.get(i);
							if ((temporalActive.isActive())
									&& (temporalActive.getLoanType().equals(temporalletter.getLoanType()))) {
								temporalActive.setActive(false);
								breaking = true;
							}
							i++;
						}

						temporalletter.setOperation(operation1);
						temporalletter.setActive(true);
						temporalletter.setLetter_fixdata(fixData);
						// temporalletter.setClients(FullName);
						temporalletter.setUniqueKey(generatedString);
						Letter_configServices.AddLetter_config(temporalletter);
					} else // si el company_realtor_operation no existe entonces creo operation nueva
							// también
					{
						Operation operation2 = new Operation();
						operation2.setName(LetterOperationViewModel.getLetter().getUniqueKey());
						OperationServices.AddOperation(operation2);

						/// creando loan_client_operation
						for (int i = 0; i < lcList.size(); i++) {
							Loan_Client_Operation lco = new Loan_Client_Operation();
							lco.setOperation(operation2);
							lco.setLoan_Client(Loan_ClientServices.getLoan_Client(loan_clientIdList.get(i)).get());
							Loan_Client_OperationServices.AddLoan_Client_Operation(lco);
						}

						/// seteo y agrego de Company_Realtor_Operation
						Company_Realtor_Operation cro = new Company_Realtor_Operation();
						cro.setCompany_Realtor(Company_Realtor);
						cro.setOperation(operation2);
						Company_Realtor_OperationServices.AddCompany_Realtor_Operation(cro);

						/// creacion de la carta
						Letter_config temporalletter = LetterOperationViewModel.getLetter();
						temporalletter.setOperation(operation2);
						temporalletter.setActive(true);
						temporalletter.setLetter_fixdata(fixData);
						// temporalletter.setClients(FullName);
						temporalletter.setUniqueKey(generatedString);
						Letter_configServices.AddLetter_config(temporalletter); 
					}

					model.addAttribute("successMessage", "The letter was successfully inserted.");
					success = true;
					
					for(int i = 0; i < lcList.size(); i++)
					{						
					EmailNewLetter.enviar(lcList.get(i).getClient().getEmail(), "New generated Pre-Approval letter", generatedString);
					}
					
				} else {
					model.addAttribute("errorMessage", "An error has occurred, Max Payment to high");
					success = false;
				}

			} 

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		//////////////// Re-Mapeo////////////////////////////
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		if (temp instanceof Loan_officer) {
			try {
				// id compañía
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				// client del loan
				List<Client> clientListLoan = ClientServices.findClientListByLoan(temp.getUserId());

				// loan de la compania
				List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);

				/// asignandole a temporal el modelo que me vino del view
				LetterOperationViewModel temporal = LetterOperationViewModel;

				if (success) {
					temporal = new LetterOperationViewModel();
					temporal.setClientList(clientListLoan);
					temporal.setRealtorList(realtorList);
					Letter_config letter = new Letter_config();
					temporal.setLetter(letter);
				} else {
					temporal.getLetter().setId((long) 1);
					temporal.setClientList(clientListLoan);
					temporal.setRealtorList(realtorList);
					temporal.setIdRealtor(null);
					temporal.setClientsids(null);
				}
				// seteo a la pagina
				model.addAttribute("viewModel", temporal);

			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////

		return "/Letter_config/CreateNewLetter";
	}

	///////// Calculator
	///////// CARTA///////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = { "/Letter_configCalculator{id}" }, method = RequestMethod.GET)
	public String Letter_configClaculatorForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			Client client = new Client();
			List<String> clientList = new ArrayList<String>();
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			User_Role user_role = User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
			model.addAttribute("user", user_role.getAppRole().getRoleName());
			// model.addAttribute("Letter_config",
			// Letter_configServices.getLetter_config(id));

			/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION
			ShowLetterViewModel lovm = new ShowLetterViewModel();
			lovm.setLetter(Letter_configServices.getLetter_config(id).get());
			List<Loan_Client_Operation> lcoList = lovm.getLetter().getOperation().getLoan_Client_Operation();

			for (int y = 0; y < lcoList.size(); y++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clientList.add(client.getName() + " " + client.getLast_name());
			}
			lovm.setClientsName(clientList);
			model.addAttribute("Letter_config", lovm);
			model.addAttribute("result", "");
			/// --------------------------------------------------------------------------------------------------

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configCalculator";
	}

	@RequestMapping(value = "/Letter_configCalculator", method = RequestMethod.POST)
	public String Letter_configEditCalculator(Model model, ShowLetterViewModel Letter_config) {
		try {
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			if ((temp instanceof Loan_officer) || (temp instanceof Realtor)) {
				// solo temporal pq esto va a la tabla tipo de préstamo
				double loanAmount = 0;
				double MI = (Letter_config.getLetter().getPrice() * ((Letter_config.getLetter().getLtv() / 100))
						* (Letter_config.getLetter().getMi() / 100)) / 12;

				if (Letter_config.getLetter().getLoanType().equals("FHA")) {
					loanAmount = Letter_config.getLetter().getPrice()
							* ((Letter_config.getLetter().getLtv() / 100) + 0.0175);
				}

				if (Letter_config.getLetter().getLoanType().equals("Conventional")) {
					loanAmount = Letter_config.getLetter().getPrice() * ((Letter_config.getLetter().getLtv() / 100));
				}

				Letter_config.getLetter().setLoanAmount(Math.ceil(loanAmount));

				double primaryPay = Letter_configServices.calculateMonthlyPayment(
						Letter_config.getLetter().getLoanAmount(), Letter_config.getLetter().getLoanTerm(),
						Letter_config.getLetter().getInterest());
				// double insurance =
				// Letter_configServices.insurance(LetterOperationViewModel.getLetter().getPrice());
				double maxPay = Letter_configServices.maxPaid(primaryPay, Letter_config.getLetter().getTaxes(),
						Letter_config.getLetter().getInsurance(), Letter_config.getLetter().getHoa(), MI);
				maxPay = Math.ceil(maxPay);

				/////////// RE-MAPEO
				/////////// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				try {
					Client client = new Client();
					List<String> clientList = new ArrayList<String>();
					User_Role user_role = User_RoleServices.getUser_RoleByUser_Id(temp.getUserId());
					model.addAttribute("user", user_role.getAppRole().getRoleName());
					// model.addAttribute("Letter_config",
					// Letter_configServices.getLetter_config(id));

					/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION
					ShowLetterViewModel lovm = new ShowLetterViewModel();
					lovm.setLetter(Letter_config.getLetter());
					List<Loan_Client_Operation> lcoList = lovm.getLetter().getOperation().getLoan_Client_Operation();

					for (int y = 0; y < lcoList.size(); y++) {
						client = lcoList.get(y).getLoan_Client().getClient();
						clientList.add(client.getName() + " " + client.getLast_name());
					}
					lovm.setClientsName(clientList);
					model.addAttribute("Letter_config", lovm);
					if (maxPay <= Letter_config.getLetter().getMaxPay()) {
						// Letter_configServices.UpdateLetter_config(Letter_config.getLetter());
						model.addAttribute("result", "Aproved");

					} else {
						model.addAttribute("result", "Not Aproved");
					}
					model.addAttribute("maxPay", maxPay);
					/// --------------------------------------------------------------------------------------------------

				} catch (Exception e) {
					model.addAttribute("errorMessage",
							"An error has occurred, please contact the system administrator");
					e.printStackTrace();
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Letter_config/Letter_configCalculator";
	}

	public ArrayList<Long> TransformStringToLong(String array) {
		ArrayList<Long> result = new ArrayList<>();
		String[] parts = array.split(",");
		for (int i = 0; i < parts.length; i++) {
			result.add(Long.valueOf(parts[i]));
		}
		return result;
	}

	public static String generateString() {
		char[] elementos = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		char[] conjunto = new char[8];

		for (int i = 0; i < 8; i++) {
			int el = (int) (Math.random() * 37);
			conjunto[i] = (char) elementos[el];
		}
		String pass = new String(conjunto);
		return pass;
	}

}
