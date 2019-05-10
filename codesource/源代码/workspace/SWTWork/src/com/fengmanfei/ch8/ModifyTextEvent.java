package com.fengmanfei.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ModifyTextEvent implements VerifyListener {

	private Shell shell;

	private Text top;

	private Text bottom;

	public ModifyTextEvent() {
		shell = new Shell();
		shell.setText("文本改变事件示例");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE).setText("请输入小写字母：");
		top = new Text(shell, SWT.SINGLE);
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.addVerifyListener(this);
		new Label(shell, SWT.NONE).setText("相应的大写字母：");
		bottom = new Text(shell, SWT.SINGLE);
		bottom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		bottom.setEditable(false);
		shell.setSize(300, 100);
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	// VerifyListener接口中的方法
	public void verifyText(VerifyEvent e) {

		char c = e.character;// 获得输入的字符
		// 如果该字符不为字母或者不是大写字母，取消键入
		if (!Character.isLetter(c) || Character.isUpperCase(c)) {
			e.doit = false;
			return;
		}
		// 在下边的文本框中输入相对应的大写字母
		bottom.append("" + Character.toUpperCase(c));
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		ModifyTextEvent sample = new ModifyTextEvent();
		sample.getShell().open();
		while (!sample.getShell().isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
