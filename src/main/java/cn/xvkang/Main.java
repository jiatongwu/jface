package cn.xvkang;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class Main extends ApplicationWindow{
	
	public Main() {
		super(null);
	}
	

	@Override
	protected Control createContents(Composite parent) {
		getShell().setText("window");
		Button button=new Button(parent, SWT.CENTER);
		button.setText("press");
		parent.pack();
		return parent;
	}


	public static void main(String[] args) {
		
		Main main=new Main();
		main.setBlockOnOpen(true);
		main.open();
		Display.getCurrent().dispose();
		
		
	}

}
