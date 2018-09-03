/**
* 异常捕获和分类
* Throwable > Exception > RuntimeException 可以不做捕获处理的异常
* Throwable > Exception 除了RuntimeException之外的异常，必须捕获处理
* Throwable > Error 没有办法捕获的异常
* 如果在方法中不想catch异常，那么可以只Throws抛出，在调用的时候catch，或者一直抛出在main的时候也抛出不catch，但是这不是好习惯
× 注意try catch的时候必须catch同级别的异常，或者先catch子异常再catch大的父类异常
**/

import java.io.*;

public class TestException
{
	public static void main(String[] args)
	{
		/*
		//m(int)方法为RuntimeException,　可以不用捕捉异常
		Ex ex = new Ex();
		ex.m(0);
		*/
		
		/*
		//m(int)方法为RuntimeException,　当然也可以捕捉处理异常
		try{
			Ex ex = new Ex();
			ex.m(0);
		}catch (ArithmeticException a){
			a.printStackTrace();
			System.out.println("oops...something wrong");
		}
		*/
		
		/*
		//must()方法为Exception中必须要捕捉处理的异常
		Ex ex = new Ex();
		ex.mustA();
		*/
		
		Ex ex = new Ex();
		try
		{
			ex.mustB();
		}catch(FileNotFoundException e)
		{
			System.out.println("File is not founded...");
			e.printStackTrace();//Throwable提供的方法，打印堆栈信息
			e.getMessage();//打印Throwable信息，可能是null
		}catch(IOException e) //IOException是FileNotFoundException的父类，不可以放在前面先catch
		{
			e.printStackTrace();
		}
	}
	
}

class Ex
{
	//RuntimeException异常例子
	void m(int i) throws ArithmeticException
	{
		if(i==0)
		{
			throw new ArithmeticException("Runtime error with 0");
		}
		
	}
	
	//必须捕获的异常，但在方法定义时只是抛出，没有catch异常，那么在调用的时候必须catch异常
	void mustB() throws IOException
	{
		FileInputStream in = new FileInputStream("test.txt");
		int b = in.read();
		while(b != 0)
		{
			System.out.println((char)b);
			b = in.read();
		}
		
	}
	
	//必须捕获的Exception异常, 方法定义时及时的捕获异常
	void mustA()
	{
		FileInputStream in = null;
		try
		{
			in = new FileInputStream("test.txt");//参照FileInputStream(String x)构造方法，有哪些异常抛出
			int b;
			b = in.read();//同样参照FileInputStream.read()有哪些异常抛出(FileInputStream.read()返回下一个byte的data)
			while(b != -1)
			{
				System.out.println((char)b);
				b = in.read();
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(SecurityException e)//RuntimeException, 可以不做处理
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				in.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
