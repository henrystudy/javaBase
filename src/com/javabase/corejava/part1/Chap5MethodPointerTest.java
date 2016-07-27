package com.javabase.corejava.part1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Chap5MethodPointerTest {

	public static void main(String[] args) 
	{	
		try {
			//get method pointer to the methods square and sqrt
			Method square = Chap5MethodPointerTest.class.getMethod("square", double.class);
			Method sqrt = Math.class.getMethod("sqrt", double.class);
			Method pow = Math.class.getMethod("pow", double.class, double.class);//参数类型, 个数要匹配(尤其注意多个参数情况)
			
			//print tables of square and sqrt methods
			printTable1(1, 10, 5, square);
			printTable1(1, 10, 10, sqrt);
			printTable2(1, 10, 10, pow);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static double square(double x)
	{
		return x * x;
	}
	
	/**
	 * Print a table with x-value and y-result from a method
	 * @param from the lower bound for the x-value
	 * @param to the upper bound for the x-value
	 * @param n the number of rows int the table
	 * @param m a method with a double parameter and double return value
	 */
	public static void printTable1(double from, double to, int n, Method m)
	{
		//print method name as table header
		System.out.println(m);
		
		double dx = (to - from)/(n - 1);//根据行数n设置等距间隔
		for(double x = from; x <=to; x += dx)
		{
			try {
				double y = (Double)m.invoke(null, x);
				System.out.printf("%10.4f| %10.4f%n", x, y);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void printTable2(double from, double to, int n, Method m)
	{
		//print method name as table header
		System.out.println(m);
		
		double dx = (to - from)/(n - 1);//根据行数n设置等距间隔
		for(double x = from; x <= to; x += dx)
		{
			for(double y = from; y <= x; y += dx)
			{
				try 
				{
					double z = (Double)m.invoke(null, x, y);
					System.out.printf("%10.4f| %10.4f| %10.4f%n", x, y, z);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
