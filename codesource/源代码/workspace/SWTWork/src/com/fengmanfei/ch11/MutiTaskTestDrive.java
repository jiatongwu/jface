package com.fengmanfei.ch11;

import org.eclipse.swt.widgets.Display;
import com.fengmanfei.util.ImageFactory;

public class MutiTaskTestDrive {
	public static void main(String[] args) {
		Display display =  Display.getDefault();
		MutiTaskGUI mutiTask= new MutiTaskGUI();
		mutiTask.getShell().open();
		while (!mutiTask.getShell().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		ImageFactory.dispose();
		display.dispose();
	}
}
