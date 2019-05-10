package com.fengmanfei.myrcp.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class JsEditor extends EditorPart {
	//对应plugin.xml指定的id
	public static final String ID = "com.fengmanfei.myrcp.editors.JsEditor";
	private Text text;
	//编辑器中的内容是否被修改的标志
	private boolean bDirty = false;
	public JsEditor() {
		super();
	}
	//初始化编辑器
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);//设置site
		this.setInput(input);//设置输入的IEditorInput对象
		this.setPartName(input.getName());//设置编辑器上方显示的名称
	}
	//创建编辑器中的控件
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.NONE);
		//当文本框修改时，设定内容被修改过
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (!isDirty()) {//如果未修改
					setDirty(true);//设置修改
					//更改编辑器的状态
					firePropertyChange(IEditorPart.PROP_DIRTY);
				}
			}
		});
	}
	//编辑器关闭时，保存编辑器内容时所调用的方法
	public void doSave(IProgressMonitor monitor) {
		//将保存状态显示在状态栏中
		try {
			monitor.beginTask("保存文件...", 100);

			for (int i = 0; i < 10 && !monitor.isCanceled(); i++) {
				Thread.sleep(500);
				monitor.worked(10);
				double d = (i + 1) / 10d;
				monitor.subTask("已完成" + d * 100 + "%");// 显示任务状态
			}
			monitor.done();
			if (monitor.isCanceled())
				throw new InterruptedException("取消保存");
		} catch (InterruptedException e) {
			;
		}
	}
	//另存为调用的方法
	public void doSaveAs() {

	}
	//判断是否被修改过
	public boolean isDirty() {
		return bDirty;
	}
	//是否允许保存
	public boolean isSaveAsAllowed() {
		return true;
	}
	//设置焦点
	public void setFocus() {
		text.setFocus();
	}
	//设置编辑器内容被修改过
	public void setDirty(boolean b) {
		bDirty = b;
	}
	
}
