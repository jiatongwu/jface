package superCRM.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import superCRM.SuperCRMPlugin;
import superCRM.dialog.NewCustomerWizard;

public class NewCustomerAction extends Action implements IWorkbenchAction {
	/** 操作所对应的ID */
	public static final String ID = "superCRM.action.NewCustomerAction";

	private IWorkbenchWindow workbenchWindow;

	public NewCustomerAction(IWorkbenchWindow window) {
		if (window == null) {
			throw new IllegalArgumentException();
		}
		this.workbenchWindow = window;
		/** 设置ID */
		setId(ID);
		setActionDefinitionId(ID);
		setText("新建客户");
		setImageDescriptor(SuperCRMPlugin.getImageDescriptor("icons/customer.gif"));
	}

	public void run() {
		if (workbenchWindow == null) {
			return;
		}
		/** 打开新建客户对话框 */
		WizardDialog dlg = new WizardDialog(workbenchWindow.getShell(), new NewCustomerWizard(workbenchWindow));
		dlg.open();
	}

	public void dispose() {
		workbenchWindow = null;
	}

}
