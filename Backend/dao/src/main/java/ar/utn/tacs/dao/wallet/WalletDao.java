package ar.utn.tacs.dao.wallet;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.User;

public interface WalletDao extends GenericDao {

	public User getUserById(int userId);

	public User validateNickAndPass(String nick, String pass);
}
