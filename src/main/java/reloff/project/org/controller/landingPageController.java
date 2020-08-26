package reloff.project.org.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import reloff.project.org.services.RecaptchaService;
import reloff.project.org.utils.EmailRequestUser;
import reloff.project.org.viewmodel.landingViewModel;


@Controller
public class landingPageController {
	
	@Autowired
	private EmailRequestUser EmailRequestUser;
	@Autowired
	RecaptchaService captchaService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String landingGet(Model model) {
		try {
				model.addAttribute("request", new landingViewModel());
				model.addAttribute("body", "");
		
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/landingPage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String RealtorEdit(@Valid landingViewModel landingViewModel, Errors errors, Model model,
			@RequestParam(name = "g-recaptcha-response") String captchaResponse, HttpServletRequest request) {

		try {
			String ip = request.getRemoteAddr();
			String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, captchaResponse);

			if (!captchaVerifyMessage.isEmpty()) {
				Map<String, Object> response = new HashMap<>();
				response.put("message", captchaVerifyMessage);
				//model.addAttribute("errorMessage", captchaVerifyMessage);
				model.addAttribute("errorMessage", "You missed the captcha");
				return landingGet(model);
			}
			else {
				
	String type;
	
			
			if(landingViewModel.getType() == 1) {
				type = "Realtor User";
			}
			else {
				type = "Loan User";
			}
				
			String body = "<p>First Name: "+ landingViewModel.getfName() +" </p>\r\n" + "<p>Last Name: "+ landingViewModel.getlName() + " </p>\r\n" + "<p>Company: "+ landingViewModel.getCompany() + " </p>\r\n"
					 + "<p>License No: "+ landingViewModel.getNmls() +" </p>\r\n" + "<p>Email: "+ landingViewModel.getEmail() +" </p>\r\n" + "<p>Address: "+ landingViewModel.getMailAdd() + " </p>\r\n" + "<p>Phone No: "+ landingViewModel.getPhoneNo() + " </p>\r\n"
					 + "<p>User type: "+ type + " </p>\r\n";

					EmailRequestUser.enviar(landingViewModel.getEmail() , "Your request has been send", body);
					
		         	model.addAttribute("body", body);
		         	model.addAttribute("request", new landingViewModel());
					model.addAttribute("successMessage", "Your request has been successfully send");
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		return "/landingPage";
	}

}

