package com.javabase.corejava.part1;

import static java.lang.Math.*; //静态导入
import java.math.*;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;

/**
*字母数字下划线$是可以作为方法变量名的
*/
public class Chap3
{
	public static void main(String[] args) throws FileNotFoundException
	{	
		Chap3 chap3 = new Chap3();
		//chap3.chap3_5_1a2();
		//System.out.println("\n");
		//chap3.chap3_5_3();
		//chap3.chap3_5_4();
		//chap3.chap3_5_8();
		//chap3.chap3_6();
		//chap3.chap3_7_1a3();
		//chap3.chap3_7_3();
		//chap3.chap3_8_3_Retirement();
		//chap3.chap3_9();
		//chap3.chap3_10_3();
		//chap3.chap3_10_5LotteryDrawing();
		//chap3.chap3_10_6();
		chap3.chap3_10_7();
	}
	
	/**
	*注意++n, n++区别: ++n先进行+1运算, n++则使用变量n原来value运算后再+1
	*/
	private void chap3_5_1a2()
	{
		int m = 2;
		int n = 5;
		System.out.println("m = " + m + ", n = " + n);
		
		//区别++n, n++
		int a = n * ++m;
		--m;//自减1恢复，m = m - 1
		int b = n * m++;
		--m;//自减1恢复, m = m - 1
		System.out.println("n * ++m = " + a);
		System.out.println("n * m++ = " + b);
		
		//条件判断(condition ? expression1 : expression2), 取较大的value
		int x = m > n ? m : n;
		System.out.println("m > n ? m : n = " + x);

		//+=, -= 右结合运算符
		n += 5;
		System.out.println("n += 5, n = " + n);
		n -= 5;
		System.out.println("n -= 5, n = " + n);
		m += n -= m;//右结合运算从右往左运算
		System.out.println("m += n -=m, m = " + m + ", n = " + n);
		m -= 3;
		n += 2;
	}

	/**
	*十进制换二进制: 除2倒序取余
	*负数十进制换二进制: 正数二进制补全8位取反再加一, 5=00000101.-5=11111011=251
	*小数十进制换二进制: 小数点后x2取结果整数部分(0或者1)...直到小数为0或者位数补足,顺序取; 整数部分按整数规则取余倒序
	*二进制换十进制: 从右往左, 第n位x2的n次方然后相加(从0位开始)
	*(负)二进制换十进制: 补足后首位为1则为负数, 除去符号位减1取反加负号(1011-->101(5)(除去符号位取反)-->(-5)纠结了很久, 多理解一下
	*小数二进制换十进制: 从左往右, 第n位x2的-n次方然后相加(从0位开始)
	*/
	private void chap3_5_3()
	{
		//System.out.println("hey, I'm not \"hello world!\"");
		
		//遍历-10~10的二进制数值
		for(int i=1;i<=10;i++)
		{
			System.out.print(i + " 二进制: ");
			printInfo(i);
		}
		for(int i=-1;i>=-10;i--)
		{
			System.out.print(i + " 二进制: ");
			printInfo(i);
		}
		
		//“&” 与, 操作数的同位均为1则同位结果为1, 否则同位结果为0
		System.out.println("5&3: " + String.valueOf(5&3));//5(0101), 3(0011), result(0001)1 
		System.out.println("3&2: " + String.valueOf(3&2));//3(0011), 2(0010), result(0010)2
		System.out.println("4&1: " + String.valueOf(4&1));//4(0100), 1(0001), result(0000)0
		
		//“|” 或, 任一操作数的同位为1则同位结果为1, 否则同位结果为0
		System.out.println("5|3: " + String.valueOf(5|3));//5(0101), 3(0011), result(0111)7
		
		//“^” 异或, 作数的同位相反则同位结果为1, 否则同位结果为0
		System.out.println("5^3: " + String.valueOf(5^3));//5(0101), 3(0011), result(0110)6
		
		//“~” 非, 操作数的同位相反则同位结果为1, 否则同位结果为0
		System.out.println("~5: " + String.valueOf(~5));//5(0101), result(1010)-6, 符号位1为负数, 去符号位减一取反加负号
		
		//"<<"(低位补0) ">>"(高位补符号位也就是最高位(0或1)) ">>>" (高位补0)
		//注意JAVA中一个有负号的int型是32位,表示范围是-2^31~2^31-1
		System.out.println("5<<2: " + String.valueOf(5<<2));//5(00000101)-->00010100(20)
		System.out.println("5<<2: " + String.valueOf(5>>2));//5(00000101)-->00000001(1)
		System.out.println("-5>>2: " + String.valueOf(-5>>2));//5(0000 0000 0000 0000 0000 0000 0000 0101)-->(-5)(1111 1111 1111 1111 1111 1111 1111 1011)-->1111 1111 1111 1111 1111 1111 1111 1110(-2)//减1=101,取反=010,加负=-2
		System.out.println("-5>>>2: " + String.valueOf(-5>>>2));//(-5)(1111 1111 1111 1111 1111 1111 1111 1011)-->(0011 1111 1111 1111 1111 1111 1111 1110)(1073741822)//补足32位之后的值
	}

	private void printInfo(int num)
	{
		System.out.println(Integer.toBinaryString(num));//转化打印二进制数值
	}
	
	/**
	*Math Classd 方法与常量, Math.sqrt(x), Math.pow(x,a), Math.round(x), Math.PI...
	*/
	private void chap3_5_4()
	{
		double a = 4;
		double b = 2;
		double x = sqrt(a);//调用Math Class的求平方根方法sqrt(), Math Class已经在顶部static静态导入
		double y = pow(a, b);//调用Math Class的幂运算方法pow
		System.out.println(a + "的平方根 = " + x);
		System.out.println(a + "的" + b + "次方 = " + y);
		System.out.println("PI = " + PI);//调用Math定义的PI常量
		
		double m = a * b / 3;
		int t = (int) round(m);//double强制类型转换成int, 调用Math四舍五入方法roung()
		System.out.println(m + "四舍五入 = " + t);
	}

	private void chap3_5_8()
	{		
		Size s = Size.XL;//声明枚举变量
		System.out.println(s);
	}

	private enum Size { S, M, L, XL }; //枚举类型, 有限个固定命名的value
	
	/**
	*字符串操作: substring(), length(), equals(), equalsIgnoreCase(), 拼接... 
	*代码单元, 代码点
	*equals()对比compareTo(), 意思不同, 返回值类型也不同
	*StringBuilder效率高但仅可以单线程, StringBuffer效率低但允许多线程
	*/
	private void chap3_6()
	{
		String str1 = "Hello!";
		String str2 = "hello!";
		
		int length1 = str1.length();
		int length2 = str2.length();
		String sub1 = str1.substring(2);//beginIndex to the end
		String sub2 = str1.substring(2, 5);//fromn beginIndex to endIndex-1(注意endIndex-1)
		String newstr1 = str1.substring(0,3) + "p!";
		boolean equal = str1.equals(str2);
		boolean equalIgnoreCase = str1.equalsIgnoreCase(str2);
		//按字典顺序比较字符串;str1在str2之前返回负数, 之后返回整数，0则两个字符串一样
		int x = str1.compareTo(str2);
		int y = str1.compareToIgnoreCase(str2);
		
		System.out.println("Original Str1= " + str1 + ", Length = " + length1);
		System.out.println("Original Str2= " + str2 + ", Length = " + length2);
		System.out.println("sub1 = " + sub1 + "; sub2 = " + sub2);
		System.out.println("New Str1 = " + newstr1);
		System.out.println("Str1 equal to Str2? " + equal);
		System.out.println("Str1 equal to Str2?(ignore case) " + equalIgnoreCase);
		System.out.println(x + ", " + y);
		
		String strx = "中文Abc";//代码单元和代码点CodePoint
		int count = strx.codePointCount(0, strx.length());
		System.out.println(count);
		
		for(int i = 0; i < strx.length(); i ++)
		{
			char chr = strx.charAt(i);//返回代码单元
			System.out.println(chr);
		}
		
		//遍历字符串并依次查看代码点
		for(int i = 0;;)
		{
			if(i < strx.length())
			{
				int cp = strx.codePointAt(i);//返回代码点
				System.out.println((char)cp + "--" + cp);//(char)强制转换后返回代码单元
				if(Character.isSupplementaryCodePoint(cp))
					i += 2;
				else
					i ++;
			}else
				break;		
		}
		
		//StringBuilder字符串构造器
		String strBd1 = "Hello, ";
		//char chrBd1 = 's';
		char[] chrBd1 = {'S', 't', 'r', 'b', 'u', 'i', 'l', 'd', 'e', 'r', '!'};
		StringBuilder builder = new StringBuilder();
		builder.append(strBd1);//append字符串
		builder.append(chrBd1);//append代码单元(字符)
		builder.insert(7, "new comer, ");//在offset位置插入
		String temp = builder.toString();//返回与构造器相同的字符串
		System.out.println(temp);
		
	}
	
	/**
	*Scanner读取明文输入
	*Console读取密码之类的输入
	*格式化输出
	*/
	private void chap3_7_1a3()
	{
		//明文读取
		Scanner in = new Scanner(System.in);
		
		System.out.println("What's your name?");
		String name = in.next();//读取输入
		System.out.println("How old are you?");
		int age = in.nextInt();//二次读取输入
		System.out.println("Hey, " + name + ", next year you will be " + (age + 1) + " years old!");
		
		//读取密码
		Console cons = System.console();
		String username = cons.readLine("Username: ");
		char[] password = cons.readPassword("Password: ");//密码放到数组中
		//System.out.println("Username: " + username + "; Password: " + password);
		//填充密码数组 
		if (cons != null && password != null) //返回的pwd放在数组中, 该马上用填充值覆盖
		{
			java.util.Arrays.fill(password, ' ');
		}
		
		//格式化输出
		double a = -100000.0/3.0;
		System.out.printf("%8.3f \n", a);//8字符宽度, 小数点后2位精度
		System.out.printf("%,.3f \n", a);//分组分隔符
		System.out.printf("%,(.3f \n", a);//负数用()包围
		
		//注意参数索引的对应顺序
		Date date = new Date();
		System.out.printf("%tc \n", date);
		System.out.printf("Due Date: %1$tB %1$td, %1$tY \n", date);
		System.out.printf("%1$s %2$tB %2$td, %2$tY \n", "Due Date:", date);//注意参数索引, 对应后面多个参数顺序
		System.out.printf("%1$s%2$tB %2$td, %2$tY \n%3$tm/%3$td/%3$tY \n", "Date Format: \n", date, date);
	}
	
	/**
	*文件输入输出操作
	*/
	private void chap3_7_3() throws FileNotFoundException //异常处理
	{
		PrintWriter writer = new PrintWriter("D:\\Java\\testText.txt");
		writer.println("this is a test file!");
		writer.println("test...\ntest...\ntest...\ntest...");
		writer.append("append to the end!");
		writer.close();//记得写入最后close()
		
		Scanner scanner = new Scanner(new File("D:\\Java\\testText.txt"));
		while(scanner.hasNextLine())
		{
			String str = scanner.nextLine();
			System.out.println(str);
		}
		scanner.close();//读取最后也要close()
	}
	
	/**
	*do{} while() 循环保证block至少被执行一次; while() {}则有可能不被执行
	*/
	private void chap3_8_3_Retirement()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("How much money will you contribute ever year? ");
		double payment = scanner.nextDouble();
		System.out.print("Interest in %: ");
		double interestRate = scanner.nextDouble();
		
		double blance = 0;
		int year = 0;
		String input;//需要再循环体外部使用的变量要在外部定义, 循环体内部的变量不可再外部调用
		
		//update account balance while user is not ready to retire
		do 
		{
			//add this year's payment and interest
			blance += payment;
			double interest = payment * interestRate/100;
			blance += interest;
			year ++;
			
			//print current balance
			System.out.printf("After %d year, your balance is %,.2f%n", year, blance);//注意格式化输出是printf
			//ask if ready to retire and get input
			System.out.print("Are you ready to retire?(Y/N) ");
			input = scanner.next();
		}
		while(input.equals("N"));//注意字符串比较是equals()
	}

	/**
	*从比较大的数据中中彩票的概率
	*/
	private void chap3_9()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("How many numbers do you need to draw? ");
		int k = scanner.nextInt();
		System.out.print("What is the highest number you can draw? ");
		int n = scanner.nextInt();
		
		//从n个数中选k个中奖的概率是 n*(n-1)*(n-2)*...(n-k+1)/1*2*...*k
		BigInteger lotteryOdds = BigInteger.valueOf(1);
		for(int i = 1; i <= k; i ++)
		{
			//+-*/运算符在BigInteger中是不能用的, 用所属方法add, subtract, mutiply, divide代替
			lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
		}
		System.out.print("You odds is 1 in " + lotteryOdds + ". Good luck!");
	}
	
	/**
	*数组操作Arrays.toString();Arrays.copyOf();System.arraycopy()
	*/	
	private void chap3_10_3()
	{
		int[] a = {0, 1, 2, 3, 4, 5};
		int[] b = {1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009};
		System.out.println("int[] a = " + Arrays.toString(a));
		System.out.println("int[] b = " + Arrays.toString(b));
		
		//Copy数组,两个变量将引用同一数组, 任一元素变化, 两个数组数据同步
		int[] cArray = a;
		a[1] = 11;
		cArray[2] = 222;
		System.out.println("int[] a = " + Arrays.toString(a));//数组转化成String
		System.out.println("int[] cArray = " + Arrays.toString(cArray));
		
		//Copy数组的值
		int[] cValue = Arrays.copyOf(a, b.length);//Copya[]的值到cValue[], 并定义cValue长度为b[]的长度, 多的空位默认赋值0
		System.out.print("int[] cValue = "); 
		for(int i = 0; i < cValue.length; i++)
		{
			System.out.print(cValue[i] + ", ");
		}
		
		//Copy数组的部分数值到另一个数组的特定位置
		System.arraycopy(a, 2, b, 6, 3);//从数组b第6位开始取3位, 替换到a从第2位开始
		System.out.print("\n数组a,b特定部分数值互换 = ");
		for(int i:b)//for each遍历循环
		{
			System.out.print(i + ", ");
		}
	}
	
	/**
	*Math.random() - 随机数 double型 (0.0-1.0)
	*数组排序等, 熟悉API  Arrays.的方法
	*/	
	private void chap3_10_5LotteryDrawing()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many numbers do you need to draw?");
		int k = scanner.nextInt();
		System.out.print("What is the highest numbers you can draw?");
		int n = scanner.nextInt();
		
		//初始化备选号码数组并赋值1,2,3...n
		int[] number = new int[n];
		for(int i = 0; i < number.length; i ++)
		{
			number[i] = i + 1;
		}
		
		//random()随机选取0-n下标的数据并放到result[]
		int[] result = new int[k];
		for(int j = 0; j < result.length; j ++)
		{
			int r = (int)random() * n;//random()是double, 需要强制类型转换
			result[j] = number[r];
			number[r] = number[n-1];//把选中的数用数组最后的数填充掉, 以免取到同样数据
			n--;
		}
		
		System.out.println("随机抽取数据 = " + Arrays.toString(result));
		Arrays.sort(result);//数组元素排序
		System.out.println("数组排序后 = " + Arrays.toString(result));
	}
	
	/**
	*多维数组操作
	*/	
	private void chap3_10_6()
	{
		//初始化一个利率的一维数组, 转化成%格式
		double[] interest = {0.1, 0.11, 0.12, 0.13, 0.14};
		NumberFormat perc = NumberFormat.getPercentInstance();
		perc.setMaximumIntegerDigits(3);
		perc.setMaximumFractionDigits(2);
						
		//初始化一个不同利率, x年后的余额
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many years will you save the money in bank? ");
		int year = scanner.nextInt();
		double[][] balance = new double[year][5];
				
		//设置本金并输出
		for(int j = 0; j < balance[0].length; j++)
		{
			balance[0][j]  = 10000;//set本金
		}
		
		//不同年限不同利率计算本息
		for(int i = 1;i < balance.length; i++)//年遍历
		{
			//System.out.print("第" + i + "年: ");
			for(int j = 0; j < balance[0].length; j++)//不同利息遍历
			{
				balance[i][j] = balance[i - 1][j] + balance[i - 1][j] * interest[j];
				//System.out.printf("%(.2f ", balance[i][j]);
			}
			//System.out.println("\n");
		}
		
		//以%格式输出利息
		for(double i : interest)
		{
			System.out.print("  " + perc.format(i) + "     ");//注意for each用法, 直接引用i, 不是interest[i]
		}

		System.out.println();
		
		//输出多维数组table
		for(double[] row : balance)
		{
			//输出行
			for(double b : row)
			{
				System.out.printf("%10.2f", b);//格式化输出
			}
			System.out.println();
		}
	}
	
	/**
	*Java没有多维数组,所谓多维数组是数组的数组,所以可以据此创建不规则数组
	*/	
	private void chap3_10_7()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many rows do you want to draw? ");
		int row = scanner.nextInt();
		//System.out.print("How many columns do you want to draw? ");
		//int column = scanner.nextInt();
		
		//初始化二维数组行数
		int[][] map = new int[row][];
		
		//初始化二维数组每行容量
		for(int i = 0; i < row; i++)
		{
			map[i] = new int[i + 1];
		}
		
		//数组赋值
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
			{
				int lotteryOdds = 1;
				for(int k = 1; k <= j; k++)
				{
					lotteryOdds = lotteryOdds * (j - k + 1)/k;
				}
				map[i][j] = lotteryOdds;
			}
		
		//数组逐个输出
		for(int[] r : map)
		{
			for(int c : r)
			{
				System.out.printf("%4d", c);
			}
		System.out.println();
		}
	}
}