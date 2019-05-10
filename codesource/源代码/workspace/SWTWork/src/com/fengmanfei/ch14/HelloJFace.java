package com.fengmanfei.ch14;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class HelloJFace extends ApplicationWindow {

	public HelloJFace() {
		super(null);
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 * 该方法负责创建窗口中的控件
	 */
	protected Control createContents(Composite parent) {
		//getShell()方法为父类中的方法，返回窗口的Shell对象
		//设置窗口显示的标题
		getShell().setText("JFace");
		//创建建一个按钮
		Button button = new Button(parent, SWT.CENTER);
		button.setText("欢迎进入到JFace的世界!");
		//调整窗口布局
		parent.pack();
		return parent;
	}
	
	public static void main(String[] args) {
		//创建窗口对象
		HelloJFace helloJFace = new HelloJFace();
		//设置窗口处于打开状态
		helloJFace.setBlockOnOpen( true );
		//打开窗口
		helloJFace.open();
		//释放Dipsplay对象
		Display.getCurrent().dispose();
	}
}
