package superCRM.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import superCRM.pojos.CustomerEO;
import superCRM.SuperCRMPlugin;

public class CustomerDetailInput implements IEditorInput {

	/** 用户保存输入的客户信息 */
	private CustomerEO customer;

	public CustomerDetailInput(CustomerEO c) {
		this.customer = c;
	}

	/** 以下为接口中的方法 */
	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		return SuperCRMPlugin.getImageDescriptor("icons/customer.gif");
	}

	public String getName() {
		return customer.getDisplayName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return customer.getDisplayName();
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	/** 获得客户对象 */
	public CustomerEO getCustomer() {
		return customer;
	}

	/** 设置客户对象 */
	public void setCustomer(CustomerEO customer) {
		this.customer = customer;
	}

}
