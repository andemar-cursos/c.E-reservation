package com.platzi.ereservation.negocio.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.platzi.ereservation.modelo.Cliente;

/**
 * Interface para definir las operaciones de DB relacionadas con cliente.
 * @author andemar-pc
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
	
	/***
	 *  Definicion de metodo para buscar los clientes por su apellido
	 *  Este metodo genera automaticamente el codigo interno. *-*
	 * @param apellidoCli
	 * @return
	 */
	public List<Cliente> findByApellidoCli(String apellidoCli);
	
	/***
	 * Definicion de metodo para buscar los clientes por su apellido.
	 * Este metodo se realizo con la etiqueta @NamedQuery en la clase Cliente.
	 * @param identificacionCli
	 * @return
	 */
	public Cliente findByIdentificacion(String identificacionCli);
  	
	
	
	
	
}
