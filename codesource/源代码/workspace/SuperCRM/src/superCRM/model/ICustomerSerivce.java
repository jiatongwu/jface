package superCRM.model;

import java.util.List;
import superCRM.pojos.CustomerEO;

/**有关对客户管理的服务*/
public interface ICustomerSerivce {
	
	/**添加一个客户*/
	public CustomerEO addCustomer ( CustomerEO customer );
	
	/**更新客户信息*/
	public void updateCustomer ( CustomerEO customer );
	
	/**根据客户ID获得客户信息*/
	public CustomerEO getCustomer ( int id );
	
	/**根据关键字获得查询出的客户信息*/
	public List getCustomers ( String keywords );
	
	/**获得所有客户信息*/
	public List getAllCustomers ();
	
	/**获得某一个客户下的联系人*/
	public List getContacts ( CustomerEO customer);
}
