package com.fengmanfei.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class UntypedEvent {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("无类型的事件");
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(5, 5, 50, 25);
		button.setText("移动我");
		Listener listener = new Listener() {
			public void handleEvent(Event e) {
				if (e.type == SWT.KeyDown) {
					Control control = (Control) e.widget;
					// 获得该控件的位置和大小
					Rectangle current = control.getBounds();
					if (e.keyCode == SWT.ARROW_DOWN)// 如果按下了“下”按键
						current.y++;// 下移一个像素
					else if (e.keyCode == SWT.ARROW_UP)// 如果按下了“上”按键
						current.y--;// 上移一个像素
					else if (e.keyCode == SWT.ARROW_LEFT)// 如果按下了“左”按键
						current.x--;// 左移一个像素
					else if (e.keyCode == SWT.ARROW_RIGHT)// 如果按下了“右”按键
						current.x++;// 右移一个像素
					// 重新设置控件的位置
					control.setBounds(current);
				}else if ( e.type == SWT.Selection){
					System.out.println("你单击了此按钮");
				}
			}

		};
		// 注册无类型的事件监听器
		button.addListener(SWT.KeyDown, listener);
		button.addListener(SWT.Selection, listener);
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
