package superCRM.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.EditorPart;

import superCRM.model.ICustomerSerivce;
import superCRM.model.SuperFactory;
import superCRM.pojos.CustomerEO;
import superCRM.views.CustomerSummaryView;

public class CustomerDetailEditor extends EditorPart {
	/** 编辑器的ID */
	public static final String ID = "superCRM.editor.CustomerDetailEditor";

	/** 编辑器是否修改过的标记 */
	private boolean bDirty = false;

	/** 编辑器保存的客户对象 */
	private CustomerEO customer;

	/** 界面控件 */
	private Text displayName;

	private Text website;

	private Combo category;

	private Combo number;

	/** 表单工具对象 */
	private FormToolkit toolkit;

	public CustomerDetailEditor() {
		super();
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
		/** 将输入的编辑器对象的客户对象赋值给客户对象 */
		this.customer = ((CustomerDetailInput) input).getCustomer();
	}

	public void createPartControl(Composite parent) {
		/** 设置标单的基本属性 */
		toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		TableWrapLayout tableLayout = new TableWrapLayout();
		tableLayout.numColumns = 2;
		form.getBody().setLayout(tableLayout);
		form.setText("客户详细信息");
		toolkit.getColors().setForeground(form.getForeground());
		/** 创建界面的控件 */
		toolkit.createLabel(form.getBody(), "客户ID：");
		toolkit.createLabel(form.getBody(), customer.getId() + "");
		toolkit.createLabel(form.getBody(), "客户名称：");
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		/** 客户名称 */
		displayName = toolkit.createText(form.getBody(), customer.getDisplayName(), SWT.BORDER);
		displayName.setLayoutData(data);
		ModifyListener listener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (!isDirty()) {
					bDirty = true;
					firePropertyChange(IEditorPart.PROP_DIRTY);
				}
			}
		};
		displayName.addModifyListener(listener);

		/** 网址 */
		toolkit.createLabel(form.getBody(), "网址：");
		data = new TableWrapData(TableWrapData.FILL_GRAB);
		website = toolkit.createText(form.getBody(), customer.getWebSite(), SWT.BORDER);
		website.setLayoutData(data);
		website.addModifyListener(listener);

		/** 类别 */
		toolkit.createLabel(form.getBody(), "类别：");
		data = new TableWrapData(TableWrapData.FILL_GRAB);
		category = new Combo(form.getBody(), SWT.BORDER);
		toolkit.adapt(category, false, false);
		category.setItems(new String[] { "Customer", "Partener", "Competitor" });
		category.setLayoutData(data);
		category.setText(customer.getCategory());
		category.addModifyListener(listener);

		/** 公司人数 */
		toolkit.createLabel(form.getBody(), "公司人数：");
		data = new TableWrapData(TableWrapData.FILL_GRAB);
		number = new Combo(form.getBody(), SWT.BORDER);
		toolkit.adapt(number, false, false);
		number.setItems(new String[] { "1-10", "11-50", "51-100", "101-500", "500-" });
		number.setLayoutData(data);
		number.setText(customer.getNumberEmployee());
		number.addModifyListener(listener);
	}

	/** 保存编辑器 */
	public void doSave(IProgressMonitor monitor) {
		try {
			monitor.beginTask("保存客户...", 100);
			/** 获得修改后的客户信息 */
			customer.setDisplayName(displayName.getText());
			customer.setWebSite(website.getText());
			customer.setCategory(category.getText());
			customer.setNumberEmployee(number.getText());
			/** 调用业务层保存更新客户信息 */
			ICustomerSerivce customerSerivce = SuperFactory.getSuperApplication().getCustomerSerivce();
			customerSerivce.updateCustomer(customer);
			/** 更新客户列表视图数据 */
			refreshView();
			monitor.done();
			if (monitor.isCanceled())
				throw new InterruptedException("取消保存");
		} catch (InterruptedException e) {
			;
		}
	}

	public void doSaveAs() {

	}

	public boolean isDirty() {
		return bDirty;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	public void setFocus() {
		displayName.setFocus();
	}

	/** 更新客户列表视图数据 */
	public void refreshView() {
		IViewPart view = getEditorSite().getPage().findView(CustomerSummaryView.ID);
		if (view == null)
			return;
		CustomerSummaryView customerSummaryView = (CustomerSummaryView) view;
		customerSummaryView.refreshData();
	}
}
