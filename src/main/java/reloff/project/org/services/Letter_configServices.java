package reloff.project.org.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import reloff.project.org.entity.AppUser;
import reloff.project.org.entity.Client;
import reloff.project.org.entity.Letter_config;
import reloff.project.org.entity.Loan_Client_Operation;
import reloff.project.org.entity.Loan_officer;
import reloff.project.org.entity.Operation;
import reloff.project.org.entity.Realtor;
import reloff.project.org.service.UserLogIn;
import reloff.project.org.viewmodel.ClientDTO;
import reloff.project.org.viewmodel.ShowLetterViewModel;

@Service
public class Letter_configServices {

	@Autowired
	private reloff.project.org.repository.Letter_configRepository Letter_configRepository;
	
	@Autowired
	private ClientServices ClientServices;
	
	@Autowired
	private Loan_Client_OperationServices Loan_Client_OperationServices;

	@ExceptionHandler
	public List<Letter_config> LoadAllLetter_config() throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();

		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();

		if (temp instanceof Loan_officer) {

			Letter_configRepository.findByLoan(temp.getUserId()).forEach(List::add);
		}
		if (temp instanceof Realtor) {
			Letter_configRepository.findByRealtor(temp.getUserId()).forEach(List::add);
		}

		// el forEach es una referencia del metodo add a la lista
		// es lo mismo que recorrer la lista entera y agregarla
		// asi se hace con expresiones LAMBA
		// Letter_configRepository.findAll().forEach(List::add);

		return List;
	}

	@ExceptionHandler
	public Optional<Letter_config> getLetter_config(Long id) throws Exception {
		return Letter_configRepository.findById(id);
	}

	@ExceptionHandler
	public void AddLetter_config(Letter_config Letter_config) throws Exception {
		Letter_configRepository.save(Letter_config);
	}

	@ExceptionHandler
	public void UpdateLetter_config(Letter_config Letter_config) throws Exception {
		Letter_configRepository.save(Letter_config);
	}

	@ExceptionHandler
	public void DeleteLetter_config(Long id) throws Exception {
		Letter_configRepository.deleteById(id);
	}

	@ExceptionHandler
	public List<Letter_config> findAllLetterListByLoan(Long loan_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findByLoan(loan_id).forEach(List::add);
		return List;
	}

	@ExceptionHandler
	public List<Letter_config> findActiveLetterListByLoan(Long loan_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findActiveByLoan(loan_id).forEach(List::add);
		return List;
	}

	@ExceptionHandler
	public List<ShowLetterViewModel> findActiveLetterListByLoanNew(Long loan_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findActiveByLoan(loan_id).forEach(List::add);

		Client client = new Client();
		List<ShowLetterViewModel> lovmList = new ArrayList<>();
		for (int i = 0; i < List.size(); i++) {
            
			List<String> clientList = new ArrayList<String>();
			ShowLetterViewModel lovm = new ShowLetterViewModel();
			lovm.setLetter(List.get(i));
			Operation op = List.get(i).getOperation();
			List<Loan_Client_Operation> lcoList = Loan_Client_OperationServices.findLCOByOperation(op);
			for (int y = 0; y < lcoList.size(); y ++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clientList.add(client.getName() + " " + client.getLast_name());
			}
			lovm.setClientsName(clientList);
			lovmList.add(lovm);
		}

		return lovmList;
	}

	@ExceptionHandler
	public List<Letter_config> findAllLetterListByRealtor(Long realtor_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findByRealtor(realtor_id).forEach(List::add);
		return List;
	}

	@ExceptionHandler
	public List<Letter_config> findActiveLetterListByRealtor(Long realtor_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findActiveByRealtor(realtor_id).forEach(List::add);
		return List;
	}

	@ExceptionHandler
	public List<ShowLetterViewModel> findActiveLetterListByRealtorNew(Long realtor_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findActiveByRealtor(realtor_id).forEach(List::add);

		Client client = new Client();

		List<ShowLetterViewModel> lovmList = new ArrayList<>();
		for (int i = 0; i < List.size(); i++) {
			List<String> clientList = new ArrayList<String>();
			ShowLetterViewModel lovm = new ShowLetterViewModel();
			lovm.setLetter(List.get(i));
			List<Loan_Client_Operation> lcoList = List.get(i).getOperation().getLoan_Client_Operation();
			for (int y = 0; y < lcoList.size(); y++) {
				client = lcoList.get(y).getLoan_Client().getClient();
				clientList.add(client.getName() + " " + client.getLast_name());
			}
			lovm.setClientsName(clientList);
			lovmList.add(lovm);
		}

		return lovmList;
	}

	@ExceptionHandler
	public Letter_config getLetterByUniqueKey(String uniqueKey) throws Exception {
		Letter_config Entity = Letter_configRepository.findByUniqueKey(uniqueKey);
		return Entity;
	}

	@ExceptionHandler
	public List<Letter_config> findActiveLetterListByOperation(Long operation_id) throws Exception {
		List<Letter_config> List = new ArrayList<Letter_config>();
		Letter_configRepository.findActiveByOperation(operation_id).forEach(List::add);
		return List;
	}

	@SuppressWarnings("unused")
	@ExceptionHandler
	public double calculateMonthlyPayment(double loanAmount, int termInMonths, double interestRate) {

		// Convert interest rate into a decimal
		// eg. 6.5% = 0.065

		interestRate /= 100.0;

		// Monthly interest rate
		// is the yearly rate divided by 12

		double monthlyRate = interestRate / 12.0;

		// The length of the term in months
		// is the number of years times 12

		// int termInMonths = termInYears * 12;

		// Calculate the monthly payment
		// Typically this formula is provided so
		// we won't go into the details

		// The Math.pow() method is used calculate values raised to a power

		double monthlyPayment1 = -((loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, termInMonths)));
		// double t =Math.pow(1+monthlyRate, termInMonths);
		// double t1 =(Math.pow(monthlyRate, termInMonths));
//		      double monthlyPayment = 
//				         (loanAmount) / 
//				            ((1-Math.pow(1+monthlyRate, -termInMonths))/(monthlyRate*Math.pow(1+monthlyRate, -termInMonths)));

		double monthlyPayment = -((loanAmount) * ((monthlyRate * (Math.pow(1 + monthlyRate, termInMonths)))
				/ (1 - Math.pow(1 + monthlyRate, termInMonths))));

		return monthlyPayment;
	}

	
	///este metodo obtienen todos los clientes de del sistema, seleccionados o no
	public List<ClientDTO> getListAllClient(List<Loan_Client_Operation> lcoList) {
		List<ClientDTO> result = new ArrayList<ClientDTO>();
		Client client = new Client();
		for (int y = 0; y < lcoList.size(); y++) {
			ClientDTO temp = new ClientDTO();

			client = lcoList.get(y).getLoan_Client().getClient();

			temp.setFullName(client.getName() + " " + client.getLast_name());
			temp.setIdCliente(client.getId());
			temp.setSelected(true);

			result.add(temp);
		}

		AppUser temp = ((UserLogIn) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUser();

		List<Client> listadototal = null;
		try {
			listadototal = ClientServices.findClientListByLoan(temp.getUserId());
		} catch (Exception e) {
		}

		for (Client cli : listadototal) {
			ArrayList<ClientDTO> clientDTOList = result.stream().filter(x -> x.getIdCliente() == cli.getId())
					.collect(Collectors.toCollection(() -> new ArrayList<ClientDTO>()));
			if (clientDTOList.size() == 0) {
				ClientDTO temp1 = new ClientDTO();

				temp1.setFullName(client.getName() + " " + client.getLast_name());
				temp1.setIdCliente(client.getId());
				temp1.setSelected(false);

				result.add(temp1);
			}
		}

		return result;
	}

	@ExceptionHandler
	public double maxPaid(double primaryPay, double taxes, double insurance, double HOA, double MI) {
		double maxPay = primaryPay + taxes + insurance + HOA + MI;
		return maxPay;
	}

	@ExceptionHandler
	public double insurance(int price) {
		return 0.01 * price / 12;
	}

}
