package com.fengmanfei.myrcp.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.fengmanfei.myrcp.editors.JsEditor;
import com.fengmanfei.myrcp.editors.JsEditorInput;

import myRCP.MyRCPPlugin;

public class MyAction extends Action implements ISelectionListener,IWorkbenchAction {
	private IWorkbenchWindow window;
	public final static String ID = " com.fengmanfei.myrcp.actions.MyAction";
	private IStructuredSelection selection;
	public MyAction(IWorkbenchWindow window) {
		super("Test Action");
		setId(ID);
		setText("My Action");
		setToolTipText("My Action");
		setImageDescriptor(MyRCPPlugin.getImageDescriptor("icons/sample.gif"));
		this.window = window;
		//注册选择服务监听器
		window.getSelectionService().addSelectionListener(this);
	}

	public void run() {
		String current = (String)selection.getFirstElement();
		IWorkbenchPage page = window.getActivePage();
		JsEditorInput editor = new JsEditorInput( current );
		try {
			page.openEditor( editor , JsEditor.ID );
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}
	//接口ISelectionListener中的方法
	//当事件发生时调用该方法
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			//获得事件发生的源所携带的对象
			selection = (IStructuredSelection) incoming;
			//强制转换
			String s = (String) selection.getFirstElement();
			Assert.isNotNull(s);
			//如果是选中的是"Three"，则设置为可用，否则设置为不可用
			if (s.equals("Three")) 
				setEnabled(true);
			else 
				setEnabled(false);
		}
	}
	//IWorkbenchAction接口中的方法，释放后取消注册
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

}
