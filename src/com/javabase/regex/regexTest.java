package com.javabase.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexTest 
{
	public static void main(String[] args)
	{
		//简单认识正则表达式./[0-9]/\d(一位数字)
/*		pr("abc".matches("..."));
		pr("a87897a".replaceAll("[0-9]", "-"));
		pr("a87897a".replaceAll("\\d", "-"));
		
		//#1 先编译好，效率高，并且可以使用Pattern/MAtcher提供的丰富方法
		Pattern p = Pattern.compile("[a-z]{3}");
		Matcher m = p.matcher("sdf");
		pr(m.matches());
		
		//#2 没有编译，效率低，不能使用Pattern/Matcher提供的其他方法
		pr("sdf".matches("[a-z]{3}"));
*/
		
		//初识. * + ?
		pr("a".matches("."));
		pr("aa".matches("aa"));
		//pr("aa".matches("*"));//报错,必须字符开头
		pr("aaaaa".matches("a*"));
		pr("assfds".matches(".*"));//任意字符，零次或多次
		pr("aaaaa".matches("a+"));
		pr("ab".matches("ab.+")); //false
		pr("ab".matches("ab.*")); //true
		pr("assfds".matches(".+"));
		pr("".matches("a*"));
		pr("".matches("a?"));
		pr("a".matches("a?"));
		
		pr("192.168.1.aa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		pr("192.168.1.16".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		//pr("192.168.1.16".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		
/*		//范围表示
		pr("a".matches("[a-z]"));
		pr("a".matches("[abc]"));
		pr("A".matches("[^abc]"));
		pr("A".matches("[a-zA-Z]"));
		pr("A".matches("[a-z]|[A-Z]"));
		pr("A".matches("[a-z]|[A-Z]"));
		pr("A".matches("[a-z[A-Z]]"));//或者
		pr("R".matches("[A-Z&&[RFG]]"));
		//pr("R".matches("[A-Z]&&[RFG]"));//API无此写法
*/	
		//特殊字符
/*		pr("s_77".matches("[\\w]{4}"));
		pr(" \t\n".matches("[\\s]{1,5}"));
		pr("231321".matches("[\\d]{1,10}"));
		pr("abc2343&%".matches("[a-z]{3}\\d+[^&%]+"));//注意[^&%]的写法^或变成非
		pr("abc2343&^#%".matches("[a-z]{3}\\d+[&^%#]+"));
		pr("\\".matches("\\\\")); //regex中两个\代表一个\
*/		
		//边界
/*		pr("hello sir".matches("^h.*"));
		//pr("hello sir".matches("^h*"));//*前面需要有任意可用字符，不是hhhhhh
		pr("hello sir".matches(".*ir$"));
		pr("hello sir".matches("^h[a-z]{3}o\\b.*"));
		//空白行
		pr("".matches("^[\\s&&[^\\n]]*\\n$")); //空白但不是换行符开头并以换行符结尾
		
		pr("aaa 8888c".matches(".*\\d{4}."));
		pr("aaa 8888c".matches(".*\\b\\d{4}."));
		pr("aaa8888c".matches(".*\\d{4}."));
		pr("aaa8888c".matches(".*\\b\\d{4}."));//false

		//匹配email地址
		pr("asd@sd.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));
*/
		//查找输出子串
/*		Pattern p = Pattern.compile("\\d{3,5}");
//		String s ="123-453232-234-00";
		String s ="123c453232d234f00";
		Matcher m = p.matcher(s);
		
		pr(m.matches());//match判断过的前5个字符不会被重置回去，配合reset使用
		m.reset();
		pr(m.find());
		pr(m.group());
		pr("起始-结束index: " + m.start() + "-" + (m.end() - 1));//注意m.end()不同于m.start(),返回的是从起始位置计算的offset，不是index
		for(int i = m.start(); i <= (m.start()+m.end()-1); i++)
		{
			System.out.print(s.charAt(i));
		}
		System.out.println();

		pr(m.find());
		pr(m.group());
		pr("起始-结束index: " + m.start() + "-" + (m.end() - 1));
		for(int i = m.start(); i <= (m.end()-1); i++)
		{
			System.out.print(s.charAt(i));
		}
		System.out.println();
//		pr(s.charAt(m.start()) + "-" + s.charAt(m.end()));
		pr(m.find());
		pr(m.group());
		pr("起始-结束index: " + m.start() + "-" + (m.end() - 1));
		for(int i = m.start(); i <= (m.end()-1); i++)
		{
			System.out.print(s.charAt(i));
		}
		System.out.println();
//		pr(s.charAt(m.start()) + "-" + s.charAt(m.end()));
		pr(m.find());
		
		pr(m.lookingAt());
		pr(m.lookingAt());
*/
		//匹配替换字符串
/*//		Pattern p = Pattern.compile("java");
		Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("java Java zxxz JAVa JaVa IloveJAVA you hatejava asdfasfa");
		
		pr(m.matches());
		
//		pr(m.replaceAll("JAVA"));//替换所有符合条件子串，返回替换后的整个字符串
		
		StringBuffer buf = new StringBuffer();
		int i = 0;
		
		while(m.find())//实现奇数小写，偶数大写
		{
			i++;
			if(i%2 == 0)
			{
				m.appendReplacement(buf, "JAVA");
			}else
			{
				m.appendReplacement(buf, "java");
			}
		}
		m.appendTail(buf);//把不符合条件的子串补进来
		pr(buf);
		
//		while(m.find())
//		{
//			pr(m.group());
//		}
*/	
		
		//正则表达式分组
/*		Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		String s ="123aa-45323df2-234wer-00";
		Matcher m = p.matcher(s);
		
		while(m.find())
		{
			pr(m.group());
			pr(m.group(1));
			pr(m.group(2));
		}
*/
		//Qualifier限定符
/*		//Pattern p = Pattern.compile("(.2,15}[0-9]");
		//Pattern p = Pattern.compile("(.2,15}?[0-9]");
		Pattern p = Pattern.compile("(.2,15}+[0-9]");
		String s = "aaa5bbbb6ccc3na";
		Matcher m = p.matcher(s);
		
		if(m.find())
		{
			pr(m.start() + "-" + m.end());
			pr(m.group());
		}else
		{
			pr("not match");
		}
*/
		
		//non-capturing 
/*		Pattern p = Pattern.compile("(?=a).{3}");
		String s = "aaaa5bbbb6cca3na";
		Matcher m = p.matcher(s);
		
		while(m.find())
		{
			pr(m.group());
		}
*/
	}
	
	public static void pr(Object o)
	{
		System.out.println(o);
	}
}
