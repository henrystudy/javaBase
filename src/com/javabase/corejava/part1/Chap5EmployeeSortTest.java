package com.javabase.corejava.part1;

import java.util.Arrays;

/**
 * This program demonstrates the use of the Comparable interface.
 * 实现某个接口才可以用某些方法
 * @author zhenhaiw
 *
 */
public class Chap5EmployeeSortTest 
{

	public static void main(String[] args) 
	{
		EmployeeI[] staff = new EmployeeI[3];
		staff[0] = new EmployeeI("Tom", 3000);
		staff[1] = new EmployeeI("Jerry", 4000);
		staff[2] = new EmployeeI("Bob", 2000);
		
		Arrays.sort(staff);
		
		for(int i = 0; i < staff.length; i++)
		{
			System.out.println("name = " + staff[i].getName() + ", salary = " + staff[i].getSalary());
		}
	}

}

class EmployeeI implements Comparable<EmployeeI>
{
	EmployeeI(String n, double s)
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
	
	public int compareTo(EmployeeI other)
	{
		if(salary > other.salary)
			return 1;
		if(salary < other.salary)
			return -1;
		return 0;
	}
	
	private String name;
	private double salary;
}
