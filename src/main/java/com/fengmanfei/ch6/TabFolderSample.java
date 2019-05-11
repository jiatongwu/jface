package com.fengmanfei.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class TabFolderSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("TabFolder");
		shell.setLayout(new FillLayout());
		// 创建TabFolder对象
		final TabFolder tabFolder = new TabFolder(shell, SWT.TOP);
		// 设置选项卡的布局
		tabFolder.setLayout(new FillLayout());
		for (int i = 1; i < 4; i++) {
			// 创建TabItem选项卡标签对象
			TabItem item = new TabItem(tabFolder, SWT.NONE);
			// 设置选项卡的文本
			item.setText("选项卡 " + i);
			// item.setImage( new Image(display,"F:\\javaDev\\eclipse\\plugins\\org.eclipse.platform_3.1.2\\eclipse.png") );
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
