package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.*;
import com.fengmanfei.ch21.JSEditor;

public class OpenAction extends Action {
	private JSEditor editor;
	public OpenAction(JSEditor editor) {
		super("´ò¿ª@Ctrl+O");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\open.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getEventManager().openFile();
	}
}
