package com.javabase.corejava.part1;

import java.util.Scanner;

/**
 * a program that displays a trace feature of a recursive method call.
 * @author zhenhaiw
 *
 */
public class Chap11StackTraceTest 
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter n: ");
		int n = in.nextInt();
		int fn = factorial(n);
		System.out.println("Finally factorial(" + n + ") = " + fn);
	}
	
	/**
	 * Computes the factorial of a number
	 * @param n a nonnegative integer
	 * @return n!= 1 * 2 * 3 * ... * n
	 */
	public static int factorial(int n)
	{
		System.out.println("factorial(" + n + "): ");
		Throwable e = new Throwable();
		StackTraceElement[] frames = e.getStackTrace();
		for(StackTraceElement f : frames)
		{
			System.out.println(f);
		}
		
		int r = 1;
		if(n <= 1)
			return r;
		else
		{
			//理解逻辑: n = 1, f(1) = 1; n = 2, f(2) = f(1) * 2 = 2; n=3, f(3) = f(2) * 3 = 6 ...
			r = n * factorial(n - 1);
//			for(int i = 1; i <= n; i++)
//			{
//				r *= i;
//			}
		}
		System.out.println("return " + r);
		return r; 
	}
	
}

