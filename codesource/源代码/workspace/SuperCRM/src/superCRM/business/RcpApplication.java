package superCRM.business;

import superCRM.model.IContactSerivce;
import superCRM.model.ICustomerSerivce;
import superCRM.model.ISuperApplication;

public class RcpApplication implements ISuperApplication {

	/** 定义客户服务对象 */
	private ICustomerSerivce customerSerivce = null;

	/** 定义联系人服务对象 */
	private IContactSerivce contactSerivce = null;

	/** 获得客户服务对象 */
	public ICustomerSerivce getCustomerSerivce() {
		if (customerSerivce == null) {
			customerSerivce = new CustomerSerivce();
		}
		return customerSerivce;
	}

	/** 获得联系人服务对象 */
	public IContactSerivce getContactSerivce() {
		if (contactSerivce == null) {
			contactSerivce = new ContactSerivce();
		}
		return contactSerivce;
	}
}
