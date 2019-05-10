package superCRM.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import superCRM.SuperCRMPlugin;
import superCRM.action.ActionManager;

/** 导航菜单视图 */
public class NavView extends ViewPart {

	/** 导航的树对象 */
	private TreeViewer viewer;

	/** 可以为树添加返回导航操作对象 */
	private DrillDownAdapter drillDownAdapter;

	/** 折叠树操作 */
	private Action collapseAction;

	/** 展开树操作 */
	private Action expandAction;

	/** 双击树结点时操作 */
	private Action doubleClickAction;

	/** 该视图的ID */
	public static final String ID = "superCRM.views.NavView";

	/** 树结点类 */
	class TreeObject implements IAdaptable {
		private String name;

		private String key;

		private TreeParent parent;

		public TreeObject(String name, String key) {
			this.name = name;
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		public String toString() {
			return getName();
		}

		public Object getAdapter(Class key) {
			return null;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	}

	/** 树的父结点类 */
	class TreeParent extends TreeObject {
		private ArrayList children;

		public TreeParent(String name) {
			super(name, "__Parent");
			children = new ArrayList();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	/** 树的内容器 */
	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot == null)
					initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		/** 初始化树中的数据 */
		private void initialize() {

			TreeObject c1 = new TreeObject("客户列表", "CUSTOMER_LIST");
			TreeObject c2 = new TreeObject("添加客户", "CUSTOMER_ADD");
			TreeParent t1 = new TreeParent("客户管理");
			t1.addChild(c1);
			t1.addChild(c2);

			TreeObject l1 = new TreeObject("联系人列表", "CONTACT_LIST");
			TreeObject l2 = new TreeObject("添加联系人", "CONTACT_ADD");
			TreeParent t2 = new TreeParent("联系人管理");
			t2.addChild(l1);
			t2.addChild(l2);

			TreeObject p2 = new TreeObject("首选项设置", "PREF");
			TreeParent t3 = new TreeParent("管理设置");
			t3.addChild(p2);

			invisibleRoot = new TreeParent("");
			invisibleRoot.addChild(t1);
			invisibleRoot.addChild(t2);
			invisibleRoot.addChild(t3);
		}
	}

	/** 树的内容器 */
	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	/** 构造方法 */
	public NavView() {
	}

	public void createPartControl(Composite parent) {
		/** 创建树 */
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		/** 初始化树 */
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(getViewSite());
		/** 创建树中所使用操作对象 */
		makeActions();
		/** 添加上下文菜单 */
		hookContextMenu();
		/** 添加双击事件 */
		hookDoubleClickAction();
		/** 添加到操作条中 */
		contributeToActionBars();
	}

	/** 添加上下文菜单 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				NavView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	/** 添加到操作条中 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	/** 添加下拉菜单项 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(collapseAction);
		manager.add(new Separator());
		manager.add(expandAction);
	}

	/** 添加上下文菜单项 */
	private void fillContextMenu(IMenuManager manager) {
		manager.add(collapseAction);
		manager.add(expandAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/** 添加工具栏工具按钮 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(collapseAction);
		manager.add(expandAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	/** 创建视图所使用的操作对象 */
	private void makeActions() {
		/** 展开全部按钮 */
		collapseAction = new Action() {
			public void run() {
				viewer.collapseAll();
			}
		};
		collapseAction.setText("折叠全部");
		collapseAction.setToolTipText("折叠全部");
		collapseAction.setImageDescriptor(SuperCRMPlugin.getImageDescriptor("icons/collapse.gif"));
		/** 折叠全部按钮 */
		expandAction = new Action() {
			public void run() {
				viewer.expandAll();
			}
		};
		expandAction.setText("展开全部");
		expandAction.setToolTipText("展开全部");
		expandAction.setImageDescriptor(SuperCRMPlugin.getImageDescriptor("icons/expand.gif"));
		/** 双击操作 */
		doubleClickAction = new Action() {
			public void run() {
				/** 获得当前选中的树节点 */
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				/** 如果选中的为TreeParent对象,则返回 */
				if (obj instanceof TreeParent)
					return;
				/** 如果选中的为子节点,则根据key值打开相应的操作 */
				TreeObject object = (TreeObject) obj;
				if (object.getKey().equals("CUSTOMER_LIST")) {
					ActionManager.createShowViewAction(getSite().getWorkbenchWindow(), CustomerSummaryView.ID).run();
				} else if (object.getKey().equals("CUSTOMER_ADD")) {
					ActionManager.createNewCustomerAction(getSite().getWorkbenchWindow()).run();
				} else if (object.getKey().equals("CONTACT_LIST")) {
					ActionManager.createShowViewAction(getSite().getWorkbenchWindow(), ContactSummaryView.ID).run();
				} else if (object.getKey().equals("CONTACT_ADD")) {
					ActionManager.createNewContactAction(getSite().getWorkbenchWindow()).run();
				} else if (object.getKey().equals("PREF")) {
					ActionFactory.PREFERENCES.create(getSite().getWorkbenchWindow()).run();
				}
			}
		};

	}

	/** 双击树事件 */
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}