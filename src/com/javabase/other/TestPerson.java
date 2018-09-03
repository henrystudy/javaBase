/**
* 继承中的构造方法
* 子类构造方法如果没有显式调用父类构造方法，则默认调用父类空参构造方法
**/

class Person
{
	private String name;
	private String location;
	
	Person(String name)
	{
		this.name = name;
		this.location = "beijing";
	}
	
	Person(String name,String location)
	{
		this.name = name;
		this.location = location;
	}
	
	protected void getInfo()
	{
		System.out.println("name: " + name + "\nlocation: " + location);
	}
}

class Student extends Person
{
	private String school;
	
	Student(String name, String school)
	{
		this(name, "shanghai", school);//this()调用本类的另一个构造方法
	}
	
	Student(String name, String location, String school)
	{
		super(name, location);
		this.school = school;
	}
	
	protected void getInfo()
	{
		super.getInfo();
		System.out.println("school: " + school);
	}
}

public class TestPerson
{
	public static void main(String[] args)
	{
		Person p1 = new Person("AAA");
		Person p2 = new Person("BBB", "BEIJING");
		Student s1 = new Student("CCC", "school");
		Student s2 = new Student("DDD", "SHANGHAI", "SCHOOL");
		
		p1.getInfo();
		p2.getInfo();
		s1.getInfo();
		s2.getInfo();
		System.out.println(p1.toString());
	}
}