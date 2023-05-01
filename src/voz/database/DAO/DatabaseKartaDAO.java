package voz.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import voz.DAO.KartaDAO;
import voz.model.Karta;

public class DatabaseKartaDAO implements KartaDAO {

	private final Connection conn;

	public DatabaseKartaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Karta karta) throws Exception {

		String sql = "INSERT INTO karte (id, vozId, datumIVreme, kupac, razred) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			int param = 0;
			stmt.setLong(++param, karta.getId());
			stmt.setLong(++param, karta.getVoz().getId());
			stmt.setTimestamp(++param, Timestamp.valueOf(karta.getDatum()));
			stmt.setString(++param, karta.getKupac());
			stmt.setInt(++param, karta.getRazred());
			stmt.executeUpdate();

		}

	}

}
