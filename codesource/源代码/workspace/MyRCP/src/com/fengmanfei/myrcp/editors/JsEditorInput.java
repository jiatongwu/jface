package com.fengmanfei.myrcp.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import myRCP.MyRCPPlugin;

public class JsEditorInput implements IEditorInput {
	//输入的字符
	private String input ;
	public JsEditorInput ( String input ){
		this.input = input ;
	}
	//是否将编辑器保存在最近访问记录中
	public boolean exists() {
		return true;
	}
	//输入内容的图标
	public ImageDescriptor getImageDescriptor() {
		return MyRCPPlugin.getImageDescriptor("icon/sample.gif");
	}
	//输入信息的名称
	public String getName() {
		return input;
	}
	//是否可以持久化该编辑器
	public IPersistableElement getPersistable() {
		return null;
	}
	//设置编辑器标签中显示提示信息
	public String getToolTipText() {
		return input;
	}
	//返回与该输入相关的类的对象
	public Object getAdapter(Class adapter) {
		return null;
	}

}
