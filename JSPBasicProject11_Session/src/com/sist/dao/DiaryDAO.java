package com.sist.dao;
import java.sql.*;
import java.util.*;

public class DiaryDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
		// 드라이버 등록
		public DiaryDAO()
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
		
		// 우편번호 검색
		public List<ZipcodeBean> postfind(String dong)
		{
			List<ZipcodeBean> list=new ArrayList<ZipcodeBean>();
			try
			{
				getConnection();
				String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') " // 번지가 null인 경우가 있어서 NVL로 번지 처리했음 
						+ "FROM zipcode "
						+ "WHERE dong LIKE '%'||?||'%'"; 
				ps=conn.prepareStatement(sql);
				ps.setString(1, dong);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					ZipcodeBean vo=new ZipcodeBean();
					vo.setZipcode(rs.getString(1));
					vo.setSido(rs.getString(2));
					vo.setGugun(rs.getString(3));
					vo.setDong(rs.getString(4));
					vo.setBunji(rs.getString(5));
					
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
		
		
		// ID 중복여부 검색 
		public int idcheck(String id)
		{
			int count=0;
			try
			{
				getConnection();
				String sql="SELECT COUNT(*) FROM member "
						+ "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				count=rs.getInt(1);
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
			return count;
		}
	
}













