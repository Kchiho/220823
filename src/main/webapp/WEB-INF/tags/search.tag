<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${member.size() == 0}">
	최근에 가입한 회원이 없습니다.
</c:if>
<c:forEach var="member" items="${member}">
	<tr>
		<th><a href="main.do?mid=${member.mid}">[${member.mname}]&nbsp;</a></th>
	</tr>
</c:forEach>