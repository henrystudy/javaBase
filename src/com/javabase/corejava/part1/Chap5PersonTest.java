package com.javabase.corejava.part1;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 抽象类abstract不能被实例化
 * @author zhenhaiw
 *
 */

abstract public class Chap5PersonTest 
{
	public static void main(String[] args) 
	{
		Person[] p = new Person[2];
		//p[0] = new Person("XXX");//abstract类不能被实例化
		p[0] = new Employee("Jerry", 3900.0, 2001, 4, 25);
		p[1] = new Student("Student", "3");
		
		for(Person e : p)
		{
			System.out.println(e.getName() + ", " + e.getDescription());
		}
	}
}

abstract class Person 
{
	public Person(String n)
	{
		name = n;
	}
	
	public abstract String getDescription();//抽象方法不需要具体实现, 但是子类需要继承实现
	
	public String getName()
	{
		return name;
	}
	
	private String name;
}

class Employee extends Person
{
	
//	构造函数如果不是public则默认保内权限,在包外不能new实例, 也不能继承
	public Employee(String n, double s, int year, int month, int day)
	{
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		hireDay = calendar.getTime();
	}
	
	public String getDescription()
	{
		return String.format("an employee with a salary of $%.2f", salary);
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
	
	private double salary;
	private Date hireDay;
}

class Student extends Person {
	public Student(String n, String m)
	{
		super(n);
		major = m;
	}
	
	//从超类继承过来抽象方法的具体实现
	public String getDescription()
	{
		return "a stdent majoring in " + major;
	}
	
	private String major;
}
