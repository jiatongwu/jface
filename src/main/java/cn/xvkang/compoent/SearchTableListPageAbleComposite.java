package cn.xvkang.compoent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import cn.xvkang.dto.Student;

public class SearchTableListPageAbleComposite extends Composite {

	private TableViewer table;// 声明一个表格对象
	private List<Student> students;
	// 表格中列的索引号
	public static final int ID = 0;
	public static final int AGE = 1;
	public static final int NAME = 2;
	public static final int REG_CODE = 3;
	public static final int PHONE = 4;
	public static final String[] COLUMN_NAMES = { "id", "姓名", "年龄", "报名号", "手机号" };

	// 初始化数据 通常是从数据库中读取出来的
	public void initStudents() {
		students = new ArrayList<>();
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("张三");
		s1.setAge(22);
		s1.setRegCode("regCode1");
		s1.setPhone("18283928392");

		Student s2 = new Student();
		s2.setId(1);
		s2.setName("李四");
		s2.setAge(23);
		s2.setRegCode("regCode2");
		s2.setPhone("18283928393");

		students.add(s1);
		students.add(s2);
	}

	public SearchTableListPageAbleComposite(Composite parent, int style) {
		super(parent, style);
//		Button button=new Button(this, SWT.NONE);
//		button.setText("点击");
		
		
		initStudents();
		// 创建 TableViewer对象
		table = new TableViewer(this, SWT.FULL_SELECTION);
		Table tableViewerTable = table.getTable();
		// 创建表头
		for (int i = 0; i < COLUMN_NAMES.length; i++) {
			new TableColumn(tableViewerTable, SWT.LEFT).setText(COLUMN_NAMES[i]);
			tableViewerTable.getColumn(i).pack();
		}
		tableViewerTable.setHeaderVisible(true);
		tableViewerTable.setLinesVisible(true);
		// 设置数据
		table.setContentProvider(new MyTableViewerContentProvider());
		// 设置视图
		table.setLabelProvider(new MyTableViewerLabelProvider());
		// 设置表格数据对象 该方法非常重要是表格数据入口
		table.setInput(students);
		
		
	}

}
