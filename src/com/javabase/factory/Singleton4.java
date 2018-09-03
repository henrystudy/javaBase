package com.javabase.factory;

/***
 * 简单的线程安全(提高效率)
 * 就是在getSingleton()方法中，进行两次null检查。看似多此一举，但实际上却极大提升了并发度，进而提升了性能。为什么可以提高并发度呢？
 * 就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
 * 因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了。
 * @author zhenhaiw
 *
 */

public class Singleton4
{
	private static volatile Singleton4 instance = null;

	private Singleton4()
	{
		
	}

	public static Singleton4 getInstance()
	{
		if (instance == null)  //再次判断null后再操作
		{
			synchronized (Singleton4.class)
			{
				instance = new Singleton4();
			}
		}
		return instance;
	}

	public static void main(String[] args)
	{
		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();

		System.out.println("s1 == s2: " + (s1 == s2));
		System.out.println(s1.toString());
		System.out.println(s2.toString());
	}
}
