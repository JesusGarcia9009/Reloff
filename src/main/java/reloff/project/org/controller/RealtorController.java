package reloff.project.org.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.AppRole;
import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Company_Realtor;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Realtor;
import reloff.project.org.entity.User_Role;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.services.BrokerCompanyServices;
import reloff.project.org.services.Company_RealtorServices;
import reloff.project.org.services.RealtorServices;
import reloff.project.org.services.RoleServices;
import reloff.project.org.services.User_RoleServices;
import reloff.project.org.utils.EmailNewRealtor;
import reloff.project.org.utils.EncrytedPasswordUtils;



@Controller
public class RealtorController {

	@Autowired
	private RealtorServices RealtorServices;
	
	@Autowired
	private Company_RealtorServices Company_RealtorServices;

	@Autowired
	private BrokerCompanyServices BrokerCompanyServices;
	
	@Autowired
	private RoleServices RoleServices;
	
	@Autowired
	private User_RoleServices User_RoleServices;
	
	//@Autowired
	//private Company_Realtor_OperationServices Company_Realtor_OperationServices;
	
	@Autowired
	private EmailNewRealtor EmailNewRealtor;
	

	@RequestMapping(value = "/realtorList")
	public String RealtorList(Model model) {

		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		try {
			if (temp instanceof Loan_officer) {
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				List<Realtor> realtorList;

				realtorList = RealtorServices.findRealtorListByCompany(idCompany);

				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("realtorList", realtorList);

			} else {
				model.addAttribute("realtorList", RealtorServices.LoadAllRealtor());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}

		return "/Realtor/realtorlist";
	}

	@RequestMapping(value = { "/realtorEdit", "/realtorEdit{id}" }, method = RequestMethod.GET)
	public String RealtorEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("realtor", RealtorServices.getRealtor(id));
				model.addAttribute("realtor_bc", RealtorServices.getRealtor(id).get().getBroker_company());
			} else {
				model.addAttribute("realtor", new Realtor());
			}
			model.addAttribute("bcList", BrokerCompanyServices.LoadAllBrokerCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor/realtorEdit";
	}

	@RequestMapping(value = "/realtorEdit", method = RequestMethod.POST)
	public String RealtorEdit(@Valid Realtor Realtor, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "/Realtor/realtorEdit";
		}
		try {
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			if (temp instanceof Loan_officer) { // AquÃ­ dejÃ© esto pq el realtor lo inserta el Loan y lo relaciona con la
												// CompaÃ±Ã­a de ese Loan
				AppRole role = RoleServices.getRoleByName("REALTOR");
				
				if (Realtor.getUserId() == null) {
					Realtor.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword("123"));
					RealtorServices.AddRealtor(Realtor);
					
					Company_Realtor cr = new Company_Realtor();
					cr.setRealtor(Realtor);
					cr.setCompany(((Loan_officer) temp).getCompany());
					Company_RealtorServices.AddCompany_Realtor(cr);					
					
					User_Role ur = new User_Role();
					ur.setAppRole(role);
					ur.setAppUser(Realtor);
					User_RoleServices.AddUser_Role(ur);
					
					EmailNewRealtor.enviar(Realtor.getEmail(), "Your new realtor account", Realtor.getUserName());
					
					model.addAttribute("successMessage", "The realtor has been successfully created");

				} else {
					RealtorServices.UpdateRealtor(Realtor);
					//EmailNewRealtor.enviar(Realtor.getEmail(), "Your new realtor account", Realtor.getUserName());
				}

				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				// List<Realtor> realtorList =
				// RealtorServices.findRealtorListByLoan(temp.getUserId()); // Hay que revisar
				// esto pq esto lo haria si condiciono al Loan ademas de a la compaÃ±ia, esto
				// pasa si tengo mas de un Loan enuna compaÃ±ia
				List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);
				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("realtorList", realtorList);
			} else {
				model.addAttribute("realtorList", RealtorServices.LoadAllRealtor());
			}

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor/realtorlist";
	}

	@RequestMapping(value = "/realtorDelete{id}", method = RequestMethod.GET) // falta el eliminar
	public String RealtorDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			Long idCompany = ((Loan_officer) temp).getCompany().getId();

		    // Primero se elimina la carta para poder eliminar el realtor. El Company_Realtor se elimina por cascada.
			
			RealtorServices.DeleteRealtor(id);
			
			if (temp instanceof Loan_officer) {
				List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);

				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("realtorList", realtorList);
				
				model.addAttribute("successMessage", "The client has been successfully deleted");
				
			} else {
				model.addAttribute("realtorList", RealtorServices.LoadAllRealtor());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
			
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			Long idCompany = ((Loan_officer) temp).getCompany().getId();
			if (temp instanceof Loan_officer) {
				List<Realtor> realtorList;
				try {
					realtorList = RealtorServices.findRealtorListByCompany(idCompany);


				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("realtorList", realtorList);
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
			}
		return "/Realtor/realtorlist";
	}

	@RequestMapping(value = "/_menu")
	public String viewSettings(Model model) {
		// do stuff
		model.addAttribute("classActiveSettings", "active");
		return "_menu";
	}

}

