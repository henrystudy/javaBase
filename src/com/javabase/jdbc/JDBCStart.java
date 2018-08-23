package com.javabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * mySQL数据库实例化连接和取数据
 * @author zhenhaiw
 *
 */
public class JDBCStart
{

	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydata?" + 
													"user=root&password=root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from dept");
			while(rs.next())
			{
				System.out.println(rs.getInt("deptno"));
				System.out.println(rs.getString("deptname"));
			}
		} catch (InstantiationException e1)
		{
			e1.printStackTrace();
		} catch (IllegalAccessException e1)
		{
			e1.printStackTrace();
		} catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
					rs = null;
				}
				if(conn != null)
				{
					conn.close();
					conn = null;
				}
				if(stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
