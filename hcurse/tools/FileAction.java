package hcurse.tools;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileAction {

	private Scanner x;
	
	
	
	public void openFile() {
		try {
			x = new Scanner(new File("res/NameList.txt"));
		}catch (Exception e) {
			System.out.println("Could not find File");
		}
	}
	
	public Vector<String> readFile() {
		Vector<String> vstr = new Vector<String>();
		
		while(x.hasNext()) {
			vstr.add(x.next());
		}
		return vstr;
	}
	
}
	

