package superCRM.intro;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import superCRM.SuperCRMPlugin;
import superCRM.business.SuperContext;
import superCRM.dialog.LoginDialog;
import superCRM.preferences.PreferenceConstants;

/**
 * 该类为主程序的入口类
 */
public class Application implements IPlatformRunnable {

	/**
	 * IPlatformRunnable接口中的方法
	 * 
	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
	 */
	public Object run(Object args) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
		/**获得当前用户的上下对象*/
			SuperContext context = SuperContext.getInstance();
		/**调用login方法验证用户，如果用户名密码不正确则退出程序*/
			if (!login(context))
				return IPlatformRunnable.EXIT_OK;
		/**验证成功后，创建住程序窗口*/
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IPlatformRunnable.EXIT_RESTART;
			}
			return IPlatformRunnable.EXIT_OK;
		} finally {
			display.dispose();
		}
	}
	/**
	 * 判断用户是否已登录
	 * 
	 * @param context 保存登录信息的上下文对象
	 * @return true表示已经登录
	 */
	private boolean login(SuperContext context) {
		/**获得首选项中对登录的设置*/
		IPreferenceStore store = SuperCRMPlugin.getDefault().getPreferenceStore();
		
		/**如果首选项设置为自动登陆，则不需要登录*/
		if ( store.getBoolean(PreferenceConstants.P_AUTO_LOGIN)){
			context.setLogin( true );
		/**否则，弹出登陆对话框*/
		}else {
			LoginDialog loginDialog = new LoginDialog(null);
		/**如果输入了正确的用户名和密码，则设置登陆成功*/
			if (loginDialog.open() == Window.OK)
				context.setLogin(true);		
		}
		return context.isLogin();
	}
}
