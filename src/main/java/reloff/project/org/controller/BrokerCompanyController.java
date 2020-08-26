package reloff.project.org.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reloff.project.org.entity.Broker_Company;
import reloff.project.org.services.BrokerCompanyServices;

@Controller
public class BrokerCompanyController {

	@Autowired
	private BrokerCompanyServices BrokerCompanyServices;

	@RequestMapping(value = "/brokercompanyList")
	public String BrokerCompanyList(Model model) {
		try {
			model.addAttribute("brokercompanyList", BrokerCompanyServices.LoadAllBrokerCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Broker/brokercompanylist";
	}

	@RequestMapping(value = { "/brokercompanyEdit", "/brokercompanyEdit{id}" }, method = RequestMethod.GET)
	public String CompanyEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("text", "Edit");
				model.addAttribute("brokercompany", BrokerCompanyServices.getBrokerCompany(id));
			} else {
				model.addAttribute("text", "Create");
				model.addAttribute("brokercompany", new Broker_Company());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Broker/brokercompanyEdit";
	}

	@RequestMapping(value = "/brokercompanyEdit", method = RequestMethod.POST)
	public String BrokerCompanyEdit(@Valid Broker_Company Broker_Company, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("brokercompany", Broker_Company);
			return "/Broker/brokercompanyEdit";
		}
		try {
			if (Broker_Company.getId() == null) {
				BrokerCompanyServices.AddBrokerCompany(Broker_Company);
				model.addAttribute("successMessage", "The Broker-Company has been successfully created");
			} else {
				BrokerCompanyServices.UpdateBrokerCompany(Broker_Company);
				model.addAttribute("successMessage", "The Broker-Company has been successfully updated");
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		try {
			model.addAttribute("brokercompanyList", BrokerCompanyServices.LoadAllBrokerCompany());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Broker/brokercompanylist";
	}

	@RequestMapping(value = "/brokercompanyDelete{id}", method = RequestMethod.GET)
	public String CompanyDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			BrokerCompanyServices.DeleteBrokerCompany(id);
			model.addAttribute("brokercompanyList", BrokerCompanyServices.LoadAllBrokerCompany());
			
			model.addAttribute("successMessage", "The Broker-Company has been successfully deleted");

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}

		return "/Broker/brokercompanylist";
	}

}
