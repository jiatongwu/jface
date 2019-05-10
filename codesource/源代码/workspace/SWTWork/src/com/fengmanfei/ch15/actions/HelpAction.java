package com.fengmanfei.ch15.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;

public class HelpAction extends Action{
	public HelpAction(){
		super();
	    setText("∞Ô÷˙ƒ⁄»›(&H)");
	    setToolTipText("∞Ô÷˙");
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons\\help.gif"));
	}
	/* £®∑« Javadoc£©
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
	}
}
