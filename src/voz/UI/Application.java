package voz.UI;

import java.sql.Connection;
import java.sql.DriverManager;

import voz.DAO.KartaDAO;
import voz.DAO.VozDAO;
import voz.database.DAO.DatabaseKartaDAO;
import voz.database.DAO.DatabaseVozDAO;
import voz.util.Meni;
import voz.util.Meni.FunkcionalnaStavkaMenija;
import voz.util.Meni.IzlaznaStavkaMenija;
import voz.util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/voz?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		KartaDAO kartaDAO = new DatabaseKartaDAO(conn);
		VozDAO vozDAO = new DatabaseVozDAO(conn);

		KartaUI.setKartaDAO(kartaDAO);
		VozUI.setVozDAO(vozDAO);
		IzvestajUI.setVozDAO(vozDAO);
	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		Meni.pokreni("Voz", new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"), new FunkcionalnaStavkaMenija("Prikaz svih vozova") {

			@Override
			public void izvrsi() { VozUI.prikazSvihVozova();
			}

		}, new FunkcionalnaStavkaMenija("Prikaz voza sa svim kartama") {

			@Override
			public void izvrsi() { VozUI.prikazVozaSaSvimKArtama();
			}

		}, new FunkcionalnaStavkaMenija("Prodaja karte") {

			@Override
			public void izvrsi() { KartaUI.NovaKarta();
			}

		}, new FunkcionalnaStavkaMenija("Izvestaj") {

			@Override
			public void izvrsi() { IzvestajUI.izvestaj();
			}

		} });
	}

}
