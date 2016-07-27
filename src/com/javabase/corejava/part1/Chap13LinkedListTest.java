package com.javabase.corejava.part1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Chap13LinkedListTest 
{

	public static void main(String[] args) 
	{
		//注意添加泛型类型参数
		List<String> a = new LinkedList<String>();
		List<String> b = new LinkedList<String>();
		
		a.add("Tom");
		a.add("Jerry");
		a.add("Bob");
		
		b.add("钓鱼");
		b.add("上网");
		b.add("逛逛");
		b.add("玩玩游戏");
		
		//merge the words from b to a
		ListIterator<String> aIter = a.listIterator();//use ListIterator.add()
		Iterator<String> bIter = b.iterator();
		
		while(bIter.hasNext())
		{
			if(aIter.hasNext())
			{
				aIter.next();
			  //aIter.add(bIter.next());
			}
			aIter.add(bIter.next());
			
			/* 
			 如果aIter.add(bIter.next());放在if条件内部
			//break;//只添加一次便跳出了循环, result = [Tom, 钓鱼, Jerry, Bob]
			//System.out.println(a);//死循环,因为bIter.hasNext() always = true (a元素 数< b元素数, b = true)
			 */
		}
		
		System.out.println("a = " + a);
		
		//remove every second word from b
		bIter = b.iterator();//重置iterator顺序, 否则bIter还停留在上面代码的迭代器最后位置
		while(bIter.hasNext())
		{
			bIter.next();
			if(bIter.hasNext())//remove()会删除当前next(), 跳两次才会保留一个元素
				bIter.next();
			bIter.remove();
		}
		System.out.println("b = " + b);
		
		//bulk operation: remove all words in b from a
		a.removeAll(b);//current b = [钓鱼, 逛逛]
		System.out.println("now (a - b) = " + a);
	}

}
