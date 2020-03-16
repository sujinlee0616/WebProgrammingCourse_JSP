package com.sist.dao;

import java.sql.*;
import java.util.*;

public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
		// 드라이버 등록
		public MovieDAO()
		{
			try 
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		
		// 연결
		public void getConnection()
		{
			try 
			{
				conn=DriverManager.getConnection(URL, "hr", "happy");
			} catch (Exception ex) {}
		}
		
		// 연결해제 
		public void disConnection()
		{
			try 
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception ex) {}
		}
		
		public List<MovieBean> movieListData(int page)
		{
			List<MovieBean> list = new ArrayList<MovieBean>();
			try
			{
				getConnection();
				String sql="SELECT mno,poster,title,type,num "
						+ "FROM (SELECT mno,poster,title,type,rownum as num "
						+ "FROM (SELECT mno,poster,title,type "
						+ "FROM movie ORDER BY mno ASC)) " //type1: 현재 상영 ==>  FROM movie WHERE type=1 ORDER BY mno ASC))
						+ "WHERE num BETWEEN ? AND ?";
				
				int rowSize=12;
				int start = (rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				
				// 전송
				ps=conn.prepareStatement(sql);
				// ?에 값을 집어넣는다 
				ps.setInt(1, start);
				ps.setInt(2, end);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					MovieBean vo = new MovieBean();
					vo.setMno(rs.getInt(1));
					vo.setPoster(rs.getString(2));
					vo.setTitle(rs.getString(3));
					vo.setType(rs.getInt(4));
					
					list.add(vo);
				}
				rs.close();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			
			return list;
		}
		
		public int movieTotalPage()
		{
			int total=0;
			try
			{
				getConnection();
				String sql="SELECT CEIL(COUNT(*)/12.0) FROM movie"; // type=1인 애만 들어가게 하려면? 
				ps=conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				rs.next();
				total = rs.getInt(1);
				rs.close();				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			return total;
		}
		
		
		
		public List<MovieBean> movieFindData(int page)
		{
			List<MovieBean> list = new ArrayList<MovieBean>();
			try
			{
				getConnection();
				String sql="SELECT mno,poster,title,genre,num "
						+ "FROM (SELECT mno,poster,title,genre,rownum as num "
						+ "FROM (SELECT mno,poster,title,genre "
						+ "FROM movie ORDER BY mno ASC)) "
						+ "WHERE num BETWEEN ? AND ?";
				
				int rowSize=50;
				int start = (rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				
				// 전송
				ps=conn.prepareStatement(sql);
				// ?에 값을 집어넣는다 
				ps.setInt(1, start);
				ps.setInt(2, end);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					MovieBean vo = new MovieBean();
					vo.setMno(rs.getInt(1));
					vo.setPoster(rs.getString(2));
					vo.setTitle(rs.getString(3));
					vo.setGenre(rs.getString(4));
					
					list.add(vo);
				}
				rs.close();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			
			return list;
		}
		
		
		public List<BoardBean> boardListData()
		{
			List<BoardBean> list = new ArrayList<BoardBean>();
			
			try
			{
				getConnection();
				String sql="SELECT no,subject,name,regdate,hit "
						+ "FROM board "
						+ "ORDER BY no DESC";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					BoardBean vo=new BoardBean();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));	
					list.add(vo);
				}
				rs.close();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			
			return list;
		}
		
		
		// 추가하기
		public void boardInsert(BoardBean vo)
		{
			try
			{
				getConnection();
				String sql="INSERT INTO board VALUES("
						+ "(SELECT NVL(MAX(no)+1,1) FROM board),?,?,?,?,SYSDATE,0)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				
				ps.executeQuery();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
		}
		
		
		
}











