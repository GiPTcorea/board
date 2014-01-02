<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link href="/Board/css/MyPage.css" rel="stylesheet">
<link href="/Board/css/Board.css" rel="stylesheet">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
	ContentNumber = -1; //맨처음 게시물 넘버

	$(function() {
		
		myinfoLoad();
		$("#last").hide();
		contentLoad();
		$("#firstContent").hide();
		
		$("#mypic").click(function(){
			mypicChange();
		});
		
		$(window).scroll(
				function() {
					if ($(window).scrollTop() == $(document).height()
							- $(window).height()) {
						if (scrollOK) {
							scrollOK = false;
							contentLoad();
						}
					}
				});
		$("#write").click(function() {
			location.href = "/Board/Board/WriteForm.jsp?currentPage=2";
		});
		$('#back').click(function() {
			location.href = "/Board/Board/Board.jsp";
		});
		$('#updateProfile').click(function() {
			location.href = "/Board/con?cmd=goProfileForm";
			//location.href ="../write.jsp";
		});
		
		

	});
	function mypicChange(){
		
	}
	function myinfoLoad() {
		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=selectMember',
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					$('#uid').text(data.logid);
					$('#nick').text(data.nickname);
					if(data.picturePath != null){
						$('#mypic').attr('src',data.picturePath);
					}
					
				} else {
					
				}
			},
			error : function(err) {
			}

		});

	}
	function contentLoad() {
		alert("데이타 로딩중");
		
		$
				.ajax({
					type : 'POST',
					url : '/Board/con',
					data : 'cmd=selectMyContent&ContentNumber=' + ContentNumber,
					dataType : 'json',
					success : function(data) {
						if (data == null) {
							alert("데이타 로딩완료");
							$('#last').show();
						} else {

							for (var i = 0; i < data.length; i++) {
								
								$('.contents_:last')
										.after(
												"<div class=contents_  id="+data[i].number+"><img class=mypic_re src='/Board/img/pic.png'><div class=contentBlock_ >"
														+ "<p class= id_>"
														+ data[i].logid
														+ "</p>"
														+ "<p class=updated><img class='delete_' src='/Board/img/delete_img.png' onclick='javascript:clickDelete("
														+ data[i].number
														+ ")'/><img class='update_' src='/Board/img/update_img.png' onclick='javascript:clickUpdate("
														+ data[i].number
														+ ")'/></p><div onclick='javascript:clickContent("
														+ data[i].number
														+ ")'>"
														+ "<p class=createdAt_>"
														+ data[i].createdAt
														+ "</p>"
														+ "<p class=content_>"
														+ data[i].content
														+ "</p></div>"
														+ "</div>"
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
								
								if (data[i].picturePath != null) {
									
									$("#" + data[i].number).children(".mypic_re").attr('src',data[i].picturePath);
								}

							}
							ContentNumber = data[data.length - 1].number;
							alert("데이타 로딩완료");
						}
					},
					error : function(err) {
						alert("데이타 로딩완료");
					}

				});
		
	}

	function clickUpdate(number) {
		location.href = "/Board/Board/UpdateForm.jsp?contentNumber=" + number;
	}
	function clickDelete(number) {
		location.href = "/Board/con?cmd=deleteContent&ContentNumber=" + number;
	}
	function clickContent(number) {

		location.href = "/Board/Board/ContentDetail.jsp?contentNumber="
				+ number+"&currentPage=2";
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
					$("#" + number).children(".CntSet_").children("span").children(".metoo").text(data.isOkay);

				}
			},
			error : function(err) {
				alert("error");
			}

		});
	}
</script>

</head>
<body>
	<div id=Menu>
		<div id="CurrentPage">내 페이지</div>
		<img id=back src='/Board/img/back.png' />
	</div>

	<div id=Profile>
		<img id=mypic src='/Board/img/pic.png' alt='pic' />
		<div id="pro_set">
			<div id=uid>
				<a>아이디</a>
			</div>
			<div id=nick>
				<a>닉네임</a>
			</div>
		</div>
		<img id=updateProfile src='/Board/img/update_pro.png' />
	</div>

	<hr />

	<div id=barFrame>
		<span id="barComment">내 글</span> <img id="write"
			src='/Board/img/write_icon.png' />
	</div>

	<div id=contentFrame>
		<div class="contents_" id="firstContent"></div>
	</div>

	<div id=last>마지막입니다.</div>
</body>
</html>