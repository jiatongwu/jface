package com.fengmanfei.ch7;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class StackLayouSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		// 创建放置文本框的面板
		final Composite parent = new Composite(shell, SWT.NONE);
		// 设置面板的布局数据
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		// 创建堆栈式布局
		final StackLayout layout = new StackLayout();
		// 将堆拽式布局应用与面板
		parent.setLayout(layout);
		// 创建10个文本框
		final Text[] textArray = new Text[10];
		for (int i = 0; i < 10; i++) {
			textArray[i] = new Text(parent, SWT.MULTI);
			textArray[i].setText("这是第" + i + "个文本框");
		}
		// 设置堆栈中当前显示的控件
		layout.topControl = textArray[0];

		Button b = new Button(shell, SWT.PUSH);
		b.setText("显示下一个文本框");
		// 保存当前显示的文本框的索引值
		final int[] index = new int[1];
		// 为按钮添加点击事件
		b.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// 计算出下一个文本框的索引数
				index[0] = (index[0] + 1) % 10;
				// 设置当前显示的控件
				layout.topControl = textArray[index[0]];
				// 重新刷新布局
				parent.layout();
			}
		});

		shell.setSize(200, 150);
		shell.open();
		while (shell != null && !shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

}
