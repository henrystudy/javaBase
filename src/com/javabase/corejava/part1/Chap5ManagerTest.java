package com.javabase.corejava.part1;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 继承: 共性到特性, 添加新功能
 * 构造器加不加public区别, 跟类的权限基本一致, 不加public默认包内权限
 * 给每个定义一个toString()方法非常的方便调试
 * @author zhenhaiw
 *
 */
public class Chap5ManagerTest
{
	public static void main(String[] args) 
	{
//		Employee e = new EmployeeM("张三", 4000.0, 2001, 3, 4);
//		System.out.println(e.getName() + ", " + e.getSalary() + ", " + e.getHireDay());
		
		Manager boss = new Manager("Boss", 6000.0, 1999, 3, 25);
		boss.setBonus(10000.0);
		
		EmployeeM[] employee = new EmployeeM[3];
		employee[0] = boss;
		employee[1] = new EmployeeM("Jerry", 3900.0, 2001, 4, 25);
		employee[2] = new EmployeeM("Tommy", 3200.0, 2013, 5, 25);
		
		for(EmployeeM e : employee)
		{
			System.out.println(e.toString());
			System.out.println(e.getName() + ", " + e.getSalary() + ", " + e.getHireDay());
		}
	}
}

class EmployeeM
{
	//构造函数如果不是public则默认保内权限,在包外不能new实例, 也不能继承
	public EmployeeM(String n, double s, int year, int month, int day)
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

class Manager extends EmployeeM
{
//	注意继承构造器的写法
	public Manager(String n, double s, int year, int month, int day)
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

