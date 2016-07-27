package com.javabase.corejava.part1;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * This program uses reflection to spy on objects
 * @author zhenhaiw
 *
 */
public class Chap5ObjectAnalyzerTest 
{
	public static void main(String[] args) 
	{
		ArrayList<Integer> square = new ArrayList<Integer>();
		for(int i = 1; i < 5; i++)
		{
			square.add(i * i);
		}
		System.out.println(new ObjectAnalyzer().toString(square));
	}
}

class ObjectAnalyzer
{
	/**
	 * Convert an object to a string representation that lists all fields.
	 * @param obj an object
	 * @return a string with the object's class name and all field names and values
	 */
	public String toString(Object obj)
	{
		if(obj == null)
		{
			return "null";
		}
		if(visited.contains(obj))
		{
			return "...";
		}
		visited.add(obj);
		Class cl = obj.getClass();
		if(cl == String.class)
		{
			return (String)obj;
		}
		if(cl.isArray())
		{
			String r = cl.getComponentType() + "[]{";
			for(int i = 0; i < Array.getLength(obj); i++)
			{
				if(i > 0)
				{
					r += ",";
				}
				Object val = Array.get(obj, i);
				if(cl.getComponentType().isPrimitive())
				{
					r += val;
				}
				else
				{
					r += toString(val);
				}
			}
			return r + "}";
		}
		
		String r = cl.getName();
		do
		{
			r += "[";
			Field[] fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			//get the name and values of all fields
			for(Field f : fields)
			{
				if(!Modifier.isStatic(f.getModifiers()))
				{
					if(!r.endsWith("["))
					{
						r += ",";
					}
					r += f.getName() + "=";
					
					try {
						Class t = f.getType();
						Object val = f.get(obj);
						if(t.isPrimitive())
						{
							r += val;
						}
						else
						{
							r += toString(obj);
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			r += "]";
			cl = cl.getSuperclass();
		}
		while(cl != null);
		
		return r;
	}
	
	private ArrayList<Object> visited = new ArrayList<Object>();
}
