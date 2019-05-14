package cn.xvkang.compoent;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import cn.xvkang.dto.Student;

public class MyTableViewerContentProvider implements IStructuredContentProvider {

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object arg0) {
		return ((List<Student>)arg0).toArray();
	}

	@Override
	public void dispose() {
		
		IStructuredContentProvider.super.dispose();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		IStructuredContentProvider.super.inputChanged(viewer, oldInput, newInput);
	}
	
	
	

}
