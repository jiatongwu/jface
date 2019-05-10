package superCRM.intro;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import superCRM.SuperCRMPlugin;

/** 工作区窗口类 */

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	/** 系统托盘对象 */
	private TrayItem trayItem;

	/** 系统托盘的图标对象 */
	private Image trayImage;

	/** 程序的菜单条 */
	private ApplicationActionBarAdvisor actionBarAdvisor;

	/** 构造方法 */
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	/** 创建菜单条对象 */
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		actionBarAdvisor = new ApplicationActionBarAdvisor(configurer);
		return actionBarAdvisor;
	}

	/** 打开窗口前调用该方法，对窗口初始化设置 */
	public void preWindowOpen() {
		/** 设置窗口初始化的各种属性 */
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(700, 550));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(false);
		configurer.setTitle("SuperCRM");
		configurer.setShowPerspectiveBar(true);
		configurer.setShowStatusLine(true);

		final IWorkbenchWindow window = getWindowConfigurer().getWindow();
		/** 创建系统托盘 */
		trayItem = initTrayItem(window);
		/** 如果支持系统托盘，则创建托盘的菜单 */
		if (trayItem != null) {
			createPopupMenu(window);
		}

	}

	/**
	 * 创建系统托盘菜单
	 * 
	 * @param window
	 *            工作台窗口对象
	 */
	private void createPopupMenu(final IWorkbenchWindow window) {
		trayItem.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				/** 通过MenuManager对象来创建菜单 */
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());

				/**
				 * 调用fillTrayItem方法创建系统托盘对象，可以直接利用菜单栏中的操作 而不需要，重新创建操作
				 */
				actionBarAdvisor.fillTrayItem(trayMenu);
				menu.setVisible(true);
			}
		});

	}

	/**
	 * 初始化系统托盘对象
	 * 
	 * @param window
	 *            工作台窗口对象
	 * @return 该程序所对应的系统托盘对象
	 */
	private TrayItem initTrayItem(IWorkbenchWindow window) {
		final Tray tray = Display.getCurrent().getSystemTray();
		if (tray == null)
			return null;
		/** 创建系统托盘并设置系统托盘的各种属性 */
		TrayItem trayItem = new TrayItem(tray, SWT.NONE);
		trayImage = SuperCRMPlugin.getImageDescriptor("icons/logo.gif").createImage();
		trayItem.setImage(trayImage);
		trayItem.setToolTipText("SuperCRM");
		return trayItem;
	}

	/** 释放窗口，释放系统托盘 */
	public void dispose() {
		if (trayImage != null) {
			trayImage.dispose();
			trayItem.dispose();
		}
	}

}
