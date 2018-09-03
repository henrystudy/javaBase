/**
* 排序参数数组args[]并输出
* 理解冒泡排序，选择排序，以及程序效率改进
*
**/

public class SortArgs
{
	public static void main(String[] args)
	{
		SortArgs as = new SortArgs();
		/*//逐个输出原数组元素
		as.printArr(args);
		
		//String数组元素排序
		//as.sortArray(args);
		as.selectionSortArray(args);
		
		//输出排序之后的数组元素
		System.out.println();
		as.printArr(args);
		*/
		///*
		//测试int[]排序输出(方法重载)
		System.out.println();
		int[] a = {6,5,7,4,2,5,1};
		as.printArr(a);
		as.selectionSortArray(a);
		System.out.println();
		as.printArr(a);
		//*/
	}
	
	//封装String数组打印为方法
	private void printArr(String[] args)
	{
		for(int i=0; i<args.length; i++)
		{
			System.out.print(args[i] + " ");
		}
	}
	
	//封装int数组打印为方法
	private void printArr(int[] args)
	{
		for(int i=0; i<args.length; i++)
		{
			System.out.print(args[i] + " ");
		}
	}
	
	//简单选择排序(常用于取最大最小值)
	//int数组排序
	public void selectionSortArray(int[] arr)
	{
		for(int i=0; i<arr.length; i++)
		{
			/*相比下面写法，效率低一些
			for(int j=i+1; j < arr.length; j++)
			{
				if(arr[i] > arr[j])
				{
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			*/
			
			/*这种写法也ok
			int key = arr[i];
			int pos = i;
			for(int j=i+1; j<arr.length; j++)
			{
				if(key > arr[j])
					{
						key = arr[j];
						pos = j;
					}
			}
			arr[pos] = arr[i];
			arr[i] = key;
			*/
		}
		
		//效率会高一点，避免了可能的多次交换，改成每个循环多次记录一次交换
		for(int i=0; i < arr.length; i++)
		{
			int k = i;//两个参数放在循环外边效率会更高一点，不需要每次循环都初始化
			int temp;
			for(int j=k+1; j<arr.length; j++)
			{
				if(arr[k] > arr[j])
				{
					k = j;//只是记录位置
				}
			}
			
			if(i != k)
			{
				temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
			}
		}
	}
	
	//String数组排序
	public void selectionSortArray(String[] arr)
	{
		//声明一个跟String arr[]同等长度的int[]
		int[] intArr = new int[arr.length];
		//String[] arr元素逐个取出转换为int并赋值给int[]
		for(int i=0; i<arr.length; i++)
		{
			intArr[i] = Integer.parseInt(arr[i]);
		}
		
		for(int i=0; i<intArr.length; i++)
		{
			for(int j=i+1; j<intArr.length; j++)
			{
				if(intArr[i] > intArr[j])
					{
						int temp = intArr[i];
						intArr[i] = intArr[j];
						intArr[j] = temp;
					}
			}
		}

		//排好序的int[]元素逐个取出并传回原String[]数组
		for(int i=0; i<intArr.length; i++)
		{
			arr[i] = Integer.toString(intArr[i]);
		}
	}
	
	//冒泡(交换)排序，多次交换，效率低，一般不用
	//int数组的排序
	public void sortArray(int[] arr)
	{
		for(int i = 0;i < arr.length;i ++)
		{
			for(int j=1; j<arr.length-i; j++)
			{
				if(arr[j-1] > arr[j])
				{
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	//String数组排序, 先转化成int数组, 排序之后再转回去String[]
	public void sortArray(String[] arr)
	{
		//声明一个跟String arr[]同等长度的int[]
		int[] intArr = new int[arr.length];
		//String[] arr元素逐个取出转换为int并赋值给int[]
		for(int i=0; i<arr.length; i++)
		{
			intArr[i] = Integer.parseInt(arr[i]);
		}
		
		//冒泡排序int[]数组元素
		for(int i = 0;i < intArr.length;i ++)
		{
			for(int j=1; j<intArr.length-i; j++)
			{
				if(intArr[j-1] > intArr[j])
				{
					int temp = intArr[j-1];
					intArr[j-1] = intArr[j];
					intArr[j] = temp;
				}
			}
		}
		
		//排好序的int[]元素逐个取出并传回原String[]数组
		for(int i=0; i<intArr.length; i++)
		{
			arr[i] = Integer.toString(intArr[i]);
		}
	}
}