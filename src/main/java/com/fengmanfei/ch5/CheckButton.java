package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class CheckButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT.CHECK");

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		Group group = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		group.setLayout(new FillLayout(SWT.VERTICAL));
		group.setText("不同样式的多选按钮");

		Button bt1 = new Button(group, SWT.CHECK | SWT.LEFT);
		bt1.setText("SWT.LEFT");
		bt1.setToolTipText("SWT.LEFT");

		Button bt2 = new Button(group, SWT.CHECK | SWT.RIGHT);
		bt2.setText("SWT.RIGHT");
		bt2.setToolTipText("SWT.RIGHT");

		Button bt3 = new Button(group, SWT.CHECK | SWT.CENTER);
		bt3.setText("SWT.CENTER");
		bt3.setToolTipText("SWT.CENTER");

		Button bt4 = new Button(group, SWT.CHECK | SWT.FLAT);
		bt4.setText("SWT.FLAT");
		bt4.setToolTipText("SWT.FLAT");

		Button bt5 = new Button(group, SWT.CHECK | SWT.BORDER);
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
