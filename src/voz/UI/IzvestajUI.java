package voz.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import voz.DAO.VozDAO;
import voz.model.Karta;
import voz.model.StavkaIzvestaja;
import voz.model.Voz;
import voz.util.Konzola;

public class IzvestajUI {

	private static VozDAO vozDAO;

	public static void setVozDAO(VozDAO vozDAO) {
		IzvestajUI.vozDAO = vozDAO;
	}

	public static void izvestaj() {

		LocalDateTime pocetniDatum = Konzola.ocitajDateTime("Unesite pocetni datum pretrage: ");
		LocalDateTime krajnjiDatum = Konzola.ocitajDateTime("Unesite krajnji datum pretrage: ");

		try {
			Collection<Voz> vozovi = vozDAO.getAll();
			Collection<String> nazivi = new LinkedHashSet<>();
			for (Voz voz : vozovi) {
				nazivi.add(voz.getNaziv());
			}

			List<StavkaIzvestaja> stavke = new ArrayList<>();
			for (String naziv : nazivi) {
				double prihod = 0;
				int maxBrojProdatihKarata = Integer.MIN_VALUE;
				LocalDateTime maxBrojPolazaka = LocalDateTime.MIN;
				for (Voz voz : vozovi) {
					if (voz.datumUOpsegu(pocetniDatum, krajnjiDatum)) {
						for (Karta karta : voz.getKarte()) {
							if (voz.getNaziv().equals(naziv)) {
								prihod += karta.cenaKarte();
								int brojProdatihKarata = voz.getKarte().size();
								if (brojProdatihKarata > maxBrojProdatihKarata) {
									maxBrojProdatihKarata = brojProdatihKarata;
									maxBrojPolazaka = voz.getDatumPolaska();
								}
							}
						}
					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(naziv, prihod, maxBrojPolazaka);
				stavke.add(stavka);
			}
			stavke.sort(StavkaIzvestaja::comparePrihod);
			try {
				String Headher = String.format("%-10s %-15s %-20s", "Naziv", "Prihod", "Datum polaska");
				System.out.println(Headher);
				System.out.println("========== =============== ====================");
				for (StavkaIzvestaja stavkaIzvestaja : stavke) {
					String foother = String.format("%-10s %-15s %-20s", stavkaIzvestaja.getNazivVoza(), stavkaIzvestaja.getPrihod(), (stavkaIzvestaja.getPoslednjiPolazak() == LocalDateTime.MIN ? "/" : Konzola.formatiraj(stavkaIzvestaja.getPoslednjiPolazak())));
					System.out.println(foother);
					System.out.println("---------- --------------- --------------------");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske!");
			e.printStackTrace();
		}

	}
}
