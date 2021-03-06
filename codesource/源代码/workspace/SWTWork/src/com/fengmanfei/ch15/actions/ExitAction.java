package com.fengmanfei.ch15.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import com.fengmanfei.ch15.*;

public class ExitAction extends Action {
	public ExitAction() {
		super();
		setText("退出(&E)");
		setToolTipText("退出系统");
		setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class, "icons\\exit.gif"));
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 * 
	 */
	public void run() {
		MainWindow.getApp().close();
	}
}
