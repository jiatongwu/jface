package com.fengmanfei.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CTabFolderSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("CTabFolder");
		FillLayout layout = new FillLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		shell.setLayout(layout);
		// 创建CTabFolder对象
		final CTabFolder tabFolder = new CTabFolder(shell, SWT.CLOSE
				| SWT.BORDER);
		// 设置选项卡的布局
		tabFolder.setLayout(new FillLayout());
		// 设置选项标签的高度
		tabFolder.setTabHeight(20);
		// 设置上下，左右补白
		tabFolder.marginHeight = 10;
		tabFolder.marginWidth = 10;
		for (int i = 1; i < 4; i++) {
			// 创建CTabItem选项卡标签对象
			CTabItem item = new CTabItem(tabFolder, SWT.NONE);
			// 设置选项卡的文本
			item.setText("选项卡 " + i);
			Text t = new Text(tabFolder, SWT.MULTI);
			t.setText("这是第" + i + "页");
			// 设置选项卡所控制的控件
			item.setControl(t);
		}
		tabFolder.pack();
		shell.setSize(300, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
