package com.fengmanfei.ch5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Text程序示例");

		final Text content = new Text(shell, SWT.WRAP | SWT.V_SCROLL);
		content.setBounds(5, 5, 325, 200);

		Button selectAll = new Button(shell, SWT.NONE);
		selectAll.setText("全选");
		selectAll.setBounds(5, 225, 75, 25);
		selectAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 选中所有文本
				content.selectAll();
			}

		});

		Button cancel = new Button(shell, SWT.NONE);
		cancel.setText("取消全选");
		cancel.setBounds(90, 225, 75, 25);
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 如果有选中的文本
				if (content.getSelectionCount() > 0)
					content.clearSelection();// 清除选择
			}

		});

		Button copy = new Button(shell, SWT.NONE);
		copy.setText("复制");
		copy.setBounds(175, 225, 75, 25);
		copy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 复制到剪切板
				content.copy();
			}

		});

		Button paste = new Button(shell, SWT.NONE);
		paste.setText("粘贴");
		paste.setBounds(260, 225, 75, 25);
		paste.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 将剪切板的内容粘贴
				content.paste();
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

	public static Label createLabel(Composite parent, String text) {
		Label l = new Label(parent, SWT.RIGHT);
		l.setText(text);
		return l;
	}

}
