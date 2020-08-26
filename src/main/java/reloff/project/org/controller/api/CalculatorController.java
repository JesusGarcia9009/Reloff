package reloff.project.org.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reloff.project.org.viewmodel.CalculateDTO;
import reloff.project.org.viewmodel.CalculateResposeDTO;


@Api(value = "Microservicio de calculadora", description = "Esta API tiene los servicios referentes a las operaciones de la calculadora")
public interface CalculatorController {


	
	@ApiOperation(value = "Buscar contratos", notes = "Retorna los datos de los contratos pasando un rut!")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio")
    })
	public ResponseEntity<String> initialCalculator();
	
	
	@ApiOperation(value = "Clacular", notes = "Retorna los datos del calculo de Morgage!")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno satisfactorio")
    })
	public ResponseEntity<CalculateResposeDTO> Calculate(@Valid @RequestBody CalculateDTO calculateDTO) throws Exception;
	

	
}
