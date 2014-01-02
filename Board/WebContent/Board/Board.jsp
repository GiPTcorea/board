<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"%>
<%@ page import="DTO.ContentDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<%

	if (session.getAttribute("uid") == null) {
	int uid = Integer.parseInt(request.getParameter("uid"));
		session.setAttribute("uid", uid);
	}
	//session.setAttribute("uid", 1);

%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
	//<![CDATA[
	ContentNumber = -1; //맨처음 게시물 넘버
	scrollOK = true;

	function dataLoad() {
		if(ContentNumber!=-1){
		alert("데이타 로딩중");
		}
		$
				.ajax({
					type : 'POST',
					url : '/Board/con',
					data : 'cmd=selectAllContent&ContentNumber='
							+ ContentNumber,
					dataType : 'json',
					success : function(data) {
						if (data == null) {
							alert("데이타 로딩완료");
							$('#last').show();
						} else {

							for (var i = 0; i < data.length; i++) {

								$('.contents_:last')
										.after(
												"<div class=contents_ id="+data[i].number+" ><img class=mypic_re src='/Board/img/pic.png'/><div class=contentBlock_  onclick='javascript:clickContent("
														+ data[i].number
														+ ")'>"
														+ "<div class= id>"
														+ data[i].logid
														+ "</div>"
														+ "<div class=createdAt_>"
														+ data[i].createdAt
														+ "</div>"
														+ "<p class=content_>"
														+ data[i].content
														+ "</p>"
														+ "</div>"
														+ "<div class=updateBt>수정</div><div class=deleteBt>삭제</div>"

														+ "<div class=CntSet_>"
														+ "<span><img class=metooCnt_ src='/Board/img/metoo_mini_icon.png'/><a class= metoo>"
														+ data[i].metooCnt
														+ "</a></span>"
														+ "<span><img class=commentCnt_ src='/Board/img/comment_mini_icon.png'/><a>"
														+ data[i].commentCnt
														+ "</a></span></div><div class=contents_bt>"
														+ "<img class=metooBt_ id=clickBt src='/Board/img/metoo_icon.png' onclick='javascript:clickMeToo("
														+ data[i].number
														+ ")'/>"
														+ "<img class=commentBt_ src='/Board/img/comment_icon.png' onclick='javascript:clickContent("
														+ data[i].number
														+ ")'/></div></div>");

								if (data[i].picturePath != null
										&& data[i].picturePath != 'null') {
									$("#" + data[i].number).children(
											".mypic_re").attr('src',
											data[i].picturePath);
								}

							}

							ContentNumber = data[data.length - 1].number;

						}
						scrollOK = true;
						$(".deleteBt").hide();
						$(".updateBt").hide();
						alert("데이타 로딩완료");
						
					},
					error : function(err) {
						alert("데이타 로딩완료");
					}

				});
	
	}

	$(function() {
		$("#last").hide();
		dataLoad();
		$("#firstContent").hide();

		$(window).scroll(
				function() {
					if ($(window).scrollTop() == $(document).height()
							- $(window).height()) {
						if (scrollOK) {
							scrollOK = false;
							dataLoad();
						}
					}
				});
		$("#write").click(function() {
			location.href = "/Board/Board/WriteForm.jsp?currentPage=1";
		});
		$('#goMyPage').click(function() {
			location.href = "/Board/Board/MyPage.jsp";
		});

	});

	function clickContent(number) {

		location.href = "/Board/Board/ContentDetail.jsp?contentNumber="
				+ number+"&currentPage=1";

	}
	function clickMeToo(number) {

		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=increaseMetooCnt&contentNumber=' + number,
			dataType : 'json',
			success : function(data) {
				if (data.isOkay == "dupli") {
					alert("이미 누르셨습니다.");
				} else if (data.isOkay == "false") {
					alert("error");
					//모바일쪽은 다른가..
				} else {
					$("#" + number).children(".CntSet_").children("span")
							.children(".metoo").text(data.isOkay);

				}
			},
			error : function(err) {
				alert("error");
			}

		});
	}
	//]]>
</script>
<link href="/Board/css/Board.css" rel="stylesheet">
<link href="/Board/css/MyPage.css" rel="stylesheet">

</head>

<body>
	<div id=Menu>
		<div id="CurrentPage">
			<a>힐링보드</a>
		</div>
		<img id="goMyPage" src='/Board/img/mypage_icon.png' />
	</div>

	<div id="barFrame">
		<span id="barComment">하고 싶은 말이 있으세요?</span> <img id="write"
			src='/Board/img/write_icon.png' />

	</div>

	<div class="contentFrame_">
		<div class="contents_" id=firstContent></div>
	</div>

</body>
</html>