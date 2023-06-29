package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatoFecha {
	public static String fecha (LocalDateTime fecha) {
		var formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String fechaLocal=fecha.format(formato);
		return fechaLocal;
	}
	public static LocalDateTime getFormatoFecha(String fecha) {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		var fechaLocal=LocalDateTime.parse(fecha, formatoFecha);
		return fechaLocal;
	}
	public static void main(String[] args) {
		String fecha ="2023/06/12 09:33";
		System.out.println(FormatoFecha.getFormatoFecha(fecha));
	}
}
