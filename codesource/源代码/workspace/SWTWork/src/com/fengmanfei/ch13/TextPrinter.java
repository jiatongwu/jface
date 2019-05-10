package com.fengmanfei.ch13;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Text;

public class TextPrinter extends Thread {

	private Text content;//显示字符的文本框对象
	private Printer printer;//打印对象
	private GC gc;//用于绘制的图形上下文对象
	private FontData[] printerFontData;//打印的字体数据
	private RGB printerForeground, printerBackground;//打印的前景色和背景色
	private int lineHeight = 0;//打印的行高
	private int tabWidth = 0;//Tab键的大小
	private int leftMargin, rightMargin, topMargin, bottomMargin;//上下左右的页边距
	private int x, y;//打印的行和列的值
	private int index, end;//字符当前打印的索引值和总长度
	private String textToPrint;//需要打印的字符
	private String tabs;//Tab键
	private StringBuffer wordBuffer;//打印字符的临时变量

	public TextPrinter(String name, Text text, Printer printer) {
		super(name);
		this.content = text;
		this.printer = printer;
		init();
	}
	//初始化属性的方法
	public void init() {
		textToPrint = content.getText();
		printerFontData = content.getFont().getFontData();
		printerForeground = content.getForeground().getRGB();
		printerBackground = content.getBackground().getRGB();
		//默认一个Tab键表示4个空格
		int tabSize = 4; 
		StringBuffer tabBuffer = new StringBuffer(tabSize);
		for (int i = 0; i < tabSize; i++)
			tabBuffer.append(' ');
		tabs = tabBuffer.toString();
		
		//计算上下左右边距值
		Rectangle clientArea = printer.getClientArea();
		Rectangle trim = printer.computeTrim(0, 0, 0, 0);
		Point dpi = printer.getDPI();
		leftMargin = dpi.x + trim.x; 
		rightMargin = clientArea.width - dpi.x + trim.x + trim.width; 
		topMargin = dpi.y + trim.y; 
		bottomMargin = clientArea.height - dpi.y + trim.y + trim.height; 
	}

	public void run() {
		//启动打印，如果不能正常启动则返回
		if (!printer.startJob("Text Print"))
			return;
		//开始打印
		print();
	}

	public void print() {
		//创建图形上下文对象
		gc = new GC(printer);
		//创建字体，前景色，背景色对象
		Font printerFont = new Font(printer, printerFontData);
		Color printerForegroundColor = new Color(printer, printerForeground);
		Color printerBackgroundColor = new Color(printer, printerBackground);
		//将字体，前景色，背景色设置给gc对象
		gc.setFont(printerFont);
		gc.setForeground(printerForegroundColor);
		gc.setBackground(printerBackgroundColor);
		tabWidth = gc.stringExtent(tabs).x;
		lineHeight = gc.getFontMetrics().getHeight();
		//打印字符
		printText();
		//结束打印工作
		printer.endJob();
		//释放资源
		printerFont.dispose();
		printerForegroundColor.dispose();
		printerBackgroundColor.dispose();
		gc.dispose();
	}

	void printText() {
		//开始打印一页
		printer.startPage();
		wordBuffer = new StringBuffer();
		x = leftMargin;
		y = topMargin;
		index = 0;
		end = textToPrint.length();
		//循环每个字符串的每个字符
		while (index < end) {
			char c = textToPrint.charAt(index);
			System.out.println(c);
			index++;
			if (c == 0)
				continue;
			//如果字符是\n换行符或\r回车符
			if (c == '\n' || c == '\r'){
				//如果字符\r\n同时出现，则只打印一行
				if (c == '\r' && index < end && textToPrint.charAt(index) == '\n') {
					index++; 
				}
				printWordBuffer();
				newline();
			} else {
				//如果字符是不是Tab键，则打印出字符
				if (c != '\t') {
					wordBuffer.append(c);
				}
				if (Character.isWhitespace(c)) {
					printWordBuffer();
					if (c == '\t') {//Tab键
						x += tabWidth;
					}
				}
			}
		}
		if (y + lineHeight <= bottomMargin) {
			printer.endPage();
		}
	}
	//打印字符，要判断是否已经到了行的末尾，如果到了末尾需要换行
	void printWordBuffer() {
		if (wordBuffer.length() > 0) {
			String word = wordBuffer.toString();
			int wordWidth = gc.stringExtent(word).x;
			if (x + wordWidth > rightMargin) {
				newline();
			}
			gc.drawString(word, x, y, false);
			x += wordWidth;
			wordBuffer = new StringBuffer();
		}
	}
	//换行，如果到了页尾，需要打印下一页
	void newline() {
		x = leftMargin;
		y += lineHeight;
		if (y + lineHeight > bottomMargin) {
			printer.endPage();
			if (index + 1 < end) {
				y = topMargin;
				printer.startPage();
			}
		}
	}

}
