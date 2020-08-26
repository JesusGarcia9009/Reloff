package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reloff.project.org.entity.Company;
import reloff.project.org.services.CompanyServices;

@Controller
public class CompanyController {

	@Autowired
	private CompanyServices CompanyServices;

	@RequestMapping(value = "/companyList")
	public String CompanyList(Model model) {
		try {
			model.addAttribute("companyList", CompanyServices.LoadAllCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Company/companylist";
	}

	@RequestMapping(value = { "/companyEdit", "/companyEdit/{id}" }, method = RequestMethod.GET)
	public String CompanyEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("text", "Edit");
				model.addAttribute("company", CompanyServices.getCompany(id));
			} else {
				model.addAttribute("text", "Create");
				model.addAttribute("company", new Company());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Company/companyEdit";
	}

	@RequestMapping(value = "/companyEdit", method = RequestMethod.POST)
	public String CompanyEdit(Model model, Company Company) {
		try {
			CompanyServices.UpdateCompany(Company);
			model.addAttribute("companyList", CompanyServices.LoadAllCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Company/companylist";
	}

	@RequestMapping(value = "/companyDelete/{id}", method = RequestMethod.GET)
	public String CompanyDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			CompanyServices.DeleteCompany(id);
			model.addAttribute("companyList", CompanyServices.LoadAllCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Company/companylist";
	}

}
