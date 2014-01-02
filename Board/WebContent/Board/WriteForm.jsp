<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link href="/Board/css/Board.css" rel="stylesheet">
<link href="/Board/css/MyPage.css" rel="stylesheet">
<link href="/Board/css/detailContent.css" rel="stylesheet">
<link href="/Board/css/writeForm.css" rel="stylesheet">
<%
	int prePage = 0;
	prePage = Integer.parseInt(request.getParameter("currentPage"));
%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	content_check = 0;
	bt_check = 0;
	$(function() {
		$('textarea[name=content]').click(function() {
			if (content_check == 0) {
				$('textarea[name=content]').val("");
				content_check = 1;
			}

		});

		$('textarea[name=content]').keyup(function() {

			if ($('textarea[name=content]').val().length > 0) {
				$('#okay').css('color', '#ffffff');
				bt_check = 1;

			} else {
				$('#okay').css('color', '#D5D5D5');
				bt_check = 0;
			}
		});

		$('#okay').click(function() {
			if (bt_check == 1) {
				document.reg.submit();
			}
		});
		$('#add_pic').click(function() {
			//사진 업로드
		});
		$('#back').click(function() {
			if (<%=prePage%>== 1) {
				location.href = "/Board/Board/Board.jsp";
			} else {
				location.href = "/Board/Board/MyPage.jsp";

			}
		});

	});
</script>

</head>
<body>
	<form name=reg action="/Board/con?cmd=writeContent" method="post">

		<div id=Menu>
			<div id="CurrentPage">
				<a>글쓰기</a>
			</div>
			<img id=back src='/Board/img/back.png' />
		</div>
		<div class="contentFrame_">
			<textarea id="textarea" name=content class="fta-textarea"></textarea>
		</div>
		<div id="writeCommentFrame_">
			<img id="add_pic" src='/Board/img/add_pic.png' /> <img id="okay"
				src='/Board/img/okay.png' />
		</div>

	</form>
</body>
</html>