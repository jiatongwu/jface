package superCRM.dialog;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import superCRM.pojos.ContactEO;

public class NewContactWizardPage extends WizardPage {
	/** 页面名称 */
	public static final String NEW_CONTACT_PAGE = "CONTACT";

	/** 姓名 */
	private Text displayName;

	/** 类别 */
	private Combo category;

	/** 性别 */
	private Combo sex;

	/** 出生日期 */
	private Text birthDate;

	/** 职位 */
	private Combo jobTitle;

	/** 联系人对象 */
	private ContactEO contact;

	public NewContactWizardPage() {
		super(NEW_CONTACT_PAGE, "新建联系人", null);
		this.setTitle("新建联系人");
		this.setDescription("请输入联系人的详细信息：");
	}

	public void createControl(Composite parent) {
		/** 设置面板布局 */
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		/** 姓名 */
		new Label(composite, SWT.NONE).setText("姓名：");
		displayName = new Text(composite, SWT.BORDER);
		displayName.setLayoutData(data);
		/** 类别 */
		new Label(composite, SWT.NONE).setText("类别：");
		category = new Combo(composite, SWT.BORDER);
		category.setItems(new String[] { "Friend", "Family", "Contact" });
		category.select(0);
		data = new GridData(GridData.FILL_HORIZONTAL);
		category.setLayoutData(data);
		/** 性别 */
		new Label(composite, SWT.NONE).setText("性别：");
		sex = new Combo(composite, SWT.BORDER);
		sex.setItems(new String[] { "Female", "Male" });
		sex.select(0);
		data = new GridData(GridData.FILL_HORIZONTAL);
		sex.setLayoutData(data);
		/** 出生日期 */
		new Label(composite, SWT.NONE).setText("出生日期:");
		birthDate = new Text(composite, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		birthDate.setLayoutData(data);
		/** 职位： */
		new Label(composite, SWT.NONE).setText("职位：");
		jobTitle = new Combo(composite, SWT.BORDER);
		jobTitle.setItems(new String[] { "CEO", "CTO", "CFO" });
		jobTitle.select(0);
		data = new GridData(GridData.FILL_HORIZONTAL);
		jobTitle.setLayoutData(data);
		setControl(composite);
	}

	/** 获得用户输入的联系人信息 */
	public ContactEO getContact() {
		if (!displayName.getText().equals("")) {
			contact = new ContactEO();
			contact.setDisplayName(displayName.getText());
			contact.setCategory(category.getText());
			contact.setSex(sex.getText());
			contact.setBirthDate(birthDate.getText());
			contact.setJobTitle(jobTitle.getText());
		}
		return contact;
	}

}
