package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext(unitName = "pu_tp")
	private EntityManager em;
	
	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public Client upDateClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
