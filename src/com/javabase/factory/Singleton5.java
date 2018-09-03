package com.javabase.factory;

public class Singleton5
{
	public static void main(String[] args)
	{
		EasySingleton e1 = EasySingleton.INSTANCE;
		EasySingleton e2 = EasySingleton.INSTANCE;
		EasySingleton e3 = EasySingleton.INSTANCE2;
		
		System.out.println(e1.getName());
		e2.setName("name");
		System.out.println(e1.getName());
		System.out.println(e2.getName());
		System.out.println(e3.getName());
	}
}

enum EasySingleton
{
	INSTANCE,
	INSTANCE2;
	
	private String name;
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
