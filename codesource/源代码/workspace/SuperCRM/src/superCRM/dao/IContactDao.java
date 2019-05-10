package superCRM.dao;

import java.util.List;
import superCRM.pojos.ContactEO;


public interface IContactDao {
	
	public ContactEO findById(int id);
	

	public List findBykeywords(String keywords);
	

	public List getAllContacts();
	public List getContactResult(String sql) ;
	public ContactEO addContact( ContactEO c );
}
