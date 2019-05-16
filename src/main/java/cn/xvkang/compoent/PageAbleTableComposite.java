package cn.xvkang.compoent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import cn.xvkang.util.ImageFactory;



public class PageAbleTableComposite extends Composite implements Listener {
	private Table table;
	private Composite pageComposite;
	private Composite tableComposite;
	private Composite searchComposite;
	private ViewForm viewForm;
	private ToolBar toolBar;
	private Menu tableMenu;
	@SuppressWarnings("unused")
	private Composite parent;
	private Shell parentShell;
	private Point itemMouseDownPoint;
	@SuppressWarnings("unused")
	private List<String> columnNames;

	private ToolItem add;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public PageAbleTableComposite(Composite parent, Shell shell, int style) {
		super(parent, style);
		this.parent = parent;
		this.parentShell = shell;
		setLayout(new FormLayout());

		pageComposite = new Composite(this, SWT.BORDER);
		FormData fd_pageComposite = new FormData();
		fd_pageComposite.top = new FormAttachment(100, -67);
		fd_pageComposite.right = new FormAttachment(100, 0);
		fd_pageComposite.bottom = new FormAttachment(100, 0);
		fd_pageComposite.left = new FormAttachment(0, 0);
		pageComposite.setLayoutData(fd_pageComposite);

		tableComposite = new Composite(this, SWT.NONE);
		tableComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_tableComposite = new FormData();
		fd_tableComposite.bottom = new FormAttachment(pageComposite, 0);
		fd_tableComposite.right = new FormAttachment(100, 0);
		fd_tableComposite.left = new FormAttachment(0, 0);
		tableComposite.setLayoutData(fd_tableComposite);

		searchComposite = new Composite(this, SWT.BORDER);
		fd_tableComposite.top = new FormAttachment(searchComposite, 0);

		viewForm = new ViewForm(tableComposite, SWT.NONE);
		viewForm.setTopCenterSeparate(true);

		toolBar = new ToolBar(viewForm, SWT.FLAT);
		add = new ToolItem(toolBar, SWT.PUSH);
		add.setText("新增");
		add.addListener(SWT.Selection, this);
		Image newImage = ImageFactory.loadImage(toolBar.getDisplay(), ImageFactory.NEW);
		add.setImage(newImage);

		viewForm.setTopLeft(toolBar);

		table = new Table(viewForm, SWT.BORDER | SWT.FULL_SELECTION);
		viewForm.setContent(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		FormData fd_searchComposite = new FormData();
		fd_searchComposite.top = new FormAttachment(0, 0);
		fd_searchComposite.right = new FormAttachment(100, 0);
		fd_searchComposite.left = new FormAttachment(0, 0);
		fd_searchComposite.bottom = new FormAttachment(0, 87);
		searchComposite.setLayoutData(fd_searchComposite);

		List<String> columnNames = new ArrayList<>();
		List<Integer> columnWidthPercents = new ArrayList<>();
		List<List<String>> datas = new ArrayList<>();

		columnNames.add("id");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("身高");

		columnWidthPercents.add(20);
		columnWidthPercents.add(30);
		columnWidthPercents.add(30);
		columnWidthPercents.add(10);
		columnWidthPercents.add(10);

		for (int i = 0; i < 100; i++) {
			List<String> asList = Arrays.asList("id" + i, "姓名" + i, "性别" + i, "年龄" + i, "身高" + i);
			datas.add(asList);
		}

		setTableData(columnNames, columnWidthPercents, datas);

		tableMenu = new Menu(shell, SWT.POP_UP);
		table.setMenu(tableMenu);
		MenuItem copyItem = new MenuItem(tableMenu, SWT.PUSH);
		copyItem.setText("复制单元格内容");
		copyItem.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TableItem[] items = table.getSelection();
				for (TableItem item : items) {
					for (int i = 0; i < columnNames.size(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(itemMouseDownPoint)) {
							String text = item.getText(i);
							System.out.println(text);
						}

					}
				}

			}

		});

	}

	@Override
	protected void checkSubclass() {
	}

	public void setTableData(List<String> columnNames, List<Integer> columnWidthPercents, List<List<String>> datas) {
		this.columnNames = columnNames;
		for (int i = 0; i < columnNames.size(); i++) {
			TableColumn header = new TableColumn(table, SWT.CENTER);
			header.setText(columnNames.get(i));
		}

		for (int i = 0; i < datas.size(); i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);

			List<String> list = datas.get(i);
			for (int z = 0; z < list.size(); z++) {
				tableItem.setText(z, list.get(z));

			}
		}
		// 设置单元格特定内容 例如单元格最后一列放按钮
//		TableItem[] items = table.getItems();
//		for (int j = 0; j < items.length; j++) {
//			List<String> list = datas.get(j);
//			TableItem tableItem = items[j];
//			for (int z = 0; z < list.size(); z++) {
//				if(z%2==0) {
//				TableEditor tableEditor = new TableEditor(table);
//				Text text = new Text(table, SWT.CENTER);
//				text.setEditable(false);
//				text.setText(list.get(z));
//				//text.setBackground(null);
//				tableEditor.grabHorizontal = true;
//				tableEditor.horizontalAlignment=SWT.CENTER;
//				tableEditor.setEditor(text, tableItem, z);
//				}
//			}
//		}

		aotuColWidth(table, columnWidthPercents);
		// 测试时使用

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {

				// Rectangle clientArea = table.getClientArea();

				itemMouseDownPoint = new Point(event.x, event.y);
//				int index = table.getTopIndex();
//				while (index < table.getItemCount()) {
//					boolean visible = false;
//					TableItem item = table.getItem(index);
//					for (int i = 0; i < columnNames.size(); i++) {
//						Rectangle rect = item.getBounds(i);
//						if (rect.contains(pt)) {
//							System.out.println("Item " + index + "-" + i);
//						}
//						if (!visible && rect.intersects(clientArea)) {
//							visible = true;
//						}
//					}
//					if (!visible)
//						return;
//					index++;
//				}
			}
		});

		// 设置好数据后 设置分页条

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setMaximized(true);
		shell.setText(" Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		PageAbleTableComposite pageAbleTableComposite = new PageAbleTableComposite(shell, shell, SWT.NONE);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		ImageFactory.dispose();
		display.dispose();
		
	}

	public static void aotuColWidth(Table table, List<Integer> columnWidthPercents) {
		table.addControlListener(new ControlAdapter() {
			public void controlResized(final ControlEvent e) {
				Rectangle clientArea = table.getClientArea();
				int width = clientArea.width - 15;
				double columnWidthDouble = Double.parseDouble(width + "");
				double onPercent = columnWidthDouble / 100;

				for (int i = 0; i < table.getColumnCount(); i++) {
					Integer integer = columnWidthPercents.get(i);
					double columnWidth = onPercent * (integer.intValue());
					table.getColumn(i).setWidth((int) columnWidth);
					// table.getColumn(i).pack();
				}
			}
		});
	}

	@Override
	public void handleEvent(Event event) {
		if (event.widget == add) {
			// 弹出一个面板进行新增
			Shell addShell = new Shell(parentShell, SWT.PRIMARY_MODAL|SWT.TITLE|SWT.CLOSE);
			addShell.setText("新增");
			addShell.setLayout(new FillLayout());
			AddComposite addComposite = new AddComposite(addShell, SWT.NONE);

			Rectangle screenSize = getDisplay().getPrimaryMonitor().getBounds();
			addShell.setLocation((screenSize.width - addShell.getBounds().width) / 2,
					(screenSize.height - addShell.getBounds().height) / 2);
			addComposite.getSaveBtn().addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					addShell.close();
					addShell.dispose();					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					
					
				}
			});

			addShell.pack();
			addShell.setSize(800, 600);
			addShell.open();

		}
	}
}
