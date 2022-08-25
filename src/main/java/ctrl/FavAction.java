package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class FavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardDAO dao=new BoardDAO();
		BoardVO vo=new BoardVO();
		String paramBid=request.getParameter("bid");
		String paramCnt=request.getParameter("cnt");
		vo.setBid(Integer.parseInt(paramBid));
		if(dao.update(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do?cnt="+Integer.parseInt(paramCnt));
			forward.setRedirect(false);
		}else {
			request.setAttribute("errormsg", "추천실패");
		}
		return forward;
	}	
}
