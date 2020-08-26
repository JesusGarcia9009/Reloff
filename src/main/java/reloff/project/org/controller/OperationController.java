package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reloff.project.org.entity.Operation;
import reloff.project.org.services.OperationServices;

@Controller
public class OperationController {

	@Autowired
	private OperationServices OperationServices;

	@RequestMapping(value = "/OperationList")
	public String OperationList(Model model) {
		try {
			model.addAttribute("OperationList", OperationServices.LoadAllOperation());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Operation/Operationlist";
	}

	@RequestMapping(value = { "/OperationEdit", "/OperationEdit/{id}" }, method = RequestMethod.GET)
	public String OperationEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("Operation", OperationServices.getOperation(id));
			} else {
				model.addAttribute("Operation", new Operation());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Operation/OperationEdit";
	}

	@RequestMapping(value = "/OperationEdit", method = RequestMethod.POST)
	public String OperationEdit(Model model, Operation Operation) {
		try {
			OperationServices.UpdateOperation(Operation);
			model.addAttribute("OperationList", OperationServices.LoadAllOperation());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Operation/Operationlist";
	}

	@RequestMapping(value = "/OperationDelete/{id}", method = RequestMethod.GET)
	public String OperationDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			OperationServices.DeleteOperation(id);
			model.addAttribute("OperationList", OperationServices.LoadAllOperation());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Operation/Operationlist";
	}

}
