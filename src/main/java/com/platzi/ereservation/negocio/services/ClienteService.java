package com.platzi.ereservation.negocio.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platzi.ereservation.modelo.Cliente;
import com.platzi.ereservation.negocio.repository.ClienteRepository;

/**
 * Clase para definir los servicios de cliente.
 * - Con @Transactional(readOnly=true) se especifica que los metodos que
 * no lleven como anotacion @transactional, seran tratados como solo lectura.
 * - Con @service "declara" la clase como servicios.
 * @author andemar-pc 
 */
@Service
@Transactional(readOnly = true)
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	/***
	 * Metodo para realizar la operacion guardar cliente
	 * 
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	/***
	 * Metodo para realizar la operacion update cliente
	 * 
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente update(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	/***
	 * Metodo para realizar la operacion delete cliente
	 * 
	 * @param cliente
	 */
	@Transactional
	public void delete(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}

	/***
	 * Metodo para consultar un cliente por su identificacion - Este metodo es otra
	 * manera de consultar un atributo, sin el generador de codigo automatico
	 * 
	 * @param identificacionCli
	 * @return
	 */
	public Cliente findByIdentificacion(String identificacionCli) {
		return this.clienteRepository.findByIdentificacion(identificacionCli);
	}
	
	
	/**
	 * Se unsa clienteRepository.findAll() ya que el JPA obtiene todos los datos
	 * de la tabla. (No confundir el actual metodo findAll con el findAll de
	 * JPARepository. 
	 * @return
	 */
	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

}
