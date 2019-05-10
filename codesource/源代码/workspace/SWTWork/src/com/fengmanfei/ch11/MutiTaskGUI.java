package com.fengmanfei.ch11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class MutiTaskGUI {
	private Shell shell = null;
	private Table table = null;
	public MutiTaskGUI( ){
		//构造方法中调用初始化窗口的方法
		init();
	}
	//初始化窗口方法
	public void init() {
		shell = new Shell();
		shell.setLayout(new GridLayout());
		shell.setText("多线程");
		Button bt = new Button ( shell , SWT.NONE);
		bt.setText("开始一个任务");
		// 创建表格对象
		table = new Table(shell, SWT.BORDER);
		table.setLayoutData( new GridData(SWT.FILL,SWT.FILL,true,true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		String[] header = new String[]{"任务","进度","操作"};
		// 创建表头
		for (int i = 0; i < 3; i++) {
			TableColumn col = new TableColumn(table, SWT.NONE);
			col.setText( header[i] );
		}
		//设置表头宽度
		table.getColumn(0).setWidth(80);
		table.getColumn(1).setWidth(150);
		table.getColumn(2).setWidth(80);
		shell.pack();
		//注册创建任务按钮事件
		bt.addSelectionListener( new SelectionAdapter(){
			//当单击创建一个任务按钮时
			public void widgetSelected(SelectionEvent e) {
				//首先创建一个Task对象
				Task task = new Task ( table );
				//然后再表格中添加一行
				task.createTableItem();
				//最后启动该任务，该任务为一个线程
				task.start();
			}
		});
	}
	//获得和设置属性的getter和setter方法
	public Shell getShell() {
		return shell;
	}
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
}
