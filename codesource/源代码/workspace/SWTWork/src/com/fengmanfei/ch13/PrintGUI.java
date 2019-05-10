package com.fengmanfei.ch13;

import java.io.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.printing.*;
import org.eclipse.swt.widgets.*;

public class PrintGUI {

	private Shell sShell ;
	private Menu menu ;
	private Text content ;
	Font font;
	Color foregroundColor, backgroundColor;
	private void createMenu() {
		menu = new Menu ( sShell , SWT.BAR);
		sShell.setMenuBar( menu );
		MenuItem item = new MenuItem(menu, SWT.CASCADE);
		item.setText("文件(&F)");
		Menu fileMenu = new Menu(sShell, SWT.DROP_DOWN);
		item.setMenu(fileMenu);
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("打开(&O)");
		item.setAccelerator(SWT.CTRL + 'O');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				menuOpen();
			}
		});
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("选择字体");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				menuFont();
			}
		});
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("选择前景色");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				menuForegroundColor();
			}
		});
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("选择背景色");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				menuBackgroundColor();
			}
		});
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("打印(@P)");
		item.setAccelerator(SWT.CTRL + 'P');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				menuPrint();
			}
		});
		new MenuItem(fileMenu, SWT.SEPARATOR);
		item = new MenuItem(fileMenu, SWT.PUSH);
		item.setText("退出(&E)");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				font.dispose();
				foregroundColor.dispose();
				backgroundColor.dispose();
				System.exit(0);
			}
		});
		
	}
	protected void menuPrint() {
		PrintDialog dialog = new PrintDialog(sShell, SWT.NONE);
		PrinterData data = dialog.open();
		if (data == null) return;
		if (data.printToFile) {
			data.fileName = "print.out";
		}	
		Printer printer = new Printer(data);
		TextPrinter textPrinter = new TextPrinter( "Print Job", content , printer);
		textPrinter.start();
	}
	protected void menuBackgroundColor() {
		ColorDialog colorDialog = new ColorDialog(sShell);
		colorDialog.setRGB(content.getBackground().getRGB());
		RGB rgb = colorDialog.open();
		if (rgb != null) {
			if (backgroundColor != null) backgroundColor.dispose();
			backgroundColor = new Color(sShell.getDisplay(), rgb);
			content.setBackground(backgroundColor);
		}
		
	}
	protected void menuForegroundColor() {
		ColorDialog colorDialog = new ColorDialog(sShell);
		colorDialog.setRGB(content.getForeground().getRGB());
		RGB rgb = colorDialog.open();
		if (rgb != null) {
			if (foregroundColor != null) foregroundColor.dispose();
			foregroundColor = new Color(sShell.getDisplay(), rgb);
			content.setForeground(foregroundColor);
		}
		
	}
	protected void menuFont() {
		FontDialog fontDialog = new FontDialog(sShell);
		fontDialog.setFontList(content.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null) font.dispose();
			font = new Font(sShell.getDisplay(), fontData);
			content.setFont(font);
		}
		
	}
	protected void menuOpen() {
		final String textString;
		FileDialog dialog = new FileDialog(sShell, SWT.OPEN);
		dialog.setFilterExtensions(new String[] {"*.java", "*.*"});
		String name = dialog.open();
		if ((name == null) || (name.length() == 0)) return;
	
		try {
			File file = new File(name);
			FileInputStream stream= new FileInputStream(file.getPath());
			try {
				Reader in = new BufferedReader(new InputStreamReader(stream));
				char[] readBuffer= new char[2048];
				StringBuffer buffer= new StringBuffer((int) file.length());
				int n;
				while ((n = in.read(readBuffer)) > 0) {
					buffer.append(readBuffer, 0, n);
				}
				textString = buffer.toString();
				stream.close();
			} catch (IOException e) {
				MessageBox box = new MessageBox(sShell, SWT.ICON_ERROR);
				box.setMessage("读文件出错:\n" + name);
				box.open();
				return;
			}
		} catch (FileNotFoundException e) {
			MessageBox box = new MessageBox(sShell, SWT.ICON_ERROR);
			box.setMessage("文件未找到:\n" + name);
			box.open();
			return;
		}	
		content.setText(textString);
	}
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setLayout(new FillLayout());
		sShell.setSize(new Point(300, 200));
		content = new Text(sShell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		createMenu();
	}
	public static void main(String[] args) {
		Display display = Display.getDefault();
		PrintGUI thisClass = new PrintGUI();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
