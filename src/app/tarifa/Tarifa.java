package app.tarifa;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Tarifa {

	private Double VALOR_TARIFA = 1.0;
	private Double VALOR;

	public Double calTarifa(LocalDateTime dateIni, LocalDateTime dateFin) {

		Long dias = ChronoUnit.DAYS.between(dateIni, dateFin);

		if (dias >= 5) {
			setVALOR(VALOR_TARIFA * dias);
		}
		return getVALOR();

	}

	public Double getVALOR() {
		return VALOR;
	}

	public Double getVALOR_TARIFA() {
		return VALOR_TARIFA;
	}

	public void setVALOR(Double vALOR) {
		VALOR = vALOR;
	}

	@Override
	public String toString() {
		return "Tarifa{" +
				"VALOR=" + VALOR +
				'}';
	}
}
