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
		boolean flag = true;
		int cnt;
		
		String paramMid=request.getParameter("mid");
		String paramCnt=request.getParameter("cnt");
		
		if(paramCnt==null || paramCnt.equals("")){
			cnt=2;
			bvo.setCnt(cnt);
		}
		else {
			cnt=Integer.parseInt(paramCnt);
			bvo.setCnt(Integer.parseInt(paramCnt));
		}
		//System.out.println("if문 전: "+paramMid);
		if(paramMid == null) {
			bvo.setMid("");
		}else {
			bvo.setMid(paramMid);		
		}
		//System.out.println("if문 후"+bvo);
		ArrayList<BoardSet> datas=bdao.selectAll(bvo);
		bvo.setCnt(cnt+1);
		ArrayList<BoardSet> datasNext=bdao.selectAll(bvo);

		if(datas.size() == datasNext.size()) {
			flag = false;
		}
		request.setAttribute("more", flag);

		ArrayList<MemberVO> member = mdao.selectAll(mvo);

		request.setAttribute("boardMidCheck", bvo.getMid());
		request.setAttribute("member", member);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", cnt);
		request.setAttribute("mid", paramMid);

		forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		return forward;
	}
}