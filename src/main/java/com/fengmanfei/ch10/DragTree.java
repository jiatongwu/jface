package com.fengmanfei.ch10;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class DragTree {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		//创建一个树对象
		final Tree tree = createFolderTree(shell);
		//定义拖放源对象
		DragSource dragSource = new DragSource(tree, DND.DROP_MOVE|DND.DROP_COPY);
		//设置传输的数据为文本型String类型
		dragSource.setTransfer(new Transfer[] { TextTransfer.getInstance()});
		//注册拖放源时的事件处理
		dragSource.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				if (tree.getSelectionCount() == 0)
					event.doit = false;
			}
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = tree.getSelection()[0].getText(0);
				}
			}
			public void dragFinished(DragSourceEvent event) {
					
			}
		});
		//定义拖放目标对象
		DropTarget dropTarget = new DropTarget(tree, DND.DROP_MOVE | DND.DROP_DEFAULT|DND.DROP_COPY);
		//设置目标对象可传输的数据类型
		dropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance()});
		//注册目标对象的事件处理
		dropTarget.addDropListener(new DropTargetListener() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;
			}
			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;
			}
			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SELECT;
			}
			//当松开鼠标时触发的事件
			public void drop(DropTargetEvent event) {
				if (event.item == null)
					return;
				//首先获得目标对象中拖拽的树节点
				TreeItem eventItem = (TreeItem) event.item;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					//获得数据源设置的字符串
					String s = (String) event.data;
					//在当前位置插入一个节点
					TreeItem newItem = null ;
					if (eventItem.getParentItem()==null)
						newItem = new TreeItem(eventItem.getParent(),SWT.NONE);
					else
						newItem = new TreeItem(eventItem.getParentItem(),SWT.NONE);
					newItem.setText(s);
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}
			public void dropAccept(DropTargetEvent event) {
			}

		});
		shell.setSize(300, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	//创建树，利用目录结构来初始化树数据
	public static Tree createFolderTree(Composite parent) {
		Tree tree = new Tree(parent, SWT.BORDER | SWT.SINGLE);
		File root = new File("F:\\Temp");
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(files[i].getName());
			if (files[i].isDirectory())
				setDirectory(files[i], item);
		}
		return tree;

	}
	//设置子树的子孙数据，递归调用该方法
	public static void setDirectory(File file, TreeItem parent) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			TreeItem item = new TreeItem(parent, SWT.NONE);
			item.setText(files[i].getName());
			if (files[i].isDirectory())
				setDirectory(files[i], item);
		}
	}
}