package com.fengmanfei.myrcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import com.fengmanfei.myrcp.views.*;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addStandaloneView(MyFormView.ID,true,IPageLayout.LEFT,.3f,editorArea);
		//layout.addStandaloneView(OpenEditorView.ID,true,IPageLayout.RIGHT,.3f,editorArea);
		//layout.addView(FirstView.ID,IPageLayout.LEFT,0.45f,editorPart);
		//为透视图添加一个视图
/*
		layout.addStandaloneView(IPageLayout.ID_OUTLINE,//视图的ID
				true,//是否显示视图的标题
				IPageLayout.LEFT,//放置在透视图的左侧位置
				0.45f,//所占透视图的百分比为45%
				editorArea);//添加的相关区域
		layout.addStandaloneView(AnotherView.ID,//视图的ID
				true,
				IPageLayout.BOTTOM,
				0.45f,
				editorArea);
		*/
		layout.addStandaloneView(SampleView.ID,true,IPageLayout.TOP,0.45f,editorArea);
		layout.addStandaloneView(IPageLayout.ID_TASK_LIST,true,IPageLayout.RIGHT,0.45f,SampleView.ID);
/*
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.25f, editorArea);
		bottom.addView(IPageLayout.ID_RES_NAV);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.25f, editorArea);
		left.addView(IPageLayout.ID_OUTLINE);
		left.addView(IPageLayout.ID_PROBLEM_VIEW);
		//topLeft.addView(IPageLayout.ID_PROP_SHEET);
		//topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		layout.addFastView(AnotherView.ID);
		//layout.setEditorAreaVisible(false);
		*/
	}
}
