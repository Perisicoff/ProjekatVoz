package voz.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import voz.DAO.VozDAO;
import voz.model.Karta;
import voz.model.Voz;
import voz.util.Konzola;

public class VozUI {

	private static VozDAO vozDAO;

	public static void setVozDAO(VozDAO vozDAO) {
		VozUI.vozDAO = vozDAO;
	}

	
	public static void prikazSvihVozova() {
		try {
			Collection<Voz> vozovi = vozDAO.getAll();
			String Headher = String.format("%-10s %-15s %-20s %-20s %-20s %-20s %-20s", "ID", "Broj", "Naziv", "Datum polaska", "Cena", "Br. mesta", "Br. slobodnih mesta");
			System.out.println(Headher);
			System.out.println("========== =============== ==================== ==================== ==================== ==================== ====================");
			for (Voz voz : vozovi) {
				String foother = String.format("%-10s %-15s %-20s %-20s %-20s %-20s %-20s", voz.getId(), voz.getBrojVoza(), voz.getNaziv(), (voz.getDatumPolaska() == LocalDateTime.MIN ? "/" : Konzola.formatiraj(voz.getDatumPolaska())), voz.getCenaKarte(), voz.getBrMesta(), voz.brojSlobodnihMesta());
				System.out.println(foother);
				System.out.println("---------- --------------- -------------------- -------------------- -------------------- -------------------- --------------------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void prikazVozaSaSvimKArtama() {
		prikazSvihVozova();
		List<Karta> karte = new ArrayList<>();
		String broj = Konzola.ocitajString("Unesite broj voza: ");
		Voz voz = pretragaVoza(broj);
		if (voz == null) {
			return;
		}
		System.out.println(voz);
		for (Karta karta : voz.getKarte()) {
			karte.add(karta);
		}
		karte.sort(Karta::compareID);
		for (Karta karta : karte) {
			System.out.println(karta);
		}
	}
	
	public static Voz pretragaVoza(String broj) {
		Voz voz = null;
		try {
			voz = vozDAO.get(broj);
			if (voz == null) {
				System.out.println("Voz sa brojem: " + broj + " ne postoji!");
			}
		} catch (Exception e) {
			System.out.println("Dogodila se greska!");
			e.printStackTrace();
		}
		return voz;
	}
}
