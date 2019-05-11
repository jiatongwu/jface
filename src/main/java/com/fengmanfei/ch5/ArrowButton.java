package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ArrowButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT.ARROW");

		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		Button bt1 = new Button(shell, SWT.ARROW | SWT.UP);
		bt1.setToolTipText("SWT.LEFT");

		Button bt2 = new Button(shell, SWT.ARROW | SWT.DOWN);
		bt2.setToolTipText("SWT.RIGHT");

		Button bt3 = new Button(shell, SWT.ARROW | SWT.LEFT);
		bt3.setToolTipText("SWT.CENTER");

		Button bt4 = new Button(shell, SWT.ARROW | SWT.RIGHT);
		bt4.setToolTipText("SWT.FLAT");

		Button bt5 = new Button(shell, SWT.ARROW | SWT.FLAT);
		bt5.setToolTipText("SWT.BORDER");

		Button bt6 = new Button(shell, SWT.ARROW | SWT.BORDER);
		bt6.setToolTipText("SWT.BORDER");

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
