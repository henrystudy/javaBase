package com.javabase.corejava.part1;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This program sorts a set of items by comparing
 * TreeSet 遍历出来是有序的, 效率比HashSet低, 但是比ArrayList, LinkedList要高
 * @author zhenhaiw
 *
 */
public class Chap13TreeSetTest {

	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("AAA", 1112));
		parts.add(new Item("BBB", 1322));
		parts.add(new Item("ABA", 2332));
		System.out.println(parts);
		
		SortedSet<Item> sortByDescription = new TreeSet<Item>
		(
				/**
				 * Customize a Comparator to sort items by description
				 */
				new Comparator<Item>() 
				{
					public int compare(Item a, Item b)
					{
						String descA = a.getDescription();
						String descB = b.getDescription();
						return descA.compareTo(descB);
					}
				}
		);
		
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
		
	}

}

/**
 * An item with a description and a part number
 * @author zhenhaiw
 *
 */
class Item implements Comparable<Item>
{
	public Item(String description, int partNumber)
	{
		this.description = description;
		this.partNumber = partNumber;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	//Overwrite toString()
	public String toString()
	{
		return "[description: " + description + ", partNumber: " + partNumber + "]";
	}
	
	/**
	 * Sort by partNumber as default
	 */
	public int compareTo(Item other)
	{
		return partNumber - other.partNumber;
	}
	
	/**
	 * Sort by Description by default
	 */
//	public int compareTo(Item other)
//	{
//		return description.compareTo(other.description);
//	}
	
	private String description;
	private int partNumber;
}
