package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.JSPreferencePage;
import com.fengmanfei.ch21.ResourceManager;

public class PreferenceAction extends Action {
	private JSEditor editor;

	public PreferenceAction(JSEditor editor) {
		super("Ê×Ñ¡Ïî@Ctrl+R");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\prefs.gif"));
		this.editor = editor;
	}

	public void run() {
		PreferenceManager mgr = new PreferenceManager();
		mgr.addToRoot(new PreferenceNode("edit", "±à¼­Æ÷", null,JSPreferencePage.class.getName()));
		PreferenceDialog dlg = new PreferenceDialog(editor.getShell(), mgr);
		dlg.setPreferenceStore(editor.getPreference());
		dlg.open();

	}

}
