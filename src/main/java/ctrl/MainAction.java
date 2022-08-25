package ctrl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardSet;
import board.BoardVO;
import member.MemberDAO;
import member.MemberVO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		MemberVO mvo = new MemberVO();
		MemberDAO mdao = new MemberDAO();
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo=new BoardVO();
		String paramCnt=request.getParameter("cnt");
		if(paramCnt==null || paramCnt.equals("")){
			bvo.setCnt(2);
		}
		else {
			bvo.setCnt(Integer.parseInt(paramCnt));
		}
		ArrayList<BoardSet> datas=bdao.selectAll(bvo);
		ArrayList<MemberVO> member = mdao.selectAll(mvo);
		request.setAttribute("member", member);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", bvo.getCnt());

		forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		return forward;
	}
}