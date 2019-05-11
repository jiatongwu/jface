package superCRM.table;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.ContactEO;
import superCRM.pojos.CustomerEO;

public class TableLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/** 设置表格显示的数据 */
	public String getColumnText(Object element, int columnIndex) {
		/** 如果输入的是客户对象,返回相应的数据值 */
		if (element instanceof CustomerEO) {
			CustomerEO c = (CustomerEO) element;
			if (columnIndex == 0)
				return c.getId() + "";
			else if (columnIndex == 1)
				return c.getDisplayName();
			else if (columnIndex == 2)
				return c.getWebSite();
			else if (columnIndex == 3)
				return c.getCategory();
			else if (columnIndex == 4)
				return c.getNumberEmployee();
			/** 如果输入的是联系人对象,返回相应的数据值 */
		} else if (element instanceof ContactEO) {
			ContactEO c = (ContactEO) element;

			if (columnIndex == 0)
				return c.getId() + "";
			else if (columnIndex == 1)
				return c.getDisplayName();
			else if (columnIndex == 2) {
				/** 如果是客户的id,要查询该客户的名称,并将名称显示 */
				ICustomerSerivce customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
				CustomerEO customer = customerSerivce.getCustomer(c.getCustomerId());
				if (customer != null)
					return customer.getDisplayName();
				return "";
			} else if (columnIndex == 3)
				return c.getCategory();
			else if (columnIndex == 4)
				return c.getSex();
			else if (columnIndex == 5)
				return c.getBirthDate();
			else if (columnIndex == 6)
				return c.getJobTitle();
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {

	}

}
