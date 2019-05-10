package com.fengmanfei.myrcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import com.fengmanfei.myrcp.views.AnotherView;
import com.fengmanfei.myrcp.views.SampleView;

public class MyPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addStandaloneView(SampleView.ID, true, IPageLayout.LEFT, 0.45f,editorArea);
		layout.addStandaloneView(AnotherView.ID, true, IPageLayout.BOTTOM,0.45f, editorArea);
	}

}
