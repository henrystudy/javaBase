package com.javabase.other;

/**
* 自定义引用类型的比较方法，据此排序
*
**/

public class SortDate
{
	public static void main(String[] args)
	{
		Date[] days = new Date[5];
		days[0] = new Date(2010,2,15);
		days[1] = new Date(2008,11,9);
		days[2] = new Date(2011,4,5);
		days[3] = new Date(2012,2,23);
		days[4] = new Date(2009,12,2);
		
		Date date = new Date();
		date.bubbleSort(days);
		//sd.selectionSort(days);
		
		for(Date d: days)
		{
			System.out.println(d);
		}
	}
}

class Date
{
	int year;
	int month;
	int day;
	
	Date()
	{
		
	}
	
	Date(int y, int m, int d)
	{
		this.year = y;
		this.month = m;
		this.day = d;
	}
	
	public int compareDate(Date date)
	{
		/*//三目写法， a>b ? x : y (true结果为x, 否则结果为y)
		return
		year > date.year ? 1
		: year < date.year ? -1
		: month > date.month ? 1
		: month < date.month ? -1
		: day > date.day ? 1
		: day < date.day ? -1
		: 0;
		*/
		
		//两种写法一样
		if(year > date.year)
		{
			return 1;
		}else if(year < date.year)
		{
			return -1;
		}else if(month > date.month)
		{
			return 1;
		}else if(month < date.month)
		{
			return -1;
		}else if(day > date.day)
		{
			return 1;
		}else if(day < date.day)
		{
			return -1;
		}else
		{
			return 0;
		}
	}
	
	//冒泡算法排序/注意范围
	public void bubbleSort(Date[] d)
	{
		for(int i=d.length-1; i>0; i--)
		{
			for(int j=i; j>0; j--)
			{
				if(d[j].compareDate(d[j-1]) < 0)
				{
					Date temp = d[j-1];
					d[j-1] = d[j];
					d[j] = temp;
				}
			}
		}
	}
	
	//选择算法排序/注意范围
	public void selectionSort(Date[] d)
	{
		for(int i=d.length-1; i>=0; i--)
		{
			int k = i;
			Date temp;
			for(int j=k-1; j>=0; j--)
			{
				if(d[k].compareDate(d[j]) < 0)
				{
					k = j;
				}
			}
			
			if(i != k)
			{
				temp = d[i];
				d[i] = d[k];
				d[k] = temp;
			}
		}
	}
	
	public String toString()
	{
		return
		"Year-Month-Day: " + year + "-" + month + "-" + day;
	}
}