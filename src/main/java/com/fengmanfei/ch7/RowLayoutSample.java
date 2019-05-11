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
		layout.type = SWT.HORIZONTAL;// 设置水平填充
		layout.marginLeft = 5;// 左补白
		layout.marginTop = 5;// 上补白
		layout.marginRight = 5;// 右补白
		layout.marginBottom = 5;// 下补白
		layout.spacing = 2;// 控件的间隙
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
