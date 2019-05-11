package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ToggleButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT.TOGGLE");

		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		Button bt1 = new Button(shell, SWT.TOGGLE | SWT.LEFT);
		bt1.setText("SWT.LEFT");
		bt1.setToolTipText("SWT.LEFT");

		Button bt2 = new Button(shell, SWT.TOGGLE | SWT.FLAT);
		bt2.setText("SWT.FLAT");
		bt2.setToolTipText("SWT.FLAT");

		Button bt3 = new Button(shell, SWT.TOGGLE | SWT.BORDER);
		bt3.setText("SWT.BORDER");
		bt3.setToolTipText("SWT.BORDER");

		shell.layout();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
