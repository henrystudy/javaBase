/**
* 数组Copy
* 注意二维数组的第一维是引用
*
**/

public class TestArrayCopy
{
	public static void main(String[] args)
	{
		//一维数组copy
		String[] a = {"AA","BB","CC"};
		String[] b = new String[8];
		b[0] = "1";
		b[1] = "2";
		b[2] = "3";
		
		System.arraycopy(a,0,b,3,a.length);
		for(int i=0; i<b.length; i++)
		{
			System.out.print(b[i] + " ");
		}
		
		System.out.println();
		
		//二维数组copy
		int[][] m = {{2,3,3},{2,3},{1,2,3,4,5,6}};
		int[][] n = new int[4][];
		n[0] = new int[5];
		System.arraycopy(m,0,n,1,m.length);
		n[0][4] = 100;
		n[3][5] = 8888;//注意这里m对应的值也跟着改变，因为m，n Copy高维是copy的引用地址
		
		for(int i=0; i<n.length; i++)
		{
			for(int j=0; j<n[i].length; j++)
			{
				System.out.print(n[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i=0; i<m.length; i++)
		{
			for(int j=0; j<m[i].length; j++)
			{
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}