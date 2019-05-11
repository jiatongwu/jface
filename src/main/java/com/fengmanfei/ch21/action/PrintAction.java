package com.fengmanfei.ch21.action;

import com.fengmanfei.ch21.JSEditor;
import com.fengmanfei.ch21.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class PrintAction extends Action{
	private JSEditor editor;
	public PrintAction(JSEditor editor){
		super("打印@Ctrl+P");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\print.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getViewer().getTextWidget().print();
	}
}
