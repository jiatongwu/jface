package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;

public class SaveAction extends Action {
	private JSEditor editor;
	public SaveAction(JSEditor editor) {
		super("保存@Ctrl+S");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\save.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getEventManager().saveFile();
	}
}
