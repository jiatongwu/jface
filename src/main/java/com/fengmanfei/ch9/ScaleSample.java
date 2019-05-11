package com.fengmanfei.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

public class ScaleSample {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout( new GridLayout());
		shell.setText("Scale");
		//创建一个标签显示当前所选中的值
		final Label sliderValue = new Label ( shell , SWT.NONE);
		//创建刻度条对象
		final Scale scale = new Scale (shell, SWT.HORIZONTAL);
		scale.setMaximum(100);//设置最大值为100
		scale.setMinimum(0);//设置最小值为0
		scale.setIncrement(1);//设置拖动滑块时增长的值
		scale.setPageIncrement(20);//设置单击滑块空白处一次移动的值
		scale.setSelection(50);//设置当前选中的值
		sliderValue.setText( "当前值为"+scale.getSelection());
		scale.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				sliderValue.setText( "当前值为"+scale.getSelection());
			}

		});
		shell.pack();
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ())
				display.sleep ();
		}
		display.dispose ();

	}

}
