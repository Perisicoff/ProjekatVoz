package voz.UI;

import java.time.LocalDateTime;
import voz.DAO.KartaDAO;
import voz.model.Karta;
import voz.model.Voz;
import voz.util.Konzola;

public class KartaUI {

	private static KartaDAO kartaDAO;

	public static void setKartaDAO(KartaDAO kartaDAO) {
		KartaUI.kartaDAO = kartaDAO;
	}

	public static void NovaKarta() {
		String broj = Konzola.ocitajString("Unesite broj voza: ");
		Voz voz = VozUI.pretragaVoza(broj);
		if (voz == null) {
			return;
		} else if (voz.brojSlobodnihMesta() <= 0) {
			System.out.println("Voz pod rednim brojem: " + broj + " je popunjen!");
			return;
		} else if (voz.getDatumPolaska().compareTo(LocalDateTime.now()) >= 0) {
			System.out.println("Voz je vec posao!");
			return;
		}

		int razred = Konzola.ocitajInt("Unesite razred: ");
		if (razred != 1 && razred != 2) {
			System.out.println("Razred mora da bude 1 ili 2");
			return;
		}
		String kupac = "";
		while (kupac.equals("")) {
			kupac = Konzola.ocitajString("Unesite ime kupca: ");
		}
		LocalDateTime datum = LocalDateTime.now();
		Karta karta = new Karta(datum, kupac, razred, voz);
		voz.addKartu(karta);
		try {
			kartaDAO.add(karta);
			System.out.println("Uspesna kupovina!");
		} catch (Exception e) {
			System.out.println("Dogodila se greska!");
			e.printStackTrace();
		}

	}
}
