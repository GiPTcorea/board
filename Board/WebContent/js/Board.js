ContentNumber = -1;

scrollOK = true;

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
		location.href = "/Board/con?cmd=goWriteForm";
	});
	$('#goMyPage').click(function() {
		location.href = "MyPage.html";
	});

});

function dataLoad() {

	$
			.ajax({
				type : 'POST',
				url : '/Board/con',
				data : 'cmd=selectAllContent&ContentNumber=' + ContentNumber,
				dataType : 'json',
				success : function(data) {
					if (data == null) {
						$('#last').show();
					} else {

						for (var i = 0; i < data.length; i++) {
							$('.contents:last')
									.after(
											"<div class=contents>"
													+ "<img class=pic src='/Board/lib/img/pic.png' alt='pic' width=15% height=30% />"
													+ "<div class=contentBlock  onclick='javascript:clickContent("
													+ data[i].number
													+ ")'>"
													+ "<div class=createdAt >"
													+ data[i].createdAt
													+ "</div>"
													+ "<div class=content><pre>"
													+ data[i].content
													+ "</pre></div>"
													+ "</div>"
													+ "<div class=updateBt>����</div><div class=deleteBt>����</div>"
													+ "<div class= id>"
													+ data[i].id
													+ "</div>"
													+ "<div class=CntSet>"
													+ "<span class=metooCnt>����"
													+ data[i].metooCnt
													+ "</span>"
													+ "<span class=commentCnt>����"
													+ data[i].commentCnt
													+ "</span></div>"
													+ "<div class=metooBt onclick='javascript:clickMeToo("
													+ data[i].number
													+ ")'>����</div>"
													+ "<div class=commentBt onclick='javascript:clickContent("
													+ data[i].number
													+ ")'>����</div></div>");

						}
						ContentNumber = data[data.length - 1].number;

					}
					scrollOK = true;
					$(".deleteBt").hide();
					$(".updateBt").hide();
				},
				error : function(err) {
				}
			});
}

function clickContent(number) {

	location.href = "/Board/Board/ContentDetail.jsp?contentNumber=" + number;
}

function clickMeToo(number) {

	$.ajax({
		type : 'POST',
		url : '/Board/con',
		data : 'cmd=increaseMetooCnt&contentNumber=' + number,
		dataType : 'json',
		success : function(data) {
			if (data == null) {

			} else {
				alert("error");
			}
		},
		error : function(err) {
		}
	});
}