package sec02.ex01;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/myoracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listMembers() {
		List membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";		// SQL문 작성
			System.out.println(query);
			pstmt = conn.prepareStatement(query);		// PrepareStatement 객체를 생성하면서 SQL문을 인자로 전달
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);	// 조회한 회원 정보를 레코드별로 MemberVO 객체의 속성에 저장
				membersList.add(memberVO);				// membersList에 MemberVO 객체들을 차례로 저장
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO t_member(id, pwd, name, email)"+ "VALUES(?, ?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);		//PrepareStatement 객체를 생성하면서 SQL문으로 인자를 전달
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
