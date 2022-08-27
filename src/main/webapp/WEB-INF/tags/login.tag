<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="login.do" method="post">
	<input type="hidden" name="cnt" value="${cnt}">
	<c:choose>
		<c:when test="${mVO.mid != null}">
			<h1>${mVO.mname}님</h1>
			<a href="logout.do">로그아웃</a>
			<a href="main.do?mid=${mVO.mid}">내글보기</a>
			<input type="button" value="탈퇴하기" onclick="delM()">
		</c:when>
		<c:otherwise>
		ID&nbsp;<input type="text" name="sessionMid">&nbsp;&nbsp;PW&nbsp;<input
				type="password" name="mpw">&nbsp;&nbsp;
		<input type="submit" value="로그인">
			<li style="list-style: none;"><a href="javascript:signup()">회원가입</a></li>
		</c:otherwise>
	</c:choose>
</form>