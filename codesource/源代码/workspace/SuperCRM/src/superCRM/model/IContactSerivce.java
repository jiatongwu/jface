package superCRM.model;

import java.util.List;
import superCRM.pojos.ContactEO;

/**有关对联系人管理的服务*/
public interface IContactSerivce {
	
	/**添加一个联系人*/
	public ContactEO addContact ( ContactEO contact );
	
	/**根据ID号获得该联系人的信息*/
	public ContactEO getContact ( int id );
	
	/**根据关键字获得查询结构的联系人*/
	public List getContacts ( String keywords );
	
	/**获得所有的联系人*/
	public List getAllContacts ();
}
