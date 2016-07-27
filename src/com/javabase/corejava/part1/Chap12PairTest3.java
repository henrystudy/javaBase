package com.javabase.corejava.part1;

import java.util.Date;
import java.util.GregorianCalendar;

public class Chap12PairTest3 
{

	public static void main(String[] args) 
	{
		ManagerT ceo = new ManagerT("Zhangsan", 100000, 1999, 11, 23);
		ManagerT cfo = new ManagerT("Lisi", 60000, 1989, 11, 23);
		EmployeeT em = new EmployeeT("Employee", 5000, 1999, 11, 23);
		
		PairT<EmployeeT> buddies = new PairT<EmployeeT>(ceo,em);
		printBuddies(buddies);
		
		ceo.setBonus(10000);
		cfo.setBonus(5000);
		ManagerT[] managers = {ceo, cfo};
		
		PairT<ManagerT> result = new PairT<ManagerT>();
		minmaxBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName() 
				+ ", second: " + result.getSecond().getName());		
		maxminBonus(managers, result);
		System.out.println("first: " + result.getFirst().getName() 
				+ ", second: " + result.getSecond().getName());		
	}

	public static void printBuddies(PairT<? extends EmployeeT> p)
	{
		EmployeeT first = p.getFirst();
		EmployeeT second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddies. ");
	}
	
	public static void minmaxBonus(ManagerT[] a, PairT<? super ManagerT> result)
	{
		if(a == null || a.length == 0)
			return;
		ManagerT min = a[0];
		ManagerT max = a[0];
		for(int i = 0; i < a.length; i++)
		{
			if(min.getBonus() > a[i].getBonus())
				min = a[i];
			if(max.getBonus() < a[i].getBonus())
				max = a[i];
		}
		result.setFirst(min);
		result.setSecond(max);
	}
	
	public static void maxminBonus(ManagerT[] a, PairT<? super ManagerT> result)
	{
		minmaxBonus(a, result);
		PairAlg.swap(result);
	}
}

class PairT<T>
{
	public PairT()
	{
		
	}
	
	public PairT(T first, T second)
	{
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() 
	{
		return first;
	}
	
	public T getSecond() 
	{
		return second;
	}
	
	public T setFirst(T first) 
	{
		return this.first = first;
	}
	
	public T setSecond(T second) 
	{
		return this.second = second;
	}
	
	private T first;
	private T second;
}

class PairAlg
{
	public static boolean hasNulls(Pair<?> p)
	{
		return p.getFirst() == null || p.getSecond() == null;
	}
	
	public static void swap(PairT<?> p)
	{
		swapHelper(p);
	}
	
	public static <T> void swapHelper(PairT<T> p)
	{
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
	}
}

class EmployeeT
{
	public EmployeeT(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
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
	
	public Date gethireDay()
	{
		return hireDay;
	}
	
	public void raiseSalary(int byPerc)
	{
		salary += salary * byPerc/100;
	}
	
	private String name;
	private double salary;
	private Date hireDay;
}

class ManagerT extends EmployeeT
{
	public ManagerT(String n, double s, int year, int month, int day)
	{
		super(n, s, year, month, day);
		bonus = 0;
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
		return super.getSalary() + bonus;
	}
	
	private double bonus;
}