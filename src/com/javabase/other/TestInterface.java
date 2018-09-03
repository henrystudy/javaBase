/**
* 接口定义interface小写
* 接口与实现类之间也满足多态性
**/

interface Singer
{
	public void sing();
	public void sleep();
}

interface Painter
{
	public void paint();
	public void eat();
}

class Student implements Singer
{
	private String name;
	
	Student(String name)
	{
		this.name = name;
	}
	
	public void getName()
	{
		System.out.println("name: " + name);
	}
	
	public void sing()
	{
		System.out.println("Student is singing...");
	}
	
	public void sleep()
	{
		System.out.println("Student is sleeping...");
	}
}

class Teacher implements Singer, Painter
{
	private String name;
	
	Teacher(String name)
	{
		this.name = name;
	}	
	
	public void getName()
	{
		System.out.println("name: " + name);
	}
	
	public void sing()
	{
		System.out.println("Teacher is singing...");
	}
	
	public void sleep()
	{
		System.out.println("Teacher is sleeping...");
	}

	public void paint()
	{
		System.out.println("Teacher is painting...");
	}

	public void eat()
	{
		System.out.println("Teacher is eating...");
	}	
}

public class TestInterface
{
	public static void main(String[] args)
	{
		Student s1 = new Student("Student1");
		Teacher t1 = new Teacher("Teacher1");
		
		s1.sing();
		s1.sleep();
		
		t1.sing();
		t1.sleep();
		t1.paint();
		t1.eat();
		
		//接口与实现类之间的多态
		Singer sg1 = new Teacher("STeacher1");
		sg1.sing();
		sg1.sleep();
		
		Painter p1 = new Teacher("PTeacher1");
		p1.paint();
		p1.eat();
		//强制转型
		Painter p2 = (Painter)sg1;
		p2.paint();
		p2.eat();
	}
}