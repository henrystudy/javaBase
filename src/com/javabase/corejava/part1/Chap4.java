package com.javabase.corejava.part1;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;

public class Chap4
{
	public static void main(String[] args)
	{
		//System.out.println(new Date());
		//Chap4 chap4 = new Chap4();
		//chap4.chap4_2_3();
		//chap4.calendarTest();
		
		
		//使用构造器构造器chap4_3Employee(String n, double s, int year, int month, int day), 实例4个employee对象, 逐个赋值并逐个输出
		chap4_3Employee[] employee = new chap4_3Employee[4];
		employee[0] = new chap4_3Employee("Tom", 40000, 1995, 12, 5);
		employee[1] = new chap4_3Employee("Jerry", 50034, 1987, 1, 25);
		employee[2] = new chap4_3Employee("Micky", 64000, 1985, 10, 5);
		employee[3] = new chap4_3Employee("Ray", 52000, 1982, 7, 2);
		
		System.out.println("Before promotion: ");
		for(chap4_3Employee e : employee)
		{
			System.out.println("ID = " + e.getID() + "Name = " + e.getName() + "; Salary = " + e.getSalary() + "; HireDay = " + e.getHireDay());
		}
		
		System.out.println("After promotion: ");

		for(chap4_3Employee e : employee)
		{
			e.raiseSalary(10);
			System.out.println("Name = " + e.getName() + "; Salary = " + e.getSalary() + "; HireDay = " + e.getHireDay());
		}
		
		
		/*
		//使用构造器chap4_3Employee(String n, double s)
		chap4_3Employee e = new chap4_3Employee("张三构造器2", 4460.0);
		System.out.println("多个构造器测试: " + e.getName() + "    " + e.getSalary());
		*/
	}
	
	private void chap4_2_3()
	{
		Date date = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);//
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
				
		System.out.println(month + "  " + weekday);
		//calendar.set(Calendar.YEAR, 2016);
		//calendar.set(Calendar.MONTH, Calendar.JUNE);
		//calendar.set(Calendar.DAY_OF_MONTH, 24);
		int year = calendar.get(Calendar.YEAR);
		int monthday = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(Calendar.MONTH + " " + Calendar.DAY_OF_MONTH + ", " + Calendar.YEAR);
	}
	
	/**
	*calendar.get(Calendar.DAY_OF_WEEK) - get and set indicating the day of the week. This field takes values SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, and SATURDAY.
	*calendar.getFirstDAY_OF_WEEK() - Gets what the first day of the week is; e.g., SUNDAY in the U.S., MONDAY in France.
	*while()之后不要忘记;
	*/
	private void calendarTest()
	{
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		
		//set calendar为当月1日, 并比对weekday是否为first day of week进行首行缩进
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		int firstDayOfWeek = calendar.getFirstDayOfWeek();//SUNDAY in the U.S., MONDAY in France.
		
		//定义indent缩进
		int indent = 0;
		while(firstDayOfWeek != weekday)
		{
			indent ++;
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			weekday = calendar.get(Calendar.DAY_OF_WEEK);
		}//跳出循环时: weekday = 1
		
		//Print weekday name, weekday = 1, do{}之后, weekday = 2 != firstDayOfWeek
		String[] weekdayName = new DateFormatSymbols().getShortWeekdays();
		do
		{
			System.out.printf("%4s", weekdayName[weekday]);//输出first day of week的名字
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			weekday = calendar.get(Calendar.DAY_OF_WEEK);//前滚一天
		}
		while(weekday != firstDayOfWeek);//跳出循环时; weekday = 1
		System.out.println();
	
		//print indent缩进
		for(int i = 0; i < indent; i++)
		{
			System.out.print("    ");//注意空格站位长度跟weekday name/ day输出保持一致
		}
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		do
		{	
			//print day
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);//3个占位 + today *的一个占位, 正好是4个占位
			
			//mark current as *
			if(day == today)
			{
				System.out.print("*");
			}
			else
			{
				System.out.print(" ");//这样不会造成格式乱掉
			}
				
			/*//先判断的话, 因为weekday = 1, 会直接换行; 并且会每次在dirDay之后换行, 完全错误
			if(weekday == firstDayOfWeek)
			{
				System.out.println();
			}
			*/
			
			//advance canlendar to the next day
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			weekday = calendar.get(Calendar.DAY_OF_WEEK);//weekday前滚
			
			//start a new line at the start of the week
			if(weekday == firstDayOfWeek)
			{
				System.out.println();
			}
		}
		//the loop exists when calendar is day 1 fo the next month
		while(month == calendar.get(Calendar.MONTH));
		
		//print final end of the line if necessary;还没弄明白用处
		if(weekday != firstDayOfWeek)
		{
			System.out.println();
		}
	}	
	
	
	
}

class chap4_3Employee
{
	//注意构造函数写法
	public chap4_3Employee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		//Gregorian use value from 0 - January
		hireDay = calendar.getTime();
	}
	
	//可以有多个构造器, 比如默认不带参数构造器, 不同参数的构造器
	public chap4_3Employee(String n, double s)
	{
		name = n;
		salary = s;
	}
		
	public String getName()
	{
		return name;
	}
		
	public double getSalary()
	{
		return salary;
	}
	
	public Date getHireDay()
	{
		return (Date)hireDay.clone();//返回一个可变数据类型的copy, 应该使用clone()
	}
	
	public void raiseSalary(int byPercent)
	{
		salary += salary * byPercent/100;
	}
		
	public int getID()
	{
		return id;
	}
		
	public static int assignId()
	{
		int r = nextId;
		nextId ++;
		return r;
	}
	
	
	private String name;
	private double salary;
	private Date hireDay;
	private int id = assignId();//new 实例的时候自动赋初值
	private static int nextId = 1;
}