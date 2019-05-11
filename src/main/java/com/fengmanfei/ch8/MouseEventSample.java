package com.fengmanfei.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

public class MouseEventSample {
	private Shell shell;
	private Button button;
	private MoveButtonListener listener;
	public MouseEventSample() {
		shell = new Shell();
		shell.setText("鼠标事件");
		button = new Button(shell, SWT.NONE);
		button.setBounds(5, 5, 50, 25);
		button.setText("按钮");
		listener = new MoveButtonListener();//创建监听器对象
		button.addMouseListener(listener);//注册鼠标监听器
		button.addMouseTrackListener(listener);//注册鼠标跟踪监听器
		shell.setSize(200, 150);
		
	}

	//监听器类，实现了鼠标监听器的接口
	public class MoveButtonListener implements MouseMoveListener, MouseListener ,MouseTrackListener{
		//当鼠标移动时
		public void mouseMove(MouseEvent e) {
			//首先当前鼠标的位置转化为窗口的坐标位置
			Point convertPoint = Display.getCurrent().map(button, shell , e.x,e.y );
			Rectangle current = button.getBounds();
			//重新设置按钮的位置，使之跟随鼠标移动
			button.setBounds(convertPoint.x, convertPoint.y, current.width, current.height);
		}
		//鼠标双击事件
		public void mouseDoubleClick(MouseEvent e) {
			System.out.println(e.toString());
			System.out.println("双击了该按钮");
		}
		//当鼠标按下时，为按钮注册鼠标移动监听器
		public void mouseDown(MouseEvent e) {
			System.out.println(e.toString());
			button.addMouseMoveListener(listener);

		}
		//当鼠标抬起时，停止拖放，移除鼠标移动监听器
		public void mouseUp(MouseEvent e) {
			button.removeMouseMoveListener(listener);
		}
		//当鼠标进入到按钮区域时
		public void mouseEnter(MouseEvent e) {
			System.out.println(e.toString());
			System.out.println("鼠标进入到按钮区域");
		}
		//当鼠标离开到按钮区域时
		public void mouseExit(MouseEvent e) {
			System.out.println("鼠标离开按钮区域");
		}
		//当鼠标在按钮区域时
		public void mouseHover(MouseEvent e) {
			System.out.println("鼠标在按钮区域");
		}
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public static void main(String[] args) {
		Display display =Display.getDefault();
		MouseEventSample sample = new MouseEventSample();
		sample.getShell().open();
		while (!sample.getShell().isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
