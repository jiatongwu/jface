package cn.xvkang;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cn.xvkang.action.ExitAction;
import cn.xvkang.compoent.PageAbleTableComposite;

public class Main extends ApplicationWindow {

	private ExitAction exitAction;
	private StatusLineManager statusLineManager;

	public Main() {
		super(null);
		this.addMenuBar();
		//this.addToolBar(SWT.FLAT);
		//this.addStatusLine();
		//statusLineManager = this.getStatusLineManager();		
	}

	@SuppressWarnings("unused")
	@Override
	protected Control createContents(Composite parent) {
		// text = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		// text.addModifyListener(new ModifyListener() {
		// @Override
		// public void modifyText(ModifyEvent arg0) {
		// }
		// });

		// Button button=new Button(parent, SWT.CENTER);
		// button.setText("press");
		//Composite control = (Composite) statusLineManager.getControl();
		//Text t = new Text(control, SWT.CENTER);
		//t.setText("当前用户");
		
		
		//总的主体部分
//		GridLayout gridLayout=new GridLayout();
//		gridLayout.numColumns=1;
		
//		Composite content=new Composite(parent, SWT.NONE);
//		content.setLayout(gridLayout);
		
		//		Button button=new Button(content, SWT.NONE);
		//		button.setText("点击");
		//搜索列表带分页部分
//		SearchTableListPageAbleComposite searchTableListPageAbleComposite=new SearchTableListPageAbleComposite(content, SWT.BORDER);
//		GridData griddata=new GridData();
//		griddata.horizontalAlignment=GridData.FILL;
//		griddata.grabExcessHorizontalSpace=true;
//		searchTableListPageAbleComposite.setLayoutData(griddata);
//		
//		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
//		searchTableListPageAbleComposite.setLayout(fillLayout);
		
		parent.setLayout(new FillLayout());
		PageAbleTableComposite pageAbleTableComposite=new PageAbleTableComposite(parent, parent.getShell(),SWT.NONE);
		
		
		
		parent.pack();
		return parent;
	}

	/**
	 * this.addMenuBar();后会调用此处
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuBar = new MenuManager();
		MenuManager fileMenu = new MenuManager("文件(&F)");
		MenuManager helpMenu = new MenuManager("帮助(&F)");

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		// 每个菜单下面添加很多个action
		// fileMenu.add(new Action());

		// 退出菜单项
		exitAction = new ExitAction(this);
		fileMenu.add(exitAction);

		return menuBar;

	}

	/**
	 * this.addToolBar();后会调用此处
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBar = new ToolBarManager(style);

		toolBar.add(exitAction);

		return toolBar;
	}

	/**
	 * this.addStatusLine();后会调用此处
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		 statusLineManager = new StatusLineManager();

		// statusLineManager.add(exitAction);

		return statusLineManager;
	}

	@Override
	protected StatusLineManager getStatusLineManager() {
		return super.getStatusLineManager();
	}

	@Override
	protected void configureShell(Shell shell) {

		super.configureShell(shell);
		shell.setText("window");
		// 设置最大化
		shell.setMaximized(true);
		shell.setMinimumSize(1024, 768);

	}

	public static void main(String[] args) {

		Main main = new Main();
		main.setBlockOnOpen(true);
		main.open();

		Display.getCurrent().dispose();

	}

}
