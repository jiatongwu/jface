package com.fengmanfei.myrcp.forms;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class NonEclipse {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT表单示例");
		shell.setLayout( new FillLayout());
		//创建表单工具对象
		FormToolkit toolkit = new FormToolkit(shell.getDisplay());
		//通过表单工具对象创建可滚动的表单对象
		ScrolledForm form = toolkit.createScrolledForm(shell);
		form.setLayout( new GridLayout());
		//设置表单文本
		form.setText("Hello, Non Eclipse 表单");
		toolkit.createLabel(form,"text");
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		toolkit.dispose();
		display.dispose();
	}
}
