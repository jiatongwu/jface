package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;

public class UndoAction extends Action {
	private JSEditor editor;
	public UndoAction(JSEditor editor) {
		super("撤销@Ctrl+Z");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\undo.gif"));
		this.editor = editor;
	}

	public void run() {
		editor.getUndoManager().undo();
	}
}
