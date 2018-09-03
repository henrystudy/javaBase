package com.javabase.factory;
/***
 * 饿汉法
 * 顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。
 * 这样做的好处是编写简单，但是无法做到延迟创建对象。但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载
 * @author zhenhaiw
 *
 */

public class Singleton1
{
	private static Singleton1 instance = new Singleton1();
	
	private Singleton1()
	{
		
	}
	
	public static Singleton1 getInstance()
	{
		return instance;
	}
	
	public static void main(String[] args)
	{
		Singleton1 s1 = Singleton1.getInstance();
		Singleton1 s2 = Singleton1.getInstance();
		
		System.out.println("s1 == s2: " + (s1 == s2));
		System.out.println(s1.toString() + "\n" + s2.toString());
	}	
}
