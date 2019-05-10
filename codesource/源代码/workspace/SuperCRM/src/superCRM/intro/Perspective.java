package superCRM.intro;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import superCRM.views.ContactSummaryView;
import superCRM.views.CustomerSummaryView;
import superCRM.views.NavView;
import superCRM.views.QuickNewContactView;
import superCRM.views.QuickNewCustomerView;
import superCRM.views.SearchView;

;
/** 默认的透视图类 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		/** 编辑器的左侧放置导航视图 */
		layout.addView(NavView.ID, IPageLayout.LEFT, 0.25f, editorArea);

		/** 导航视图的下方放置一些工具视图，以文件夹的形式 */
		IFolderLayout leftBottom = layout.createFolder("left", IPageLayout.BOTTOM, 0.6f, NavView.ID);
		
		/** 搜索视图 */
		leftBottom.addView(SearchView.ID);
		
		/** 快速新建联系人视图 */
		leftBottom.addView(QuickNewContactView.ID);
		
		/** 快速新建客户视图 */
		leftBottom.addView(QuickNewCustomerView.ID);
		
		/** 编辑器的上方放置客户列表视图 */
		layout.addView(CustomerSummaryView.ID, IPageLayout.TOP, 0.25f, editorArea);
		
		/** 编辑器的上方预留放置联系人列表视图 */
		layout.addPlaceholder(ContactSummaryView.ID, IPageLayout.TOP, 0.25f, editorArea);
	}
}
