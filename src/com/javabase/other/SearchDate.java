package com.javabase.other;


/**
* 调用的SortDate类中定义的Date类的排序和比较方法bubbleSort(), compareDate()
*
**/

public class SearchDate
{
	public static void main(String[] args)
	{
		Date[] days = new Date[5];
		days[0] = new Date(2010,2,15);
		days[1] = new Date(2008,11,9);
		days[2] = new Date(2011,4,5);
		days[3] = new Date(2012,2,23);
		days[4] = new Date(2009,12,2);
		
		SearchDate sd = new SearchDate();
		Date x = new Date(2011,4,5); 
		
		System.out.println(x + "> " + sd.binarySearchDate(days,x));
		for(Date d : days)//print是已经排好序的数组(调用binarySearchDate()已经做了排序)
		{
			System.out.println(d);
		};
	}
	
	int binarySearchDate(Date[] days, Date day)
	{
		Date d = new Date();
		d.bubbleSort(days);//调的SortDate类>Date类的方法
		
		int startPos = 0;
		int endPos = days.length - 1;
		int m = (startPos + endPos) / 2;
		
		if(startPos > endPos)//等同days.length < 0?
		{
			return -1;
		}
		
		while(startPos <= endPos)
		{
			if(day.compareDate(days[m]) == 0)
			{
				return m;
			}else if(day.compareDate(days[m]) < 0)
			{
				endPos = m - 1;
			}else if(day.compareDate(days[m]) > 0)
			{
				startPos = m + 1;
			}
			m = (startPos + endPos) / 2;
		}
		return -1;//注意这里的其他情况返回
	}
}