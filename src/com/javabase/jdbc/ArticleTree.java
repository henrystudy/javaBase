package com.javabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * 树状结构打印Article这张表的记录
 * 使用递归
 * tree()打印出所有child
 * show()建立连接并调用tree()
 * @author henry
 *
 */

public class ArticleTree 
{

	public static void main(String[] args) 
	{
		new ArticleTree().show();
	}
	
	public void show()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bbs?" +
													"user=root&password=root");
			stmt = conn.createStatement();
			String sql = "select * from article where pid = 0";
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("cont"));
				tree(conn, rs.getInt("id"), 1);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void tree(Connection conn, int id, int level)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		StringBuffer strPre = new StringBuffer("");
		for(int i=0; i<level; i++)
		{
			strPre.append("----");
		}
		
		try
		{
			stmt = conn.createStatement();
			String sql = "select * from article where pid =" + id;
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(strPre + rs.getString("cont"));
				if(rs.getInt("isleaf") != 0)
				{
					tree(conn,rs.getInt("id"),level + 1);
				}
			}
		}catch(SQLException e)
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
				if(stmt != null)
				{
					stmt .close();
					stmt = null;
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
