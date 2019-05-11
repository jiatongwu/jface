package superCRM.views;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import superCRM.editor.CustomerDetailEditor;
import superCRM.editor.CustomerDetailInput;
import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.CustomerEO;
import superCRM.table.TableContentProvider;
import superCRM.table.TableLabelProvider;

public class CustomerSummaryView extends ViewPart {
	/** 该视图的ID */
	public static final String ID = "superCRM.views.CustomerSummaryView";

	/** 表头数据 */
	public static final String[] COLUMN_NAME = { "ID号", "客户名称", "网址", "客户类别", "公司人数" };

	/** 表中的数据 */
	public List data;

	/** 表对象 */
	private TableViewer viewer;

	/** 客户服务对象 */
	private ICustomerSerivce customerSerivce;

	/** 客户详细编辑器输入对象 */
	private CustomerDetailInput customerDetailInput;

	public CustomerSummaryView() {
		super();
		/** 获得客户服务对象 */
		customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
		/** 查询所有的客户数据 */
		data = customerSerivce.getAllCustomers();
	}

	/** 创建视图的界面 */
	public void createPartControl(Composite parent) {
		/** 初始化表格 */
		viewer = new TableViewer(parent, SWT.FULL_SELECTION);
		for (int i = 0; i < COLUMN_NAME.length; i++) {
			new TableColumn(viewer.getTable(), SWT.LEFT).setText(COLUMN_NAME[i]);
			viewer.getTable().getColumn(i).pack();
		}
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		/** 设置表格的内容器 */
		viewer.setContentProvider(new TableContentProvider());
		/** 设置表格的标签器 */
		viewer.setLabelProvider(new TableLabelProvider());
		/** 设置表格的数据 */
		viewer.setInput(data);
		/** 当双击表格时 */
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				/** 打开客户详细编辑器 */
				StructuredSelection select = (StructuredSelection) event.getSelection();
				/** 获得当前选中的客户 */
				CustomerEO c = (CustomerEO) select.getFirstElement();
				/** 创建客户详细编辑器输入对象 */
				customerDetailInput = new CustomerDetailInput(c);
				/** 打开客户详细编辑器 */
				IWorkbenchPage page = getViewSite().getWorkbenchWindow().getActivePage();
				try {
					page.openEditor(customerDetailInput, CustomerDetailEditor.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}

		});
		/** 将该表格注册为选择服务提供者 */
		this.getSite().setSelectionProvider(viewer);

	}

	public void setFocus() {
		viewer.getTable().setFocus();
	}

	public List getData() {
		return data;
	}

	/** 设置表格数据 */
	public void setData(List data) {
		this.data = data;
		viewer.setInput(this.data);
		viewer.refresh();
	}

	/** 刷新表格数据 */
	public void refreshData() {
		data = customerSerivce.getAllCustomers();
		viewer.setInput(data);
		viewer.refresh();
	}

}
