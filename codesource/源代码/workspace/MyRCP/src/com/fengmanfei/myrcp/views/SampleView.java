package com.fengmanfei.myrcp.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.fengmanfei.myrcp.actions.MyAction;


public class SampleView extends ViewPart {
	//该视图的id,在plugin.xml文件中定义,通常使用字符串常量的方式
	public static final String ID = "com.fengmanfei.myrcp.views.SampleView";
	private TableViewer viewer;//视图中显示的表格对象
	//操作对象
	private Action action1;
	private Action action2;
	//构造方法
	public SampleView() {
	}
	//为父类中的抽象方法，创建视图中的各种控件
	public void createPartControl(Composite parent) {
		//创建一个表格对象
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		makeActions();//创建操作对象
		hookDoubleClickAction();//添加表格双击事件
		hookContextMenu();//添加上下文菜单
		contributeToActionBars();//添加视图工具栏操作
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getTable(),"SampleViewContextId");
	}

	private void hookContextMenu() {
		//创建菜单管理器对象
		MenuManager menuMgr = new MenuManager("#PopupMenu");	
		menuMgr.add(action1);
		menuMgr.add(action2);
		menuMgr.add(new MyAction( getSite().getWorkbenchWindow()));
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
		//为列表对象创建上下文菜单
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		//设置菜单
		viewer.getControl().setMenu(menu);
		//注册上下文菜单
		getSite().registerContextMenu(menuMgr, viewer);
		getSite().setSelectionProvider(viewer); 
	}

	private void contributeToActionBars() {
		//获得视图的操作栏对象
		IActionBars bars = getViewSite().getActionBars();
		//添加下拉菜单
		fillLocalPullDown(bars.getMenuManager());
		//添加工具栏
		fillLocalToolBar(bars.getToolBarManager());
	}
	//添加下拉菜单
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}
	
	//添加工具栏
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}
	
	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void hookDoubleClickAction() {
		//注册双击事件监听器
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			//当双击时
			public void doubleClick(DoubleClickEvent event) {
				//获得当前选中的一项
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				//获得要显示内容的视图对象
				IViewPart viewPart =  getSite().getPage().findView(AnotherView.ID);
				//确保找到所要使用的视图
				Assert.isNotNull(viewPart);
				//强制类型转换为另一个视图对象
				AnotherView view = (AnotherView)viewPart;
				//设置该视图中显示的内容
				view.setContent( "你双击了"+(String)obj );
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"样本视图",
			message);
	}
	//父类中的抽象方法，该视图获得焦点时，将焦点设置为表格
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}
}