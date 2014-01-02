<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%
	
	int contentNumber = -1;
	contentNumber = Integer.parseInt(request.getParameter("contentNumber"));
	
	//int contentNumber =10;
%>
<title>Insert title here</title>
<link href="/Board/css/Board.css" rel="stylesheet">
<link href="/Board/css/MyPage.css" rel="stylesheet">
<link href="/Board/css/detailContent.css" rel="stylesheet">
<link href="/Board/css/writeForm.css" rel="stylesheet">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
	content_check = 0;
	bt_check = 1;
	
	$(function() {
		/*$('textarea[name=content]').click(function() {
			if (content_check == 0) {
				content_check = 1;
			}

		});*/

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
		contentLoad();
		$('#back').click(function() {
			location.href = "/Board/con?cmd=goMyPage";
		});

	});
	function contentLoad(){
		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=selectContent&contentNumber=' +<%=contentNumber%>,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
						$('textarea[name=content]').text(data.content);
				}else{
					alert("해당 내용이 존재하지 않습니다.");
				}
			},
			error : function(err) {
			}

		});
	}
</script>

</head>
<body>
	<form name=reg action="/Board/con?cmd=updateContent&ContentNumber=<%=contentNumber%>" method="post">
		<div id=Menu>
			<div id="CurrentPage">
				<a>게시물 수정</a>
			</div>
			<img id=back src='/Board/img/back.png' />
		</div>
		
		<div id=contentFrame_>
			<textarea id="textarea"  name=content class="fta-textarea"></textarea>
		</div>
		<div id="writeCommentFrame_">
			<img id="add_pic" src='/Board/img/add_pic.png' />
			<img id="okay" src='/Board/img/okay.png' />
		</div>
	</form>
</body>
</html>