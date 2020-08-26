package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.services.BrokerCompanyServices;
import reloff.project.org.services.RealtorServices;

@Controller
public class PassChangeController {

	@SuppressWarnings("unused")
	@Autowired
	private RealtorServices RealtorServices;
	
	@SuppressWarnings("unused")
	@Autowired
	private BrokerCompanyServices BrokerCompanyServices;
	
	@RequestMapping(value = { "/passChange"}, method = RequestMethod.GET)
	public String passChange(Model model) {
		model.addAttribute("message", "Login");
		return "passChange";
	}
	

	/*@RequestMapping(value = "/passChange", method = RequestMethod.POST)
	public String RealtorEdit(Model model, AppUser appUser) {
		
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		if (temp instanceof Loan_officer) {  // Aquí dejé esto pq el realtor lo inserta el Loan y lo relaciona con la Compañía de ese Loan
			if (Realtor.getUserId() == null) 
			   {
				Realtor.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword("123"));
				RealtorServices.UpdateRealtor(Realtor);
				Company_Realtor tempLR = new Company_Realtor();
				tempLR.setCompany(((Loan_officer)temp).getCompany());				  // Necesito sacar la Company a la que pertenece este Loan
				tempLR.setRealtor(Realtor);
				CompanyRealtorServices.UpdateCompany_Realtor(tempLR);
				
			    } else {
				RealtorServices.UpdateRealtor(Realtor);
			           }

			Long idCompany = ((Loan_officer) temp).getCompany().getId();
			//List<Realtor> realtorList = RealtorServices.findRealtorListByLoan(temp.getUserId());  // Hay que revisar esto pq esto lo haria si condiciono al Loan ademas de a la compañia, esto pasa si tengo mas de un Loan enuna compañia
			List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);
			model.addAttribute("name", ((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
			model.addAttribute("type", "LOAN OFFICER");
			model.addAttribute("companyname", idCompany);
			model.addAttribute("cantRealter", realtorList.size());
			model.addAttribute("realtorList", realtorList);			
		} else {
			model.addAttribute("realtorList", RealtorServices.LoadAllRealtor());
		}
		return "/Realtor/realtorlist";
	}*/



}
