package superCRM.business;

import java.util.List;
import superCRM.dao.DaoFactory;
import superCRM.dao.ICustomerDao;
import superCRM.model.ICustomerSerivce;
import superCRM.pojos.CustomerEO;

public class CustomerSerivce implements ICustomerSerivce {

	/** 数据库访问的 DAO,通过这个对象来实现对数据库的操作 */
	private ICustomerDao customerDao;

	/** 构造方法 */
	public CustomerSerivce() {

		/** 创建一个DaoFactory工厂对象 */
		DaoFactory daoFacory = new DaoFactory();
		/** 通过工厂方法来创建一个ICustomerDao对象 */
		customerDao = daoFacory.getCustomerDao();
	}

	public CustomerEO addCustomer(CustomerEO customer) {
		/** 添加一个客户 */
		return customerDao.addCustomer(customer);
	}

	public CustomerEO getCustomer(int id) {
		/** 查找指定客户 */
		return customerDao.findById(id);
	}

	public List getCustomers(String keywords) {

		/** 如果关键字为空或者为null,则返回所有的客户 */
		if (keywords == null || keywords.equals(""))
			return getAllCustomers();
		/** 根据关键字获得查询客户结果 */
		return customerDao.findBykeywords(keywords);
	}

	public List getAllCustomers() {
		/** 获得所有的客户 */
		return customerDao.getAllCustomers();
	}

	public void updateCustomer(CustomerEO customer) {
		/** 更新客户 */
		customerDao.updateCustomer(customer);

	}

	public List getContacts(CustomerEO customer) {
		/** 获得该客户所属的联系人 */
		return customerDao.getContacts(customer);
	}

}
