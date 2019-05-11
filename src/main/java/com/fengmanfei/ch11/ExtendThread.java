package com.fengmanfei.ch11;

public class ExtendThread extends Thread {
	int count = 0;
	public void run() {
		while (count <10) {
			System.out.println("Extend Thread");
			count++;
		}
	}
}
