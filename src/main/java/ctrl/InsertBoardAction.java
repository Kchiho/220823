package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;
import board.ReplyVO;

public class InsertBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo=new BoardVO();
		ReplyVO rvo = new ReplyVO();
		
		String paramMid=request.getParameter("mid");
		String paramMsg=request.getParameter("msg");
		String paramCnt=request.getParameter("cnt");
		
		bvo.setMid(paramMid);
		rvo.setMid(paramMid);
		
		if(paramMsg != null) {
			bvo.setMsg(paramMsg);
			if(bdao.insert(bvo)) {
				forward=new ActionForward();
				forward.setPath("main.do?mid=&cnt="+Integer.parseInt(paramCnt));
				forward.setRedirect(true);
			}else {
				request.setAttribute("errormsg", "게시글 추가 실패");
			}
		}else {
			String paramRmsg=request.getParameter("rmsg");
			String paramBid=request.getParameter("bid");
			rvo.setRmsg(paramRmsg);
			rvo.setBid(Integer.parseInt(paramBid));
			if(bdao.insertR(rvo) & bdao.updateR(rvo)) {
				forward=new ActionForward();
				forward.setPath("main.do?mid=&cnt="+Integer.parseInt(paramCnt));
				forward.setRedirect(false);
			}else {
				request.setAttribute("errormsg", "댓글 추가 or 댓글 수 증가 실패");
			}
		}	
		return forward;
	}
}