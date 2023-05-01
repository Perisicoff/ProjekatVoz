package voz.DAO;

import java.util.Collection;

import voz.model.Voz;

public interface VozDAO {

	public Voz get(String broj) throws Exception;
	public Collection<Voz> getAll() throws Exception;
}
