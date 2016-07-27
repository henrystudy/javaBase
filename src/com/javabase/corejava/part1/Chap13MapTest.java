package com.javabase.corejava.part1;

import java.util.HashMap;
import java.util.Map;

/**
 * This program demonstrates the use of a map with key type String and value type Employee
 * 熟悉Lambda expression写法; 1, 变量 -> {code block} 结构; 2, 类名::方法名 结构, employee::getSalary 等同employee -> employee.getSalary()
 * 熟悉Map k - v基本操作[put/replace/remove/get]
 * @author zhenhaiw
 *
 */

public class Chap13MapTest 
{
	public static void main(String[] args) 
	{
		Map<String, EmployeeMap> staff = new HashMap<String, EmployeeMap>();
		staff.put("21254125", new EmployeeMap("Tom"));
		staff.put("54454556", new EmployeeMap("Jerry"));
		staff.put("65652631", new EmployeeMap("Bob"));
		
		for(String s : staff.keySet())
			System.out.println("Original k - v: " + s + " - " + staff.get(s));
		
		staff.remove("54454556");
		System.out.println("New k - v: (remove key \"54454556\") ");
		//传统的遍历方式
		for(String s : staff.keySet())
			System.out.println(s + " - " + staff.get(s));
		
		System.out.println("Key \"65652631\": Old value - " + staff.get("65652631"));
		staff.replace("65652631", new EmployeeMap("newBob"));
		System.out.println("Key \"65652631\": New value - " + staff.get("65652631"));
		
		//Lambda expression 遍历; 变量 ->程序块 格式
		staff.forEach((k, v) ->
			System.out.println("key = " + k + ", value = " + v));
	}

}

class EmployeeMap
{
	public EmployeeMap(String name)
	{
		this.name = name;
		this.salary = 0;
	}
	
	public String toString()
	{
		return "[" + name + "]";
	}
	
	private String name;
	private double salary;
}
