package com.javabase.other;

public class SortMethods
{
	public static void main(String[] args)
	{
		SortMethods sm = new SortMethods();
		int[] num = {4,5,3,7,9,2,1,6,8,8};
//		sm.bubbleSort(num);
//		sm.selectSort(num);
		sm.quickSort(num, 0, num.length-1);
		for(int i:num)
		{
			System.out.print(i + " ");
		}
	}
	
	/**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。一趟之后，最后的元素应该会是最大的数。  
     * 每趟针对所有的元素重复以上的步骤，除了最后几个排完最大的。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
     * @param numbers 需要排序的整型数组
     */
	public void bubbleSort(int[] numbers)
	{
		int temp = 0;
		for(int i=0; i<numbers.length-1; i++)
		{
			for(int j=0; j<numbers.length-i-1; j++)
			{
				if(numbers[j] > numbers[j+1])
				{
					temp = numbers[j+1];
					numbers[j+1] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
	}
	
	/***
	 * 选择排序
	 * min下标对应元素(起始是第一个元素)逐个跟后续元素比较大小，如果大于后续元素，那么min就把后续元素的位置记住
	 * 一趟过后把min对应的元素跟首元素做一下交换,首元素成为最小元素
	 * 每趟类似操作
	 */
	public void selectSort(int[] numbers)
	{
		int temp;
		int minIndex;
		for(int i=0; i<numbers.length-1; i++)
		{
			minIndex = i;
			for(int j=i+1; j<numbers.length; j++)
			{
				if(numbers[j] < numbers[minIndex])
					minIndex = j;
			}
			
			temp = numbers[minIndex];
			numbers[minIndex] = numbers[i];
			numbers[i] = temp;
		}
	}
	
	/**
	 * 快速排序
	 * (1)从待排序的n个记录中任意选取一个记录（通常选取第一个记录）为分区标准;
	 * (2)把所有小于该排序列的记录移动到左边，把所有大于该排序码的记录移动到右边，中间放所选记录，称之为第一趟排序；
	 * (3)然后对前后两个子序列分别重复上述过程，直到所有记录都排好序。
	 */

	//a：待排序数组，low：最低位的下标，high：最高位的下标
	void quickSort(int a[],int low, int high)
	{
	    if(low>=high)
	    {
	        return;
	    }
	    int left=low;
	    int right=high;
	    int key=a[left];    /*用数组的第一个记录作为分区元素*/
	    while(left!=right){
	        while(left<right&&a[right]>=key)    /*从右向左扫描，找第一个码值小于key的记录，并交换到key*/
	            --right;
	        a[left]=a[right];
	        while(left<right&&a[left]<=key)
	            ++left;
	        a[right]=a[left];    /*从左向右扫描，找第一个码值大于key的记录，并交换到右边*/
	    }
	    a[left]=key;    /*分区元素放到正确位置*/
	    quickSort(a,low,left-1);//分区递归
	    quickSort(a,left+1,high);
	}
}
