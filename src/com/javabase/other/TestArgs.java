/**
* String[] args数组的用法
* 
*
**/

public class TestArgs
{
	public static void main(String[] args)
	{
		if(args.length < 3)
		{
			System.out.println("Usage: \"n1\" \"ops\" \"n2\"");
			//避免因为长度不够后边args[2]抛出越界异常,手动抛出异常
			throw new ArrayIndexOutOfBoundsException("Length is not enough, please input again following above usage."); 
		}
		
		//double封装类转换String为相应数据类型, 以下两种都可以
		//double d1 = Double.parseDouble(args[0]);
		//double d2 = Double.parseDouble(args[2]);
		double d1 = new Double(args[0]).doubleValue();
		double d2 = new Double(args[2]).doubleValue();
		double d = 0;
		
		if(args[1].equals("+"))
		{
			d = d1 + d2;
			for(int i = 0;i < args.length;i ++)
			{
				System.out.print(args[i] + " ");//遍历打印args[]参数数组
			}
			System.out.println("= " + d);
		}else if(args[1].equals("-"))
		{
			d = d1 - d2;
			for(int i = 0;i < args.length;i ++)
			{
				System.out.print(args[i] + " ");
			}
			System.out.println("= " + d);
		}else if(args[1].equals("x"))
		{
			d = d1 * d2;
			for(int i = 0;i < args.length;i ++)
			{
				System.out.print(args[i] + " ");
			}
			System.out.println("= " + d);
		}else if(args[1].equals("/"))
		{
			d = d1 / d2;
			for(int i = 0;i < args.length;i ++)
			{
				System.out.print(args[i] + " ");
			}
			System.out.println("= " + d);
		}else
		{
			System.out.println("Error...");
			System.exit(-1);//非0为非正常退出JVM
		}
	}
}