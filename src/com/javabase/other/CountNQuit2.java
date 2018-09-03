package com.javabase.other;

/**
* 面向对象的思维重新设计，一个n个人的circle， 每数x数退出一人
* 实际上是双向链表的实现
*
**/

public class CountNQuit2
{
	public static void main(String[] args)
	{
		KidCircle kc = new KidCircle(500);
		
		int countNum = 0;
		Kid k = kc.firstKid;
		while(kc.count > 1)
		{
			countNum++;
			if(countNum == 3)
			{
				countNum = 0;
				kc.delete(k);
			}
			k = k.rightKid;
		}
		System.out.println(kc.firstKid.id);
	}
}

class Kid
{
	int id;
	Kid leftKid;
	Kid rightKid;
}

class KidCircle
{
	Kid firstKid;
	Kid lastKid;
	int count;
	KidCircle(int n)//初始化Circle中每个元素
	{
		for(int i=0; i<n; i++)
		{
			add();
		}
	}
	
	void add()
	{
		Kid k = new Kid();
		k.id = count;
		
		if(count < 1)
		{
			k.leftKid = k;
			k.rightKid = k;
			firstKid = k;
			lastKid = k;
		}else
		{
			lastKid.rightKid = k;
			firstKid.leftKid = k;
			k.leftKid = lastKid;
			k.rightKid = firstKid;
			lastKid = k;//新添加的Kid变成last
		}
		count++;
	}
	
	void delete(Kid k)
	{
		if(count == 0)
		{
			return;
		}else if(count == 1)
		{
			firstKid = null;
			lastKid = null;
		}else
		{
			k.leftKid.rightKid = k.rightKid;
			k.rightKid.leftKid = k.leftKid;
			
			//首尾的特殊情况
			if(k == firstKid)
			{
				firstKid = k.rightKid;
			}else if(k == lastKid)
			{
				lastKid = k.leftKid;
			}
		}
		count--;
	}
	
}