package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class ComplexSWT {
	public static void main(String[] args) {
		Display display = new Display();
		// 获得该Display的一个Shell类的实例
		Shell shell = new Shell(display);
		// 为窗口设置大小
		shell.setSize(200, 200);
		// 为窗口设置标题栏文字
		shell.setText("Shell");
		// 为窗口设置布局类型
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		// 在当前窗口中创建分组
		Group group = new Group(shell, SWT.SHADOW_ETCHED_OUT);
		// 为分组设置标题栏内容
		group.setText("这是一个组");
		// 为分组设置布局类型
		group.setLayout(new FillLayout(SWT.VERTICAL));
		// 在当前分组中创建单选钮1
		Button radio1 = new Button(group, SWT.RADIO);
		// 为单选钮1添加说明文字
		radio1.setText("第一个单选钮");
		// 在当前分组中创建单选钮2
		Button radio2 = new Button(group, SWT.RADIO);
		// 为单选钮2添加说明文字
		radio2.setText("第二个单选钮");
		// 在当前窗口中创建普通按钮
		Button button = new Button(shell, SWT.PUSH);
		// 为普通按钮添加说明文字
		button.setText("按钮");
		// 使分组的布局生效
		group.layout();
		// 使窗口的布局生效
		shell.layout();
		// 打开窗口，并且将窗口显示在屏幕上
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		// 销毁Display实例，释放创建display时所获取的内存资源，断开与本地操作系统得连接
		display.dispose();

	}
}
