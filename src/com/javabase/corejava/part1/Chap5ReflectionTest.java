package com.javabase.corejava.part1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Modifier - 修饰符, 例如public, private...
 * Field - 域, 例如变量等
 * @author zhenhaiw
 *
 */
public class Chap5ReflectionTest {
	
	public static void main(String[] args)
	{
		//print class name from command or user input
		String name;
		if(args.length > 0)
		{
			name = args[0];
		}
		else
		{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name(e.g. java.util.Date): ");
			name = in.nextLine();
		}
		
		//print class name and superclass name (if != Object)
		try {
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if(modifiers.length() > 0)
			{
				System.out.print(modifiers + " ");
			}
			System.out.print("class " + name);
			if(supercl != null && supercl != Object.class)
			{
				System.out.print(" extends " + supercl.getName());
			}
			
			Chap5ReflectionTest reflection = new Chap5ReflectionTest();
			System.out.print("\n{\n");
			reflection.printConstructors(cl);
			System.out.println();
			reflection.printMethods(cl);
			System.out.println();
			reflection.printFields(cl);
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 * print all constructors of a class
	 * @param cl
	 */
	public void printConstructors(Class cl)
	{
		Constructor[] constructors = cl.getDeclaredConstructors();
		
		for(Constructor c : constructors)
		{
			String name = c.getName();
			System.out.print("  ");//缩进
			String modifiers = Modifier.toString(c.getModifiers());
			if(modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.print(name + "(");
			
			//print parameter type, 构造器惨呼类型
			Class[] paramTypes = c.getParameterTypes();
			for(int i = 0; i < paramTypes.length; i++)
			{
				if(i > 0 && i < paramTypes.length - 1)
				{
					System.out.print(paramTypes[i].getName());
					System.out.print(", ");
				}
				else if(i == paramTypes.length - 1)
				{
					System.out.print(paramTypes[i].getName());
				}
			}
			System.out.println(");");
		}
	}
	
	/**
	 * print all methods of a class
	 * @param cl
	 */
	public void printMethods(Class cl)
	{
		Method[] methods = cl.getMethods();
		
		for(Method m : methods)
		{
			Class retType = m.getReturnType();
			String name = m.getName();
			
			System.out.print("  ");
			
			//print modifiers, return type, and method name
			String modifiers = Modifier.toString(m.getModifiers());
			if(modifiers.length() > 0)
				System.out.print(modifiers + " ");
			System.out.print(name + "(");
			
			//print parameter type
			Class[] paramTypes = m.getParameterTypes();
			for(int i = 0; i < paramTypes.length; i++)
			{
				if(i > 0 && i < paramTypes.length - 1)
				{
					System.out.print(paramTypes[i].getName());
					System.out.print(", ");
				}
				else if(i == paramTypes.length - 1)
				{
					//删掉最后一个,
					System.out.print(paramTypes[i].getName());
				}
			}
			System.out.println(");");
		}
	}
	
	/**
	 * print all fields of a class
	 * @param cl
	 */
	public void printFields(Class cl)
	{
		Field[] fields = cl.getDeclaredFields();
		
		for(Field f : fields)
		{
			Class type = f.getType();
			String name = f.getName();
			System.out.print("  ");
			String modifiers = Modifier.toString(f.getModifiers());
			if(modifiers.length() > 0)
			{
				System.out.print(modifiers + " ");
			}
			System.out.println(type.getName() + " " + name + ";");
		}
	}
}
