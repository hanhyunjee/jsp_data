package sec02.ex01;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/*")
public class MemeberController extends HttpServlet {
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
			nextPage = "/test02/listMembers.jsp";		// test02폴더의 listMember.jsp로 포워딩
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMembers.do";		//회원 등록 후 다시 회원 목록을 출력
		} else if (action.equals("/memberForm.do")) {		//action값이 /memberForm.do면 회원가입창을 화면에 출력
			nextPage = "/test02/memberForm.jsp";		// test02폴더의 memberForm.jsp로 포워딩
		} else {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	// nextPage에 지정한 요청명으로 다시 서블릿에 요청
		dispatcher.forward(request, response);
	}

}
