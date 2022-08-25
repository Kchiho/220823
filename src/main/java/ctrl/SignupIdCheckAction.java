package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class SignupIdCheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		String paramMid = request.getParameter("mid");
		
		vo.setMid(paramMid);
		int result = dao.joinIdCheck(paramMid);
		
		if(result == 1 || result == 0) {
			request.setAttribute("id", paramMid);
			request.setAttribute("result", result);
			forward=new ActionForward();
			forward.setPath("/signupIdCheck.jsp");
			forward.setRedirect(false);
		}else {
			request.setAttribute("errormsg", "아이디 중복확인 오류");
		}
		return forward;
	}
}