package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CLabelSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("CLabel");

		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		CLabel cl = new CLabel(shell, SWT.LEFT);
		cl.setText("这是一个带图标的自定义标签");
		cl.setImage(display.getSystemImage(SWT.ICON_INFORMATION));

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
