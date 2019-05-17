package cn.xvkang.compoent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
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
	private Combo pageSizeCombo;
	private Label label;
	private Text currentPageText;
	private Label label_1;
	private Label totalPageLabel;
	private Label label_3;
	private Label label_4;
	private Label totalCountLabel;
	private Label label_6;

	private Button firstPageBtn;
	private Button prePageBtn;
	private Button nextPageBtn;
	private Button lastPageBtn;

	private int totalCount;
	private int totalPage;
	private int pageSize;
	private int pageNum;
	private Text nameTextSearch;
	private Text homeAddressSearchText;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public PageAbleTableComposite(Composite parent, Shell parentShell,  int style) {

		super(parent, style);
		this.parent = parent;
		this.parentShell = parentShell;
		setLayout(new FormLayout());

		pageComposite = new Composite(this, SWT.BORDER);
		pageComposite.setLayout(new FormLayout());
		FormData fd_pageComposite = new FormData();
		fd_pageComposite.top = new FormAttachment(100, -47);
		fd_pageComposite.right = new FormAttachment(100, 0);
		fd_pageComposite.bottom = new FormAttachment(100, 0);
		fd_pageComposite.left = new FormAttachment(0, 0);
		pageComposite.setLayoutData(fd_pageComposite);

		tableComposite = new Composite(this, SWT.NONE);
		tableComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_tableComposite = new FormData();
		fd_tableComposite.bottom = new FormAttachment(pageComposite, 0);

		pageSizeCombo = new Combo(pageComposite, SWT.READ_ONLY);

		pageSizeCombo.setItems("10", "20", "30", "40", "50", "60");
		pageSizeCombo.select(0);
		FormData fd_pageSizeCombo = new FormData();
		fd_pageSizeCombo.left = new FormAttachment(0, 23);
		fd_pageSizeCombo.top = new FormAttachment(0, 6);
		pageSizeCombo.setLayoutData(fd_pageSizeCombo);
		pageSizeCombo.addListener(SWT.Selection, this);

		firstPageBtn = new Button(pageComposite, SWT.NONE);
		fd_pageSizeCombo.bottom = new FormAttachment(firstPageBtn, 0, SWT.BOTTOM);
		fd_pageSizeCombo.right = new FormAttachment(firstPageBtn, -19);
		firstPageBtn.addListener(SWT.Selection, this);
		FormData fd_firstPageBtn = new FormData();
		fd_firstPageBtn.top = new FormAttachment(0, 6);
		fd_firstPageBtn.left = new FormAttachment(0, 97);
		firstPageBtn.setLayoutData(fd_firstPageBtn);
		firstPageBtn.setText("首页");

		nextPageBtn = new Button(pageComposite, SWT.NONE);
		nextPageBtn.addListener(SWT.Selection, this);
		nextPageBtn.setText("下一页");
		FormData fd_nextPageBtn = new FormData();
		fd_nextPageBtn.top = new FormAttachment(0, 6);
		nextPageBtn.setLayoutData(fd_nextPageBtn);

		lastPageBtn = new Button(pageComposite, SWT.NONE);
		lastPageBtn.addListener(SWT.Selection, this);
		lastPageBtn.setText("未页");
		FormData fd_lastPageBtn = new FormData();
		fd_lastPageBtn.top = new FormAttachment(0, 6);
		fd_lastPageBtn.left = new FormAttachment(nextPageBtn, 6);
		lastPageBtn.setLayoutData(fd_lastPageBtn);

		prePageBtn = new Button(pageComposite, SWT.NONE);
		prePageBtn.addListener(SWT.Selection, this);
		fd_nextPageBtn.left = new FormAttachment(prePageBtn, 251);
		prePageBtn.setText("上一页");
		FormData fd_prePageBtn = new FormData();
		fd_prePageBtn.bottom = new FormAttachment(firstPageBtn, 0, SWT.BOTTOM);
		fd_prePageBtn.left = new FormAttachment(firstPageBtn, 2);
		fd_prePageBtn.top = new FormAttachment(0, 6);
		prePageBtn.setLayoutData(fd_prePageBtn);

		label = new Label(pageComposite, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(100, -17);
		fd_label.left = new FormAttachment(prePageBtn, 19);
		fd_label.top = new FormAttachment(0, 12);
		label.setLayoutData(fd_label);
		label.setText("第");

		currentPageText = new Text(pageComposite, SWT.BORDER);
		FormData fd_currentPageText = new FormData();
		fd_currentPageText.bottom = new FormAttachment(100, -15);
		fd_currentPageText.left = new FormAttachment(label, 6);
		fd_currentPageText.top = new FormAttachment(0, 10);
		fd_currentPageText.width = 25;

		currentPageText.setLayoutData(fd_currentPageText);
		currentPageText.addListener(SWT.Traverse, this);
		// currentPageText.setSize(80, currentPageText.getSize().y);

		label_1 = new Label(pageComposite, SWT.NONE);
		// fd_currentPageText.right = new FormAttachment(label_1, -6);
		label_1.setText("页　共");
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 309);
		fd_label_1.bottom = new FormAttachment(100, -17);
		fd_label_1.top = new FormAttachment(0, 12);
		label_1.setLayoutData(fd_label_1);

		totalPageLabel = new Label(pageComposite, SWT.NONE);
		FormData fd_totalPageLabel = new FormData();
		fd_totalPageLabel.bottom = new FormAttachment(100, -17);
		fd_totalPageLabel.left = new FormAttachment(label_1, 8);
		fd_totalPageLabel.top = new FormAttachment(0, 12);
		totalPageLabel.setLayoutData(fd_totalPageLabel);
		totalPageLabel.setText("88");

		label_3 = new Label(pageComposite, SWT.NONE);
		FormData fd_label_3 = new FormData();
		fd_label_3.bottom = new FormAttachment(100, -17);
		fd_label_3.left = new FormAttachment(totalPageLabel, 6);
		fd_label_3.top = new FormAttachment(0, 12);
		label_3.setLayoutData(fd_label_3);
		label_3.setText("页");

		label_4 = new Label(pageComposite, SWT.NONE);
		FormData fd_label_4 = new FormData();
		fd_label_4.bottom = new FormAttachment(100, -17);
		fd_label_4.right = new FormAttachment(100, -98);
		fd_label_4.top = new FormAttachment(0, 12);
		label_4.setLayoutData(fd_label_4);
		label_4.setText("共");

		totalCountLabel = new Label(pageComposite, SWT.NONE);
		FormData fd_totalCountLabel = new FormData();
		fd_totalCountLabel.bottom = new FormAttachment(100, -17);
		fd_totalCountLabel.left = new FormAttachment(label_4, 6);
		fd_totalCountLabel.top = new FormAttachment(0, 12);
		totalCountLabel.setLayoutData(fd_totalCountLabel);
		totalCountLabel.setText("188");

		label_6 = new Label(pageComposite, SWT.NONE);
		FormData fd_label_6 = new FormData();
		fd_label_6.bottom = new FormAttachment(100, -17);
		fd_label_6.left = new FormAttachment(totalCountLabel, 6);
		fd_label_6.top = new FormAttachment(0, 12);
		label_6.setLayoutData(fd_label_6);
		label_6.setText("条记录");
		fd_tableComposite.right = new FormAttachment(100, 0);
		fd_tableComposite.left = new FormAttachment(0, 0);
		fd_tableComposite.top = new FormAttachment(0, 12);
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
		searchComposite.setLayout(new FormLayout());
		FormData fd_searchComposite = new FormData();
		fd_searchComposite.top = new FormAttachment(0, 0);
		fd_searchComposite.right = new FormAttachment(100, 0);
		fd_searchComposite.left = new FormAttachment(0, 0);
		fd_searchComposite.bottom = new FormAttachment(0, 87);
		searchComposite.setLayoutData(fd_searchComposite);
		
		Label label_2 = new Label(searchComposite, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(0, 10);
		fd_label_2.left = new FormAttachment(0, 24);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("姓名");
		
		nameTextSearch = new Text(searchComposite, SWT.BORDER);
		FormData fd_nameTextSearch = new FormData();
		fd_nameTextSearch.top = new FormAttachment(label_2, 0, SWT.TOP);
		fd_nameTextSearch.left = new FormAttachment(label_2, 24);
		nameTextSearch.setLayoutData(fd_nameTextSearch);
		
		Label label_5 = new Label(searchComposite, SWT.NONE);
		FormData fd_label_5 = new FormData();
		fd_label_5.top = new FormAttachment(label_2, 0, SWT.TOP);
		fd_label_5.left = new FormAttachment(nameTextSearch, 45);
		label_5.setLayoutData(fd_label_5);
		label_5.setText("家庭住址");
		
		homeAddressSearchText = new Text(searchComposite, SWT.BORDER);
		FormData fd_homeAddressSearchText = new FormData();
		fd_homeAddressSearchText.bottom = new FormAttachment(nameTextSearch, 0, SWT.BOTTOM);
		fd_homeAddressSearchText.left = new FormAttachment(label_5, 37);
		homeAddressSearchText.setLayoutData(fd_homeAddressSearchText);
		
		Button searchBtn = new Button(searchComposite, SWT.NONE);
		FormData fd_searchBtn = new FormData();
		fd_searchBtn.bottom = new FormAttachment(100, -10);
		fd_searchBtn.left = new FormAttachment(label_2, 0, SWT.LEFT);
		searchBtn.setLayoutData(fd_searchBtn);
		searchBtn.setText("搜索");

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

		int totalCount = 100;
		int totalPage = 40;
		int pageSize = 20;
		int pageNum = 1;

		setTableData(columnNames, columnWidthPercents, datas, totalCount, totalPage, pageSize, pageNum);

		tableMenu = new Menu(parentShell, SWT.POP_UP);
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

	public void setTableData(List<String> columnNames, List<Integer> columnWidthPercents, List<List<String>> datas,
			int totalCount, int totalPage, int pageSize, int pageNum) {

		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.pageNum = pageNum;

		pageSizeCombo.select(pageSizeCombo.indexOf(pageSize + ""));
		currentPageText.setText(pageNum + "");
		// currentPageText.setSize(80, currentPageText.getSize().y);
		totalPageLabel.setText(totalPage + "");
		totalCountLabel.setText(totalCount + "");

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
		shell.setToolTipText("Shell \n toolTip");

		PageAbleTableComposite pageAbleTableComposite = new PageAbleTableComposite(shell, shell,SWT.NONE);

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
			Shell addShell = new Shell(parentShell, SWT.APPLICATION_MODAL | SWT.TITLE);
			addShell.addListener(SWT.Traverse, new Listener() {
				public void handleEvent(Event e) {
					if (e.detail == SWT.TRAVERSE_ESCAPE) {
						e.doit = false;
					}
				}
			});
			addShell.setText("新增");
			addShell.setLayout(new FillLayout());
			AddComposite addComposite = new AddComposite(addShell, SWT.NONE);

			addComposite.getSaveBtn().addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {

					Callable<Integer> callable = new Callable<Integer>() {

						@Override
						public Integer call() throws Exception {
							for (long i = 0; i < 10000l; i++) {
								System.out.println(
										"kjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkkkjjkkkkkkkkkkkkkkk");
								System.out.println();
							}
							return 8;
						}
					};
					alertProcessingMessage(addShell, callable);

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {

				}
			});
			addComposite.getCancelBtn().addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					System.out.println("cancelBtn click");
					addShell.close();
					addShell.dispose();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {

				}
			});

			addShell.pack();
			addShell.setSize(800, 600);
			Rectangle parentRectangle = parentShell.getBounds();
			Rectangle rect = addShell.getBounds();
			int x = parentRectangle.x + (parentRectangle.width - rect.width) / 2;
			int y = parentRectangle.y + (parentRectangle.height - rect.height) / 2;
			addShell.setLocation(x, y);
			addShell.open();

		}
		if (event.widget == pageSizeCombo) {
			String text = pageSizeCombo.getText();
			System.out.println(text);
		}
		if (event.widget == currentPageText && event.detail == SWT.TRAVERSE_RETURN) {
			String gotoPage = currentPageText.getText();
			System.out.println(gotoPage);
		}
		if (event.widget == firstPageBtn) {
			System.out.println("firstPageBtn clicked");
		}
		if (event.widget == prePageBtn) {
			System.out.println("prePageBtn clicked");
		}
		if (event.widget == nextPageBtn) {
			System.out.println("nextPageBtn clicked");
		}
		if (event.widget == lastPageBtn) {
			System.out.println("lastPageBtn clicked");
		}

	}

	public void alertProcessingMessage(Shell parentShell, Callable<Integer> callable) {
		getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				Shell alterProcessingMessageShell = new Shell(parentShell, SWT.APPLICATION_MODAL);
				alterProcessingMessageShell.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event e) {
						if (e.detail == SWT.TRAVERSE_ESCAPE) {
							e.doit = false;
						}
					}
				});
				Rectangle parentRectangle = parentShell.getBounds();
				alterProcessingMessageShell.setSize(80, 20);
				alterProcessingMessageShell.setLayout(new FillLayout());
				Text text = new Text(alterProcessingMessageShell, SWT.NONE);
				text.setEditable(false);
				text.setText("处理中...");
				Rectangle rect = alterProcessingMessageShell.getBounds();
				int x = parentRectangle.x + (parentRectangle.width - rect.width) / 2;
				int y = parentRectangle.y + (parentRectangle.height - rect.height) / 2;
				alterProcessingMessageShell.setLocation(x, y);
				alterProcessingMessageShell.open();

				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							int i = callable.call();
							if (i == 8) {
								alertProcessingMessageThreeSecond(parentShell, "操作失败");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								alterProcessingMessageShell.close();
								alterProcessingMessageShell.dispose();
							}
						});

					}
				});
				thread.start();
			}
		});

	}

	public void alertProcessingMessageThreeSecond(Shell parentShell, String message) {
		getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				Shell alterProcessingMessageShell = new Shell(parentShell, SWT.APPLICATION_MODAL);
				alterProcessingMessageShell.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event e) {
						if (e.detail == SWT.TRAVERSE_ESCAPE) {
							e.doit = false;
						}
					}
				});
				Rectangle parentRectangle = parentShell.getBounds();
				alterProcessingMessageShell.setSize(80, 20);
				alterProcessingMessageShell.setLayout(new FillLayout());
				Text text = new Text(alterProcessingMessageShell, SWT.NONE);
				text.setEditable(false);
				text.setText(message);
				Rectangle rect = alterProcessingMessageShell.getBounds();
				int x = parentRectangle.x + (parentRectangle.width - rect.width) / 2;
				int y = parentRectangle.y + (parentRectangle.height - rect.height) / 2;
				alterProcessingMessageShell.setLocation(x, y);
				alterProcessingMessageShell.open();

				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								alterProcessingMessageShell.close();
								alterProcessingMessageShell.dispose();
							}
						});

					}
				});
				thread.start();
			}
		});

	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Composite getPageComposite() {
		return pageComposite;
	}

	public void setPageComposite(Composite pageComposite) {
		this.pageComposite = pageComposite;
	}

	public Composite getTableComposite() {
		return tableComposite;
	}

	public void setTableComposite(Composite tableComposite) {
		this.tableComposite = tableComposite;
	}

	public Composite getSearchComposite() {
		return searchComposite;
	}

	public void setSearchComposite(Composite searchComposite) {
		this.searchComposite = searchComposite;
	}

	public ViewForm getViewForm() {
		return viewForm;
	}

	public void setViewForm(ViewForm viewForm) {
		this.viewForm = viewForm;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public Menu getTableMenu() {
		return tableMenu;
	}

	public void setTableMenu(Menu tableMenu) {
		this.tableMenu = tableMenu;
	}

	public Composite getParent() {
		return parent;
	}

	public Shell getParentShell() {
		return parentShell;
	}

	public void setParentShell(Shell parentShell) {
		this.parentShell = parentShell;
	}

	public Point getItemMouseDownPoint() {
		return itemMouseDownPoint;
	}

	public void setItemMouseDownPoint(Point itemMouseDownPoint) {
		this.itemMouseDownPoint = itemMouseDownPoint;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public ToolItem getAdd() {
		return add;
	}

	public void setAdd(ToolItem add) {
		this.add = add;
	}

	public Combo getPageSizeCombo() {
		return pageSizeCombo;
	}

	public void setPageSizeCombo(Combo pageSizeCombo) {
		this.pageSizeCombo = pageSizeCombo;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Text getCurrentPageText() {
		return currentPageText;
	}

	public void setCurrentPageText(Text currentPageText) {
		this.currentPageText = currentPageText;
	}

	public Label getLabel_1() {
		return label_1;
	}

	public void setLabel_1(Label label_1) {
		this.label_1 = label_1;
	}

	public Label getTotalPageLabel() {
		return totalPageLabel;
	}

	public void setTotalPageLabel(Label totalPageLabel) {
		this.totalPageLabel = totalPageLabel;
	}

	public Label getLabel_3() {
		return label_3;
	}

	public void setLabel_3(Label label_3) {
		this.label_3 = label_3;
	}

	public Label getLabel_4() {
		return label_4;
	}

	public void setLabel_4(Label label_4) {
		this.label_4 = label_4;
	}

	public Label getTotalCountLabel() {
		return totalCountLabel;
	}

	public void setTotalCountLabel(Label totalCountLabel) {
		this.totalCountLabel = totalCountLabel;
	}

	public Label getLabel_6() {
		return label_6;
	}

	public void setLabel_6(Label label_6) {
		this.label_6 = label_6;
	}

	public Button getFirstPageBtn() {
		return firstPageBtn;
	}

	public void setFirstPageBtn(Button firstPageBtn) {
		this.firstPageBtn = firstPageBtn;
	}

	public Button getPrePageBtn() {
		return prePageBtn;
	}

	public void setPrePageBtn(Button prePageBtn) {
		this.prePageBtn = prePageBtn;
	}

	public Button getNextPageBtn() {
		return nextPageBtn;
	}

	public void setNextPageBtn(Button nextPageBtn) {
		this.nextPageBtn = nextPageBtn;
	}

	public Button getLastPageBtn() {
		return lastPageBtn;
	}

	public void setLastPageBtn(Button lastPageBtn) {
		this.lastPageBtn = lastPageBtn;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void processingShellMessage(Shell parentShell, Shell childShell, String message) {
		Rectangle parentRectangle = parentShell.getBounds();
		childShell.setSize(80, 20);
		childShell.setLayout(new FillLayout());
		Text text = new Text(childShell, SWT.NONE);
		text.setText(message);
		Rectangle rect = childShell.getBounds();
		int x = parentRectangle.x + (parentRectangle.width - rect.width) / 2;
		int y = parentRectangle.y + (parentRectangle.height - rect.height) / 2;
		childShell.setLocation(x, y);
		childShell.open();
	}
}
