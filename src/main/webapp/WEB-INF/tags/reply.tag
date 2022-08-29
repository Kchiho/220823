<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="midCheck" %>
<%@ attribute name="rid" %>
<%@ attribute name="bid" %>

<c:if test="${mVO.mid==midCheck}">
<a href="deleteB.do?rid=${rid}&bid=${bid}&cnt=${cnt}">[삭제]</a>
</c:if>