/**
* 线程合并
*
**/

public class TestJoinThread
{
	public static void main(String[] args)
	{
		MyThread2 ms2 = new MyThread2("T1");
		ms2.start();
		try
		{
			ms2.join();//合并进来，主线程等着ms2执行完再继续执行，有点类似方法调用
		}catch(InterruptedException e)
		{
		}
		
		for(int i=0; i<=100; i++)
		{
			System.out.println("I am main Thread");
		}
	}
}

class MyThread2 extends Thread
{
	MyThread2(String name)
	{
		super(name);//Thread的构造方法，对线程起一个名字
	}
	
	public void run()
	{
		for(int i=0; i<=100; i++)
		{
			System.out.println("I am " + getName());
		}
	
		try
		{
			sleep(1000);
		}catch(InterruptedException e)
		{
			return;
		}
	}
}