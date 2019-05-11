package cn.xvkang.action;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;

public class ExitAction extends Action{
	private ApplicationWindow applicationWindow;
	public ExitAction(ApplicationWindow applicationWindow) {
		super();
		this.applicationWindow=applicationWindow;
		setText("退出(&E)");
		setToolTipText("退出系统");
		setImageDescriptor(ImageDescriptor.createFromFile(ExitAction.class, "icons"+File.separator+"exit.gif"));
	}
	public void run() {
		applicationWindow.close();
	}
}
