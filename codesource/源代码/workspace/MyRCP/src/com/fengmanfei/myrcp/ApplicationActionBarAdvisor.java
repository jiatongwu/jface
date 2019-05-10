package com.fengmanfei.myrcp;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private NewAction newAction;//自定义的action
	private IWorkbenchAction exitAction;//退出
	private IWorkbenchAction aboutAction;//关于
	private IWorkbenchAction refAction;
	private IWorkbenchAction helpAction;
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		newAction = new NewAction();//创建action对象
		register(newAction);//父类方法，注册该操作
		exitAction = ActionFactory.QUIT.create(window);//Eclipse内置的退出操作
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);//内置的关于操作
		register(aboutAction);
		refAction = ActionFactory.PREFERENCES.create(window);
		register(refAction);
		helpAction = ActionFactory.HELP_CONTENTS.create(window);
		register(helpAction);
	}
	//覆盖父类中的方法，创建菜单栏
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager codeMenu = new MenuManager("CodeMenu(&C)","fileMenu");
		codeMenu.add(newAction);
		codeMenu.add(aboutAction);
		codeMenu.add(refAction);
		codeMenu.add(exitAction);
		codeMenu.add(helpAction);
		menuBar.add(codeMenu);
	}
	//覆盖父类中的方法，创建工具栏
	protected void fillCoolBar(ICoolBarManager coolBar) {
		  IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		  toolbar.add(helpAction);
		  coolBar.add(toolbar);
	}
	//继承自Action
	class NewAction extends Action {
		NewAction() {
			super("new");
			this.setId("NewAction");
		}

		public void run() {
		}
	}
}
