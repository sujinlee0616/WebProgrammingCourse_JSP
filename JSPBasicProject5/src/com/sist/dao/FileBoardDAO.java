package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class FileBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public FileBoardDAO()
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
	
	// 목록출력 
	public List<FileBoardVO> boardListData(int page)
	{
		List<FileBoardVO> list = new ArrayList<FileBoardVO>();
		
		try 
		{
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM fileBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start = (rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			// ?에 값을 집어넣는다 
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				FileBoardVO vo=new FileBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				vo.setRownum(rs.getInt(6));
				
				list.add(vo);
			}
			rs.close();
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	// 새 게시물 데이터 추가 
	public void boardInsert(FileBoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO fileBoard VALUES("
					+ "fb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?)";
			
			// ? = insert_ok.jsp에서 보낸 6개의 데이터
			// ?에 값을 채운다 
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			
			// 실행요청 
			ps.executeUpdate();
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
	
	// 게시글 상세보기 
	public FileBoardVO boardDetailData(int no)
	{
		FileBoardVO vo = new FileBoardVO();
		
		try
		{
			getConnection();
			// 1) 조회수 증가시킴 
			String sql="UPDATE fileBoard SET "
					+ "hit=hit+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			// 2) 데이터 가져옴
			sql="SELECT no,name,subject,content,regdate,hit,filename,filesize "
					+ "FROM fileBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			
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
		
		return vo;
	}
	
	
}











