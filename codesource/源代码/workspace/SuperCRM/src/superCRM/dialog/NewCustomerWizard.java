package superCRM.dialog;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchWindow;

import superCRM.model.IContactSerivce;
import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.ContactEO;
import superCRM.pojos.CustomerEO;

public class NewCustomerWizard extends Wizard {
	/** 新建客户页面 */
	private NewCustomerWizardPage newCustomerPage;

	/** 新建联系人页面 */
	private NewContactWizardPage newContactPage;

	/** 构造方法,初始化页面 */
	public NewCustomerWizard(IWorkbenchWindow window) {
		newCustomerPage = new NewCustomerWizardPage();
		this.addPage(newCustomerPage);
		newContactPage = new NewContactWizardPage();
		this.addPage(newContactPage);
		this.setWindowTitle("新建客户向导");
	}

	/** 单击完成按钮时 */
	public boolean performFinish() {
		/** 获得新建客户页面输入的客户对象 */
		CustomerEO customer = newCustomerPage.getCustomer();
		/** 如果客户对象不为null */
		if (customer != null) {
			/** 调用业务层,保存客户到数据库中 */
			ICustomerSerivce customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
			CustomerEO c = customerSerivce.addCustomer(customer);
			/** 获得新建联系人页面的联系人对象 */
			ContactEO contact = newContactPage.getContact();
			/** 如果对象为 null 或客户对象为null */
			if (c == null || contact == null)
				return true;
			/** 调用业务层,保存该联系人到数据库 */
			IContactSerivce contactSerivce = SuperFactory.getSuperApplication().getContactSerivce();
			contact.setCustomerId(c.getId());
			contactSerivce.addContact(contact);
		}
		return true;
	}

}
