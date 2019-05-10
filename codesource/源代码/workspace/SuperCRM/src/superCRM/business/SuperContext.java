package superCRM.business;

public class SuperContext {

	/** 构造方法为private */
	private SuperContext() {
	}

	/** 单例模式的应用 */
	private static SuperContext context = null;

	public static SuperContext getInstance() {
		if (context == null)
			context = new SuperContext();
		return context;
	}

	/** 保存登录状态属性 */
	private boolean bLogin = false;

	/** 是否已登录 */
	public boolean isLogin() {
		return bLogin;
	}

	/** 设置登录状态 */
	public void setLogin(boolean login) {
		bLogin = login;
	}

}
