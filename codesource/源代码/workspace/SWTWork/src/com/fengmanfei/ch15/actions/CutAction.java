package com.fengmanfei.ch15.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import com.fengmanfei.ch15.MainWindow;

public class CutAction extends Action {
	public CutAction( ){
		super();
	    setText("ºÙ«–(&C)");
	    setToolTipText("ºÙ«–");
	    setAccelerator( SWT.CTRL + 'X');
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons\\cut.gif"));
	}
	/* £®∑« Javadoc£©
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		MainWindow.getApp().getContent().cut();
	}
}
