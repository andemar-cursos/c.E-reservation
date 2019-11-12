/**
 * 
 */
package com.platzi.ereservation.vista.resources;

import java.util.Date;
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

import com.platzi.ereservation.modelo.Reserva;
import com.platzi.ereservation.negocio.services.ReservaService;
import com.platzi.ereservation.vista.resources.vo.ReservaVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author andemar-pc
 *
 */
@RestController
@RequestMapping("/api/reserva")
@Api(tags = "reserva")
public class ReservaResource {
	
	private final ReservaService reservaService;
	
	public ReservaResource(ReservaService reservaService) {
		this.reservaService = reservaService;
	}
	
	
	
	@PostMapping
	@ApiOperation(value = "Crear reserva", notes = "Servicio para crear una nueva reserva")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reserva creada correctamente"),
						   @ApiResponse(code = 400, message = "Solicitud invalida")})
	public ResponseEntity<Reserva> createReserva(@RequestBody ReservaVO reservaVo){
		Reserva reserva = new Reserva();
		reserva.setFechaIngresoRes(reservaVo.getFechaIngresoRes());
		reserva.setFechaSalidaRes(reservaVo.getFechaSalidaRes());
		reserva.setCantidadPersonasRes(reservaVo.getCantidadPersonasRes());
		reserva.setDescripcionRes(reservaVo.getDescripcionRes());
		reserva.setCliente(reservaVo.getCliente());
		
		return new ResponseEntity<>(this.reservaService.create(reserva), HttpStatus.CREATED);
	}
	
	
	
	
	
	@PutMapping("/{idRes}")
	@ApiOperation(value = "Actualizar reserva", notes = "Servicio para actualizar una reserva")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reserva actualizada correctamente"),
						   @ApiResponse(code = 404, message = "Reserva no encontrada")})
	public ResponseEntity<Reserva> updateReserva(@PathVariable("idRes") String idRes, ReservaVO reservaVo){
		
		Reserva reserva = this.reservaService.findByIdRes(idRes);
		//El if identifica si existe la reserva o no.
		if(reserva == null) {
			return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
		}else {
			reserva.setFechaIngresoRes(reservaVo.getFechaIngresoRes());
			reserva.setFechaSalidaRes(reservaVo.getFechaSalidaRes());
			reserva.setCantidadPersonasRes(reservaVo.getCantidadPersonasRes());
			reserva.setDescripcionRes(reservaVo.getDescripcionRes());
			reserva.setCliente(reservaVo.getCliente());
		}
		
		return new ResponseEntity<>(this.reservaService.create(reserva), HttpStatus.CREATED);
	}
	
	
	
	
	
	@DeleteMapping("/{idRed}")
	@ApiOperation(value = "Eliminar reserva", notes = "Servicio para eliminar una reserva")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
	   		   			   @ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void removeReserva(@PathVariable("idRes")String idRes) {
		Reserva reserva = this.reservaService.findByIdRes(idRes);
		
		if(reserva != null) {
			this.reservaService.delete(reserva);
		}
		
	}
	
	
	
	@GetMapping
	@ApiOperation(value = "Listar reserva", notes = "Servicio para listar todas las reservas")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reservas encontrados"),
	   		   			   @ApiResponse(code = 404, message = "Reservas no encontrados")})
	public ResponseEntity<List<Reserva>> findAll(){
		return ResponseEntity.ok(this.reservaService.findAll());
	}
	
	
	
	
	@GetMapping("/{fechaInicio, fechaSalida")
	@ApiOperation(value = "Reserva por fecha", notes = "Servicio para obtener reservas por fecha")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Reservas encontrados"),
	   		   			   @ApiResponse(code = 404, message = "Reservas no encontrados")})
	public ResponseEntity<List<Reserva>> fechaReserva(@PathVariable("fechaInicio") Date fechaInicio, @PathVariable("fechaSalida") Date fechaSalida) {
		return ResponseEntity.ok(this.reservaService.findByFecha(fechaInicio, fechaSalida));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
