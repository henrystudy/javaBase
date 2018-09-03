/**
* 实现类Runnable接口必须实现run()方法	
* 线程同步，语句执行过程中锁定，确保不会被打断
* synchronized void 方法执行过程中锁定当前对象
**/

public class TestSyncThread implements Runnable
{
	Timer timer = new Timer();
	
	public static void main(String[] args)
	{
		TestSyncThread ts = new TestSyncThread();
		Thread t1 = new Thread(ts);
		Thread t2 = new Thread(ts);
		Thread t3 = new Thread(ts);
		Thread t4 = new Thread(ts);
		Thread t5 = new Thread(ts);
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		t4.setName("t4");
		t5.setName("t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
	public void run()
	{
		timer.add(Thread.currentThread().getName());
	}
}

class Timer
{
	private static int num = 0;
	
	public synchronized void add(String name)//方法执行过程中锁定当前对象
	{
		//synchronized(this) //这么些也ok
		{
			num++;
			try
			{
				Thread.sleep(10);//语句执行过程中会锁定，不会被打断
			}catch(InterruptedException e)
			{
				
			}
			System.out.println(name + "你是第 " + num + "个使用timer的线程");
		}
		
		/*
		//未做线程同步的情况
		num++;
		try
		{
			Thread.sleep(3);//只是为了看效果，不同线程的不同步，语句执行过程中可能会被打断
			//时间长短结果不同，sleep时间长一点到10，那么所有5个线程都起来了第一个线程还在sleep中，结果自然所有线程打印是都是5
			//sleep时间如果是2，那么第一个线程先sleep，第二个线程执行后第一个醒来，结果是2，具体看可能还要看CPU
		}catch(InterruptedException e)
		{
			
		}
		System.out.println(name + "你是第 " + num + "个使用timer的线程");
		*/
	}
	
}