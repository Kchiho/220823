<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입하기</title>
</head>
<body>
	<form action="insertM.do" method="post" name="userInfo"
		onsubmit="return checkValue()">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mid" /> <input type="button"
					value="중복확인" class="dup" onclick="winopen()"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mpw"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="mname"></td>
			</tr>
			<tr>
				<td><input type="submit" value="회원가입하기" onclick="reload()"></td>
			</tr>
		</table>
		<hr>
	</form>
	<script type="text/javascript">
		// 회원가입 화면의 입력값들을 검사한다.
		function checkValue() {
			var form = document.userInfo;

			if (!form.sessionMid.value) {
				alert("아이디를 입력하세요.");
				return false;
			}

			if (form.idDuplication.value != "idCheck") {
				alert("아이디 중복체크를 해주세요.");
				return false;
			}

			if (!form.mpw.value) {
				alert("비밀번호를 입력하세요.");
				return false;
			}

			if (!form.name.value) {
				alert("이름을 입력하세요.");
				return false;
			}
		}
		// 아이디 중복체크 화면open
		function winopen() {
			//새창을 열어서 페이지를 오픈 후 -> 회원아이디정보를 가지고 중복체크
			//1. 아이디가 없으면 알림창과 진행x
			if (document.userInfo.mid.value == ""
					|| document.userInfo.mid.value.length < 0) {
				alert("아이디를 먼저 입력해주세요")
				document.fr.id.focus();
			} else {
				//2. 회원정보아이디를 가지고 있는 지 체크하려면 DB에 접근해야한다.
				//자바스크립트로 어떻게 DB에 접근할까? => 파라미터로 id값을 가져가서 jsp페이지에서 진행하면 된다.
				window.open("signupIdCheck.do?mid="
						+ document.userInfo.mid.value, "",
						"width=500, height=300");
			}
		}
		function reload(){
			  opener.location.reload();
		}
	</script>

</body>
</html>