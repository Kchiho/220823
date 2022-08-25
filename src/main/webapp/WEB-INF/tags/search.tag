<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="datas" items="${member}">
<c:if test="${datas.mid == null}">
	최근에 가입한 회원이 없습니다.
</c:if>
	<tr>
		<th><a href="search.do?mid=${datas.mid}&cnt=2&search=2">[${datas.mname}]&nbsp;</a></th>
	</tr>
</c:forEach>