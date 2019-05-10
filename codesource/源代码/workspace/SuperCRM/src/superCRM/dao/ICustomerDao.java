package superCRM.dao;

import java.util.List;
import superCRM.pojos.CustomerEO;

/** 客户数据库访问DAO */
public interface ICustomerDao {

	/** 客户数据库访问DAO */
	public CustomerEO findById(int id);

	/** 根据关键字查找获得查询结果 */
	public List findBykeywords(String keywords);

	/** 获得所有的客户记录 */
	public List getAllCustomers();

	/** 获得该客户的联系人 */
	public List getContacts(CustomerEO customer);

	/** 根据sql语句获得客户记录 */
	public List getCustomerResult(String sql);

	/** 添加客户 */
	public CustomerEO addCustomer(CustomerEO c);

	/** 更新客户 */
	public void updateCustomer(CustomerEO c);
}
