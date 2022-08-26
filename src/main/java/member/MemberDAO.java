package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JDBCUtil;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_selectOne="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	final String sql_insert="INSERT INTO MEMBER VALUES((SELECT NVL(MAX(MPK),0) +1 FROM MEMBER),?,?,?)";
	final String sql_delete="DELETE FROM MEMBER WHERE MID=?";
	final String sql_joinIdCheck="SELECT MID FROM MEMBER WHERE MID=?";
	final String sql_selectAll="SELECT * FROM (SELECT A.*,ROWNUM AS RNUM FROM (SELECT * FROM MEMBER ORDER BY MPK DESC) A WHERE ROWNUM<=3) WHERE RNUM>=1";
	
	public ArrayList<MemberVO> selectAll(MemberVO mvo){
		ArrayList<MemberVO> datas=new ArrayList<MemberVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setMid(rs.getString("MID"));
				memberVO.setMname(rs.getString("MNAME"));
				datas.add(memberVO);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	public int joinIdCheck(String id) {
		conn=JDBCUtil.connect();
		int result = -1;
		try {
			pstmt=conn.prepareStatement(sql_joinIdCheck);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){	
				result = 0;
			}else{
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return result;
	}
	public MemberVO selectOne(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				MemberVO data=new MemberVO();
				data.setMid(rs.getString("MID"));
				data.setMpw(rs.getString("MPW"));
				data.setMname(rs.getString("MNAME"));
				return data;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}
	public boolean insert(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			pstmt.setString(3, vo.getMname());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean delete(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setString(1, vo.getMid());
			int res = pstmt.executeUpdate();
			if(res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false;
	}
}
