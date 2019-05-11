package superCRM.views;

import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import superCRM.SuperCRMPlugin;
import superCRM.preferences.PreferenceConstants;
import superCRM.model.IContactSerivce;
import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;

public class SearchView extends ViewPart {
	/** 该视图的ID */
	public static final String ID = "superCRM.views.SearchView";

	/** 客户单选按钮 */
	private Button btCustomer;

	/** 联系人单选按钮 */
	private Button btContact;

	/** 关键字文本 */
	private Text keywords;

	public SearchView() {
		super();
	}

	public void createPartControl(Composite parent) {
		/** 设置面板布局 */
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		/** 创建分组面板放置两个单选按钮 */
		Group group = new Group(composite, SWT.NONE);
		group.setText("搜索类别");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(data);
		/** 客户单选按钮 */
		btCustomer = new Button(group, SWT.RADIO);
		btCustomer.setText("客户");
		/** 联系人单选按钮 */
		btContact = new Button(group, SWT.RADIO);
		btContact.setText("联系人");
		/** 根据首选项设置默认选中客户还是选中联系人 */
		IPreferenceStore store = SuperCRMPlugin.getDefault().getPreferenceStore();
		String type = store.getString(PreferenceConstants.P_DEFAULT_SEARCH);
		if (type.equals("Customer"))
			btCustomer.setSelection(true);
		else
			btContact.setSelection(true);
		/** 关键字 */
		new Label(composite, SWT.NONE).setText("关键字：");
		keywords = new Text(composite, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		keywords.setLayoutData(data);
		/** 搜索按钮 */
		Button btSearch = new Button(composite, SWT.PUSH);
		btSearch.setText("搜索");
		/** 当单击选中按钮时 */
		btSearch.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				doSearch();
			}

		});
	}

	/** 执行搜索 */
	protected void doSearch() {
		/** 如果选中了客户 */
		if (btCustomer.getSelection()) {
			/** 获得客户服务对象 */
			ICustomerSerivce customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
			/** 获得查询结果的客户数据 */
			List customers = customerSerivce.getCustomers(keywords.getText());
			/** 打开客户列表视图,并刷新数据 */
			IViewPart view = getViewSite().getPage().findView(CustomerSummaryView.ID);
			if (view == null) {
				try {
					view = getViewSite().getPage().showView(CustomerSummaryView.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
			CustomerSummaryView customerSummaryView = (CustomerSummaryView) view;
			customerSummaryView.setData(customers);
			/** 如果选中了联系人 */
		} else if (btContact.getSelection()) {
			/** 获得联系人服务对象 */
			IContactSerivce contactSerivce = SuperFactory.getSuperApplication().getContactSerivce();
			/** 获得查询结果的联系人数据 */
			List contacts = contactSerivce.getContacts(keywords.getText());
			/** 打开联系人列表视图,并刷新数据 */
			IViewPart view = getViewSite().getPage().findView(ContactSummaryView.ID);
			if (view == null) {
				try {
					view = getViewSite().getPage().showView(ContactSummaryView.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
			ContactSummaryView contactSummaryView = (ContactSummaryView) view;
			contactSummaryView.setData(contacts);

		}
	}

	public void setFocus() {
		keywords.setFocus();
	}

}
