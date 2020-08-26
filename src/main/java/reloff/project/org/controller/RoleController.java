package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.AppRole;
import reloff.project.org.services.RoleServices;

@Controller
public class RoleController {

	@Autowired
	private RoleServices roleServices;

	@RequestMapping(value = "/roleList")
	public String RoleList(Model model) {
		try {
			model.addAttribute("roleList", roleServices.LoadAllRole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Role/rolelist";
	}

	@RequestMapping(value = { "/roleEdit", "/roleEdit/{id}" }, method = RequestMethod.GET)
	public String roleEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		try {
			if (null != id) {
				model.addAttribute("role", roleServices.getRole(id));
			} else {
				model.addAttribute("role", new AppRole());
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Role/roleEdit";
	}

	@RequestMapping(value = "/roleEdit", method = RequestMethod.POST)
	public String roleEdit(Model model, AppRole role) {
		try {
			roleServices.SaveAndUpdateRole(role);
			model.addAttribute("roleList", roleServices.LoadAllRole());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Role/rolelist";
	}

	@RequestMapping(value = "/roleDelete/{id}", method = RequestMethod.GET)
	public String roleDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			roleServices.DeleteRole(id);
			model.addAttribute("roleList", roleServices.LoadAllRole());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/Role/rolelist";
	}

}
