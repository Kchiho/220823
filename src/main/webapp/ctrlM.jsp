<%--
<%@page import="member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="mDAO" class="member.MemberDAO" />
<jsp:useBean id="mVO" class="member.MemberVO" />
<jsp:setProperty property="*" name="mVO" />
<%
String action = request.getParameter("action");

if (action.equals("insert")) {
	if (mDAO.insert(mVO)) {
		out.println("<script>alert('회원가입 완료!');opener.parent.location.reload();window.close();</script>");
	} else {
		out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
	}
} else if (action.equals("login")) {
	mVO = mDAO.selectOne(mVO);
	if (mVO != null) {
		session.setAttribute("mVO", mVO);
		response.sendRedirect("ctrlB.jsp?action=main");
	} else {
		out.println("<script>alert('로그인 실패!');history.go(-1);</script>");
	}
} else if (action.equals("logout")) {
	session.invalidate();
	response.sendRedirect("ctrlB.jsp?action=main");
} else if (action.equals("deleteM")) {
	if (mDAO.delete(mVO)) {
		session.invalidate();
		out.println("<script>alert('탈퇴완료');location.href='ctrlB.jsp?action=main'</script>");
	} else {
		out.println("<script>alert('탈퇴실패');location.href='ctrlB.jsp?action=main'</script>");
	}
}else if(action.equals("recent")){
	ArrayList<MemberVO> member = mDAO.selectAll(mVO);
	if(member.size() == 0){
		out.println("<script>alert('최근에 가입한 회원이 없습니다');location.href='ctrlB.jsp?action=main'</script>");
	}else{
		request.setAttribute("member", member);
		pageContext.forward("ctrlB.jsp?action=main");
	}
}
%>
 --%>