package com.platzi.ereservation.vista.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.ereservation.modelo.Cliente;
import com.platzi.ereservation.negocio.services.ClienteService;
import com.platzi.ereservation.vista.resources.vo.ClienteVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el servicio web de cliente - Con @RestController se
 * expecifica que la clase sera usada como web service. - Con @RequestMapping
 * permite a la clase ser expuesta como servicio. y expecificar la direccion web
 * al ser llamado.
 * 
 * - Con @Api(tags = "className") Se expecifica el uso de esta clase en swagger como cliente
 * 
 *  LOS CODIGOS DE STATUS SON 201=ACCION SATISFACTORIA - 400=NO SE PUEDE REALIZAR - 404=NO ENCONTRADO
 * 
 * @author andemar-pc
 *
 */
@RestController
@RequestMapping("/api/cliente")
@Api(tags = "cliente")
public class ClienteResource {

	private final ClienteService clienteService;

	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	
	
	
	
	
	
	/**
	 * Con @postMapping se expecifica que se realizara una accion de creacion.
	 * 
	 * - Con @ApiOperation(value = "accion", notes = "Descripcion") Se expecifica la accion y
	 * descripcion que hara el webService.
	 * 
	 * - Con @ApiResponses, se expecifica un mensage, dependiendo del resultado del servicio.
	 * @param clienteVo
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "Crear cliente", notes = "Servicio para crear un nuevo cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
				   		   @ApiResponse(code = 400, message = "Solicitud invalida")})
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clienteVo) {
		/**
		 * Se pasan los atributos de la clase clienteVo a cliente. Esto se hace ya que
		 * clienteVo Es una clase "esqueleto" de transmicion de datos.
		 */
		Cliente cliente = new Cliente();
		cliente.setNombreCli(clienteVo.getNombreCli());
		cliente.setApellidoCli(clienteVo.getApellidoCli());
		cliente.setDireccionCli(clienteVo.getDireccionCli());
		cliente.setTelefonoCli(clienteVo.getTelefonoCli());
		cliente.setEmailCli(clienteVo.getEmailCli());
		// retorna el cliente creado y un status.
		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}

	
	
	
	
	
	
	
	
	/**
	 * Con @putMapping("/{id}") se expecifica que es un servicio de update, el cual
	 * recibe un dato, el cual sirve para saber si existe o no en la DB.
	 * 
	 * @param clienteVo
	 * @return
	 */
	@PutMapping("/{identificacion}")
	@ApiOperation(value = "Actualizar cliente", notes = "Servicio para actualizar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
	   		   			   @ApiResponse(code = 404, message = "Cliente no encontrado")})
	
	public ResponseEntity<Cliente> updateCliente(@PathVariable("identificacion") String identificacion, ClienteVO clienteVo) {
		
		//Con esta linea se busca la existencia en la DB del indentificador del cliente a update. 
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		//Llegado el caso no existe, retorna un estado de not_found, en caso contrario se crea.
		if (cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		} else {
			cliente.setNombreCli(clienteVo.getNombreCli());
			cliente.setApellidoCli(clienteVo.getApellidoCli());
			cliente.setDireccionCli(clienteVo.getDireccionCli());
			cliente.setTelefonoCli(clienteVo.getTelefonoCli());
			cliente.setEmailCli(clienteVo.getEmailCli());
		}
		// retorna el cliente update y un status.
		return new ResponseEntity<>(this.clienteService.update(cliente), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	/**
	 * Con @DeleteMapping("/{id}") se expecifica que es un servicio de delete, el cual
	 * recibe el id del cliente a delete.
	 * @param identificacion
	 */
	@DeleteMapping("/{identificacion}")
	@ApiOperation(value = "Eliminar cliente", notes = "Servicio para eliminar un cliente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
	   		   			   @ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void removeCliente(@PathVariable("identificacion") String identificacion) {
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);
		
		if(cliente != null) {
			this.clienteService.delete(cliente);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Con @GetMapping se expecifica que es un servicio que retorna el resultado de una
	 * consulta, en este caso una lista de clientes.
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Listar cliente", notes = "Servicio para listar todos los clientes")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Clientes encontrados"),
	   		   			   @ApiResponse(code = 404, message = "Clientes no encontrados")})
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	
	
	
	
	

}
