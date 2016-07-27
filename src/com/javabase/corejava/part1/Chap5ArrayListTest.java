package com.javabase.corejava.part1;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ArrayList用法, 元素调用方法
 * ArrayList转换成数组
 * @author zhenhaiw
 *
 */
public class Chap5ArrayListTest 
{
	public static void main(String[] args) 
	{
		ManagerA boss = new ManagerA("Boss", 6000.0, 1999, 3, 25);
		boss.setBonus(10000.0);
		
		ArrayList<EmployeeA> employee = new ArrayList<EmployeeA>();
		employee.add(boss);
		employee.add(new EmployeeA("Jerry", 3900.0, 2001, 4, 25));
		employee.add(new EmployeeA("Tommy", 3200.0, 2013, 5, 25));
		
		for(EmployeeA e : employee)
		{
			System.out.println("Employee ArrayList: " + e);//直接调用重写的toString()方法
		}
		System.out.println();
		
//		ArrayList转换成数组, 调用数组的方法输出
		EmployeeA[] em = new EmployeeA[employee.size()];
		employee.toArray(em);
		
		for(EmployeeA e : em)
		{
			System.out.println("Employee[]: " + e);
		}
	}
}

class EmployeeA
{
	//构造函数如果不是public则默认保内权限,在包外不能new实例, 也不能继承
	public EmployeeA(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		hireDay = calendar.getTime();
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
		return (Date)hireDay.clone();//注意引用可变变量用clone()
	}

	public void raiseSalary(int byPercent)
	{
		double raise = salary * byPercent/100;
		salary += raise;
	}
	
	//为每个类添加一个toString()非常方便
	public String toString()
	{
		return getClass().getName()
				+ "[name=" + name 
				+ ", salary=" + salary
				+ ", hireDay=" + hireDay
				+ "]";
	}

	private String name;
	private double salary;
	private Date hireDay;
}

class ManagerA extends EmployeeA
{
//	注意继承构造器的写法
	public ManagerA(String n, double s, int year, int month, int day)
	{
//		由于不能访问超类的私有变量域, 需要调用超类构造器对私有域初始化
//		super显式调用, 没有显式调用则调用超类的默认构造器,如果超类没有定义默认构造器则报错
		super(n, s, year, month, day);//调用Employee构造器对私有变量初始化
		bonus = 0;//初始化bonus
	}
	
	public void setBonus(double b)
	{
		bonus = b;
	}
	
	public double getBonus()
	{
		return bonus;
	}
	
	public double getSalary()
	{
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}
	
	private double bonus;
}
