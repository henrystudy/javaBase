package com.javabase.corejava.part1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Chap13SetTest {

	public static void main(String[] args) {
		Set<String> words = new HashSet<String>();
		long totalTime = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a word or type \"exit\" to end: ");
		while(in.hasNextLine())
		{
			String word = in.nextLine();
			if(!word.equals("exit"))//type "exit" to end, "!="比较的是地址,eqquals比较的是内容
			{
				long callTime = System.currentTimeMillis();
				words.add(word);
				callTime = System.currentTimeMillis() - callTime;
				totalTime += callTime;
			}
			else
				break;
			System.out.print("Enter a word or type \"exit\" to end: ");
		}
		in.close();
		
		
		Iterator<String> iter = words.iterator();
		if(!words.isEmpty())
		{
//			两种遍历均可(SET无序无重复元素)
//			for(String w : words)
//			{
//				System.out.println(w);
//			}
			for(int i = 0; i < words.size(); i++)
			{
				System.out.println(iter.next());
			}
		}
		
		System.out.println("............................................");
		System.out.println(words.size() + " distinc words. " + totalTime + " milliseconds.");
	}

}
