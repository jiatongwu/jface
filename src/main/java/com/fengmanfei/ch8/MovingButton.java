package com.fengmanfei.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

public class MovingButton {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("可移动的按钮");
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(5, 5, 50, 25);
		button.setText("移动我");
		// 注册键盘事件监听器
		button.addKeyListener(new KeyListener() {
			// 键盘按下后
			public void keyPressed(KeyEvent e) {
				/*
				 * int bits = SWT.CTRL | SWT.ALT | SWT.SHIFT; if ((e.stateMask &
				 * bits) != 0) { e.doit = false ;//取消该事件 return;//并返回 }
				 */
				// 获得触发该事件的控件对象
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
			}

			public void keyReleased(KeyEvent e) {

			}

		});

		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
