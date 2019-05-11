package com.fengmanfei.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ProgreeBarTableSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		// 创建表格对象
		Table table = new Table(shell, SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		// 创建表头
		for (int i = 0; i < 2; i++) {
			new TableColumn(table, SWT.NONE);
		}
		table.getColumn(0).setText("任务");
		table.getColumn(1).setText("进度");
		// 创建10行数据
		for (int i = 0; i < 10; i++) {
			//创建一行
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText("任务 " + i);
			//创建一个进度条
			ProgressBar bar = new ProgressBar(table, SWT.NONE);
			bar.setSelection((i + 1) * 10);
			//创建一个可编辑的表格对象
			TableEditor editor = new TableEditor(table);
			editor.grabHorizontal = true;
			editor.grabVertical = true;
			//将进度条绑定到单元格上
			editor.setEditor(bar, item, 1);
		}
		table.getColumn(0).pack();
		table.getColumn(1).setWidth(100);
		table.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}
