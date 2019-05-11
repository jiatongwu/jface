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

import superCRM.model.IContactSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.ContactEO;

public class QuickNewContactView extends ViewPart {
	/** 该视图的ID */
	public static final String ID = "superCRM.views.QuickNewContactView";

	/** 姓名 */
	private Text displayName;

	/** 类别 */
	private Combo category;

	/** 性别 */
	private Combo sex;

	public QuickNewContactView() {
		super();
	}

	public void createPartControl(Composite parent) {
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
		/** 添加按钮 */
		Button btAdd = new Button(composite, SWT.PUSH);
		btAdd.setText("添加");
		btAdd.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				/** 创建联系人对象 */
				ContactEO contact = new ContactEO();
				/** 设置输入的联系人信息 */
				contact.setDisplayName(displayName.getText());
				contact.setCategory(category.getText());
				contact.setSex(sex.getText());
				/** 调用业务层保存到数据库 */
				IContactSerivce contactSerivce = SuperFactory.getSuperApplication().getContactSerivce();
				contactSerivce.addContact(contact);
				/** 查找当前页面是否打开了联系人列表视图 */
				IViewPart view = getViewSite().getPage().findView(ContactSummaryView.ID);
				/** 如果未打开联系人列表视图,则打开视图 */
				if (view == null) {
					try {
						view = getViewSite().getPage().showView(ContactSummaryView.ID);
					} catch (PartInitException ee) {
						ee.printStackTrace();
					}
				}
				/** 更新联系人列表视图 */
				ContactSummaryView contactSummaryView = (ContactSummaryView) view;
				contactSummaryView.refreshData();
			}

		});
	}

	public void setFocus() {
		displayName.setFocus();

	}

}
