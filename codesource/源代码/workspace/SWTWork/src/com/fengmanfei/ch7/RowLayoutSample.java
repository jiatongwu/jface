package com.fengmanfei.ch7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RowLayoutSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);

		RowLayout layout = new RowLayout();
		layout.type = SWT.HORIZONTAL;// ÉèÖÃË®Æ½Ìî³ä
		layout.marginLeft = 5;// ×ó²¹°×
		layout.marginTop = 5;// ÉÏ²¹°×
		layout.marginRight = 5;// ÓÒ²¹°×
		layout.marginBottom = 5;// ÏÂ²¹°×
		layout.spacing = 2;// ¿Ø¼þµÄ¼äÏ¶
		shell.setLayout(layout);

		new Button(shell, SWT.NONE).setText("B1");
		new Button(shell, SWT.NONE).setText("Button2");
		new Button(shell, SWT.NONE).setText("Wide Button3");
		new Button(shell, SWT.NONE).setText("B4");

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
