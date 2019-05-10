package com.fengmanfei.myrcp.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import myRCP.MyRCPPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		//获得该插件保存的首选项保存对象
		IPreferenceStore store = MyRCPPlugin.getDefault().getPreferenceStore();
		//设置默认的一些首选项值
		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		store.setDefault(PreferenceConstants.P_STRING,"Default value");
	}

}
