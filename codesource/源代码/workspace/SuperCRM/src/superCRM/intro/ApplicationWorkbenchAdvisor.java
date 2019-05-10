package superCRM.intro;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**工作区类*/
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	
	/**工作区默认的透视图ID*/
	private static final String PERSPECTIVE_ID = "SuperCRM.perspective";

	/**创建工作区窗口*/
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
    /**初始化工作区设置*/
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        configurer.setSaveAndRestore(true);
    }
    
    /**获得默认透视图的ID*/
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
