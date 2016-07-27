package com.javabase.corejava.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chap13ShuffleTest {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		
		for(int i = 0; i < 49; i++)
			numbers.add(i+1);
		System.out.println("Original :" + numbers);
		
		//shuffle打乱排序
		Collections.shuffle(numbers);
		System.out.println("Shuffle: " + numbers);
		
		//sort排序
		List<Integer> topNumber= numbers.subList(0, 9);
		Collections.sort(topNumber);
		System.out.println("Sort the first 10: " + topNumber);
		
		//Collections.binarySearch(), 查找有序集合
		int i;
		Collections.sort(numbers);
		i = Collections.binarySearch(numbers, 45);
		System.out.println(numbers.get(i) == 45);
		
		int max = Collections.max(numbers);
		System.out.println("MAX: " + max);
	}

}
