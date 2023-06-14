package jdbc.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {
	private Integer id;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private BigDecimal valor;
	private String formaPago;
	public Reserva() {
		
	}
	public Reserva(Integer id,LocalDateTime fechaEntrada,LocalDateTime fechaSalida,BigDecimal valor,String formaPago) {
		this.id=id;
		this.fechaEntrada=fechaEntrada;
		this.fechaSalida=fechaSalida;
		this.valor=valor;
		this.formaPago=formaPago;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public LocalDateTime getFechaEntrada() {
		return this.fechaEntrada;
	}
	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada=fechaEntrada;
	}
	public LocalDateTime getFechaSalida() {
		return this.fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida=fechaSalida;
	}
	public BigDecimal getValor() {
		return this.valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor=valor;
	}
	public String getFormaPago() {
		return this.formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago=formaPago;
	}
	@Override
	public String toString() {
		return this.formaPago+" Total "+this.valor+" Soles";
	}
}
