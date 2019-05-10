package superCRM.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import superCRM.SuperCRMPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer {
	/** 设置首选项默认值 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = SuperCRMPlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_AUTO_LOGIN, false);
		store.setDefault(PreferenceConstants.P_USER_NAME, "admin");
		store.setDefault(PreferenceConstants.P_PASSWORD, "admin");
		store.setDefault(PreferenceConstants.P_DEFAULT_SEARCH, "Customer");
	}

}
