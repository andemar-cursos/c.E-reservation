/**
 * 
 */
package com.platzi.ereservation.negocio.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platzi.ereservation.modelo.Reserva;
import com.platzi.ereservation.negocio.repository.ReservaRepository;

/**
 * @author andemar-pc
 *
 */
@Service
@Transactional(readOnly = true)
public class ReservaService {
	
	private final ReservaRepository reservaRepository;
	
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}
	
	
	public Reserva create (Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}
	
	
	public Reserva update(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}
	
	
	public void delete(Reserva reserva) {
		this.reservaRepository.delete(reserva);
	}
	
	/*public Optional<Reserva> findByIdRes(String idRes) {
		return this.reservaRepository.findById(idRes);
	}*/
	
	public Reserva findByIdRes(String idRes) {
		return this.reservaRepository.findByIdRes(idRes);
	}
	
	public List<Reserva> findByFecha(Date fechaInicio, Date fechaSalida) {
		return this.reservaRepository.find(fechaInicio, fechaSalida);
	}
	
	public List<Reserva> findAll(){
		return this.reservaRepository.findAll();
	}

}
