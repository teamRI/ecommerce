package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext(unitName = "eCommerc")
	private EntityManager em;
	
	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public Client upDateClient(Client cl) {
		em.find(Client.class, cl.getId());
		em.merge(cl);
		return cl;
	}

	@Override
	public int deleteClient(Client cl) {
		try{
			Client clOut=em.find(Client.class, cl.getId());
			em.remove(clOut);
			return 1;
			}catch (Exception ex){
				
			}
		return 0;
	}

	@Override
	public Client getClient(Client cl) {
		
		return em.find(Client.class, cl.getId());
	}

}
