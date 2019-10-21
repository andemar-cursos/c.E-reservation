/**
 * 
 */
package com.platzi.ereservation.modelo;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Clase que representa la tabla reserva
 * @author andemar-pc
 *
 */
@Data
@Entity
@Table(name="reserva")

public class Reserva {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String idRes;
	
	//Esto es para especificar el formato de Date. (yyyy-mm-dd-hh, yyyy-mm-dd, etc).
	@Temporal(TemporalType.TIME)
	private Date fechaIngresoRes;
	@Temporal(TemporalType.TIME)
	private Date fechaSalidaRes;
	private int cantidadPersonasRes;
	private String descripcionRes;
	/**
	 * El nombre del atributo, debe ser el mismo de la clase cliente del OneToMany.
	 * Con @JoinColumn Se expecifica el key de la asociacion.
	 */
	@ManyToOne
	@JoinColumn(name="idCli")
	private Cliente cliente;
}
