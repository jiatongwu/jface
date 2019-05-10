package superCRM.business;

import java.util.List;

import superCRM.dao.DaoFactory;
import superCRM.dao.IContactDao;
import superCRM.model.IContactSerivce;
import superCRM.pojos.ContactEO;

public class ContactSerivce implements IContactSerivce {
	private IContactDao contactDao ;
	public ContactSerivce(){
		DaoFactory daoFacory = new DaoFactory();
		contactDao = daoFacory.getContactDao(); 
	}
	public ContactEO addContact(ContactEO contact) {
		return contactDao.addContact(contact);
	}

	public ContactEO getContact(int id) {
		return contactDao.findById(id);
	}

	public List getContacts(String keywords) {
		return contactDao.findBykeywords(keywords);
	}

	public List getAllContacts() {
		return contactDao.getAllContacts();
	}

}
