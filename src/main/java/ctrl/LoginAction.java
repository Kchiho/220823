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
		String paramMid=request.getParameter("mid");
		String paramMpw=request.getParameter("mpw");
		String paramCnt=request.getParameter("cnt");

		vo.setMid(paramMid);
		vo.setMpw(paramMpw);

		vo = dao.selectOne(vo);
		if(vo != null) {
			session.setAttribute("mVO", vo);
			forward=new ActionForward();
			forward.setPath("main.do?mid=&cnt="+Integer.parseInt(paramCnt));
			forward.setRedirect(true);
		}else {
			request.setAttribute("errormsg", "로그인실패");
		}
		return forward;
	}
}