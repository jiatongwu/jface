package com.fengmanfei.ch7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FormLayoutSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);

		// 创建FormLayout对象
		FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 5;// 设置上下补白为5个像素
		formLayout.marginWidth = 5;// 设置左右补白为5个像素
		shell.setLayout(formLayout);

		Button bt1 = new Button(shell, SWT.PUSH);
		bt1.setText("B1");
		// 创建FormData对象
		FormData formData = new FormData();
		// 设定控件的上边框的位置
		formData.top = new FormAttachment(30, 70, 60);
		// 设定控件的下边框的位置
		formData.bottom = new FormAttachment(100, -5);
		bt1.setLayoutData(formData);

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
