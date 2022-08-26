<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kim" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function signup() {
		window.open("signup.jsp", "회원가입 페이지", "width=500,height=200");
	}
	function delM() {
		location.href = "deleteM.do?mid=${mVO.mid}";
	}
</script>
</head>
<body>

	<div id="header">
		<div class="gnb">
			<ul>
				<li style="list-style: none;"><kim:login /></li>
				<li style="list-style: none;"><a href="main.do">메인으로</a></li>
			</ul>
		</div>
		<div>
			<span>최근 가입한 회원</span>
			<kim:search />
		</div>
	</div>

	<div id="wrapper">
		<div></div>
		<div id="content">
			<h2>글 등록하기</h2>
			<kim:write type="msg" cnt="${cnt}" />
		</div>

		<div id="main">
			<h2>글 목록보기</h2>
			<c:if test="${search == 1 || search == 2}">
				<c:if test="${datas.size() == 0}">작성한 글이 없습니다</c:if>
			</c:if>
			<c:forEach var="v" items="${datas}">
				<c:set var="b" value="${v.boardVO}" />
				<h3>
					[${b.mid}] ${b.msg} [ 좋아요 ${b.favcnt} | 댓글 ${b.rcnt} ]
					<kim:board midCheck="${b.mid}" bid="${b.bid}" cnt="${cnt}" />
				</h3>

				<div class="reply">
					<ul>
						<c:forEach var="r" items="${v.rList}">
							<li>[${r.mid}] ${r.rmsg} <kim:reply midCheck="${r.mid}"
									rid="${r.rid}" bid="${r.bid}" cnt="${cnt}" /></li>
						</c:forEach>
					</ul>
				</div>

				<div class="reply">
					<kim:write type="rmsg" bid="${b.bid}" cnt="${cnt}" />
				</div>
			</c:forEach>
		</div>
		<c:if test="${search == 0}">
			<c:if test="${datas.size()==cnt}">
				<a href="main.do?cnt=${cnt+2}">더보기&gt;&gt;</a>
			</c:if>
		</c:if>
		<c:if test="${search == 1}">
			<c:if test="${datas.size()==cnt}">
				<a href="main.do?mid=${b.mid}&cnt=${cnt+2}&search=1">더보기&gt;&gt;</a>
			</c:if>
		</c:if>
	</div>

	<div id="footer">
		회사소개 | 이용약관 | <strong>개인정보처리방침</strong> | 보호정책 | 고객센터 <strong>ⓒ
			Corp.</strong>
	</div>

</body>
</html>