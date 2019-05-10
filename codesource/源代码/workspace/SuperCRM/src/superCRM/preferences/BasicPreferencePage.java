package superCRM.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import superCRM.SuperCRMPlugin;

public class BasicPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public BasicPreferencePage() {
		super(GRID);
		/** 设置首选项保存对象 */
		setPreferenceStore(SuperCRMPlugin.getDefault().getPreferenceStore());
		/** 设置首选项页面描述信息 */
		setDescription("基本设置");
	}

	public void createFieldEditors() {
		/** 添加一个单选按钮组字段 */
		addField(new RadioGroupFieldEditor(PreferenceConstants.P_DEFAULT_SEARCH, "默认的搜索类型", 2, new String[][] { { "客户", "Customer" }, { "联系人", "Contact" } }, getFieldEditorParent(), true));
	}

	public void init(IWorkbench workbench) {
	}

}