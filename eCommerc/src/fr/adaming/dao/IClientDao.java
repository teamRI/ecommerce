package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {

	public Client addClient(Client cl);
	public Client upDateClient(Client cl);
	public int deleteClient(Client cl);
	public Client getClient(Client cl);

}
