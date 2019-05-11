package com.fengmanfei.ch7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CompareLayout {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);
		Button bt1 = new Button(shell, SWT.NONE);
		bt1.setBounds(10, 10, 80, 30);
		bt1.setText("Button One");

		Button bt2 = new Button(shell, SWT.NONE);
		bt2.setBounds(10, 50, 80, 30);
		bt2.setText("Button Two");

		shell.setSize(150, 150);
		shell.open();

		Shell shell1 = new Shell(display, SWT.SHELL_TRIM);
		shell1.setLayout(new FillLayout(SWT.VIRTUAL));

		bt1 = new Button(shell1, SWT.NONE);
		bt1.setBounds(10, 10, 80, 30);
		bt1.setText("Button One");

		bt1 = new Button(shell1, SWT.NONE);
		bt1.setBounds(10, 10, 80, 30);
		bt1.setText("Button Two");

		shell1.layout();
		shell1.setSize(150, 150);
		shell1.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
