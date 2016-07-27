package com.javabase.corejava.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 注意当前路径, 用相对路径的时候回用到
 * File的路径应该指向File而不是Folder
 * @author zhenhaiw
 *
 */
public class Chap1TextFileTest 
{
	public static void main(String[] args) 
	{
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Tom", 3000, 1982, 1, 4);
		staff[1] = new Employee("Jerry", 3400, 1998, 11, 23);
		staff[2] = new Employee("Bob", 5400, 2001, 8, 21);
		
		try {
			Chap1TextFileTest textTest = new Chap1TextFileTest();
			//Save all employee records into the file
			String currentPath = System.getProperty("user.dir");
			System.out.println("Current file path = " + currentPath);
			//String filePath = "files/Employee.txt/";
			//File路径要注意到文件,不是folder, 否则会报错(Access denied)
			//注意相对路径位置, System.getProperty("user.dir")可以打印当前路径
			File file = new File(".\\src\\com\\javabase\\corejava\\part2\\files\\Employee.txt\\");
			if(!file.exists())
				file.createNewFile();
			//PrintWriter out = new PrintWriter(filePath);
			PrintWriter out = new PrintWriter(file);
			textTest.writeData(staff, out);
			out.close();//记得close()释放资源
			
			//Retrieve all records into a new array
			Scanner in = new Scanner(new FileReader(file));
			Employee[] newStaff = textTest.readData(in);
			in.close();
			
			//print the newly read employee records
			for(Employee e : newStaff)
				System.out.println(e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Write all employees in an array to a print writer
	 * @param staff an array of employees
	 * @param out a print writer
	 */
	public void writeData(Employee[] staff, PrintWriter out)
	{
		//writer number of employees, will use it as array length in readData()
		out.println(staff.length);
		
		//调用Employee.writeData()逐个写入
		for(Employee e : staff)
		{
			e.writeData(out);
		}
	}
	
	/**
	 * Read all employees to an array from scanner
	 * @param in the scanner
	 * @return an array of employees
	 */
	public Employee[] readData(Scanner in)
	{
		//retrieve the array size from the first line
		int n = in.nextInt();
		in.nextLine();
		
		Employee[] staff = new Employee[n];
		for(int i = 0; i < staff.length; i++)
		{
			staff[i] = new Employee();//调用定义好的default Constructor
			staff[i].readData(in);;
		}
		
		return staff;
	}
}



class Employee
{
	Employee()
	{
		
	}
	
	Employee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
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
	
	public void raiseSalary(int byPerc)
	{
		salary += salary * byPerc/100;
	}
	
	public String toString()
	{
		return this.getClass().getName() 
				+ "[name= " + name 
				+ ", salary" + salary 
				+ ", hireDay=" 
				+ hireDay 
				+ "]";
	}
	
	/**
	 * Write employee data to a print writer
	 * @param out the print writer
	 */
	public void writeData(PrintWriter out)
	{
		//Read employee's hire day and write to out
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		
		out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR) 
				+ "|" + calendar.get(Calendar.MONTH) + "|" + calendar.getMaximum(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * Read and parse employee data from a buffered reader
	 * @param in the scanner
	 */
	public void readData(Scanner in)
	{
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		name = tokens[0];
		salary = Double.parseDouble(tokens[1]);//解析String to double
		int year = Integer.parseInt(tokens[2]);//解析String to int
		int month = Integer.parseInt(tokens[3]);
		int day = Integer.parseInt(tokens[4]);
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	
	private String name;
	private double salary;
	private Date hireDay;
}