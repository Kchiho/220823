<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="type" %>
<%@ attribute name="bid" %>
<%@ attribute name="cnt" %>
<%@ attribute name="mid" %>

<c:if test="${mVO.mid != null}">
<form action="insertB.do" method="post">
	<input type="hidden" name="sessionMid" value="${mVO.mid}">
	<c:if test="${mid != null}">
		<input type="hidden" name="mid" value="${mid}">
	</c:if>
	<input type="hidden" name="cnt" value="${cnt}">
<c:choose>
	<c:when test="${type=='msg'}">
		<input type="text" name="msg">
		<input type="submit" value="글 등록">
	</c:when>
	
	<c:when test="${type=='rmsg'}">
		<input type="hidden" name="bid" value="${bid}">
		댓글: <input type="text" name="rmsg">
		<input type="submit" value="댓글 등록">
	</c:when>
</c:choose>
</form>
</c:if>


<c:if test="${mVO.mid == null}">
<c:choose>
	<c:when test="${type=='msg'}">
		<input type="text" disabled value="등록하려면 로그인하세요!">
	</c:when>
	
	<c:when test="${type=='rmsg'}">
		댓글: <input type="text" disabled value="등록하려면 로그인하세요!">
	</c:when>
</c:choose>
</c:if>