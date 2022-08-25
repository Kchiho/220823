<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function close(){
		for(var index=0;index<win.length;index++){
			// 창이 닫히면
			  if(win[index].closed){
				  continue;
			  }
			  // 창이 하위 바람을 열지 않으면
			  if(typeof(win[index].openedWindow)=="undefined"){
				  win[index].close();
				  continue;
			  }
			  if(win[index].openedWindow.length==0){
				  win[index].close();
			  }else{
				  closeSonWindow(win[index].openedWindow);
				  win[index].close();
			  }
	}
</script>
</head>
<body>
	<a href="javascript:close()">[닫기]</a>
</body>
</html>