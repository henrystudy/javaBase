package com.javabase.corejava.part1;

import java.lang.reflect.Array;

public class Chap5ArrayGrowTest {
	public static void main(String[]  args)
	{
		Chap5ArrayGrowTest gr = new Chap5ArrayGrowTest();
		
		int[] a = {1, 2, 3};
		a = (int[])gr.goodArrayGrow(a);
		gr.printArray(a);
		
		String[] b = {"Tom", "Jerry", "Bob"};
		b = (String[])gr.goodArrayGrow(b);
		gr.printArray(b);
		
		System.out.println("The following call will generate an exception.");
		b = (String[] )gr.badArrayGrow(b);
	}
	
	
	/**
	 * This method attempts to grow an array by allocating a new array and copying all elements.
	 * @param a the original array to grow
	 * @return a larger array that contains all elements of a. However, the returned array has 
	 * type Object[]. not the same type as a
	 */
	public Object[] badArrayGrow(Object[] a)
	{
		int newLength = a.length * 11/10 + 10;
		Object[] newArray = new Object[newLength];
		System.arraycopy(a, 0, newArray, 0, a.length);
		return newArray;
	}
	
	/**
	 * This method grows an array by allocating a new array of the same type and copying all elements.
	 * @param a the array to grow. This can be an object array or a primitive.
	 * @return a larger array that contains all elements of a and with same type of a.
	 */
	public Object goodArrayGrow(Object a)
	{
		//首先获得数组a的类对象
		Class cl = a.getClass();
		//判断是不是数组
		if(!cl.isArray())
		{
			return null;
		}
		//获取数组的数据类型
		Class componentType = cl.getComponentType();
		int length = Array.getLength(a);
		int newLength = length * 11/10 + 10;
		
		//声明一个扩展长度的同类型的数组
		Object newArray = Array.newInstance(componentType, newLength);
		//copy原来的数组到新的数组
		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}
	
	/**
	 * A convenience method to print all elements in a array
	 * @param a the array to print. It can be an object array or a primitive type array
	 */
	public void printArray(Object a)
	{
		Class cl = a.getClass();
		if(!cl.isArray())
		{
			return;
		}
		Class componentType = cl.getComponentType();
		int length = Array.getLength(a);
		System.out.print(componentType.getName() + "[" + length + "]{");
		for(int i = 0;i < length;i++)
		{
			System.out.print(Array.get(a, i) + " ");
		}
		System.out.println("}");
	}
}
