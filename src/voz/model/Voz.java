package voz.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import voz.util.Konzola;

public class Voz {

	private long id;
	private String brojVoza = "";
	private String naziv = "";
	private LocalDateTime datumPolaska = LocalDateTime.now();
	private double cenaKarte;
	private int brMesta;

	final Set<Karta> karte = new HashSet<>();

	public Voz() {
		super();
	}

	public Voz(String brojVoza, String naziv, LocalDateTime datumPolaska, double cenaKarte, int brMesta) {
		super();
		this.brojVoza = brojVoza;
		this.naziv = naziv;
		this.datumPolaska = datumPolaska;
		this.cenaKarte = cenaKarte;
		this.brMesta = brMesta;
	}

	public Voz(long id, String brojVoza, String naziv, LocalDateTime datumPolaska, double cenaKarte, int brMesta) {
		super();
		this.id = id;
		this.brojVoza = brojVoza;
		this.naziv = naziv;
		this.datumPolaska = datumPolaska;
		this.cenaKarte = cenaKarte;
		this.brMesta = brMesta;
	}

	public boolean datumUOpsegu(LocalDateTime pocetni, LocalDateTime krajnji) {
		return datumPolaska.compareTo(pocetni) >= 0 && datumPolaska.compareTo(krajnji) <= 0;
	}
	
	public int brojSlobodnihMesta() {
		return this.brMesta - this.karte.size();
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
		Voz other = (Voz) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ID voza: " + id + "   Broj voza: " + brojVoza + "   Naziv: " + naziv + "   Datum i vreme polaska:"
				+ Konzola.formatiraj(datumPolaska) + "   Cena karte: " + cenaKarte + "   Br. mesta: " + brMesta
				+ "   Br. slobodnih mesta: " + brojSlobodnihMesta();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrojVoza() {
		return brojVoza;
	}

	public void setBrojVoza(String brojVoza) {
		this.brojVoza = brojVoza;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalDateTime getDatumPolaska() {
		return datumPolaska;
	}

	public void setDatumPolaska(LocalDateTime datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public int getBrMesta() {
		return brMesta;
	}

	public void setBrMesta(int brMesta) {
		this.brMesta = brMesta;
	}

	public Collection<Karta> getKarte() {
		return Collections.unmodifiableCollection(this.karte);
	}

	public void addKartu(Karta karta) {
		this.karte.add(karta);
		karta.voz = this;
	}

	public void addSveKarte(Collection<Karta> karte) {
		this.karte.addAll(karte);
		for (Karta karta : karte) {
			karta.voz = this;
		}
	}

	public void removeKartu(Karta karta) {
		karta.voz = null;
		this.karte.remove(karta);
	}

	public void removeSveKarte() {
		for (Karta karta : karte) {
			karta.voz = null;
		}
		this.karte.clear();
	}

}
