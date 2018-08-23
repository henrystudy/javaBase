import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/***
 * 
 * @author zhenhaiw
 *
 */


public class codeCounter
{
	static int normalLine = 0;
	static int commentLine = 0;
	static int whiteLine = 0;
	
	public static void main(String[] args)
	{
		File file = new File("D:\\eclipse_workspace\\RegularExpression\\temp");
		File[] fileList = file.listFiles();
		for(File f : fileList)
		{
			parseFile(f);
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
				}else if(comments == true)
				{
					commentLine++;
				}else if(line.startsWith("*/"))
				{
					comments = false;
				}
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
