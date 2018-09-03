import java.io.*;
/**
* java.io.File类常用方法练习
* 路径尽量使用正斜杠/
* 递归的使用(使用递归重点是找到可以递归的方法)
**/

public class TestFile
{
	public static void main(String[] args)
	{
		TestFile tf = new TestFile();
		
		String path = "./mydir1/mydir2";
		File file = new File(path);
		//tf.createFile();
		tf.listAsTree(file, 0);
	}
	
	//创建特定目录的文件
	void createFile()
	{
		String separator = File.separator;
		String filename = "myfile.txt";
		String directory = "mydir1" + separator + "mydir2";
		File file = new File(directory, filename);
		
		if(file.exists())
		{
			System.out.println("文件名: " + file.getAbsolutePath());
			System.out.println("文件大小: " + file.length());
		}else
		{
			file.getParentFile().mkdirs();
			try
			{
				file.createNewFile();
			}catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
		
	//树状呈现特定文件夹下的文件夹以及文件, 使用递归
	void listAsTree(File file, int level)
	{
		String preStr = "";
		for(int i=0; i<level; i++)
		{
			preStr += "    ";//根据level层数，每层缩进4个空格
		}
		
		if(!file.exists())
		{
			System.out.println("File is not exists!");
		}else
		{
			File[] files = file.listFiles();
			for(File f : files)
			{
				System.out.println(preStr + f.getName());
				if(f.isDirectory())
				{
					listAsTree(f, level + 1);//根据level层次逐层缩进
				}
			}
		}
	}
}