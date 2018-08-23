package com.javabase.regex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/***
 * 计算文件夹下的.java代码文件的有效代码行数，注释行数，空白行数
 * @author zhenhaiw
 *
 */


public class codeCounter
{
	static long normalLine = 0;
	static long commentLine = 0;
	static long whiteLine = 0;
	
	public static void main(String[] args)
	{
		File file = new File("D:\\eclipse_workspace\\RegularExpression\\temp");
		File[] fileList = file.listFiles();
		for(File f : fileList)
		{
			if(f.getName().endsWith(".java")) //或者使用regex ".*\\.java$"
			{
				parseFile(f);
			}
		}
		
		System.out.println("normal line: " + normalLine);
		System.out.println("comments line: " + commentLine);
		System.out.println("white line: " + whiteLine);
	}
	
	public static void parseFile(File f)
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(f));
			String line = "";
			boolean comments = false;
			
			while((line = br.readLine()) != null)
			{
				line = line.trim();//
				
				if(line.matches("^[\\s&&[^\\n]]*")) //这里注意不是使用"^[\\s&&[^\\n]]*[\\n]$"，是因为readLine()会使用掉\n
				{
					whiteLine++;
				}else if((line.startsWith("/*")) && !(line.endsWith("*/")))
				{
					commentLine++;
					comments = true;
				}else if((line.startsWith("/*")) && (line.endsWith("*/")))
				{
					commentLine++;
				}else if(true == comments) //区别comments == true
				{
					commentLine++;
					if(line.endsWith("*/"))//注意
					{
						comments = false;
					}
				}
/*				else if(line.endsWith("*\/"))
				{
					comments = false;
				}
*/
				else if(line.startsWith("//"))
				{
					commentLine++;
				}else
				{
					normalLine++;
				}
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
