package com.fengmanfei.ch8;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class EventSample {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout( new FillLayout());
		shell.setText("Simple Event");
		
		final List list = new List ( shell , SWT.NONE);
		for ( int i=0;i<10;i++)
			list.add("Item"+i);
		list.setData( shell );
		SelectionListener listener = new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				System.out.println("widgetSelected事件");
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefaultSelected事件");
				
			}
		};
		list.addSelectionListener(listener);
		list.removeSelectionListener(listener);
		shell.pack();
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	public class MyAdapter extends  SelectionAdapter{

		public void widgetSelected(SelectionEvent e) {
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
		
	}

}
