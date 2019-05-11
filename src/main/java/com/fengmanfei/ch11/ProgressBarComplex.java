package com.fengmanfei.ch11;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ProgressBarComplex {
	// 表示线程的状态
	static boolean bCancel = false;
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("ProgressBar");
		shell.setLayout(new GridLayout(2, false));
		// 开始和取消按钮
		final Button start = new Button(shell, SWT.NONE);
		start.setText("开始");
		Button cancel = new Button(shell, SWT.NONE);
		cancel.setText("取消");
		// 创建滚动条实例
		final ProgressBar progressBar = new ProgressBar(shell, SWT.HORIZONTAL);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		progressBar.setLayoutData(data);
		progressBar.setMaximum(100);// 设置最大值
		progressBar.setMinimum(0);// 设置最小值
		final int maximum = progressBar.getMaximum();// 获取最大值
		final int minimus = progressBar.getMinimum();// 获取最小值
		// 为开始按钮注册事件
		start.addSelectionListener(new SelectionAdapter() {
			// 当单击开始按钮时
			public void widgetSelected(SelectionEvent e) {
				// 首先设置开始按钮不可用状态
				start.setEnabled(false);
				// 创建更新进度条的线程
				Runnable runnable = new Runnable() {
					public void run() {
						for (int i = minimus; i < maximum; i++) {
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
							}
							// 注意在更新进度条时加上了判断线程状态的条件
							shell.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (progressBar.isDisposed())
										return;
									// 如果此时取消了线程，将进度条设置为初始状态
									if (bCancel) {
										progressBar.setSelection(0);
									}
									progressBar.setSelection(progressBar.getSelection() + 1);
								}
							});
							// 如果此时取消了线程，结束该循环，这个线程也就结束了
							// 并重置线程状态
							if (bCancel) {
								bCancel = false;
								break;
							}
						}
					}
				};
				// 启动这个线程
				new Thread(runnable).start();
			}
		});
		// 注册取消按钮事件
		cancel.addSelectionListener(new SelectionAdapter() {
			// 当单击取消按钮时
			public void widgetSelected(SelectionEvent e) {
				// 如果此时线程在执行，则取消线程并将开始按钮置为可用
				if (!bCancel) {
					bCancel = true;
					start.setEnabled(true);
				}
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
