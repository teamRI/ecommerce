package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {

	public Client addClient(Client cl);
	public Client upDateClient(Client cl);
	public int deleteClient(Client cl);
	public Client getClient(Client cl);
	public int isExist(Client c);
}
