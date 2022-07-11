package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		if (action == null || action.equals("/listMembers.do")) {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/modMemberForm.do")) {		// 회원 수정창 요청 시 ID로 회원정보를 조회한 후 수정창으로 포워딩
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);		// 회원정보 수정창을 요청하면서 전송된 ID를 이용해 수정 전 회원 정보를 조회
			request.setAttribute("memInfo", memInfo);				//request에 바인딩하여 회원 정보 수정창에 수정하기 전 회원정보를 전달
			nextPage = "/test03/modMemberForm.jsp";	
			
		} else if (action.equals("/modMember.do")) {			// 테이블의 회원 정보를 수정
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";			
		} else if (action.equals("/delMember.do")) {		// 회원 ID를 SQL문으로 전달해 테이블의 회원정보 삭제
			String id = request.getParameter("id");			// 삭제할 회원 ID를 받아옴
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
		} else {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	// nextPage에 지정한 요청명으로 다시 서블릿에 요청
		dispatcher.forward(request, response);
	}

}
