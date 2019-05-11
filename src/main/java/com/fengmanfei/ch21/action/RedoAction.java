package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;

public class RedoAction extends Action {
	private JSEditor editor;
	public RedoAction(JSEditor editor) {
		super("重做@Ctrl+Y");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\redo.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getUndoManager().redo();
	}
}
