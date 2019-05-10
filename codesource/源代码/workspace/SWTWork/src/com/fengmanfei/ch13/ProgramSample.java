package com.fengmanfei.ch13;

import org.eclipse.swt.program.*;
import org.eclipse.swt.widgets.Display;

public class ProgramSample {
	public static void main(String[] args) {
		Display display = new Display ();
		String[] s = Program.getExtensions();
		for (int i =0;i<s.length;i++){
			System.out.println(s[i]);
		}
		Program[] programs =Program.getPrograms();
		for (int i =0;i<programs.length;i++){
			System.out.println(programs[i].getName());
		}
		Program p = Program.findProgram (".pdf");
		if (p != null) 
			p.execute ("F:\\Text Print.pdf");
		Program.launch("D:\\Program Files\\Internet Explorer\\IEXPLORE.EXE");
		display.dispose ();
	}
}
