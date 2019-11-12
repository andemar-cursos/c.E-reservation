/**
 * 
 */
package com.platzi.ereservation.vista.resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que representa el servicio web de cliente
 * - Con @RestController se expecifica que la clase sera usada como web service.
 * - Con @RequestMapping permite a la clase ser expuesta como servicio. y expecificar la
 * direccion web al ser llamado.
 * @author andemar-pc
 *
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {
	
}
