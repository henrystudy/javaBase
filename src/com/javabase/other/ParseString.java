package com.javabase.other;

/**
* 通过解析给定的字符串（特定的两种分隔符），返回double二维数组，
* 主要练习String以及封装型Double的方法
**/

public class ParseString
{
	public static void main(String[] args)
	{
		String str = "1,2;3,4,5;6,7,8";
		
		ParseString pStr = new ParseString();
		pStr.parseStrToDouble(str, ";", ",");
	
	}
	
	//给定一个有两种特定分隔符的字符串，按每种分割符二次切割为二维数组，并转化为double输出
	void parseStrToDouble(String sourceStr, String separator1, String separator2)
	{
		String[] s1 = sourceStr.split(separator1);
		
		double[][] result = new double[s1.length][];
		//char[] char = new char[temp.length];
		for(int i=0; i<s1.length; i++)
		{
			String[] s2 = s1[i].split(separator2);
			result[i] = new double[s2.length];//注意这里不要漏掉二维数组的低维初始化(理解：数组的数组，凡是数组需要初始化)
			for(int j=0; j<s2.length; j++)
			{
				result[i][j] = Double.parseDouble(s2[j]);
				System.out.print("result [" + i + "][" + j + "] =" + result[i][j] + " ");
			}
			System.out.println();
		}
	}
}