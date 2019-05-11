package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class RadioButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT.RADIO");

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		// 这是第一组单选按钮
		Group group1 = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		group1.setLayout(new FillLayout(SWT.VERTICAL));
		group1.setText("这是一组样式");

		Button bt1 = new Button(group1, SWT.RADIO | SWT.LEFT);
		bt1.setText("SWT.LEFT");
		bt1.setToolTipText("SWT.LEFT");

		Button bt2 = new Button(group1, SWT.RADIO | SWT.RIGHT);
		bt2.setText("SWT.RIGHT");
		bt2.setToolTipText("SWT.RIGHT");

		Button bt3 = new Button(group1, SWT.RADIO | SWT.CENTER);
		bt3.setText("SWT.CENTER");
		bt3.setToolTipText("SWT.CENTER");

		// 这是第二组单选按钮
		Group group2 = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		group2.setLayout(new FillLayout(SWT.VERTICAL));
		group2.setText("这是另一组样式");

		Button bt4 = new Button(group2, SWT.RADIO | SWT.FLAT);
		bt4.setText("SWT.FLAT");
		bt4.setToolTipText("SWT.FLAT");
		bt4.setSelection(true);

		Button bt5 = new Button(group2, SWT.RADIO | SWT.BORDER);
		bt5.setText("SWT.BORDER");
		bt5.setToolTipText("SWT.BORDER");

		Button bt6 = new Button(group2, SWT.RADIO);
		bt6.setText("SWT.RADIO");
		bt6.setToolTipText("SWT.RADIO");

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
