package voz.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import voz.DAO.VozDAO;
import voz.model.Karta;
import voz.model.Voz;

public class DatabaseVozDAO implements VozDAO {

	private final Connection conn;

	public DatabaseVozDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Voz get(String broj) throws Exception {
		Voz voz = null;
		List<Karta> karte = new ArrayList<>();
		String sql = "SELECT v.id, v.naziv, v.datumIVreme, v.cena, v.brMesta, k.id, k.datumIVreme, k.kupac, k.razred FROM voz.vozovi v LEFT JOIN voz.karte k ON v.id = k.vozId WHERE v.broj = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setString(++param, broj);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vNaziv = rset.getString(++kolona);
					LocalDateTime vDatum = rset.getTimestamp(++kolona).toLocalDateTime();
					double vCena = rset.getDouble(++kolona);
					int vBrMesta = rset.getInt(++kolona);

					voz = new Voz(vId, broj, vNaziv, vDatum, vCena, vBrMesta);

					long kId = rset.getLong(++kolona);
					if (kId != 0) {
						LocalDateTime kDatum = rset.getTimestamp(++kolona).toLocalDateTime();
						String kupac = rset.getString(++kolona);
						int razred = rset.getInt(++kolona);
						Karta karta = new Karta(kId, kDatum, kupac, razred, voz);
						karte.add(karta);
						voz.addSveKarte(karte);
					}

				}

			}
		}

		return voz;
	}
	
	@Override
	public Collection<Voz> getAll() throws Exception {
		Map<Long, Voz> vozovi = new LinkedHashMap<>();

		String sql = "SELECT v.id, v.broj, v.naziv, v.datumIVreme, v.cena, v.brMesta, k.id, k.datumIVreme, k.kupac, k.razred FROM voz.vozovi v LEFT JOIN voz.karte k ON v.id = k.vozId";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					int kolona = 0;
					long vId = rset.getLong(++kolona);
					String vBroj = rset.getString(++kolona);
					String vNaziv = rset.getString(++kolona);
					LocalDateTime vDatum = rset.getTimestamp(++kolona).toLocalDateTime();
					double vCena = rset.getDouble(++kolona);
					int vBrMesta = rset.getInt(++kolona);

					Voz voz = vozovi.get(vId);
					if (voz == null) {
						voz = new Voz(vId, vBroj, vNaziv, vDatum, vCena, vBrMesta);
					}
					long kId = rset.getLong(++kolona);
					if (kId != 0) {
						LocalDateTime kDatum = rset.getTimestamp(++kolona).toLocalDateTime();
						String kupac = rset.getString(++kolona);
						int razred = rset.getInt(++kolona);
						Karta karta = new Karta(kId, kDatum, kupac, razred, voz);
						voz.addKartu(karta);
					}
					vozovi.put(vId, voz);
				}

			}
		}

		return vozovi.values();
	}


}
