<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<%
	//임의로
	int contentNumber = -1;
	int prePage = 0;
	contentNumber = Integer.parseInt(request
			.getParameter("contentNumber"));
	prePage = Integer.parseInt(request.getParameter("currentPage"));
%>
<link href="/Board/css/detailContent.css" rel="stylesheet">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	CommentLastNumber = -1; //맨처음 게시물 넘버
	CommentLatestNumber = -1;
	comment_check = 0;
	bt_check = 0;
	loadCount = 0;
	$(function() {

		$('#last').hide();
		contentLoad();
		commentLoad();
		$("#firstComment").hide();

		$('#writeComment').keyup(function() {

			if ($('#writeComment').val().length > 0) {
				$('#submitComment').css('color', '#ffffff');
				bt_check = 1;

			} else {
				$('#submitComment').css('color', '#D5D5D5');
				bt_check = 0;
			}
		});

		$('#submitComment').click(function() {
			if (bt_check == 1) {
				writeComment();
				bt_check = 0;

			}
		});
		$('#back').click(function() {
			if (<%=prePage%>== 1) {
				location.href = "/Board/Board/Board.jsp";
			} else {
				location.href = "/Board/Board/MyPage.jsp";

			}
		});
		$('#last').click(function() {
			commentLoad();
		});

	});
	function commentLoad() {
		$
				.ajax({
					type : 'POST',
					url : '/Board/con',
					data : 'cmd=selectAllComment&CommentNumber='
							+ CommentLastNumber + "&ContentNumber="
							+
<%=contentNumber%>
	,
					dataType : 'json',
					success : function(data) {

						if (loadCount == 0) {
							CommentLatestNumber = data[0].number;
							loadCount = 1;
						}
						if (data[data.length - 1].hasMore != 0) {
							$('#last').show();
						} else {
							$('#last').hide();
						}

						for (var i = 0; i < data.length - 1; i++) {
							$('.comments:first')
									.before(
											"<div class='comments' id="+data[i].number+">"
													+ "<img class='pic_comment_writer' src='/Board/img/pic.png'/><div class='commentBlock'><div class='ids'>"
													+ "<a class='id'>"
													+ data[i].logid
													+ "</a><a class='time'>"
													+ data[i].createdAt
													+ "</a></div><div class='comment'>"
													+ "<a>" + data[i].comment
													+ "</a></div></div></div>");

							if (data[i].picturePath != null
									&& data[i].picturePath != 'null') {

								$("#" + data[i].number).children(
										".pic_comment_writer").attr('src',
										data[i].picturePath);
							}

						}
						CommentLastNumber = data[data.length - 2].number;

					},
					error : function(err) {
					}

				});
		
	}

	function contentLoad() {

		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=selectContent&contentNumber=' +
<%=contentNumber%>
	,
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					$('#nick').text(data.logid);
					$('#date').text(data.createdAt);
					$('#content').text(data.content);
					$('#commentCnt').text(data.commentCnt);
					$('#metooCnt').text(data.metooCnt);
					if (data.picturePath != null && data.picturePath != 'null') {
						$('#mypic').attr('src', data.picturePath);
					}
				} else {
					alert("해당 내용이 존재하지 않습니다.");
				}
			},
			error : function(err) {
			}

		});
	}
	function writeComment() {
		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=writeComment&comment=' + $("#writeComment").val()
					+ "&contentNumber=" +
<%=contentNumber%>
	,
			dataType : 'json',
			success : function(data) {
				if (data.isokay == true) {
					$("#writeComment").val("");
					newCommentLoad();
					contentLoad();
				}
			},
			error : function(err) {
			}

		});
	}
	function newCommentLoad() {

		$
				.ajax({
					type : 'POST',
					url : '/Board/con',
					data : "cmd=selectNewComment&contentNumber="
							+
<%=contentNumber%>
	+ "&commentLatestNumber="
							+ CommentLatestNumber,
					dataType : 'json',
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
							$('.comments:last')
									.after(
											"<div class='comments' id="+data[i].number+">"
													+ "<img class='pic_comment_writer' src='/Board/img/pic_icon.png'/><div class='commentBlock'><div class='ids'>"
													+ "<a class='id'>"
													+ data[i].logid
													+ "</a><a class='time'>"
													+ data[i].createdAt
													+ "</a></div><div class='comment'>"
													+ "<a>" + data[i].comment
													+ "</a></div></div></div>");
							if (data[i].picturePath != null
									&& data[i].picturePath != 'null') {
								$("#" + data[i].number).children(
										".pic_comment_writer").attr('src',
										data[i].picturePath);
							}

						}

						CommentLatestNumber = data[0].number;
					},
					error : function(err) {
					}

				});

	}
	function clickContent(number) {

		alert(number);
	}
	function clickMeToo(number) {
		alert(number);
	}
</script>
<link href="/Board/css/Board.css" rel="stylesheet">
</head>
<body>

	<div id=Menu>
		<!-- <div id="CurrentPage">힐링보드</div> -->
		<img id=back src='/Board/img/back.png' />
	</div>

	<div id="detailContent">
		<div id=userFrame>
			<img id='mypic' class='pic' src='/Board/img/pic.png' alt='pic' />
			<div class="pic_detail">
				<div id=nick>닉네임</div>
				<div class="metooCnt_commentCnt">
					<img class="_icon_layer" src='/Board/img/comment_black_icon.png' />
					<a id="metooCnt"></a>
				</div>
				<div class="metooCnt_commentCnt">
					<img class="_icon_layer" src='/Board/img/metoo_black_icon.png' />
					<a id="commentCnt"></a>
				</div>

			</div>
		</div>

		<div id=contentFrame>
			<div id=date>
				<a>날짜</a>
			</div>
			<div id=content>
				<a>내용</a>
			</div>
		</div>

		<div id=last>더보기</div>
		<div id=firstComment class=comments></div>

		<div id="writeCommentFrame">
			<textarea id=writeComment></textarea>
			<img id=submitComment src='/Board/img/comment_bt.png' />
		</div>
	</div>
</body>
</html>