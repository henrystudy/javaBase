package com.javabase.factory;

/***
 * 简单的线程安全
 * 这种写法考虑了线程安全，将对singleton的null判断以及new的部分使用synchronized进行加锁。同时，对singleton对象使用volatile关键字进行限制，
 * 保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。
 * 如此即可从语义上保证这种单例模式写法是线程安全的。注意，这里说的是语义上，实际使用中还是存在小坑的，会在后文写到。
 * 注意*********************************************
 * 禁止指令重排优化这条语义直到jdk1.5以后才能正确工作。此前的JDK中即使将变量声明为volatile也无法完全避免重排序所导致的问题。
 * 所以，在jdk1.5版本前，双重检查锁形式的单例模式是无法保证线程安全的。
 * *****************************************************************
 * @author zhenhaiw
 *
 */

public class Singleton3
{
	//volatile关键字的两层语义
	//	一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
	//	1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
	//	2）禁止进行指令重排序。
	private static volatile Singleton3 instance = null;

	private Singleton3()
	{
		
	}

	public static Singleton3 getInstance()
	{
		synchronized (Singleton3.class)
		{
			if (instance == null)
				instance = new Singleton3();
		}
		return instance;
	}

	public static void main(String[] args)
	{
		Singleton3 s1 = Singleton3.getInstance();
		Singleton3 s2 = Singleton3.getInstance();

		System.out.println("s1 == s2: " + (s1 == s2));
		System.out.println(s1.toString());
		System.out.println(s2.toString());
	}
}
