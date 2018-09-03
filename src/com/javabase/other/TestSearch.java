/**
* 查找算法，二分法等
*
**/

public class TestSearch
{
	public static void main(String[] args)
	{
		int[] arr = {1, 4, 6, 7, 8, 12, 14, 19};
		int a = 19;
		
		System.out.println(binarySearch(arr, a));
	}
	
	static int binarySearch(int[] arr, int a)
	{
		int startPos = 0;
		int endPos = arr.length;
		int m = (startPos + endPos)/2;
		
		if(arr.length <= 0)
		{
			return -1;
		}
		
		while(startPos <= endPos)
		{
			if(arr[m] == a)
			{
				return m;
			}else if(arr[m] > a)
			{
				endPos = m -1;
			}else if(arr[m] < a)
			{
				startPos = m + 1;
			}
			m = (startPos + endPos)/2;
		}
		return -1;//这里是其他的情况
	}
}
