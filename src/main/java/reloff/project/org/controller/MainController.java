package reloff.project.org.controller;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Client;
import reloff.project.org.entity.Letter_config;
import reloff.project.org.entity.Loan_Client_Operation;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Realtor;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.services.AppUserServices;
import reloff.project.org.services.ClientServices;
import reloff.project.org.services.Letter_configServices;
import reloff.project.org.services.RealtorServices;
import reloff.project.org.utils.EmailResetPass;
import reloff.project.org.utils.EncrytedPasswordUtils;
import reloff.project.org.utils.GeneratePdfReport;
import reloff.project.org.utils.WebUtils;
import reloff.project.org.viewmodel.ShowLetterViewModel;
import reloff.project.org.viewmodel.letteverify;
import reloff.project.org.viewmodel.userProfile;

@Controller
public class MainController {

	@Autowired
	private RealtorServices RealtorServices;
	@Autowired
	private ClientServices ClientServices;
	@Autowired
	private Letter_configServices Letter_configServices;
	@Autowired
	private AppUserServices appUserServices;	
	@Autowired
	ServletContext context;
	@Autowired
	private EmailResetPass EmailResetPass;

//	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
//	public String landing(Model model) {
//		//model.addAttribute("message", "Login");
//		return "loginPage";
//	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("message", "Login");
		return "loginPage";
	}
	
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean iguales = encoder.matches("123", temp.getEncrytedPassword());
		if(iguales) {
			userProfile user = new userProfile();
			user.setUserId(temp.getUserId());
			user.setUserName(temp.getUserName());
			
			model.addAttribute("user", user);
			return "changePasswordUser";
		}
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Reloff Dashboard");

		if (temp instanceof Loan_officer) {
			try {
				
				
				Long idCompany = ((Loan_officer) temp).getCompany().getId(); // sacando la compañía del Loan logueado
				List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);
				List<Client> clientList = ClientServices.findClientListByLoan(((Loan_officer) temp).getUserId());

				model.addAttribute("name",((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("cantClient", clientList.size());
				model.addAttribute("realtorList", realtorList);
				model.addAttribute("clientList", clientList);

			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
		}
		
		if (temp instanceof Realtor) {
			try {
				
				
				//Long idCompany = ((Realtor) temp).getCompany_Realtor_List(); // sacando la compañía del Loan logueado
				//List<Realtor> realtorList = RealtorServices.findRealtorListByCompany(idCompany);
				List<Client> clientList = ClientServices.findClientListByRealtor(((Realtor) temp).getUserId());

				model.addAttribute("name",((Realtor) temp).getName() + "  " + ((Realtor) temp).getLast_name());
				model.addAttribute("type", "REALTOR");
			//	model.addAttribute("companyname", idCompany);
				//model.addAttribute("cantRealter", realtorList.size());
				model.addAttribute("cantClient", clientList.size());
				//model.addAttribute("realtorList", realtorList);
				model.addAttribute("clientList", clientList);

			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
		}
		return "welcomePage";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String RealtorEdit(Model model, userProfile user) {
		try {
			
			if(user.getUserName().isEmpty() || user.getOldPassword().isEmpty() || user.getNewPassword().isEmpty() || user.getConfirmNewPassword().isEmpty() ) {
				model.addAttribute("errorMessage", "Missing data in the form, please fill in all fields");
				model.addAttribute("user", user);
				return "changePasswordUser";
			}
			
			
			
			AppUser usuarioBase = appUserServices.getAppUser(user.getUserId()).get();
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 //matches primero desencriptado segundo encriptado
			boolean iguales = encoder.matches(user.getOldPassword(), usuarioBase.getEncrytedPassword());
			
			if(iguales) {
				if(user.getNewPassword().equals(user.getConfirmNewPassword())) {
					String newpass = EncrytedPasswordUtils.encrytePassword(user.getNewPassword());
					
					usuarioBase.setEncrytedPassword(newpass);
					appUserServices.SaveAndUpdateAppUser(usuarioBase);
					
					model.addAttribute("successMessage", "The operation completed successfully");
					
					((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser().setEncrytedPassword(newpass);
					return welcomePage(model);
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
		model.addAttribute("user", user);
		return "changePasswordUser";
	}
	
	//////// Solo para cambiar resetear el password manualmente//////////////////
	
	@RequestMapping(value = { "/changePasswordAdmin" }, method = RequestMethod.GET)
	public String changePasswordAdmin(Model model) {
		
		    userProfile user = new userProfile();		
			model.addAttribute("user", user);
			return "changePasswordAdmin";
	
	  }
	
	@RequestMapping(value = "/changePasswordAdmin", method = RequestMethod.POST)
	public String passAdmin(Model model, userProfile user) {
		try {
			
			if(user.getUserName().isEmpty()) {
				model.addAttribute("errorMessage", "Missing data in the form, please fill in all fields");
				model.addAttribute("user", user);
				return "changePasswordAdmin";
			}
			
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();

			if (temp instanceof Loan_officer) {
				AppUser usuarioBase = appUserServices.getAppUserByName(user.getUserName()).get();
			
			//AppUser usuarioBase = appUserServices.getAppUser(user.getUserId()).get();
			
			//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 //matches primero desencriptado segundo encriptado
			//boolean iguales = encoder.matches(user.getOldPassword(), usuarioBase.getEncrytedPassword());
			
					String newpass = EncrytedPasswordUtils.encrytePassword("123");
					
					usuarioBase.setEncrytedPassword(newpass);
					appUserServices.SaveAndUpdateAppUser(usuarioBase);
					
					model.addAttribute("successMessage", "The operation completed successfully");
					
					((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser().setEncrytedPassword(newpass);
					
			    	EmailResetPass.enviar("nveitia@dgpinnacle.com", "Reset successful", usuarioBase.getUserName());
			    	
					return "loginPage";



			}
			else {
				model.addAttribute("user", user);
				model.addAttribute("errorMessage", "You do not have sufficient privileges for this operation");
				return "changePasswordAdmin";
			}
				
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		return "changePasswordAdmin";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		model.addAttribute("message", "Admin Page!");

		return "adminPage";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		model.addAttribute("message", "Logout Successful!");
		return "index";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		model.addAttribute("message", "User Info Page!");

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}
	
	@RequestMapping(value = { "/verify" }, method = RequestMethod.GET)
	public String Verify(Model model) {
		letteverify temp = new letteverify();
		model.addAttribute("Letter", temp);
		return "verifyLetter";
	}
	
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String VerifyLetter(@Valid letteverify letteverify, Model model) {
	    String code = letteverify.getUniqueKey();
	    
	    try {
	    
		  //  Letter_config letter = Letter_configServices.getLetterByUniqueKey(code);

				Client client = new Client();
				List<String> clientList = new ArrayList<String>();
				
				/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION
				ShowLetterViewModel lovm = new ShowLetterViewModel();
				lovm.setLetter(Letter_configServices.getLetterByUniqueKey(code));
				
			    if (lovm.getLetter() != null) {
				List<Loan_Client_Operation> lcoList = lovm.getLetter().getOperation().getLoan_Client_Operation();
				
				for (int y = 0; y < lcoList.size(); y++) {
					client = lcoList.get(y).getLoan_Client().getClient();
					clientList.add(client.getName() + " " + client.getLast_name());
				}
				lovm.setClientsName(clientList);
				model.addAttribute("Letter_config", lovm);
				/// --------------------------------------------------------------------------------------------------
		            model.addAttribute("fix_data_subject", lovm.getLetter().getLetter_fixdata().getSubject());   // Por qué necesito especificar el get para meterlo en un span tag ??? 
		            model.addAttribute("fix_data_conditions", lovm.getLetter().getLetter_fixdata().getConditions());
		            model.addAttribute("fix_data_ftext", lovm.getLetter().getLetter_fixdata().getFinaltext());
		  
		            
		            letteverify.setLetter(Letter_configServices.getLetterByUniqueKey(code));
		            letteverify.setUniqueKey(code);
		            
		            model.addAttribute("Letter", letteverify);
	        }else {
	            
		    model.addAttribute("Letter", letteverify);
			model.addAttribute("successMessage", "The letter do not exist");
	        }
			    
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        return "verifyLetter";
    }
	
	@RequestMapping(value = "/pdfreport{uniqueKey}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(Model model, @PathVariable(required = false, name = "uniqueKey") String uniqueKey) {

		List<Client> clients = new ArrayList<Client>();
		ByteArrayInputStream bis = null;
		try {
			
			
			Letter_config letter = Letter_configServices.getLetterByUniqueKey(uniqueKey);
			
			Client client = new Client();
			
			/// PARA SACAR LOS CLIENTES DE LA CARTA EN CUESTION
			
		    if (letter != null) {
			List<Loan_Client_Operation> lcoList = letter.getOperation().getLoan_Client_Operation();
			
			for (int y = 0; y < lcoList.size(); y++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clients.add(client);
			}
	
			clients = (List<Client>) ClientServices.LoadAllClient();
			bis = GeneratePdfReport.clientsReport(clients, letter.getLetter_fixdata().getSubject(), letter);
			}
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Letter.pdf");
        
        //EnvioEmail.enviar("jgarcia@dl.cl", "Asunto del correo", "Test Este es el test de envio de correo");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	

}