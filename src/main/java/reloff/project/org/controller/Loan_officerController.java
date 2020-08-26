package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.Company;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.services.CompanyServices;
import reloff.project.org.services.Loan_officerServices;

@Controller
public class Loan_officerController {

	@Autowired
	private Loan_officerServices Loan_officerServices;

	@Autowired
	private CompanyServices CompanyServices;

	private static Company company;

	@RequestMapping(value = "/Loan_officerList/{id}", method = RequestMethod.GET)
	public String Loan_officerList(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				Company c = (CompanyServices.getCompany(id)).get();
				company = c;
				model.addAttribute("text", c.getName());
				model.addAttribute("loan_officerList", Loan_officerServices.LoadAllLoan_officerByCompany(id));
			} else {
				model.addAttribute("loan_officerList", Loan_officerServices.LoadAllLoan_officer());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Loan_officer/Loan_officerlist";
	}

	@RequestMapping(value = { "/Loan_officerEdit", "/Loan_officerEdit/{id}" }, method = RequestMethod.GET)
	public String Loan_officerEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("loan_officer", Loan_officerServices.getLoan_officer(id));
			} else {
				model.addAttribute("loan_officer", new Loan_officer());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Loan_officer/Loan_officerEdit";
	}

	@RequestMapping(value = "/Loan_officerEdit", method = RequestMethod.POST)
	public String Loan_officerEdit(Model model, Loan_officer Loan_officer) {
		try {
			if (Loan_officer.getCompany() == null)
				Loan_officer.setCompany(company);
			Loan_officerServices.UpdateLoan_officer(Loan_officer);
			model.addAttribute("Loan_officerList", Loan_officerServices.LoadAllLoan_officer());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Loan_officer/Loan_officerlist";
	}

	@RequestMapping(value = "/Loan_officerDelete/{id}", method = RequestMethod.GET)
	public String Loan_officerDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			Loan_officerServices.DeleteLoan_officer(id);
			model.addAttribute("Loan_officerList", Loan_officerServices.LoadAllLoan_officer());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Loan_officer/Loan_officerlist";
	}

}
