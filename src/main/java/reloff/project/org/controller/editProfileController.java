package reloff.project.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Realtor;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.services.Loan_officerServices;
import reloff.project.org.services.RealtorServices;
import reloff.project.org.utils.EncrytedPasswordUtils;
import reloff.project.org.viewmodel.loanProfile;
import reloff.project.org.viewmodel.realtorProfile;

@Controller
public class editProfileController {

	@Autowired
	private RealtorServices RealtorServices;

	@Autowired
	private Loan_officerServices Loan_officerServices;
	
	@Autowired
	private MainController mainController;

	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String changePassword(Model model) {
		model.addAttribute("message", "changePassword");

		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		try {
			if (temp instanceof Loan_officer) {
				Loan_officer loan = Loan_officerServices.getLoan_officer(temp.getUserId());
				
				loan.setEncrytedPassword("");
				
				loanProfile loanProfile = new loanProfile(loan.getName(), loan.getLast_name(), loan.getEmail(), loan.getMailing_add(), 
						loan.getCellphone(), loan.getNmls(), loan.getUserId(), loan.getUserName(), "", "", "");
				
				model.addAttribute("loan_officer", loanProfile);
				return "changePasswordLoan";
			} else if (temp instanceof Realtor) {
				Realtor realtor = RealtorServices.getRealtor(temp.getUserId()).get();
				
				realtorProfile realtorProfile = new realtorProfile(realtor.getName(), realtor.getLast_name(), realtor.getEmail(), realtor.getMailing_add(), 
						realtor.getCellphone(), realtor.getLicense_number(), realtor.getUserId(), realtor.getUserName(), "", "", "");
				
				
				model.addAttribute("realtor", realtorProfile);
				return "changePasswordRealtor";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "changePasswordRealtor";
	}

	@RequestMapping(value = "/profileEditRealtor", method = RequestMethod.POST)
	public String RealtorEdit(Model model, realtorProfile Realtor) {
		try {
			
			if(haveErrorRealtor(Realtor)) {
				model.addAttribute("errorMessage", "Missing data in the form, please fill in all fields");
				model.addAttribute("realtor", Realtor);
				return "changePasswordRealtor";
			}
			
			Realtor realtor = RealtorServices.getRealtor(Realtor.getUserId()).get();
			
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 //maches primero desemcritado segundo emcriptado
			 boolean iguales = encoder.matches(Realtor.getOldPassword(), realtor.getEncrytedPassword());
			
			if(iguales) {
				if(Realtor.getNewPassword().equals(Realtor.getConfirmNewPassword())) {
					String newpass = EncrytedPasswordUtils.encrytePassword(Realtor.getNewPassword());
					
					realtor.setCellphone(Realtor.getCellphone());
					realtor.setEmail(Realtor.getEmail());
					realtor.setMailing_add(Realtor.getMailing_add());
					realtor.setLast_name(Realtor.getLast_name());
					realtor.setName(Realtor.getName());
					realtor.setLicense_number(Realtor.getLicense_number());
					realtor.setUserName(Realtor.getUserName());
					realtor.setEncrytedPassword(newpass);
					RealtorServices.UpdateRealtor(realtor);
					
					model.addAttribute("successMessage", "The operation completed successfully");
					
					return mainController.welcomePage(model);
				}else {
					model.addAttribute("errorMessage", "Alert, new passwords do not match");
				}
			}else {
				model.addAttribute("errorMessage", "The previous password is not valid");
			}
			// EmailNewRealtor.enviar(Realtor.getEmail(), "Your new realtor account", Realtor.getUserName());

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		model.addAttribute("realtor", Realtor);
		return "changePasswordRealtor";
	}

	@RequestMapping(value = "/profileEditLoan", method = RequestMethod.POST)
	public String Loan_officerEdit(Model model, loanProfile Loan_officer) {
		try {
			
			if(haveErrorLoan(Loan_officer)) {
				model.addAttribute("errorMessage", "Missing data in the form, please fill in all fields");
				model.addAttribute("loan_officer", Loan_officer);
				return "changePasswordLoan";
			}
			
			Loan_officer loan = Loan_officerServices.getLoan_officer(Loan_officer.getUserId());
			
			 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 //maches primero desemcritado segundo emcriptado
			 boolean iguales = encoder.matches(Loan_officer.getOldPassword(), loan.getEncrytedPassword());
			
			if(iguales) {
				if(Loan_officer.getNewPassword().equals(Loan_officer.getConfirmNewPassword())) {
					String newpass = EncrytedPasswordUtils.encrytePassword(Loan_officer.getNewPassword());
					
					loan.setCellphone(Loan_officer.getCellphone());
					loan.setEmail(Loan_officer.getEmail());
					loan.setMailing_add(Loan_officer.getMailing_add());
					loan.setLast_name(Loan_officer.getLast_name());
					loan.setName(Loan_officer.getName());
					loan.setNmls(Loan_officer.getNmls());
					loan.setUserName(Loan_officer.getUserName());
					loan.setEncrytedPassword(newpass);
					Loan_officerServices.UpdateLoan_officer(loan);
					
					model.addAttribute("successMessage", "The operation completed successfully");
					
					return mainController.welcomePage(model);
					
				}else {
					model.addAttribute("errorMessage", "Alert, new passwords do not match");
				}
			}else {
				model.addAttribute("errorMessage", "The previous password is not valid");
			}
			// EmailNewRealtor.enviar(Realtor.getEmail(), "Your new realtor account", Realtor.getUserName());

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		model.addAttribute("loan_officer", Loan_officer);
		return "changePasswordLoan";
	}
	
	
	private boolean haveErrorLoan(loanProfile Loan_officer) {
		boolean result = false;
		if(Loan_officer.getName().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getLast_name().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getCellphone().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getNmls().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getEmail().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getMailing_add().isEmpty()) {
			result = true;
			return result;
		}
		//users
		if(Loan_officer.getUserName().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getOldPassword().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getNewPassword().isEmpty()) {
			result = true;
			return result;
		}
		if(Loan_officer.getConfirmNewPassword().isEmpty()) {
			result = true;
			return result;
		}
		return result;
	}
	
	private boolean haveErrorRealtor(realtorProfile Realtor) {
		boolean result = false;
		if(Realtor.getName().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getLast_name().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getCellphone().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getLicense_number().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getEmail().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getMailing_add().isEmpty()) {
			result = true;
			return result;
		}
		//users
		if(Realtor.getUserName().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getOldPassword().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getNewPassword().isEmpty()) {
			result = true;
			return result;
		}
		if(Realtor.getConfirmNewPassword().isEmpty()) {
			result = true;
			return result;
		}
		return result;
	}

}
