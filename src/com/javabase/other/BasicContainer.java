package com.javabase.other;

import java.util.*;

/**
* 基本的容器的使用
* java.util.Collection
* 重写equals()和hashCode()方法，添加删除时判断是否相等
* 实现Comparable接口并重写compareTo()方法，以便对象方便用来排序
* <泛型>使用
**/

public class BasicContainer
{
	public static void main(String[] args)
	{
		//testCollection();
		//testSet();
		//testIterator();
		//testList();
		//testOverwritenCompareTo();
		//testMap();
		testArgsWords(args);
	}
	
	//Collection基本方法用法
	static void testCollection()
	{
		Collection c = new HashSet();
		c.add("hello");
		c.add(new Name("f1","l1"));
		c.add(new Integer(100));
		c.remove("hello");
		c.remove(new Integer(100));//会remove掉，因为Integer重写了equals()，只要包含的int值一样就相等
		System.out.println(c.remove("hello"));//已经remove过了，容器中没有，所以返回false
		System.out.println(c.remove(new Name("f1","l1")));//new新的不同的对象，容器中不存在, 返回false；重写Name类的equals()之后返回true
		System.out.println(c);//只有[f1 11]存在，查看Collection重写的toString()
	}
	
	//Iterator使用
	static void testIterator()
	{
		Collection c = new ArrayList();
		c.add(new Name("f1","l1"));
		c.add(new Name("f2","l2"));
		c.add(new Name("f3","l3"));
		c.add(new Name("f4","l4"));
		
		Iterator i = c.iterator();
		while(i.hasNext())
		{
			Name name = (Name)i.next();//next()返回值为泛型，需要转换为响应类型
			System.out.println(name.getFirstName() + " ");//name.firstName为private,在这里强制转型不可用
		}
	}
	
	//Set的特性测试
	static void testSet()
	{
		Set s1 = new HashSet();
		Set s2 = new HashSet();
		s1.add("a");s1.add("b");s1.add("c");
		s2.add("a");s2.add("b");s2.add("d");
		Set sn = new HashSet(s1);
		sn.retainAll(s2);//交集
		Set su = new HashSet(s1);
		su.addAll(s2);//Set无序无重，会去重
		System.out.println(sn);
		System.out.println(su);
	}
	
	//java.util.Collections类提供了基于List容器的常用算法的静态实现
	static void testList()
	{
		List c1 = new ArrayList();//大部分方法仅适用于List容器，也有部分适用所有Collection容器，具体查看API
		for(int i=0; i<=9; i++)
		{
			c1.add("A" + i);
		}
		
		System.out.println(c1);
		Collections.reverse(c1);//逆序
		System.out.println(c1);
		Collections.shuffle(c1);//随机排列
		System.out.println(c1);
		Collections.sort(c1);//排序
		System.out.println(c1);
		System.out.println(Collections.binarySearch(c1,"A5"));
	}
	
	//Name重写compareTo()方法，List容器可以对其对象进行排序
	static void testOverwritenCompareTo()
	{
		List l = new ArrayList();
		l.add(new Name("Ray", "Zhang"));
		l.add(new Name("Henry","Li"));
		l.add(new Name("Rocky","Yang"));
		l.add(new Name("William","Li"));
		
		System.out.println(l);
		Collections.sort(l);
		System.out.println(l);
	}
	
	//Map的常用方法用法
	static void testMap()
	{
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		Map<String, Integer> m2 = new TreeMap<String, Integer>();
		
		//JDK1.5之后加入自动打包/解包机制，会在合适时机在对象和基本类型之间自动转换
		m1.put("one", 1);
		m1.put("two", 2);
		m1.put("three", 3);
		m2.put("A", 1);
		m2.put("B", 2);
		
		/*JDK1.5之前
		m1.put("one", new Integer(1));
		m1.put("two", new Integer(2));
		m1.put("three", new Integer(3));
		m2.put("A", new Integer(1));
		m2.put("B", new Integer(2));
		*/
		System.out.println(m1.size());
		System.out.println(m1.containsKey("one"));
		System.out.println(m2.containsValue(2));
		if(m1.containsKey("two"))
		{
			//JDK1.5之后
			int i = m1.get("two");//不可以直接用Object，不能识别为特定包装类，还是要转化成(Integer)
			//JDK1.5之前
			//int i = ((Integer)m1.get("two")).intValue();//get()返回的是Object
			System.out.println(i);
		}
		Map m3 = new HashMap(m1);
		m3.putAll(m2);
		System.out.println(m3);
		System.out.println(m3.put("B",100000));//put返回的是同样的key存在的情况下被替换的原value，没有同key则返回null
		System.out.println(m3.put("C ",100000));//null
		System.out.println(m3);
	}
	
	//小程序用来统计args[]相同参数出现的次数
	static void testArgsWords(String[] args)
	{
		//使用泛型和自动打包解包
		final int ONE = 1;
		Map<String, Integer> m = new HashMap<String, Integer>();//Integer不能使用int，需要时引用类型
		
		for(int i=0; i<args.length; i++)
		{
			if(!m.containsKey(args[i]))
			{
				m.put(args[i],ONE);
			}else
			{
				int freq = m.get(args[i]);
				m.put(args[i], freq + 1);
			}
			/*用泛型之前
			int freq = m.get(args[i]) == null ? 0 : m.get(args[i]);//注意初始的null值判断
			m.put(args[i],freq == 0 ? ONE : freq+1);
			*/
		}
		System.out.println(m.size() + " distinct words detected: ");
		System.out.println(m);
		
		/*自动打包解包但没有使用泛型
		final int ONE = 1;
		Map m = new HashMap();
		
		for(int i=0; i<args.length; i++)
		{
			int freq = (Integer)m.get(args[i]) == null ? 0 : (Integer)m.get(args[i]);//注意初始的null值判断
			m.put(args[i],freq == 0 ? ONE : freq+1);
		}
		System.out.println(m.size() + " distinct words detected: ");
		System.out.println(m);
		*/
		
		/*JDK1.5没有自动打包解包的写法
		final Integer ONE = new Integer(1);
		Map m = new HashMap();
		
		for(int i=0; i<args.length; i++)
		{
			Integer freq = (Integer)m.get(args[i]);//新key返回null，返回null之后给value=1，再次找到这个key的话+1
			m.put(args[i],freq == null ? ONE : new Integer(freq.intValue()+1));
		}
		System.out.println(m.size() + " distinct words detected: ");
		System.out.println(m);
		*/
	}
}

class Name implements Comparable<Name>  //泛型使用，Comparable<T>接口API中说明可以接泛型
{
	private String firstName, lastName;
	
	public Name(String fName, String lName)
	{
		this.firstName = fName;
		this.lastName = lName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
	//重写equals()方法
	public boolean equals(Object obj)
	{
		if(obj instanceof Name)
		{
			Name name = (Name)obj;
			return (firstName.equals(name.firstName))
				&& (lastName.equals(name.lastName));
		}
		return super.equals(obj);
	}
	
	//重写hashCode()方法
	public int hashCode()
	{
		return firstName.hashCode();//调用现成的String.hashCode（）， 因为String重写了hashCode()方法，只要String一样就会返回一样的hashCode
	}
	
	//重写compareTo()方法，以便于可以对Name对象进行排序
	public int compareTo(Name obj)
	{
		Name name = obj;
		return  //先比较lastName,如果相等在比较firstName(调用String重写的compareTo())
		(lastName.compareTo(name.getLastName()) != 0) ? lastName.compareTo(name.getLastName()) : 
		firstName.compareTo(name.getFirstName());
	}
}