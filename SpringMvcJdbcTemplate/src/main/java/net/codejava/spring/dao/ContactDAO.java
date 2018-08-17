package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Contact;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface ContactDAO {
	
	public void saveOrUpdate(Contact contact);
	
	public void delete(String contactEmail);
	
//	public Contact get(String contactEmail);
	
	public List<Contact> list();

//	Contact get(Contact contact);

	Contact get(String contactEmail);

	public void update(Contact contact);
}
