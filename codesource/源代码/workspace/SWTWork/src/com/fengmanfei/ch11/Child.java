package com.fengmanfei.ch11;

public class Child extends Parent implements Runnable{
	int count = 0;
	public void run() {
		while (count <10) {
			System.out.println("Implements Thread");
			count++;
		}
	}
}
