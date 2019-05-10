package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PushButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT.PUSH");

		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		Button bt1 = new Button(shell, SWT.PUSH | SWT.LEFT);
		// 设置文本
		bt1.setText("SWT.LEFT");
		// 设置悬浮提示
		bt1.setToolTipText("SWT.LEFT");

		Button bt2 = new Button(shell, SWT.PUSH | SWT.RIGHT);
		bt2.setText("SWT.RIGHT");
		bt2.setToolTipText("SWT.RIGHT");

		Button bt3 = new Button(shell, SWT.PUSH | SWT.CENTER);
		bt3.setText("SWT.CENTER");
		bt3.setToolTipText("SWT.CENTER");

		Button bt4 = new Button(shell, SWT.PUSH | SWT.FLAT);
		bt4.setText("SWT.FLAT");
		bt4.setToolTipText("SWT.FLAT");

		Button bt5 = new Button(shell, SWT.PUSH | SWT.BORDER);
		bt5.setText("SWT.BORDER");
		bt5.setToolTipText("SWT.BORDER");

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
