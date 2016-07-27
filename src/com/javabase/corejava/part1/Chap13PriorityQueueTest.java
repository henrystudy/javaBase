package com.javabase.corejava.part1;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

/**
 * PriorityQueue 无须插入存放, 遍历是无序的, 但是add/remove总能把最小的值移动到根
 * @author zhenhaiw
 *
 */
public class Chap13PriorityQueueTest 
{
	public static void main(String[] args) 
	{
		PriorityQueue<GregorianCalendar> pq = new PriorityQueue<GregorianCalendar>();
		pq.add(new GregorianCalendar(2002, 11, 12));
		pq.add(new GregorianCalendar(1985, 8, 25));
		pq.add(new GregorianCalendar(1993, 5, 24));
		pq.add(new GregorianCalendar(1998, 6, 4));
		
		System.out.println("Iterating over elements...");
		for(GregorianCalendar c : pq)
			System.out.println(c.get(Calendar.YEAR));
		System.out.println("Removing elements...");
		while(!pq.isEmpty())
			System.out.println(pq.remove().get(Calendar.YEAR));
		System.out.println("pq is empty? \n" + pq.isEmpty());
	}

}
