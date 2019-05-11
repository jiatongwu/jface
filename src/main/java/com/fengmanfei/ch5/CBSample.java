package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class CBSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("SWT示例");

		// 定义shell的布局
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginWidth = 10;
		shell.setLayout(layout);

		Group group = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		group.setLayout(new FillLayout(SWT.VERTICAL));
		group.setText("您的爱好是：");

		// 定义按钮为数组，要在事件处理类中使用按钮的引用，要设置成final型
		final Button[] button = new Button[5];
		String[] items = { "看书", "游泳", "音乐", "睡觉", "逛街" };
		// 初始化按钮数组
		for (int i = 0; i < button.length; i++) {
			button[i] = new Button(group, SWT.CHECK);
			button[i].setText(items[i]);
		}
		// 确定按钮
		Button ok = new Button(group, SWT.PUSH);
		ok.setText("确定");
		// 添加确定按钮事件
		ok.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				// 循环所有按钮
				for (int i = 0; i < button.length; i++)
					// 如果选中，则输出选中的值
					if (button[i].getSelection())
						System.out.println("您选择了：" + button[i].getText());
			}

		});
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
