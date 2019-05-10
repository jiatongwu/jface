package com.fengmanfei.ch9;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.fengmanfei.util.ImageFactory;

public class CoolBarSample {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setText("CoolBar");
		shell.setLayout( new GridLayout());
		Composite tool = new Composite( shell ,SWT.NONE);
		tool.setLayoutData(new GridData(SWT.LEFT,SWT.TOP,true ,false));
		//定义可拖动的工具栏对象
		CoolBar coolBar = new CoolBar (tool, SWT.FLAT);
		//创建两个工具栏项
	    createItem(display ,coolBar);
	    createItem(display,coolBar);
		coolBar.pack ();
		Text content = new Text (shell,SWT.MULTI);
		content.setLayoutData( new GridData(SWT.FILL,SWT.FILL,true,true));
		
		shell.setSize( new Point ( 200,150 ));
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		//释放图片资源
		ImageFactory.dispose();
		display.dispose ();

	}
	public static CoolItem createItem(Display display ,CoolBar coolBar) {
		//创建一个工具栏
	    ToolBar toolBar = createToolBar(display, coolBar);
	    //获得工具栏的大小
	    Point size = toolBar.getSize();
	    //定义一个CoolItem对象
	    CoolItem item = new CoolItem(coolBar, SWT.NONE);
	    //将工具栏绑定到这个工具栏项上
	    item.setControl(toolBar);
	    //计算工具栏项合适的大小
	    Point preferred = item.computeSize(size.x, size.y);
	    //设置大小
	    item.setPreferredSize(preferred);
	    return item;
	}
	public static ToolBar createToolBar(Display display, CoolBar coolBar) {
		ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
		//创建保存工具按钮
		ToolItem saveItem = new ToolItem ( toolBar , SWT.PUSH);
		//设置工具按钮的图片
		saveItem.setImage( ImageFactory.loadImage( display , ImageFactory.SAVE_EDIT));
		//设置工具按钮上的文字
		saveItem.setText("保存");
		//设置工具按钮上的提示信息
		saveItem.setToolTipText("保存");
		//创建打印工具按钮
		ToolItem printItem = new ToolItem ( toolBar , SWT.PUSH);
		printItem.setImage( ImageFactory.loadImage( display , ImageFactory.PRINT_EDIT));
		printItem.setText("打印");
		printItem.setToolTipText("打印");
		//创建帮助工具按钮
		ToolItem helpItem = new ToolItem ( toolBar , SWT.PUSH);
		helpItem.setImage( ImageFactory.loadImage( display , ImageFactory.HELP_CONTENTS));
		helpItem.setText("帮助");
		helpItem.setToolTipText("帮助");
	    toolBar.pack();
		return toolBar;
	}
}
