package com.fengmanfei.ch21.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;
import com.fengmanfei.ch21.dialog.FindAndReplace;

public class SearchAction extends Action {
	private JSEditor editor;
	public SearchAction(JSEditor editor) {
		super("≤È’“\\ÃÊªª@Ctrl+F");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\search.gif"));
		this.editor = editor;
	}
	public void run() {
		new FindAndReplace( editor , editor.getShell()).open();
	}
}
