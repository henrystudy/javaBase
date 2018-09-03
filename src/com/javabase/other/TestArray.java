/**
* 数组声明时不能指定长度比如int[3] name; 可以在new的时候指定比如int[] name; name = new int[3];
* 数组元素为引用类型的，需要每个元素实例化
*
**/

public class TestArray
{
	public static void main(String[] args)
	{
		Days d = new Days();
		d.testDays();
	}
}

class Days
{
	int year;
	int month;
	int day;
	
	Days()
	{
		
	}
	
	Days(int y, int m, int d)
	{
		this.year = y;
		this.month = m;
		this.day = d;
	}
	
	public String toString()//重写Object.toString()方法，输出便于阅读的数据
	{
		return year + ", " + month + ", " + day;
	}
	
	void testDays()
	{
		Days[] d = new Days[3];
		for(int i = 0;i < 3;i ++)
		{
			d[i] = new Days(2017, 12, i + 1);//数组元素为引用类型，每个元素都需要实例化
			System.out.println(d[i]);//按照重写后的toString()输出数据
		}
	}
}