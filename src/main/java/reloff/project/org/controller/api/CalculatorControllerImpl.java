package reloff.project.org.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reloff.project.org.services.CalculateServices;
import reloff.project.org.viewmodel.CalculateDTO;
import reloff.project.org.viewmodel.CalculateResposeDTO;

@RestController
@RequestMapping("/calculator")
public class CalculatorControllerImpl implements CalculatorController {

	@Autowired
	public CalculateServices calculateServices;
	
	@Override
	@GetMapping("/initial-calculator")
	public ResponseEntity<String> initialCalculator(){
		ResponseEntity<String> response = new ResponseEntity<>("Jesus", HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value = "/calculate")
	public ResponseEntity<CalculateResposeDTO> Calculate(@Valid @RequestBody CalculateDTO calculateDTO) throws Exception {
		ResponseEntity<CalculateResposeDTO> result = new ResponseEntity<>(calculateServices.calculate(calculateDTO), HttpStatus.OK);
		return result;
	}
	
	
}
