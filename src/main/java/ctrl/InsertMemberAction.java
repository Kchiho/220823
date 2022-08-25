package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class InsertMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		
		String paramMid=request.getParameter("mid");
		String paramMpw=request.getParameter("mpw");
		String paramMname=request.getParameter("mname");
		
		vo.setMid(paramMid);
		vo.setMpw(paramMpw);
		vo.setMname(paramMname);
		if(dao.insert(vo)) {
			forward=new ActionForward();
			forward.setPath("/close.jsp");
			forward.setRedirect(true);
		}else {
			request.setAttribute("errormsg", "회원가입실패");
		}
		return forward;
	}
}