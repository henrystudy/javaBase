/**
* 二维数组的声明和初始化
*
**/

public class Test2Arr
{
	public static void main(String[] args)
	{
		String[][] s = new String[3][];
		s[0] = new String[3];
		s[1] = new String[6];
		s[2] = new String[5];
		
		//数组初始化赋值
		for(int i=0; i<s.length; i++)
		{
			for(int j=0; j<s[i].length; j++)
			{
				s[i][j] = "我的位置： " + "[" + i + "]" + "[" + j + "]";
			}
		}
		
		//数组元素输出
		for(int i=0; i<s.length; i++)
		{
			for(int j=0; j<s[i].length; j++)
			{
				System.out.println(s[i][j]);
			}
		}
	}
}