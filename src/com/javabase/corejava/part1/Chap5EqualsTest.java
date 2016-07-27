package com.javabase.corejava.part1;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * hashCode, 重写equals()
 * 重写Object.toString()之后, print(e.toString()); print(e) 将打印出一样的信息, 因为e会自动调用.toString()方法
 * @author zhenhaiw
 *
 */

public class Chap5EqualsTest {

	public static void main(String[] args) {
		EmployeeE alice1 = new EmployeeE("Alice Adams", 75000, 1987, 12, 15);
		EmployeeE alice2 = alice1;
		EmployeeE alice3 = new EmployeeE("Alice Adams", 7500, 1987, 12, 15);
		EmployeeE bob = new EmployeeE("Bob Brandson", 50000, 1989, 10, 1);
		
		System.out.println("alice1 == alice2: " + (alice1 == alice2));
		
		System.out.println("alice1 == alice3: " + (alice1 == alice3));
		
		System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));
		
		System.out.println("alice1.equals(bob): " + alice1.equals(bob));
		
		System.out.println("bob.toString(): " + bob.toString());
		
		ManagerE carl = new ManagerE("Carl Cracker", 80000, 1987, 12, 15);
		ManagerE boss = new ManagerE("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		System.out.println("boss.toString(): " + boss);//boss会自动调用重写后的.toString()方法
		System.out.println("carl.equals(boss): " + carl.equals(boss));
		System.out.println("alice1.hashCode(): " + alice1.hashCode());
		System.out.println("alice3.hashCode(): " + alice3.hashCode());
		System.out.println("bob.hashCode(): " + bob.hashCode());
		System.out.println("carl.hashCode(): " + carl.hashCode());
	}

}

class EmployeeE
{
	//构造函数如果不是public则默认保内权限,在包外不能new实例, 也不能继承
	public EmployeeE(String n, double s, int year, int month, int day)
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
	
//	重写Object对象的equals()方法
	public boolean equals(Object otherObject)
	{
//		a quick test to see if the objects are identical
		if(this == otherObject)
			return true;
		
//		must return false if the explicit parameter is null
		if(otherObject == null)
			return false;
		
//		if the classed don't match, they cannot be equal	
		if(getClass() != otherObject.getClass())
			return false;
		
//		now we can know otherObject is not a null employee
		EmployeeE other = (EmployeeE)otherObject;
		
//		test if the fields have identical values
		return name.equals(other.name) && salary == other.salary && hireDay.equals(other.hireDay);
		
	}
	
//	重新定义equals()就必须要重新定义hashCode()方法, 一遍用户可以将对象插入到散列表中
	public int hashCode()
	{
		return 7 * name.hashCode() + 11 * new Double(salary).hashCode() + 13 * hireDay.hashCode();
	}
}

class ManagerE extends EmployeeE
{
//	注意继承构造器的写法
	public ManagerE(String n, double s, int year, int month, int day)
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
	
	public boolean equals(Object otherObject)
	{
		if(!super.equals(otherObject))
			return false;
		ManagerE other = (ManagerE)otherObject;
//		super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}
	
	public int hashCode()
	{
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
	public String toString()
	{
		return super.toString() + "[bonus=" + bonus + "]";
	}
	
	private double bonus;
}


