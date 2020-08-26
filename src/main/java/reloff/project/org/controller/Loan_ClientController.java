package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reloff.project.org.entity.Loan_Client;
import reloff.project.org.services.Loan_ClientServices;

@Controller
public class Loan_ClientController {

	@Autowired
	private Loan_ClientServices Loan_ClientServices;

	@RequestMapping(value = "/Realtor_ClientList")
	public String Loan_ClientList(Model model) {
		try {
			model.addAttribute("Loan_ClientList", Loan_ClientServices.LoadAllLoan_Client());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor_Client/Realtor_Clientlist";
	}

	@RequestMapping(value = { "/Realtor_ClientEdit", "/Realtor_ClientEdit/{id}" }, method = RequestMethod.GET)
	public String Realtor_ClientEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("Realtor_Client", Loan_ClientServices.getLoan_Client(id));
			} else {
				model.addAttribute("Realtor_Client", new Loan_Client());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor_Client/Realtor_ClientEdit";
	}

	@RequestMapping(value = "/Realtor_ClientEdit", method = RequestMethod.POST)
	public String Realtor_ClientEdit(Model model, Loan_Client Realtor_Client) {
		try {
			Loan_ClientServices.UpdateLoan_Client(Realtor_Client);
			model.addAttribute("Realtor_ClientList", Loan_ClientServices.LoadAllLoan_Client());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor_Client/Realtor_Clientlist";
	}

	@RequestMapping(value = "/Realtor_ClientDelete/{id}", method = RequestMethod.GET)
	public String Realtor_ClientDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			Loan_ClientServices.DeleteLoan_Client(id);
			model.addAttribute("Realtor_ClientList", Loan_ClientServices.LoadAllLoan_Client());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Realtor_Client/Realtor_Clientlist";
	}

}
