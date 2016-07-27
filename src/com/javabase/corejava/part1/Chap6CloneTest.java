package com.javabase.corejava.part1;

import java.util.Date;
import java.util.GregorianCalendar;

public class Chap6CloneTest 
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		EmployeeC original = new EmployeeC("Tom", 4000);
		original.setHireDay(2000, 12, 23);
		EmployeeC copy = original.clone();
		copy.setHireDay(1010, 11, 10);
		
		System.out.println("Original: " + original.toString());
		System.out.println("Copy: " + copy);//直接打印对象也会直接调用toString()
	}

}

class EmployeeC implements Cloneable
{
	EmployeeC(String n, double s)
	{
		name = n;
		salary = s;
		hireDay = new Date();//必须初始化变量, 否则报nullPointerException
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public EmployeeC clone() throws CloneNotSupportedException
	{
		//call Object.clone()
		EmployeeC cloned = (EmployeeC)super.clone();
		
		//clone mutable fields
		cloned.hireDay = (Date)hireDay.clone();
		return cloned;
	}
	
	/**
	 * Set the hireDay to given data
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setHireDay(int year, int month, int day)
	{
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
	
	public String toString()
	{
		return "Employee[name" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	
	private String name;
	private double salary;
	private Date hireDay;
}