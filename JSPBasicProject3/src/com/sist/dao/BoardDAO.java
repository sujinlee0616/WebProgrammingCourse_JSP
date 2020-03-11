package com.sist.dao;
import java.util.*;
import java.sql.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public BoardDAO()
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
	
	// 기능 => 목록 출력 
	public List<BoardVO> boardListData(int page) 
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try 
		{
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
					+ "FROM replyBoard ORDER BY group_id DESC, group_step ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			// ※ rownum은 중간에서부터 잘라낼 수 없다 (Top-N) ==> 인라인뷰 사용
			// ※ rownum은 1번부터 시작
			
			int rowSize=10;
			//int start=(rowSize*page) - (rowSize-1);
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			
			// 전송
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				 
				list.add(vo);
			}
			rs.close();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
		return list; // ArrayList가 아니라 상위 클래스인 List 쓰는게 낫다
		// 왜냐면 List가 ArrayList, Vector, LinkedList의 상위 클래스이니까 다 포괄하고 있어서.
	}
	
	public int boardRowCount()
	{
		int count=0;
		try 
		{
			getConnection();
			String sql="SELECT COUNT(*) FROM replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return count;
	}
	
	// 제네릭스 사용 이유 
	// https://wikidocs.net/268
	
	
	// 새 글 작성 후 새 글을 테이블에 삽입
	public void boardInsert(BoardVO vo)
	{
		try {
			getConnection();
			String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) VALUES("  // 이 외의 컬럼은 디폴트값 들어가므로 지정 안 해줘도 됨 
					+ "rb_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";   // group_id는 기존 group_id의 max값보다 1 크게. 
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();  // 실행요청 (INSERT니까 executeUpdate)
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	// 내용보기(조회수 증가)(type=0) & 수정하기 (데이터 읽기)(type=1) 
	public BoardVO boardDetailData(int no,int type)
	{
		BoardVO vo = new BoardVO();
		try 
		{
			getConnection();
			String sql="";
			
			// 조회수 증가 
			if(type==0)
			{
				sql="UPDATE replyBoard SET "
						+ "hit=hit+1 "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
				ps.close();
			}
			// 상세보기, 수정하기 (데이터를 읽는다) 
			sql="SELECT no,name,subject,content,regdate,hit "
					+ "FROM replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			// 데이터를 읽기 시작
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			// VO에 값을 저장
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	
	// 수정
	public boolean boardUpdate(BoardVO vo)
	{
		// 비밀번호 일치여부 체크 
		boolean bCheck=false;
		
		try {
			getConnection();
			String sql="SELECT pwd FROM replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			// ?에 값을 채운다
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			// 비밀번호가 같다면 
			if(db_pwd.equals(vo.getPwd()))
			{
				bCheck=true;
				sql="UPDATE replyBoard SET "
						+ "name=?,subject=?,content=? "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				// ?에 값 채운다
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				
				// 실행
				ps.executeUpdate(); // commit
			}
			// 비밀번호가 틀리다면 
			else{
				bCheck=false;
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return bCheck;		 
	}
	
	// 삭제
	
	// 답변하기
	public void replyInsert(int pno, BoardVO vo)
	{
		try
		{
			getConnection();
			
			conn.setAutoCommit(false);
			
			// 0. 부모의 (no=pno) group_id, group_step, group_tab을 가져온다. 
			String sql="SELECT group_id, group_step, group_tab "
					+ "FROM replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			
			// ★★★★★ 답변형 게시판의 핵심 쿼리 ★★★★★
			
			// <1. 같은 그룹 안에 있는 글들의 group_step을 증가시킨다. (내 엄마보다 group_step이 큰 놈들만)>
			sql="UPDATE replyBoard SET "
					+ "group_step=group_step+1 "
					+ "WHERE group_id=? AND group_step>?";
			// 내가 엄마에 대한 답글을 작성할 때, 
			// group_id가 같은 기존 글들 중에서,   
			// group_step이 내 엄마 글의 group_step보다 큰 애들은 group_step을 1씩 증가시킨다.
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeQuery(); 
			// executeQuery하면 commit이 날아감 
			// ==> 1.번 작업만 수행하고 commit 날라가므로, 1~3 다 수행해야하는데 다른데서 에러나도 이미 1번은 커밋된 상태 ㅠㅠ 
			// ==> ★★오토커밋 되지 않도록 한다.★★ 
			// ==> 1) 위에서 conn.setAutocommit=false 해주고 
			//     2) 1~3번 sql 문장 이후에 conn.commit() 하고 
			//     3) conn이 멤버변수이므로 다른 데 영향 가지 않도록 원래대로 오토커밋되게 셋팅을 돌려준다. conn.setAutocommit(true)
			// ==> 이런걸 "transaction 프로그램" 이라고 한다. 이번처럼 ☆여러개의 문장을 모두 다 수행해야 할 때 사용.☆  
			
			// <2. 답글 데이터를 추가한다.> 
			sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id, group_step, group_tab, root) VALUES("
					+ "rb_no_seq.nextval,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi); // 자식gi=부모gi (같은 그룹이니까)
			ps.setInt(6, gs+1); // 자식 gs=부모gs+1
			ps.setInt(7, gt+1); // 자식 gt=부모gt+1
			ps.setInt(8, pno); // root는 부모글의 번호니까 pno
			ps.executeUpdate();
			
			// <3. 내 엄마글의 depth를 증가시킨다.> 
			sql="UPDATE replyBoard SET "
					+ "depth=depth+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			
			conn.commit();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			try
			{
				conn.rollback(); // 1~3 중 하나라도 실패하면 롤백한다. 
			}catch(Exception e){}
		}
		finally
		{
			try
			{
				conn.setAutoCommit(true);
			}catch(Exception ex){}
			disConnection();
		}
	}
	
	public int boardDelete(int no,String pwd) // delete.jsp의 결과값이 0 or 1이었으니까 int로 잡음  
	{
		int result=0;
		try 
		{
			getConnection();
			
			// 비밀번호 검색
			String sql="SELECT pwd FROM replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) // 비밀번호가 같다면
			{
				result=1;
				// 0. 삭제하려는 글의 1)depth와 2)엄마글 번호를 알아온다. 
				//  - 1) depth: 삭제하려는 글의 depth가 0이면 쉽게 삭제 가능. 1 이상이면 밑에 달린 글이 있는거니까 '삭제된 글입니다' 표시해줘야.
				//  - 2) 엄마글 번호: 삭제하려는 글의 엄마글의 depth 감소시켜줘야하니까 알아야.
				sql="SELECT root,depth FROM replyBoard "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				rs=ps.executeQuery();
				rs.next();
				
				int root=rs.getInt(1); 
				int depth=rs.getInt(2);
				rs.close();
				
				if(depth==0) // 삭제하려는 글의 depth가 0이면 걍 바로 지우면 된다. 
				{
					sql="DELETE FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}
				else // 삭제하려는 글의 depth가 0이 아니면 
				{
					// 1) '관리자가 삭제한 글입니다' 메시지 노출. 
					String msg="관리자가 삭제한 게시물입니다.";
					sql="UPDATE replyBoard SET "
							+ "subject=?,content=? "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setString(2, msg);
					ps.setInt(3, no);
					ps.executeUpdate();
					
					// 2) 엄마글의 depth를 1 감소시킨다.
					sql="UPDATE replyBoard SET "
							+ "depth=depth-1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ps.executeUpdate();
				}
			}
			else // 비밀번호가 틀리다면 
			{
				result=0;
				
			}
			
			
			
		} 
		catch (Exception ex) 
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







