package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ActionForward forward = null;
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		String paramMid=request.getParameter("sessionMid");
		String paramMpw=request.getParameter("mpw");
		String paramCnt=request.getParameter("cnt");

		vo.setMid(paramMid);
		vo.setMpw(paramMpw);

		request.setAttribute("cnt", paramCnt);
		vo = dao.selectOne(vo);
		if(vo != null) {
			session.setAttribute("mVO", vo); // 로그인한 회원정보
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false);
		}else {
			request.setAttribute("errormsg", "로그인실패");
		}
		return forward;
	}
}