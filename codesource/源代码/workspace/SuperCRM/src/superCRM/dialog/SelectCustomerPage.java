package superCRM.dialog;

import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.CustomerEO;
import superCRM.table.TableContentProvider;
import superCRM.table.TableLabelProvider;

public class SelectCustomerPage extends WizardPage {
	/** 页面名称 */
	public static final String SELECT_CUSTOMER_PAGE = "SELECT_CUSTOMER";

	/** 客户列表的标题 */
	public static final String[] COLUMN_NAME = { "ID号", "客户名称", "网址", "客户类别", "公司人数" };

	/** 带选择框的表格 */
	private CheckboxTableViewer viewer;

	/** 表格数据 */
	public List data;

	/** 客户服务对象 */
	private ICustomerSerivce customerSerivce;

	/** 客户对象 */
	private CustomerEO cusotmer;

	public SelectCustomerPage() {
		super(SELECT_CUSTOMER_PAGE, "新建客户", null);
		this.setTitle("选择客户");
		this.setDescription("请选择客户的详细信息：");
		this.setPageComplete(false);
		customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
		data = customerSerivce.getAllCustomers();
	}

	public void createControl(Composite parent) {
		/** 设置面板布局 */
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		/** 初始化表数据 */
		viewer = CheckboxTableViewer.newCheckList(composite, SWT.FULL_SELECTION | SWT.SINGLE);
		for (int i = 0; i < COLUMN_NAME.length; i++) {
			new TableColumn(viewer.getTable(), SWT.LEFT).setText(COLUMN_NAME[i]);
			viewer.getTable().getColumn(i).pack();
		}
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new TableContentProvider());
		viewer.setLabelProvider(new TableLabelProvider());
		viewer.setInput(data);
		/** 注册表格单击监听器 */
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			/** 当单击表格中一行时 */
			public void selectionChanged(SelectionChangedEvent event) {
				/** 获得表格选中的行 */
				Object[] checkedObj = viewer.getCheckedElements();
				/** 如果不是选择了一个客户,则提示错误 */
				if (checkedObj.length != 1) {
					setErrorMessage("请选择一个客户");
					setPageComplete(false);
					return;
				}
				/** 设置下一步可用状态 */
				setErrorMessage(null);
				setPageComplete(true);
				/** 将选中的客户对象赋值给该页面的客户对象 */
				cusotmer = (CustomerEO) checkedObj[0];
			}

		});
		setControl(composite);
	}

	/** 获得该页面的客户对象 */
	public CustomerEO getCusotmer() {
		return cusotmer;
	}

	/** 设置该页面的客户对象 */
	public void setCusotmer(CustomerEO cusotmer) {
		this.cusotmer = cusotmer;
	}

}
