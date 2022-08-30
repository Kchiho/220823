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
		boolean flag = true; // 더보기를 할 데이터가 있는지 확인하는 변수
		int cnt; // 메인페이지에서 보여줘야 할 데이터의 갯수
		
		String paramMid=request.getParameter("mid");
		String paramCnt=request.getParameter("cnt");
		
		if(paramCnt==null || paramCnt.equals("")){ // cnt의 값이 없으면 2로 set
			cnt=2;
			bvo.setCnt(cnt);
		}
		else { // 값이 있을 때 해당 값으로 set
			cnt=Integer.parseInt(paramCnt);
			bvo.setCnt(Integer.parseInt(paramCnt));
		}
		//System.out.println("if문 전: "+paramMid);
		if(paramMid == null) { // 전체보기
			bvo.setMid("");
		}
		else { // 특정회원 보기
			bvo.setMid(paramMid);		
		}
		//System.out.println("if문 후"+bvo);
		ArrayList<BoardSet> datas=bdao.selectAll(bvo); // 페이지에서 보여줘야 할 데이터
		bvo.setCnt(cnt+1);
		ArrayList<BoardSet> datasNext=bdao.selectAll(bvo); // 페이지에서 보여줘야 할 데이터 + 1

		if(datas.size() == datasNext.size()) { // 위의 2개가 같다면 더보기 xxx
			flag = false;
		}
		request.setAttribute("more", flag);

		ArrayList<MemberVO> member = mdao.selectAll(mvo); // 최근 가입한 회원 3명 조회

		request.setAttribute("boardMidCheck", bvo.getMid());
		request.setAttribute("member", member);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", cnt);
		//request.setAttribute("mid", paramMid);

		forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		return forward;
	}
}