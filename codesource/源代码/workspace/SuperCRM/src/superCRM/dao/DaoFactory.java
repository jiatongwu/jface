package superCRM.dao;

public class DaoFactory {

	/** 定义数据库类型的常量 */
	public static final String MYSQL = "mysql";

	public static final String ORACLE = "oracle";

	public static final String SQLSERVER = "sql_server";

	/** 数据库的类型 */
	private String dbType = MYSQL;

	private ICustomerDao customerDao;

	private IContactDao contactDao;

	/** 构造方法 */
	public DaoFactory() {
		/** 如果数据库类型为mysql */
		if (dbType.equals(MYSQL)) {
			customerDao = new CustomerDaoMysqlImpl();
			contactDao = new ContactDaoMysqlImpl();
		}
	}

	/** 一些getter和setter方法 */
	public IContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(IContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

}
