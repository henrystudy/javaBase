package com.javabase.regex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 从一个文本文件匹配email地址并打印出来
 * @author zhenhaiw
 *
 */

public class emailSpider 
{

	public static void main(String[] args) 
	{
		String FilePath = "D:\\01 Self Study\\Java\\Practice Code\\regex_email_html\\leave_email.html";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(FilePath));
			String line = "";
			while((line = br.readLine()) != null)
			{
				parseLine(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseLine(String line)
	{
		Pattern p = Pattern.compile("([\\w[.-]]+@[\\w[.-]]+)(\\.[\\w]+)");
		Matcher m = p.matcher(line);
		
		while(m.find() && !m.group(2).equals(".png"))//去掉符合格式的png字符串
		{
			System.out.println(m.group());
		}
	}

}
