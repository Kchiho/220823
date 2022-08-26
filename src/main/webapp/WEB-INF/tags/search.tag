<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="member" items="${member}">
<c:if test="${member.mid == null}">
	최근에 가입한 회원이 없습니다.
</c:if>
	<tr>
		<th><a href="main.do?mid=${member.mid}&cnt=2&search=1">[${member.mname}]&nbsp;</a></th>
	</tr>
</c:forEach>