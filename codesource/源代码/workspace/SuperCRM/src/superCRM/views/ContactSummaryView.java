package superCRM.views;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import superCRM.model.IContactSerivce;
import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.CustomerEO;
import superCRM.table.TableContentProvider;
import superCRM.table.TableLabelProvider;

public class ContactSummaryView extends ViewPart implements ISelectionListener {
	/** 该视图的ID */
	public static final String ID = "superCRM.views.ContactSummaryView";

	/** 表头数据 */
	public static final String[] COLUMN_NAME = { "ID号", "姓名", "客户名称", "类别", "性别", "出生日期", "职位" };

	/** 当前选中的对象 */
	private IStructuredSelection currentSelection;

	/** 联系人数据所属的客户 */
	private CustomerEO customer;

	/** 联系人数据对象 */
	public List data;

	/** 联系人表对象 */
	private TableViewer viewer;

	/** 客户服务 */
	private ICustomerSerivce customerSerivce;

	/** 联系人服务 */
	private IContactSerivce contactSerivce;

	public ContactSummaryView() {
		super();
		/** 创建客户和联系人服务对象 */
		customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
		contactSerivce = SuperFactory.getSuperApplication().getContactSerivce();
	}

	public void createPartControl(Composite parent) {
		/** 注册选择服务对象 */
		this.getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		/** 初始化联系人表格 */
		viewer = new TableViewer(parent, SWT.FULL_SELECTION);
		for (int i = 0; i < COLUMN_NAME.length; i++) {
			new TableColumn(viewer.getTable(), SWT.LEFT).setText(COLUMN_NAME[i]);
			viewer.getTable().getColumn(i).pack();
		}
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new TableContentProvider());
		viewer.setLabelProvider(new TableLabelProvider());
		/** 默认情况下设置显示所有联系人 */
		data = contactSerivce.getAllContacts();
		viewer.setInput(data);
	}

	public void setFocus() {
		viewer.getTable().setFocus();
	}

	/** 当单击客户记录时 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			/** 获得当前选中的客户对象 */
			currentSelection = (IStructuredSelection) selection;
			if (currentSelection.getFirstElement() instanceof CustomerEO) {
				customer = (CustomerEO) currentSelection.getFirstElement();
				/** 重新设置联系人列表中的数据 */
				this.setData(customerSerivce.getContacts(customer));
			}
		}
	}

	public void dispose() {
		super.dispose();
		this.getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
	}

	/** 设置数据 */
	public void setData(List data) {
		this.data = data;
		viewer.setInput(this.data);
		viewer.refresh();
	}

	/** 更新数据 */
	public void refreshData() {
		data = contactSerivce.getAllContacts();
		viewer.setInput(data);
		viewer.refresh();
	}

}
