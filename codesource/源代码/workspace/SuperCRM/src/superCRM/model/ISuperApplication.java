package superCRM.model;

/**定义整个系统的各个服务模块*/
public interface ISuperApplication {
	
	/**客户管理服务模块*/
	public ICustomerSerivce getCustomerSerivce();
	
	/**联系人管理服务模块*/
	public IContactSerivce getContactSerivce();
}
