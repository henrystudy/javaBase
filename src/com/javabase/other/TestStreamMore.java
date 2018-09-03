import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
* 处理流的练习程序
* BufferedInputStream/BufferedOutputStream(字节流)
* BufferedReader/BufferedWriter(字符流)
* InputStreamReader/OutputStreamWriter(字符流字节流转换)
* DataInput/OutputStream(提供基本数据类型的存取方法)
* PrintStream/PrintWriter(字节字符打印流)
* ObjectInput/OutputStream(对象流，对象序列化为字节流)
**/

public class TestStreamMore
{
	public static void main(String[] args)
	{
		TestStreamMore ts = new TestStreamMore();
		String inputPath = "D:/Java/Practice Code/temp/test.txt";
		String outputPath = "D:/Java/Practice Code/temp/test.txt";
		String log = "D:/Java/Practice Code/temp/logFile.log";
		//ts.testBufferedStream(inputPath);
		//ts.testBufferRW(inputPath,outputPath);
		//ts.testTransform(outputPath);
		//ts.testSystemIn();
		//ts.testDataStream();
		//ts.testPrintStream(inputPath);
		//ts.testPrintStream1(args[0],System.out);
		//ts.testPrintWriter(log);
		ts.testObjectStream(outputPath);
	}
	
	//缓冲流-字节处理流
	void testBufferedStream(String path)
	{
		try
		{
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));//BufferedInputStream构造参数为InputStream的子类
			int c = 0;
			//理解为标记点的意思，下面reset()一下之后还是会从标记点开始读取
			//(至于参数跟API说明会有点出入，跟缓冲区有关，跟参数反而关系不大，如果去超过缓冲区，那么mark就会失效，reset就会异常)
			bis.mark(10);
			for(int i=0; i<=20&&(c=bis.read())!=-1; i++)
			{
				System.out.print((char)c + " ");
			}
			System.out.println();
			bis.reset();//reset()之后会从前面标记点开始读取，如果不reset会继续读取
			for(int i=0; i<=20&&(c=bis.read())!=-1; i++)
			{
				System.out.print((char)c + " ");
			}
			bis.close();//不要忘记close()
		}catch(FileNotFoundException e)
		{
			System.out.println("Cannot find file!");
		}catch(IOException e)
		{
			System.out.println("文件读取错误！");
		}
		
	}
	
	//缓冲流-字符处理流(可以直接行处理)
	void testBufferRW(String inputPath, String outputPath)
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outputPath));
			BufferedReader bfr = new BufferedReader(new FileReader(inputPath));
			
			String s = null;
			for(int i=0; i<=100; i++)
			{
				s= String.valueOf(Math.random());
				bfw.write(s);
				bfw.newLine();
			}
			bfw.flush();//write的时候不要忘记flush()
			int num = 0;
			while((s = bfr.readLine()) != null)//读取整行的内容
			{
				System.out.println(s);
				num++;
			}
			System.out.println("共打印 " + num + "行");
			bfw.close();
			bfr.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//实现字节流字符流之间的转换，直接读取String
	void testTransform(String outputPath)
	{
		try
		{
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputPath));
			String s = "test String 123456789";
			osw.write(s);//可以直接写入String类型
			System.out.println(osw.getEncoding());//获取字符编码
			osw.close();
			
			//FileOutputStream(String, boolean) 是否append在文件后边
			osw = new OutputStreamWriter(new FileOutputStream(outputPath, true),"ISO8859_1");//指定字符编码
			osw.write(s);//可以直接写入String类型
			System.out.println(osw.getEncoding());//获取字符编码
			osw.close();
			
			BufferedReader br = new BufferedReader(new FileReader(outputPath));
			while((s = br.readLine()) != null)
			{
				System.out.println(s);
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//System.in 屏幕输入, System.in是standard InputStream(API java.lang.System)
	void testSystemIn()
	{
		InputStreamReader in = new InputStreamReader(System.in);//System.in是InputStream可以直接作为参数,本身就是一条管道
		//三层管道，System.in>InputStreamReader(转化为字符避免多字节字符显示问题)>BufferedReader(readLine()方便用)
		BufferedReader bis = new BufferedReader(in);
		String s = null;
		try
		{
			while((s = bis.readLine()) != null)
			{
				if(s.equalsIgnoreCase("exit"))
				{
					break;
				}else
				{
					System.out.println(s.toUpperCase());
				}
				
			}
		bis.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	//测试数据处理流DataInput/OutputStream
	void testDataStream()
	{
		//内存中分配字节数组，并new字节数组流管道(按照API默认占用32bytes,会自己按需要扩展)
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try
		{
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//字节数组作为输入参数
			DataInputStream dis = new DataInputStream(bais);
			
			System.out.println(bais.available());//返回管道中剩余字节数,double占8个字节，boolean占一个，一共9个
			//System.out.println(dis.readBoolean());
			System.out.println(dis.readDouble());
			System.out.println(dis.readBoolean());//读取数据时要先进先出，先读取首先写入的数据(队列，否则boolean会读出来double的第一个字节)
			dos.close();
			dis.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//测试打印流PrintStream/PrintWriter
	//System.out输出到文件
	void testPrintStream(String path)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(path);
			PrintStream ps = new PrintStream(fos);
			
			if(ps !=  null)
			{
				System.setOut(ps);//System.setOut(PrintStream ps) 设置out为指定输出源,默认out输出到屏幕
			}
			
			int ln = 0;
			for(char c=0; c<=60000; c++)
			{
				System.out.print(c + " ");
				if(ln++ >= 100) 
				{
					System.out.println();
					ln = 0;//100个字符换行
				}
			}
			fos.close();
			ps.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//输出文件到指定输出源
	void testPrintStream1(String f, PrintStream ps)
	{
		try
		{
			FileReader fr = new FileReader(f);
		BufferedReader br = null;
		if(fr != null)
		{
			String s = null;
			br = new BufferedReader(fr);
			while((s=br.readLine()) != null)
			{
				ps.println(s);//输出line到指定输出源
			}
			fr.close();
			br.close();
		}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//屏幕输入类似log text保存到文件
	void testPrintWriter(String filePath)
	{
		try
		{
			InputStreamReader bsr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(bsr);
			String s = null;
			
			FileWriter fw = new FileWriter(filePath,true);
			PrintWriter pw = new PrintWriter(fw);
			
			while((s = br.readLine()) != null)
			{
				if(s.equalsIgnoreCase("exit"))
				{
					break;
				}
				System.out.println(s.toUpperCase());
				pw.println("-----------------");
				pw.println(s.toUpperCase());
				pw.flush();//print流自动flash,写了也没影响
			}
			
			//注意日期的获取方式(尝试其他方式没试出来)
			Calendar c = Calendar.getInstance();
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
			pw.println("===" + date + "===");
			pw.flush();
			pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Object对象流，对象序列化
	void testObjectStream(String path)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			
			T t = new T();
			t.k = 20;
			oos.writeObject(t);//对象写入
			oos.flush();
			oos.close();
			
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			T tReaded = (T)ois.readObject();//对象读取,会抛出两个异常IOException, ClassNotFoundException
			System.out.println(tReaded.i + " " + tReaded.j + " " + tReaded.d + " " + tReaded.k + " " + tReaded.x);
			ois.close();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

//实现序列化接口，该类可以转化成字节流
class T implements Serializable
{
	int i = 10;
	int j = 9;
	double d = 2.3;
	int k = 15;
	transient int x = 100;//transient关键字，参数不会被序列化，透明
}