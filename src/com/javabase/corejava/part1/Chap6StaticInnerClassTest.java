package com.javabase.corejava.part1;

import java.util.Arrays;

/**
 * This program demonstrates the use of static inner classes
 * @author zhenhaiw
 *
 */
public class Chap6StaticInnerClassTest 
{

	public static void main(String[] args) 
	{
		double[] numbers = new double[30];
		for(int i = 0; i < numbers.length; i++)
		{
			numbers[i] = 100 * Math.random();
			System.out.printf("%2.2f, ", numbers[i]);
			
		}
		
		System.out.println();
		
		ArrayAlg.Pair pair = ArrayAlg.minmax(numbers);
		System.out.println("MIN = " + pair.getMin());
		System.out.println("MAX = " + pair.getMax());
//		格式化输出为什么出现值不正确?
//		System.out.printf("MIN = %2.2f",  pair.getMin());
//		System.out.printf("MAX = %2.2f", pair.getMax());
	}

}

class ArrayAlg
{
	/**
	 * A pair of floating point numbers
	 * @author zhenhaiw
	 *
	 */
	public static class Pair
	{
		/**
		 * Construct a pair from two floating point numbers
		 * @param min
		 * @param max
		 */
		public Pair(double min, double max)
		{
			this.max = max;
			this.min = min;
		}
		
		public double getMax()
		{
			return max;
		}
		
		public double getMin()
		{
			return min;
		}
		
		private double min;
		private double max;
	}
	
	/**
	 * Computes both the minimum and maximum numbers of an array
	 * @param values an array of floating numbers
	 * @return a pair with min and max value
	 */
	public static Pair minmax(double[] values)
	{
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		for(double v : values)
		{
			if(min > v)
				min = v;
			if(max < v)
				max = v;
		}
		return new Pair(min, max);//Return new Pair, 注意必须是对象实例
	}
}