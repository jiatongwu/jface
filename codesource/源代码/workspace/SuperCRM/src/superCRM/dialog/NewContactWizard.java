package superCRM.dialog;

import org.eclipse.jface.wizard.Wizard;

import superCRM.model.IContactSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.ContactEO;
import superCRM.pojos.CustomerEO;

public class NewContactWizard extends Wizard {
	/** 选择客户页面 */
	private SelectCustomerPage selectCustomerPage;

	/** 新建联系人页面 */
	private NewContactWizardPage newContactPage;

	/** 构造方法,初始化页面 */
	public NewContactWizard() {
		selectCustomerPage = new SelectCustomerPage();
		this.addPage(selectCustomerPage);
		newContactPage = new NewContactWizardPage();
		this.addPage(newContactPage);
		this.setWindowTitle("新建联系人向导");
	}

	/** 单击完成按钮时 */
	public boolean performFinish() {
		/** 获得新建联系人页面的联系人对象 */
		ContactEO contact = newContactPage.getContact();
		/** 如果联系人对象为null */
		if (contact == null)
			return true;
		/** 获得选择客户页面所选择的客户对象 */
		CustomerEO customer = selectCustomerPage.getCusotmer();
		/** 设置联系人的客户ID */
		contact.setCustomerId(customer.getId());
		/** 调用业务层,保存联系人信息 */
		IContactSerivce contactSerivce = SuperFactory.getSuperApplication().getContactSerivce();
		contactSerivce.addContact(contact);
		return true;
	}

}
