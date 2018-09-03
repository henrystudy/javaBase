/**
* 静态Thread.yield()线程让行一次
*
**/

public class TestYieldThread
{
	public static void main(String[] args)
	{
		MyThread3 mt1 = new MyThread3("T1");
		MyThread3 mt2 = new MyThread3("T2");
		mt1.start();
		mt2.start();
	}
}

class MyThread3 extends Thread
{
	MyThread3(String name)
	{
		super(name);
	}
	
	public void run()
	{
		for(int i=0; i<=100; i++)
		{
			System.out.println(getName() + ": " + i);
			if(i%10 == 0) //一旦打印到10整数倍时，肯定会让一下，切换线程
				yield();
		}
	}
}