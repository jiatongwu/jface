package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;
import com.fengmanfei.ch21.dialog.AboutDialog;

public class HelpAction extends Action {
	private JSEditor editor;
	public HelpAction(JSEditor editor) {
		super("°ïÖú@Ctrl+O");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\help.gif"));
		this.editor = editor;
	}
	public void run() {
		AboutDialog dlg = new AboutDialog( editor.getShell());
		dlg.open();
	}
}
