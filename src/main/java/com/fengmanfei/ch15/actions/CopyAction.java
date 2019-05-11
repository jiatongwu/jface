package com.fengmanfei.ch15.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import com.fengmanfei.ch15.MainWindow;


public class CopyAction extends Action{
	public CopyAction( ){
		super();
	    setText("复制(&C)");
	    setToolTipText("复制");
	    setAccelerator( SWT.CTRL + 'C');
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons\\copy.gif"));
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		MainWindow.getApp().getContent().copy();
	}
}
