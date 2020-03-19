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
		

	
		public void memberInsert(MemberBean vo)
		{
			try
			{
				getConnection();
				
				/* [member 테이블]
				ID       NOT NULL VARCHAR2(20)  
				PWD      NOT NULL VARCHAR2(10)  
				NAME     NOT NULL VARCHAR2(34)  
				EMAIL             VARCHAR2(100) 
				SEX               VARCHAR2(10)  
				BIRTHDAY NOT NULL VARCHAR2(20)  
				POST     NOT NULL VARCHAR2(7)   
				ADDR1    NOT NULL VARCHAR2(200) 
				ADDR2             VARCHAR2(200) 
				TEL               VARCHAR2(20)  
				CONTENT  NOT NULL CLOB          
				REGDATE           DATE          
				ADMIN             CHAR(1)       
				*/
				
				String sql="INSERT INTO member VALUES("
						+ "?,?,?,?,?,?,?,?,?,?,?,SYSDATE,'n')";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getId());
				ps.setString(2, vo.getPwd());
				ps.setString(3, vo.getName());
				ps.setString(4, vo.getEmail());
				ps.setString(5, vo.getSex());
				ps.setString(6, vo.getBirthday());
				ps.setString(7, vo.getPost());
				ps.setString(8, vo.getAddr1());
				ps.setString(9, vo.getAddr2());
				ps.setString(10, vo.getTel());
				ps.setString(11, vo.getContent());
				
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
		
		// 로그인 여부 판단 
		public String isLogin(String id, String pwd)
		{
			String result="";
			// ID/PW 일치 여부에 따라 result에 값을 부여하고, 
			// 이 값을 기반으로 login_ok.jsp 에서 적절한 행동(로그인 실패했다고 alert or 화면 리다이렉트)을 취함. 
			
			try
			{
				getConnection();
				String sql="SELECT COUNT(*) FROM member "
						+ "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				int count=rs.getInt(1);
				rs.close();
				
				if(count==0) // ID가 없는 상태 
				{
					result="NO_ID";
					// ID가 없으므로, 비밀번호 갖고 올 필요도 없음. 여기서 끝내면 됨. 
				}
				else // ID가 존재하는 상태 
				{
					sql="SELECT pwd,name FROM member "
							+ "WHERE id=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, id);
					rs=ps.executeQuery();
					rs.next();
					
					String db_pwd=rs.getString(1);
					String login_id=rs.getString(2); // 선생님은 'login_id' 대신 'name'이라는 이름의 변수 사용하셨음 
					rs.close();
					
					if(db_pwd.equals(pwd)) // ID,PW 모두 맞음 ==> 로그인 
					{
						result=login_id;
					}
					else // 비밀번호가 틀렸음 
					{
						result="WRONG_PWD";
					}
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			
			return result;
			
		}
	
}













