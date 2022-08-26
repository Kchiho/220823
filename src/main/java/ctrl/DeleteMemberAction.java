package ctrl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;
import board.BoardSet;
import board.BoardVO;
import board.ReplyVO;
import member.MemberDAO;
import member.MemberVO;

public class DeleteMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo = new BoardVO();
		MemberVO mvo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		ReplyVO rvo = new ReplyVO();
		String paramMid=request.getParameter("mid");
		mvo.setMid(paramMid);
		bvo.setMid(paramMid);
		rvo.setMid(paramMid);
		bvo.setCnt(2);
		if(bdao.selectAll(bvo).size() != 0) {
			request.setAttribute("errormsg", "게시글이 남아있습니다");
		}else {
			if(bdao.selectSR(rvo).size() != 0) {
				request.setAttribute("errormsg", "댓글이 남아있습니다");
			}else {
				if(mdao.delete(mvo)) {
					HttpSession session=request.getSession();
					session.invalidate();
					forward=new ActionForward();
					forward.setPath("main.do");
					forward.setRedirect(false);
				}
				else {
					request.setAttribute("errormsg", "탈퇴실패");
				}
			}
		}
		return forward;					
	}
}