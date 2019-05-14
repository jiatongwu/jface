package cn.xvkang.compoent;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import cn.xvkang.dto.Student;

public class MyTableViewerLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		
		
	}

	@Override
	public void dispose() {
		
		
		
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		
		
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		
		return null;
	}

	@Override
	public String getColumnText(Object arg0, int arg1) {
		Student s=(Student)arg0;
		if(arg1==SearchTableListPageAbleComposite.ID) {
			return s.getId()+"";
		}else if(arg1==SearchTableListPageAbleComposite.NAME) {
			return s.getName();
		}else if(arg1==SearchTableListPageAbleComposite.AGE) {
			return s.getAge()+"";
		}else if(arg1==SearchTableListPageAbleComposite.REG_CODE) {
			return s.getRegCode();
		}else if(arg1==SearchTableListPageAbleComposite.PHONE) {
			return s.getPhone();
		} 			
		return "";
	}

	

}
