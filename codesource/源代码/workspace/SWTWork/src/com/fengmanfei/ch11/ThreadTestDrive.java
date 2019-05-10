package com.fengmanfei.ch11;

public class ThreadTestDrive {
	public static void main(String[] args) {
		System.out.println("Hello");
		//创建线程对象
		ExtendThread extendThread = new ExtendThread ();
		//启动线程后开始调用线程对象中的run()方法体
		extendThread.start();	
		//创建Child对象，Child类实现了Runnable接口
		Child child = new Child();
		//创建线程对象，将child对象作为构造参数
		Thread implementThread = new Thread ( child );
		implementThread.start();
		System.out.println("World!");
	}
}
