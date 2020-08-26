package reloff.project.org.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Client;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Realtor;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.services.ClientServices;
import reloff.project.org.services.Loan_ClientServices;

@Controller
public class ClientController {

	@Autowired
	private ClientServices ClientServices;

	@Autowired
	private Loan_ClientServices Loan_ClientServices;
	
	//@Autowired
	//private Loan_officerServices Loan_officerServices;

	@RequestMapping(value = { "/clientList" })
	public String ClientList(Model model) throws Exception {
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		if (temp instanceof Loan_officer) {
			Long idCompany = ((Loan_officer) temp).getCompany().getId();
			List<Client> clientList;

			clientList = ClientServices.findClientListByLoan(temp.getUserId());

			model.addAttribute("name", ((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
			model.addAttribute("type", "LOAN OFFICER"); 
			model.addAttribute("companyname", idCompany);
			model.addAttribute("clientList", clientList);

			model.addAttribute("successMessage", null);
			model.addAttribute("errorMessage", null);
		}

		if (temp instanceof Realtor) {
			List<Client> clientList = ClientServices.findClientListByRealtor(temp.getUserId());
			// model.addAttribute("name", ((Realtor) temp).getName() + " " +
			// ((Realtor)temp).getLast_name()); model.addAttribute("type", "REALTOR");
			model.addAttribute("clientList", clientList);
			model.addAttribute("type", "REALTOR");
		}

		return "/Client/clientlist";
	}

	@RequestMapping(value = { "/clientEdit", "/clientEdit{id}" }, method = RequestMethod.GET)
	public String ClientEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();

		try {

			if (temp instanceof Loan_officer) {
				List<Client> clientList = ClientServices.findClientListByLoan(temp.getUserId());
				model.addAttribute("client", clientList);
			}
			if (null != id) {
				model.addAttribute("client", ClientServices.getClient(id));
				model.addAttribute("text", "Modify");

			} else {
				model.addAttribute("client", new Client());
				model.addAttribute("text", "Create New");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Client/clientEdit";
	}

	@RequestMapping(value = "/clientEdit", method = RequestMethod.POST)
	public String ClientEdit(@Valid Client Client, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "/Client/clientEdit";
		}
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		if (temp instanceof Loan_officer) {
			try {
				if (Client.getId() == null) {
					ClientServices.AddClientAndLoanClient(Client, ((Loan_officer) temp));
					model.addAttribute("successMessage", "The client has been successfully created");
				} else {
					ClientServices.UpdateClient(Client);
					model.addAttribute("successMessage", "The client has been successfully updated");
				}
			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
			try {
				// para mostrar lista en clientList
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				List<Client> clientList = ClientServices.findClientListByLoan(temp.getUserId());
				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("clientList", clientList);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
				e.printStackTrace();
			}
		}
		return "/Client/clientlist";
	}

//	@RequestMapping(value = "/clientNew", method = RequestMethod.POST)
//	public String ClientAdd(Model model, Client Client, HttpServletRequest request) {
//
//		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
//		if (temp instanceof Loan_officer) {
//			try {
//				ClientServices.AddClientAndLoanClient(Client, ((Loan_officer) temp));
//
//				// para mostrar lista en clientList
//				Long idCompany = ((Loan_officer) temp).getCompany().getId();
//				List<Client> clientList = ClientServices.findClientListByLoan(temp.getUserId());
//				model.addAttribute("name",
//						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
//				model.addAttribute("type", "LOAN OFFICER");
//				model.addAttribute("companyname", idCompany);
//				model.addAttribute("clientList", clientList);
//
//				model.addAttribute("successMessage", "The client has been successfully created");
//
//			} catch (Exception e) {
//				model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
//				e.printStackTrace();
//			}
//		}
//
//		return "/Client/clientlist";
//	}

	@RequestMapping(value = "/clientDelete{id}", method = RequestMethod.GET)
	public String ClientDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		try {
			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			if (temp instanceof Loan_officer) {

				Loan_ClientServices.DeleteLoan_ClientByIdClient(id);

				ClientServices.DeleteClient(id);

				// para mostrar lista en clientList
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				List<Client> clientList = ClientServices.findClientListByLoan(temp.getUserId());

				model.addAttribute("name",
						((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
				model.addAttribute("type", "LOAN OFFICER");
				model.addAttribute("companyname", idCompany);
				model.addAttribute("clientList", clientList);

				model.addAttribute("successMessage", "The client has been successfully deleted");
				
			} else {
				model.addAttribute("clientList", ClientServices.LoadAllClient());
			}

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An error has occurred, please contact the system administrator");
			e.printStackTrace();

			AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAppUser();
			if (temp instanceof Loan_officer) {

				// para mostrar lista en clientList
				Long idCompany = ((Loan_officer) temp).getCompany().getId();
				List<Client> clientList;
				try {
					clientList = ClientServices.findClientListByLoan(temp.getUserId());

					model.addAttribute("name",
							((Loan_officer) temp).getName() + "  " + ((Loan_officer) temp).getLast_name());
					model.addAttribute("type", "LOAN OFFICER");
					model.addAttribute("companyname", idCompany);
					model.addAttribute("clientList", clientList);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		return "/Client/clientlist";
	}
/*
	/// exprt to PDF
	@RequestMapping(value = { "/clientExport" })
	public ResponseEntity<InputStreamResource> ExportClientTable() {
		
		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();
		
	
		
		ByteArrayInputStream bis = null;
		HttpHeaders headers = new HttpHeaders();
		String pie = "";
		
		try {
			
			if (temp instanceof Loan_officer) {
				List<Client> clientList;

				clientList = ClientServices.findClientListByLoan(temp.getUserId());
				
				Loan_officer loan = Loan_officerServices.getLoan_officer(temp.getUserId());
				pie += "\n\n\n\n\n";
				pie += loan.getName() + loan.getLast_name() + "\n";
				pie += loan.getNmls() + "\n";
				pie += "Your Home Financing Partner." + "\n";

				// create 6 column table
				PdfPTable table = new PdfPTable(6);

				// set the width of the table to 100% of page
				table.setWidthPercentage(100);

				// set relative columns width
				table.setWidths(new float[] { 0.6f, 1.4f, 0.8f, 0.8f, 1.8f, 2.6f });

								
				table.addCell(createLabelCell("ID"));
				table.addCell(createValueCell("Name"));
				table.addCell(createLabelCell("Last Name"));
				table.addCell(createValueCell("Mailing Add"));
				table.addCell(createLabelCell("Email"));
				table.addCell(createValueCell("Cell Phone"));

				for (Client c : clientList) {

					// 1st Row
					table.addCell(createValueCell(String.valueOf(c.getId())));
					table.addCell(createLabelCell(c.getName()));
					table.addCell(createLabelCell(c.getLast_name()));
					table.addCell(createValueCell(c.getMailing_add()));
					table.addCell(createLabelCell(c.getEmail()));
					table.addCell(createValueCell(c.getCellphone()));

				}

				Chunk texto1 = new Chunk("Reloff.com - Client Table Report", FontFactory.getFont(BaseFont.HELVETICA_BOLD, 11));
				bis = GeneratePdfReport.ReportTable(table, texto1, pie);
				headers.add("Content-Disposition", "inline; filename=ClientTable.pdf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ClientTable.pdf")
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(bis.length) //
                .body(new InputStreamResource(bis));

		//return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		//		.body(new InputStreamResource(bis));
	}*/

	// create cells
	@SuppressWarnings("unused")
	private static PdfPCell createLabelCell(String text) {
		// font
		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));

		return cell;
	}

	// create cells
	@SuppressWarnings("unused")
	private static PdfPCell createValueCell(String text) {
		// font
		Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text, font));

		return cell;
	}

}
