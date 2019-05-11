package com.fengmanfei.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class SpinnerSample {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout( new GridLayout());
		shell.setText("Spinner");
		//创建微调按钮对象
		final Spinner spinner = new Spinner (shell, SWT.NONE);
		spinner.setMaximum(100);//设置最大值为100
		spinner.setMinimum(0);//设置最小值为0
		spinner.setIncrement(1);//设置每次单击箭头时增长的值
		spinner.setPageIncrement(20);
		spinner.setSelection(50);//设置当前选中值
		spinner.setDigits(2);//设置小数点两位
		shell.pack();
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ())
				display.sleep ();
		}
		display.dispose ();

	}

}
