package superCRM.intro;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import superCRM.action.ActionManager;
import superCRM.views.ContactSummaryView;
import superCRM.views.CustomerSummaryView;
import superCRM.views.NavView;
import superCRM.views.QuickNewContactView;
import superCRM.views.QuickNewCustomerView;
import superCRM.views.SearchView;

/** 窗口的菜单条类 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	/** 新建菜单的菜单项 */
	private IWorkbenchAction newCustomerAction;

	private IWorkbenchAction newContactAction;

	private IWorkbenchAction exitAction;

	/** 查看菜单的菜单项 */
	private IWorkbenchAction viewNavAction;

	private IWorkbenchAction viewSearchAction;

	private IWorkbenchAction viewFastNewCustomerAction;

	private IWorkbenchAction viewFastNewCintactAction;

	private IWorkbenchAction viewCustomerAction;

	private IWorkbenchAction viewContactAction;

	/** 帮助菜单的菜单项 */
	private IWorkbenchAction introAction;

	private IWorkbenchAction helpAction;

	private IWorkbenchAction aboutAction;

	private IWorkbenchAction preferenceAction;

	/** 构造方法 */
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		/** 新建客户操作 */
		newCustomerAction = ActionManager.createNewCustomerAction(window);
		register(newCustomerAction);
		
		/** 新建联系人操作 */
		newContactAction = ActionManager.createNewContactAction(window);
		register(newContactAction);
		
		/**退出操作*/
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);

		/**查看导航视图操作*/
		viewNavAction = ActionManager.createShowViewAction(window, NavView.ID);
		register(viewNavAction);
		
		/**查看搜索视图操作*/
		viewSearchAction = ActionManager.createShowViewAction(window, SearchView.ID);
		register(viewSearchAction);
		
		/**查看快速新建客户视图操作*/
		viewFastNewCustomerAction = ActionManager.createShowViewAction(window, QuickNewCustomerView.ID);
		register(viewFastNewCustomerAction);
		
		/**查看快速新建联系人视图操作*/
		viewFastNewCintactAction = ActionManager.createShowViewAction(window, QuickNewContactView.ID);
		register(viewFastNewCintactAction);
		
		/**查看客户列表视图操作*/
		viewCustomerAction = ActionManager.createShowViewAction(window, CustomerSummaryView.ID);
		register(viewCustomerAction);
		
		/**查看联系人列表视图操作*/
		viewContactAction = ActionManager.createShowViewAction(window, ContactSummaryView.ID);
		register(viewContactAction);

		/**打开欢迎视图操作*/
		introAction = ActionFactory.INTRO.create(window);
		register(introAction);
		
		/**打开帮助窗口操作*/
		helpAction = ActionFactory.HELP_CONTENTS.create(window);
		register(helpAction);
		
		/**打开关于窗口操作*/
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);

		/**打开首选项操作*/
		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);
	}
	
	/**
	 * 创建菜单条,并添加菜单项
	 */
	protected void fillMenuBar(IMenuManager menuBar) {

		MenuManager systemMenu = new MenuManager("新建(&N)");
		systemMenu.add(newCustomerAction);
		systemMenu.add(newContactAction);
		systemMenu.add(new Separator());
		systemMenu.add(exitAction);
		menuBar.add(systemMenu);

		MenuManager viewMenu = new MenuManager("查看(&V)");
		viewMenu.add(viewNavAction);
		viewMenu.add(viewSearchAction);
		viewMenu.add(new Separator());
		viewMenu.add(viewFastNewCustomerAction);
		viewMenu.add(viewFastNewCintactAction);
		viewMenu.add(new Separator());
		viewMenu.add(viewCustomerAction);
		viewMenu.add(viewContactAction);
		menuBar.add(viewMenu);

		MenuManager helpMenu = new MenuManager("帮助(&H)", IWorkbenchActionConstants.M_HELP);
		helpMenu.add(introAction);
		helpMenu.add(helpAction);
		helpMenu.add(aboutAction);
		helpMenu.add(preferenceAction);
		menuBar.add(helpMenu);
	}
	/**
	 * 创建工具条,并添加工具按钮
	 */
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager barManager = new ToolBarManager(coolBar.getStyle());
		barManager.add(newCustomerAction);
		barManager.add(newContactAction);
		barManager.add(new Separator());
		barManager.add(viewNavAction);
		barManager.add(viewSearchAction);
		
		barManager.add(viewFastNewCustomerAction);
		barManager.add(viewFastNewCintactAction);
		
		barManager.add(viewCustomerAction);
		barManager.add(viewContactAction);
		coolBar.add(barManager);
	}
	/**
	 * 添加系统托盘所使用的操作项
	 * 
	 * @param trayMenu
	 */
	public void fillTrayItem(MenuManager trayMenu) {
		trayMenu.add(aboutAction);
		trayMenu.add(introAction);
		trayMenu.add(new Separator());
		trayMenu.add(exitAction);

	}
}
