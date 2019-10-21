/**
 * 
 */
package com.platzi.ereservation.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Clase que representa la tabla cliente
 * @author andemar-pc
 * - Con @data de lombok, lo que hace es que al momento de contruccion
 * de la app. Se crean los getters y setters del modelo.
 * - Con @data no se necesita colocar private en los atributos debido
 * a que lombok detecta que es una clase pojo.
 * - Con @entity (ajax) permite la representacion de una tabla en la db como clase de java.
 * - Con @table se indica que tabla se va a mapear.
 * -
 * - Con "Select c from Cliente c where c.identificacionCli = ?1" significa => Seleccionar c "es uno de los
 * objetos de cliente) de la clase cliente, donde el atributo indentificacionCli de c, sea igual a ?1 (El primer
 * parametro que le enviemos a la consulta)"
 */
@Data
@Entity
@Table(name = "Cliente")
//@NamedQuery(name="cliente.findByIdentificacion", query="Select c from Cliente c where c.identificacionCli = ?1")
@NamedQuery(name="Cliente.findByIdentificacion", query="Select c from Cliente c where c.identificacionCli = ?1")
public class Cliente {
	/**
	 * Con @Id (ajax) permite la manipulacion de id de la tabla.
	 * Con @GeneratedValue (ajax) permite generar el id mediante postgres (system-uuid)
	 * Con @genericGenerator, NPI.
	 * Todo esto es para generar las llaves primarias.
	 */ 
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String idCli;
	private String nombreCli;
	private String apellidoCli;
	private String identificacionCli;
	private String direccionCli;
	private String telefonoCli;
	private String emailCli;
	
	/**
	 *Con @OneToMany, se especifica que es una relacion uno a muchos. Un cliente, muchas reservas.
	 *Cuando es uno a muchos, se usa Set<modelo>, pero cuando es uno a uno se usa solo una instancia
	 *de modelo.
	 */
	@OneToMany(mappedBy="cliente")
	private Set<Reserva> reservas;
	
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

}
