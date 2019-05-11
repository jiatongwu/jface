package superCRM.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import superCRM.pojos.CustomerEO;

;

public class NewCustomerWizardPage extends WizardPage {
	/** 页面名称 */
	public static final String NEW_CUSTOMER_PAGE = "CUSTOMER";

	/** 客户名称 */
	private Text displayName;

	/** 网址 */
	private Text website;

	/** 客户分类 */
	private Combo category;

	/** 公司人数 */
	private Combo number;

	private CustomerEO customer;

	public NewCustomerWizardPage() {
		super(NEW_CUSTOMER_PAGE, "新建客户", null);
		this.setTitle("新建客户");
		this.setDescription("请输入客户的详细信息：");
	}

	public void createControl(Composite parent) {
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
		setControl(composite);
	}

	/** 获得用户输入的客户信息 */
	public CustomerEO getCustomer() {
		if (!displayName.getText().equals("")) {
			customer = new CustomerEO();
			customer.setDisplayName(displayName.getText());
			customer.setWebSite(website.getText());
			customer.setCategory(category.getText());
			customer.setNumberEmployee(number.getText());
		}
		return customer;
	}

}
