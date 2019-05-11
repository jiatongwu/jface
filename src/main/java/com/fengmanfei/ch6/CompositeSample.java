package com.fengmanfei.ch6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class CompositeSample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell (display);
		shell.setText("GROUP");
		shell.setLayout (new RowLayout (SWT.VERTICAL));
		//创建分组框
		Group group = new Group (shell, SWT.SHADOW_OUT);
		group.setText("分组框");//设置标题
		group.setLayout (new RowLayout ());//设置布局
		//添加按钮
		for (int i=0; i<2;i++) {
		    Button button = new Button (group, SWT.PUSH);
			button.setText ("按钮 " + i);
			
		}

		shell.pack ();
		shell.open ();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();

	}

}
