package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService{

	@EJB
	private IClientDao clDao;
	
	@Override
	public Client addClient(Client cl) {
		Client clOut=clDao.addClient(cl);
		return clOut;
	}

	@Override
	public Client upDateClient(Client cl) {
		
		return clDao.upDateClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {
		
		return clDao.deleteClient(cl);
	}

	@Override
	public Client getClient(Client cl) {

		return clDao.getClient(cl);
	}

	
}
