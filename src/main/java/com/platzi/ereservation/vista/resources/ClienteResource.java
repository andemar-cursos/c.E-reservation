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

/**
 * Clase que representa el servicio web de cliente - Con @RestController se
 * expecifica que la clase sera usada como web service. - Con @RequestMapping
 * permite a la clase ser expuesta como servicio. y expecificar la direccion web
 * al ser llamado.
 * 
 * @author andemar-pc
 *
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {

	private final ClienteService clienteService;

	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * Con @postMapping se expecifica que se realizara una accion de creacion.
	 * 
	 * @param clienteVo
	 * @return
	 */
	@PostMapping
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
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	
	
	
	
	

}
