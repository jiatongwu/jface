package com.fengmanfei.ch13;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

public class SimplePrint {
	public static void main(String[] args) {
		PrinterData data = Printer.getDefaultPrinterData();
		if (data == null) {
			return;
		}
		Printer printer = new Printer(data);
		//开始打印任务
		if (printer.startJob("Simple Print")) {
			Color black = printer.getSystemColor(SWT.COLOR_BLACK);
			Color gray = printer.getSystemColor(SWT.COLOR_GRAY);
			Color red = printer.getSystemColor(SWT.COLOR_RED);
			//计算左边踞和上边距的位置
			Rectangle trim = printer.computeTrim(0, 0, 0, 0);
			Point dpi = printer.getDPI();
			int leftMargin = dpi.x + trim.x;
			int topMargin = dpi.y / 2 + trim.y;
			//创建图形上下文对象
			GC gc = new GC(printer);
			Font font = gc.getFont();
			//打印第一页
			if (printer.startPage()) {
				gc.setBackground(gray);
				gc.setForeground(black);
				String testString = "Hello World!";
				//使用gc对象画字符串的方法显示字符
				gc.drawString(testString, leftMargin, topMargin + font.getFontData()[0].getHeight());
				printer.endPage();
			}
			//打印第二页
			if (printer.startPage()) {
				gc.setBackground(gray);
				gc.setForeground(black);
				String testString = "Hello World!";
				Point extent = gc.stringExtent(testString);
				gc.drawString(testString, leftMargin, topMargin + font.getFontData()[0].getHeight());
				gc.setForeground(red);
				gc.drawRectangle(leftMargin, topMargin, extent.x, extent.y);
				printer.endPage();
			}
			//释放gc对象
			gc.dispose();
			//结束打印任务
			printer.endJob();
		}
		printer.dispose();
	}
}
