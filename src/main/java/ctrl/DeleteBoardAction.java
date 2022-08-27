package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;
import board.ReplyVO;

public class DeleteBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo=new BoardVO();
		ReplyVO rvo=new ReplyVO();

		String paramCnt=request.getParameter("cnt");
		String paramRid=request.getParameter("rid");
		String paramBid=request.getParameter("bid");
		
		request.setAttribute("cnt", paramCnt);

		if(paramRid == null) {
			bvo.setBid(Integer.parseInt(paramBid));
			if(bdao.delete(bvo)) {
				forward=new ActionForward();
				forward.setPath("main.do");
				forward.setRedirect(true);
			}else {
				request.setAttribute("errormsg", "게시글 삭제 실패");
			}
		}else {
			rvo.setBid(Integer.parseInt(paramBid));
			rvo.setRid(Integer.parseInt(paramRid));
			if(bdao.deleteR(rvo) & bdao.updateRd(rvo)) {
				forward=new ActionForward();
				forward.setPath("main.do");
				forward.setRedirect(false);
			}else {
				request.setAttribute("errormsg", "댓글 삭제 실패");
			}
		}
		return forward;
	}
}
