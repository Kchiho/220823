<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,board.*" %>
<jsp:useBean id="bDAO" class="board.BoardDAO" />
<jsp:useBean id="bVO" class="board.BoardVO" />
<jsp:setProperty property="*" name="bVO" />
<jsp:useBean id="rVO" class="board.ReplyVO" />
<jsp:setProperty property="*" name="rVO" />
<jsp:useBean id="mVO" class="member.MemberVO" />
<jsp:setProperty property="*" name="mVO" />
<%
	String action=request.getParameter("action");

	String paramCnt=request.getParameter("cnt");
	if(paramCnt==null || paramCnt.equals("")){
		bVO.setCnt(2); // 향후 초기화 매개변수 등으로 설정가능
	}

	if(action.equals("main")){ // 메인
		ArrayList<BoardSet> datas=bDAO.selectAll(bVO);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", bVO.getCnt());
		pageContext.forward("main.jsp");
	}
	else if(action.equals("insertB")){ // 게시글 추가
		if(bDAO.insert(bVO)){
			response.sendRedirect("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("insertB 오류");
		}
	}
	else if(action.equals("insertR")){ // 댓글 추가
		if(bDAO.insertR(rVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("insertR 오류");
		}
	}
	else if(action.equals("deleteB")){ // 게시글 삭제
		if(bDAO.delete(bVO)){
			response.sendRedirect("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("deleteB 오류");
		}
	}
	else if(action.equals("deleteR")){ // 댓글 삭제
		if(bDAO.deleteR(rVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("deleteR 오류");
		}
	}
	else if(action.equals("fav")){ // 좋아요
		if(bDAO.update(bVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("fav 오류");
		}
	}
	else if(action.equals("deleteM")){ // 회원탈퇴시 게시글, 댓글이 없어야 회원 탈퇴 가능
		if(bDAO.selectB(mVO).size() != 0){
			out.println("<script>alert('게시글이 남아있어 탈퇴할 수 없습니다');location.href='ctrlB.jsp?action=main'</script>");
		}else {
			if(bDAO.selectR(mVO).size() != 0){
				out.println("<script>alert('댓글이 남아있어 탈퇴할 수 없습니다');location.href='ctrlB.jsp?action=main'</script>");
			}else{
				response.sendRedirect("ctrlM.jsp?action=deleteM&mid="+mVO.getMid());
			}
		}
	}
	else if(action.equals("search")){ // 특정 회원이 작성한 글 모아보기
		if(bDAO.selectB(mVO).size() != 0){
			ArrayList<BoardSet> datas = bDAO.selectB(mVO);
			request.setAttribute("datas", datas);
			pageContext.forward("main.jsp");
		}else{
			out.println("<script>alert('작성한 게시글이 없습니다');location.href='ctrlB.jsp?action=main'</script>");
		}
	}
%>
 --%>