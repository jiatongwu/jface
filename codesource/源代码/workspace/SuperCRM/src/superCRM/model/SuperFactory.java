package superCRM.model;

import superCRM.business.RcpApplication;

/**通过单例模式来创建管理整个系统的对象*/
public class SuperFactory {
	
	/**设置为私有类型,不允许实例化*/
	private SuperFactory(){	}
	
	/**定义为静态类型*/
	private static ISuperApplication app = null ;
	
	/**获得该实例*/
	public static ISuperApplication getSuperApplication (){
		if ( app == null){
			app = new RcpApplication();
		}
		return app;
	}
}
