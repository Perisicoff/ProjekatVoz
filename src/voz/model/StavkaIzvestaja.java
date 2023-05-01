package voz.model;

import java.time.LocalDateTime;

import voz.util.Konzola;

public class StavkaIzvestaja {

	private String nazivVoza = "";
	private double prihod;
	private LocalDateTime poslednjiPolazak = LocalDateTime.now();

	public StavkaIzvestaja(String nazivVoza, double prihod, LocalDateTime poslednjiPolazak) {
		super();
		this.nazivVoza = nazivVoza;
		this.prihod = prihod;
		this.poslednjiPolazak = poslednjiPolazak;
	}

	@Override
	public String toString() {
		return "Naziv voza: " + nazivVoza + "   Prihod: " + prihod + "   Polazak voza sa najvise prodatih karata: "
				+ (poslednjiPolazak == LocalDateTime.MIN ? "/" : Konzola.formatiraj(poslednjiPolazak));
	}

	public static int comparePrihod(StavkaIzvestaja stavka1, StavkaIzvestaja stavka2) {
		return Double.compare(stavka1.prihod, stavka2.prihod);
	}

	public String getNazivVoza() {
		return nazivVoza;
	}

	public double getPrihod() {
		return prihod;
	}

	public LocalDateTime getPoslednjiPolazak() {
		return poslednjiPolazak;
	}

}
