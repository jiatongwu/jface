package superCRM.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import superCRM.SuperCRMPlugin;

public class LoginPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	/** 是否自动登录按钮 */
	private Button bAutoLogin;

	/** 用户名 */
	private Text userName;

	/** 密码 */
	private Text password;

	public LoginPreferencePage() {
		super("登录设置");
		setPreferenceStore(SuperCRMPlugin.getDefault().getPreferenceStore());
	}

	protected Control createContents(Composite parent) {
		/** 设置面板布局 */
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(2, false));
		/** 是否登录按钮 */
		bAutoLogin = new Button(composite, SWT.CHECK);
		bAutoLogin.setText("是否自动登录");
		/** 根据首选项设置登录按钮状态 */
		bAutoLogin.setSelection(getPreferenceStore().getBoolean(PreferenceConstants.P_AUTO_LOGIN));
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		bAutoLogin.setLayoutData(gridData);
		/** 注册登录按钮选中事件监听器 */
		bAutoLogin.addSelectionListener(new SelectionAdapter() {
			/** 当选中是否登录按钮时 */
			public void widgetSelected(SelectionEvent e) {
				/** 根据登录状态设置用户名和密码框的可用状态 */
				setLoginEnabled(bAutoLogin.getSelection());
			}
		});
		/** 用户名 */
		new Label(composite, SWT.NONE).setText("登录的用户名：");
		userName = new Text(composite, SWT.BORDER);
		userName.setText(getPreferenceStore().getString(PreferenceConstants.P_USER_NAME));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		userName.setLayoutData(gridData);
		/** 密码 */
		new Label(composite, SWT.NONE).setText("登录的密码：");
		password = new Text(composite, SWT.BORDER);
		password.setEchoChar('*');
		password.setText(getPreferenceStore().getString(PreferenceConstants.P_PASSWORD));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		password.setLayoutData(gridData);
		/** 根据登录状态设置用户名和密码框的可用状态 */
		setLoginEnabled(bAutoLogin.getSelection());
		return composite;
	}

	public void init(IWorkbench workbench) {

	}

	/** 设置用户名和密码文本框状态 */
	private void setLoginEnabled(boolean enable) {
		userName.setEnabled(!enable);
		password.setEnabled(!enable);
	}

	/** 单击确定按钮时,设置首选项的值 */
	public boolean performOk() {
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.P_AUTO_LOGIN, bAutoLogin.getSelection());
		store.setValue(PreferenceConstants.P_USER_NAME, userName.getText());
		store.setValue(PreferenceConstants.P_PASSWORD, password.getText());
		return true;
	}

	/** 单击恢复默认按钮时,恢复默认值 */
	protected void performDefaults() {
		IPreferenceStore store = getPreferenceStore();
		bAutoLogin.setSelection(store.getDefaultBoolean(PreferenceConstants.P_AUTO_LOGIN));
		userName.setText(store.getDefaultString(PreferenceConstants.P_USER_NAME));
		password.setText(store.getDefaultString(PreferenceConstants.P_PASSWORD));
		setLoginEnabled(bAutoLogin.getSelection());
	}

}
