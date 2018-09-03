package com.javabase.other;

/**
* String方法练习
* 1) 输出字符串中小写，大写，非字母的个数
* 2) 输出字符串中特定字符串出现的次数
**/

public class CaseOutput
{
	public static void main(String[] args)
	{
		CaseOutput co = new CaseOutput();
		
		/*
		String s = "sD3Ds$ sDD%%";
	
		int[] result = co.caseOutput(s);
		for(int r : result)
		{
			System.out.println(r);
		}
		*/
		
		String str = "ajavaxxssjavaxdsdfsfjava###java";
		String subStr = "a";
		
		System.out.println(co.countStr2(str,subStr));
	}
	
	//分别输出大写字母，小写字母，非字母的个数
	int[] caseOutput(String str)
	{
		int upperCase = 0;
		int lowerCase = 0;
		int nonCase = 0;
		
		String s = null;
		for(int i=0; i<str.length(); i++)
		{
			s = String.valueOf(str.charAt(i));
			//char c >= 'a' && c <= 'z'也可以
			//使用char包装类Charactor的方法isUpperCase()/isLowerCase()也行
			//定义String x = "abcd...xyz"; Str.startsWith()也可以
			if(s.matches("[a-z]"))
			{
				upperCase ++;
			}else if(s.matches("[A-Z]"))			
			{
				lowerCase ++;
			}else
			{
				nonCase ++;
			}
		}
		
		int[] result = {upperCase, lowerCase, nonCase};
		return result;
		
	}
	
	//输出字符串中特定字符串出现的次数#1
	int countStr1(String str, String substr)
	{
		int countNum = 0;
		int substrL = substr.length();
		String temp = null;
		
		for(int i=0; i<str.length()-substrL+1; i++)
		{
			temp = str.substring(i,i+substrL);
			if(temp.equals(substr))//substring全小写
			{
				countNum ++;
			}
		}
		return countNum;
		
	}
	
	//输出字符串中特定字符串出现的次数#2
	int countStr2(String str, String substr)
	{
		int countNum = 0;
		int index = -1;
		
		while((index = str.indexOf(substr)) != -1)
		{
			//index = str.indexOf(substr);//这一步要放在前面，要不然结果会+1
			countNum ++;
			str = str.substring(index + substr.length());//Returns a string that is a substring of this string start from given index.
		}
		
		return countNum;
		
	}
}