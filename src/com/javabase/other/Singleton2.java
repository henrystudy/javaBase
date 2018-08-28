package com.javabase.other;

/***
 * 懒汉法(单线程)
 * 这种写法是最简单的，由私有构造器和一个公有静态工厂方法构成，在工厂方法中对singleton进行null判断，
 * 如果是null就new一个出来，最后返回singleton对象。
 * 这种方法可以实现延时加载，但是有一个致命弱点：线程不安全。如果有两条线程同时调用getSingleton()方法，就有很大可能导致重复创建对象。
 * @author zhenhaiw
 *
 */
public class Singleton2
{
	private static Singleton2 instance = null;
	
	private Singleton2()
	{
		
	}
	
	public static Singleton2 getInstance()
	{
		if(instance == null)
			instance = new Singleton2();
		return instance;
	}
	
	public static void main(String[] args)
	{
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		
		System.out.println("s1 == s2: " + (s1 == s2));
		System.out.println(s1.toString());
		System.out.println(s2.toString());
	}
}
