package superCRM.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import superCRM.SuperCRMPlugin;
import superCRM.preferences.PreferenceConstants;
import superCRM.util.LayoutUtil;

/** 登录对话框 */
public class LoginDialog extends TitleAreaDialog {

	/** 用户名 */
	private Text userName;

	/** 密码 */
	private Text password;

	public LoginDialog(Shell parentShell) {
		super(parentShell);
	}

	/** 设置登录对话框的属性 */
	protected void configureShell(Shell newShell) {
		newShell.setText("用户登录");
		newShell.setSize(300, 200);
		newShell.setImage(SuperCRMPlugin.getImageDescriptor("icons/logo.gif").createImage());
		LayoutUtil.centerShell(Display.getCurrent(), newShell);
	}

	/** 设置登录对话框的内容属性 */
	protected Control createContents(Composite parent) {
		super.createContents(parent);
		this.setTitle("用户登录");
		this.setMessage("请输入用户名和密码登录系统");
		return parent;
	}

	/** 设置登录对话框内容区的属性 */
	protected Control createDialogArea(Composite parent) {
		super.createDialogArea(parent);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		new Label(composite, SWT.NONE).setText("用户名：");
		userName = new Text(composite, SWT.BORDER);
		userName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(composite, SWT.NONE).setText("密码：");
		password = new Text(composite, SWT.BORDER);
		password.setEchoChar('*');
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return parent;
	}

	/** 覆盖父类的方法,当单击按钮时调用 */
	protected void buttonPressed(int buttonId) {
		/** 如果单击了确定按钮 */
		if (IDialogConstants.OK_ID == buttonId) {
			/** 用户名不为空 */
			if (userName.getText().equals("")) {
				this.setErrorMessage("用户名不为空");
				return;
			}
			/** 密码不为空 */
			if (password.getText().equals("")) {
				this.setErrorMessage("密码不为空！");
				return;
			}
			/**验证用户名密码*/
			boolean bValid = checkValid();
			if (!bValid) {
				this.setErrorMessage("用户名或密码错误！");
				return;
			}
			okPressed();
		} else if (IDialogConstants.CANCEL_ID == buttonId)
			cancelPressed();
	}

	/** 判断验证用户名和密码 */
	private boolean checkValid() {
		boolean bValid = false;
		/**将用户输入用户名与首选项中设置的用户名和密码对比,如果正确,则验证成功*/
		IPreferenceStore store = SuperCRMPlugin.getDefault().getPreferenceStore();
		if (userName.getText().equals(store.getString(PreferenceConstants.P_USER_NAME)) && password.getText().equals(store.getString(PreferenceConstants.P_USER_NAME)))
			bValid = true;
		return bValid;
	}
}
