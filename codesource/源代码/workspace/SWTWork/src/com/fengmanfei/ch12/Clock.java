package com.fengmanfei.ch12;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Clock extends Thread{
	private Shell shell = null;
	private Canvas canvas = null;
	public Clock() {
		createContent();
	}
	public void createContent() {
		shell = new Shell();
		shell.setLayout(new FillLayout());

		
		canvas = new Canvas(shell, SWT.NONE);
		GC gc = new GC(canvas);
		gc.drawRectangle(0,0,canvas.getClientArea().x,canvas.getClientArea().y);
		// 注册画布的画图事件
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				//Rectangle rect = new Rectangle ( );
				//e.gc.drawOval(0,0,101,101);
				//e.gc.
			}
		});
		shell.setSize(200, 150);
		gc.dispose();
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public Shell getShell() {
		return shell;
	}
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Clock clock = new Clock();
		clock.getShell().open();
		while (!clock.getShell().isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	/* （非 Javadoc）
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		// TODO 自动生成方法存根
		super.run();
	}
	
}
