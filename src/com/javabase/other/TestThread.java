import java.util.*;

/**
* Thread.run() 方法调用 /start() 启动线程
* 线程创建启动的两种方式，实现Runnable接口(更灵活)，继承Thread线程类
* Thread.sleep()静态方法
* 合理的停止子线程的方法
* 同一个线程类可以起多个线程
**/

public class TestThread
{
	public static void main(String[] args)
	{
		myThread ms = new myThread();
		/*实现接口
		Thread t = new Thread(ms);
		t.start();
		//t.run();//方法调用，还是单个线程
		*/
		
		//继承Thread线程类
		ms.start();
		
		/*测试多线程和主线程
		for(int i=0; i<=100; i++)
		{
			System.out.println("Main Thread: " + i);
		}
		*/
		
		try
		{
			Thread.sleep(10000);//主线程睡眠10秒
		}catch(InterruptedException e)
		{
		}
		
		//ms.interrupt();//主线程睡眠10秒后打断ms子线程，抛异常(return)结束
		//ms.flag = false;//比较合理的停止子线程的操作，由线程类提供对外的方法更好
		ms.shutDown();//对外方法停止线程
	}
}

//class myThread implements Runnable  //实现Runnable接口来创建线程
class myThread extends Thread  //继承Thread类来创建线程
{
	protected boolean flag = true;
	public void run()
	{
		/*测试多线程
		for(int i=0; i<=100; i++)
		{
			System.out.println("myThread: " + i);
		}
		*/
		
		while(flag)
		{
			System.out.println("=== " + new Date() + "===");
			try
			{
				Thread.sleep(1000);
			}catch(InterruptedException e)
			{
				return;
			}
		}
	}
	
	public void shutDown()
	{
		flag = false;
	}
	
}