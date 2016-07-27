package com.javabase.corejava.part1;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Chap12PairTest2 
{

	public static void main(String[] args) 
	{
		GregorianCalendar[] birthday = 
			{
				new GregorianCalendar(1902, Calendar.DECEMBER, 9),
				new GregorianCalendar(1985, Calendar.JANUARY, 12),
				new GregorianCalendar(1995, Calendar.MARCH, 3),
			};
		
		Pair<GregorianCalendar> mm = ArrayAlgT.minmax(birthday);
		System.out.println("MIN = " + mm.getFirst());
		System.out.println("MAX = " + mm.getSecond());
		
	}

}

class ArrayAlgT
{
	/**
	 * Get the min and max of a n array of objects of type T.
	 * @param a an array of objects of type T.
	 * @return a pair with the mi and max value, or null if a is null or empty
	 */
	public static <T extends Comparable> Pair<T> minmax(T[] a)
	{
		if(a == null || a.length == 0)
			return null;
		T min = a[0];
		T max = a[0];
		
		for(int i = 0; i < a.length; i ++)
		{
			if(min.compareTo(a[i]) > 0)
				min = a[i];
			if(max.compareTo(a[i]) < 0)
				max = a[i];
		}
		
		return new Pair<T> (min, max);
		
	}
}

/**
 * 定义一个泛型类Pair, 两个泛型返回值
 * @author zhenhaiw
 *
 * @param <T> 泛型数据类型
 */
class Pair<T>
{
	public Pair(T first, T second)
	{
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() 
	{
		return first;
	}
	
	public T getSecond() 
	{
		return second;
	}
	
	public T setFirst(T first) 
	{
		return this.first = first;
	}
	
	public T setSecond(T second) 
	{
		return this.second = second;
	}
	
	private T first;
	private T second;
}