package superCRM.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.CustomerEO;

public class QuickNewCustomerView extends ViewPart {
	public static final String ID = "superCRM.views.QuickNewCustomerView";

	/** 客户名称 */
	private Text displayName;

	/** 网址 */
	private Text website;

	/** 客户分类 */
	private Combo category;

	/** 公司人数 */
	private Combo number;

	public QuickNewCustomerView() {
		super();
	}

	/** 创建视图的界面 */
	public void createPartControl(Composite parent) {
		/** 设置面板布局 */
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		/** 客户名称 */
		new Label(composite, SWT.NONE).setText("客户名称：");
		displayName = new Text(composite, SWT.BORDER);
		displayName.setLayoutData(data);
		/** 网址 */
		new Label(composite, SWT.NONE).setText("网址：");
		website = new Text(composite, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		website.setLayoutData(data);
		/** 客户分类 */
		new Label(composite, SWT.NONE).setText("类别：");
		category = new Combo(composite, SWT.BORDER);
		category.setItems(new String[] { "Customer", "Partener", "Competitor" });
		category.select(0);
		data = new GridData(GridData.FILL_HORIZONTAL);
		category.setLayoutData(data);
		/** 公司人数 */
		new Label(composite, SWT.NONE).setText("公司人数：");
		number = new Combo(composite, SWT.BORDER);
		number.setItems(new String[] { "1-10", "11-50", "51-100", "101-500", "500-" });
		number.select(0);
		data = new GridData(GridData.FILL_HORIZONTAL);
		number.setLayoutData(data);
		/** 添加按钮 */
		Button btAdd = new Button(composite, SWT.PUSH);
		btAdd.setText("添加");
		/** 注册添加按钮监听器 */
		btAdd.addSelectionListener(new SelectionAdapter() {
			/** 当单击添加按钮时 */
			public void widgetSelected(SelectionEvent e) {
				/** 创建客户对象 */
				CustomerEO customer = new CustomerEO();
				/** 分别取出用户输入值,并赋值给客户对象 */
				customer.setDisplayName(displayName.getText());
				customer.setWebSite(website.getText());
				customer.setCategory(category.getText());
				customer.setNumberEmployee(number.getText());
				/** 调用业务层保存客户数据 */
				ICustomerSerivce customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
				customerSerivce.addCustomer(customer);
				/** 查找是否客户列表视图已经打开 */
				IViewPart view = getViewSite().getPage().findView(CustomerSummaryView.ID);
				/** 若没打开,则首先打开客户列表视图 */
				if (view == null) {
					try {
						view = getViewSite().getPage().showView(CustomerSummaryView.ID);
					} catch (PartInitException ee) {
						ee.printStackTrace();
					}
				}
				/** 更新客户列表数据 */
				CustomerSummaryView customerSummaryView = (CustomerSummaryView) view;
				customerSummaryView.refreshData();
			}

		});

	}

	public void setFocus() {
		displayName.setFocus();
	}

}
