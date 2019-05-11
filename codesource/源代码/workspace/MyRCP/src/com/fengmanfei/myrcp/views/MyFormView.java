package com.fengmanfei.myrcp.views;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;
import myRCP.MyRCPPlugin;

public class MyFormView extends ViewPart {

	public static final String ID = "com.fengmanfei.myrcp.views.MyFormView";

	private FormToolkit toolkit;// 表单的工具对象

	private ScrolledForm form;// 可滚动的表单对象

	public MyFormView() {
		super();
	}

	// 实现父类中的抽象方法，创建视图中的各种控件
	public void createPartControl(Composite parent) {
		// 创建表单工具对象
		toolkit = new FormToolkit(parent.getDisplay());
		// 通过表单工具对象创建可滚动的表单对象
		form = toolkit.createScrolledForm(parent);
		// 设置表单文本
		form.setText("Hello, Eclipse 表单");
		
		// form.setBackgroundImage( MyRCPPlugin.getImageDescriptor("icons/form_bg.gif").createImage());
		//设置表单的布局
		form.getBody().setLayout( new TableWrapLayout());
		
		//创建可折叠的面板
		ExpandableComposite expcomp = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TREE_NODE);
		//定义字符串
		String txt = "在Web网页的UI体系中，最常见的也是效果最好的就是使页面中的部分区域中的内容可以折叠和展开，Eclipse表单也提供了可折叠的面板（ExpandableComposite）来实现相同的功能.";
		//创建一个标签并显示字符串
		Label client = toolkit.createLabel(expcomp, txt, SWT.WRAP);
		//为折叠面板设置设置标题
		expcomp.setText("这是一个可折叠的面板（ExpandableComposite）");
		//expcomp.setToggleColor( Display.getCurrent().getSystemColor( SWT.COLOR_DARK_BLUE));
		// 将Label作为折叠面板折叠区的内容
		expcomp.setClient(client);
		// 为折叠面板添加展开/折叠时的监听器
		expcomp.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				// 根据控件的新尺寸重新定位和更新滚动条
				form.reflow(true);
			}
		});

		//创建内容区域，使用TWISTIE样式，并且使用TITLE_BAR显示背景的标题
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE|Section.TITLE_BAR|Section.DESCRIPTION);
		//设置标题文字
		section.setText("这是一个内容区(Section)");
		//创建一个面板，作为内容折叠区放置的控件
		Composite sectionClient = toolkit.createComposite(section);
		sectionClient.setLayout( new GridLayout());
		Button bt1 = toolkit.createButton(sectionClient, "CheckBox 1", SWT.CHECK);
		Button bt2 = toolkit.createButton(sectionClient, "CheckBox 2", SWT.CHECK);
		//设置内容区控件
		section.setClient(sectionClient);
		toolkit.createCompositeSeparator(section);
		section.setDescription("这是在标题栏下的一段描述");
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				// 根据控件的新尺寸重新定位和更新滚动条
				form.reflow(true);
			}
		});
		//创建带图标的超链接
		ImageHyperlink imageHyperlink =  toolkit.createImageHyperlink(section,SWT.CENTER);
		//设置超链接的图标
		imageHyperlink.setImage( PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD).createImage());
		//将该图标超链接添加到内容区的工具栏中
		section.setTextClient( imageHyperlink );
		
		//创建一个内容区
		Section linkSection = toolkit.createSection( form.getBody(), Section.TWISTIE|Section.TITLE_BAR);
		linkSection.setText("这是超级链接");
		Composite linkComp = toolkit.createComposite( linkSection );
		linkComp.setLayout( new GridLayout());
		linkSection.setClient( linkComp );
		//创建超链接，文字可以折行显示
		Hyperlink textLink = toolkit.createHyperlink(linkComp,"这是一个文本超链接",SWT.WRAP);
		//注册超链接事件监听器
		textLink.addHyperlinkListener( new IHyperlinkListener(){
			public void linkEntered(HyperlinkEvent e) {
				//光标进入超链接区域
			}
			public void linkExited(HyperlinkEvent e) {
				//光标离开超链接区域
			}

			public void linkActivated(HyperlinkEvent e) {
				//单击超链接激活时
				System.out.println("超链接被激活");
			}
			
		});
		//创建带有图片的超链接
		ImageHyperlink imageLink = toolkit.createImageHyperlink( linkComp ,SWT.WRAP );
		//设置超链接的图标
		imageLink.setImage( MyRCPPlugin.getImageDescriptor("icons/help_contents.gif").createImage() );
		//设置超链接的文本
		imageLink.setText("这是一个图片超链接");
		//设置当鼠标放到超链接上时的图标
		imageLink.setHoverImage( MyRCPPlugin.getImageDescriptor("icons/linkto_help.gif").createImage());
		//注册超链接监听器，使用HyperlinkAdapter适配器对象
		imageLink.addHyperlinkListener( new HyperlinkAdapter(){
			public void linkActivated(HyperlinkEvent e) {
				System.out.println("超链接被激活");
			}
		});
		linkSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				// 根据控件的新尺寸重新定位和更新滚动条
				form.reflow(true);
			}
		});
		
		//创建一个表单本文对象，true表示当该表单获得焦点时，将超级链接的焦点设置为可见
		FormText formText = toolkit.createFormText(form.getBody(), false);
		//定义一个字符串，其中包含http://格式的字符
		//String text = "这是无格式的文本"+"这是带有超链接的文本 http://www.eclipse.org 将自动转化为超链接,<a> http://www.eclipse.org</a>";
		//设置文本false表示不转化tag标记，true表示转化超链接
		//formText.setText(text, true, false);
		//注册单击超链监听器
		formText.addHyperlinkListener(new HyperlinkAdapter(){
			//当单击链接时
			public void linkActivated(HyperlinkEvent e) {
				//打印出单击的超链接地址
				System.out.println("单击了该超链接："+e.getHref());
			}
		});
		//设置文本所需要使用图片，使用第一参数为图片的key，该值可被xml标记中的<img>标记中的href引用
		formText.setImage("image1",MyRCPPlugin.getImageDescriptor("icons/samples.gif").createImage());
		//设置文本中所需要使用的字体，第一个参数表示字体的key，值可被<span>的font属性引用
		formText.setFont("head",form.getFont());
		//设置文本中所需要使用的颜色，第一个参数表示颜色的key，值可被<span>的color属性引用
		formText.setColor("headColor",form.getForeground());
		//设置文本中所引用的控件,第一个参数表示控件的key，值可被<control>的href属性引用
		formText.setControl("myControl",toolkit.createButton(form.getBody(),"这是一个按钮",SWT.NONE));
		//装载该与MyFormView.java同文件夹下的demo.xml文件
		InputStream is = MyFormView.class.getResourceAsStream("demo.xml"); //$NON-NLS-1$
		if (is!=null) {//若存在该文件
			//设置表单文本输入数据流
			formText.setContents(is, true);
			try {
				//最后关闭数据流
				is.close();
			}
			catch (IOException e) {
			}
		}
		//获得超链接组
		HyperlinkGroup group = toolkit.getHyperlinkGroup();
	}

	// 视图获取焦点时
	public void setFocus() {
		form.setFocus();// 将焦点放置在表单上
	}

	// 覆盖父类中的方法，释放视图时
	public void dispose() {
		toolkit.dispose();// 释放表单工具对象
		super.dispose();
	}
}
