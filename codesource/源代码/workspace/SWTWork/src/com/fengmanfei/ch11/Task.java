package com.fengmanfei.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.fengmanfei.util.ImageFactory;

//该Task类继承自Thread，并且覆盖了run()方法
public class Task extends Thread {
	//该类的一些属性
	private Table table = null;
	//是否停止的标志
	private boolean done = false;
	//声明进度条对象
	private ProgressBar bar = null;
	private int min = 0;
	private int max = 100;
	public Task(Table table) {
		this.table = table;
	}
	//创建表格中的一行
	public void createTableItem() {
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(this.getName());
		item.setImage(ImageFactory.loadImage(table.getDisplay(), ImageFactory.PROGRESS_TASK));
		// 创建一个进度条
		bar = new ProgressBar(table, SWT.NONE);
		bar.setMinimum(min);
		bar.setMaximum(max);
		// 创建一个可编辑的表格对象
		TableEditor editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		// 将进度条绑定到第二列中
		editor.setEditor(bar, item, 1);
		//重新创建一个可编辑的表格对象
		editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		//创建一个按钮
		Button stop = new Button(table, SWT.NONE);
		stop.setText("Stop");
		editor.setEditor(stop, item, 2);
		stop.addSelectionListener(new SelectionAdapter() {
			//当停止按钮按下时，设置停止标记true
			public void widgetSelected(SelectionEvent e) {
				if (!isDone())
					setDone(true);
			}
		});
	}
	//线程方法体，与前面单个的进度条的程序类似
	public void run() {
		for (int i = min; i < max; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			table.getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (bar.isDisposed())
						return;
					bar.setSelection(bar.getSelection() + 1);
				}
			});
			//如果停止，则结束该线程
			if (isDone()) {
				break;
			}
		}
	}
	//获得和设置属性的getter和setter方法
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
