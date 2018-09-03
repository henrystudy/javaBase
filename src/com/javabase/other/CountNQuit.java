package com.javabase.other;

/**
* 给定m长度的数组，数n个数退掉一个，求最后剩下的数的原下标
*
*
**/

public class CountNQuit
{
	public static void main(String[] args)
	{
		CountNQuit cnq= new CountNQuit();
		System.out.println(cnq.countNQuit(40,3));
	}
	
	public int countNQuit(int m, int n)
	{
		int result = -1;
		if(m >= n)
		{
			boolean[] arr = new boolean[m];
			for(int i=0; i<arr.length; i++)
			{
				arr[i] = true;
			}
			
			int leftCount = arr.length;//剩余个数
			int countNum = 0;//计数器
			int index = 0;//第几个，动态index
			
			
			while(leftCount > 1)
			{
				if(arr[index] == true)
				{
					countNum ++;
					if(countNum == n)
					{
						arr[index] = false;
						countNum = 0;
						leftCount--;
					}
				}
				index++;
				
				if(index == arr.length)
				{
					index = 0;
				}
			}
			
			for(int i=0; i<arr.length; i++)
			{
				if(arr[i] == true)
				{
					result = i;
				}
			}
		}else
		{
			result = -1;
			System.out.println("Usage: countNQuit(m, n), m >= n.");
		}
		return result;
	}
}