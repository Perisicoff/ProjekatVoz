package voz.model;

import java.time.LocalDateTime;
import java.util.Objects;

import voz.util.Konzola;

public class Karta {

	private long id;
	private LocalDateTime datum = LocalDateTime.now();
	private String kupac = "";
	private int razred;

	Voz voz;

	public Karta() {
		super();
	}

	public Karta(LocalDateTime datum, String kupac, int razred, Voz voz) {
		super();
		this.datum = datum;
		this.kupac = kupac;
		this.razred = razred;
		this.voz = voz;
	}

	public Karta(long id, LocalDateTime datum, String kupac, int razred, Voz voz) {
		super();
		this.id = id;
		this.datum = datum;
		this.kupac = kupac;
		this.razred = razred;
		this.voz = voz;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Karta other = (Karta) obj;
		return id == other.id;
	}
	
	public static int compareID(Karta id, Karta id2) {
		return Long.compare(id.getId(), id2.getId());
		
	}

	public double cenaKarte() {
		if (this.razred == 1) {
			return voz.getCenaKarte();
		} else if (this.razred == 2) {
			return voz.getCenaKarte() * 0.85;
		}
		return Double.MAX_VALUE;
	}

	@Override
	public String toString() {
		return "ID karte: " + id + "   Datum kupovine: " + Konzola.formatiraj(datum) + "   Kupac: " + kupac + "   Razred: " + razred + "   Voz: " + voz.getNaziv();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public int getRazred() {
		return razred;
	}

	public void setRazred(int razred) {
		this.razred = razred;
	}

	public Voz getVoz() {
		return voz;
	}

	public void setVoz(Voz voz) {
		this.voz = voz;
		voz.karte.add(this);
	}

}
