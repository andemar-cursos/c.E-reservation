/**
 * 
 */
package com.platzi.ereservation.vista.resources.vo;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.platzi.ereservation.modelo.Cliente;
import lombok.Data;

/**
 * Clase que representa la tabla reserva
 * @author andemar-pc
 *
 */
@Data

public class ReservaVO {
	private String idRes;
	@Temporal(TemporalType.TIME)
	private Date fechaIngresoRes;
	@Temporal(TemporalType.TIME)
	private Date fechaSalidaRes;
	private int cantidadPersonasRes;
	private String descripcionRes;
	private Cliente cliente;
	
}
