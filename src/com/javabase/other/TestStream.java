import java.io.*;
/**
* 测试程序,节点流>字节流 FileInputStream/FileOutputStream
* 节点流>字符流 FileReader/FileWriter
**/

public class TestStream
{
	public static void main(String[] args)
	{
		TestStream ts = new TestStream();
		String inputPath = "D:/Java/Practice Code/TestStream.java";
		String outputPath = "D:/Java/Practice Code/001.java";
		
		/*看一下每一种整形占用几个字节几个bits，取值范围		
		System.out.println(Long.SIZE + " " + Long.MAX_VALUE + " " + Long.MIN_VALUE);
		System.out.println(Integer.SIZE + " " + Integer.MAX_VALUE + " " + Integer.MIN_VALUE);
		System.out.println(Short.SIZE + " " + Short.MAX_VALUE + " " + Short.MIN_VALUE);
		System.out.println(Byte.SIZE + " " + Byte.MAX_VALUE + " " + Byte.MIN_VALUE);
		*/
		
		//ts.testFileInputStream(inputPath);
		//ts.testFileOutputStream(inputPath,outputPath);
		//ts.testFileReader(inputPath);
		ts.testFileWriter("D:/Java/Practice Code/001.txt");
	}
	
	//FileInputStream读取代码自身文件
	void testFileInputStream(String path)
	{
		FileInputStream in = null;
		int b = 0;
		try
		{
			in = new FileInputStream(path);
			long num = 0;//计数器
			while((b = in.read()) != -1)
			{
				System.out.print((char)b);
				num++;
			}
			in.close();
			System.out.println();
			System.out.println("共读取了" + num + "个字节。");//字节流只能读取一个字节，中文两个字节，所以一个字节转换char中文会乱码
		}catch(FileNotFoundException e)
		{
			System.out.println("File cannot be found, 找不到指定文件！");
			System.exit(-1);
		}catch(IOException e)
		{
			System.out.println("文件读取错误");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	//FileOutputStream自身文件Copy到另一个文件
	void testFileOutputStream(String inputPath, String outputPath)
	{
		FileInputStream in = null;
		FileOutputStream out = null;
		int b = 0;
		try
		{
			in = new FileInputStream(inputPath);
			out = new FileOutputStream(outputPath);
			while((b = in.read()) != -1)
			{
				out.write(b);
			}
			in.close();
			out.close();
		}catch(FileNotFoundException e)
		{
			System.out.println("File cannot be found, 找不到指定文件！");
			System.exit(-1);
		}catch(IOException e)
		{
			System.out.println("文件复制错误");
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("文件已复制");
	}
	
	//FileReader读取代码自身文件(字符流可以读取两个字节中文)
	void testFileReader(String path)
	{
		FileReader reader = null;
		int b = 0;
		try
		{
			reader = new FileReader(path);
			long num = 0;//计数器
			while((b = reader.read()) != -1)
			{
				System.out.print((char)b);
				num++;
			}
			reader.close();
			System.out.println();
			System.out.println("共读取了" + num + "个字符。");//字符流按字节读取decode成字符
		}catch(FileNotFoundException e)
		{
			System.out.println("File cannot be found, 找不到指定文件！");
			System.exit(-1);
		}catch(IOException e)
		{
			System.out.println("文件读取错误");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	//FileWriter写unicode字符到文件
	void testFileWriter(String outputPath)
	{
		
		FileWriter writer = null;
		
		try
		{
			writer = new FileWriter(outputPath);
			for(int c=0; c<=50000; c++)//unicode编码中大部分国家的字符都包含
			{
				writer.write(c);
			}
			writer.close();
		}catch(FileNotFoundException e)
		{
			System.out.println("File cannot be found, 找不到指定文件！");
			System.exit(-1);
		}catch(IOException e)
		{
			System.out.println("文件写入错误");
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("文件已写入");
	}
}