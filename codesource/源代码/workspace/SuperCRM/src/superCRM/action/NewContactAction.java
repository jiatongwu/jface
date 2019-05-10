package superCRM.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import superCRM.SuperCRMPlugin;
import superCRM.dialog.NewContactWizard;

public class NewContactAction extends Action implements IWorkbenchAction{
	public static final String ID = "superCRM.action.NewContactAction";
	private IWorkbenchWindow workbenchWindow;

	public NewContactAction(IWorkbenchWindow window) {
		if (window == null) {
			throw new IllegalArgumentException();
		}
		this.workbenchWindow = window;
		setId( ID );
		setActionDefinitionId(ID);
		setText("新建联系人");
		setImageDescriptor(SuperCRMPlugin.getImageDescriptor("icons/contact.gif"));
	}

	public void run() {
		if (workbenchWindow == null) {
			return;
		}
		WizardDialog dlg = new WizardDialog(workbenchWindow.getShell(), new NewContactWizard());
		dlg.open();
	}

	public void dispose() {
		workbenchWindow = null;

	}
}
