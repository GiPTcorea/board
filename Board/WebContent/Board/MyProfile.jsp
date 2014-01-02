<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0">
<!-- <link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/bootstrap-responsive.css" rel="stylesheet"> -->
<link href="/Board/css/Board.css" rel="stylesheet">
<link href="/Board/css/writeForm.css" rel="stylesheet">
<link href="/Board/css/MyPage.css" rel="stylesheet">
<link href="/Board/css/updateContent.css" rel="stylesheet">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
	isOkay = 0;
	$(function() {
		$('#namechk').hide();
		//$('#path').hide();
		$('#update_okay').click(function() {
			checkData();

		});

		loadMyInfo();
		$('#back').click(function() {
			location.href = "/Board/con?cmd=goMyPage";
		});
		$('#update_mypic_').click(function() {
			imgclick();
		});
		$('#imgForm').ajaxForm({
			dataType : 'json',
			success : function(data) {
				//alert(data.type);
				$('#update_mypic_').attr('src', data.type);
				alert("데이타 로딩완료");
			}
		});

		$('#imgForm').submit(function() {
			return false;
		});

	});
	/*
	function FileUploadCallback(){
		alert("success");
	}
	 */
	function reviewUploadImg(fileObj) {
		var filePath = fileObj.value;
		var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		var fileKind = fileName.split(".")[1];

		if (fileKind != "jpg" && fileKind != "gif" && fileKind != "png"
				&& fileKind != "jpeg") {
			alert("jpg, gif, png íì¥ìë¥¼ ê°ì§ ì´ë¯¸ì§ íì¼ë§ ì¬ë ¤ì£¼ì¸ì.");
			document.getElementById("path").value = "";
			document.getElementById("path").select();
			document.selection.clear();
		} else {
			$('#imgForm').submit();
			alert("데이타 로딩중");
		}

	}

	function imgclick() {
		$('#path').click();
	}

	function loadMyInfo() {
		$.ajax({
			type : 'POST',
			url : '/Board/con',
			data : 'cmd=selectMember',
			dataType : 'json',
			success : function(data) {
				$('#update_mypic_').attr('src', data.picturePath);
				$('#field_id').text(data.logid);
				$('#field_name').val(data.name);
				$('#field_age').val(data.age);
				$('#field_phone').val(data.phone);
				$('#field_nick').val(data.nickname);
				$('#field_car').val(data.car);
				$('#field_ton').val(data.ton);
			},
			error : function(err) {

			}
		});
	}

	function checkData() {
		if ($('#field_name').val().length == 0) {
			alert("이름을 입력하지 않으셨습니다.");
			$('#field_name').focus();
			return;
		}
		if ($('#field_name').val().length > 6
				|| $('#field_name').val().length < 2) {
			alert("이름을 2~6자리로 입력하세요.");
			$('#field_name').val("");
			$('#field_name').focus();
			return;
		}
		if (isKorean($('#field_name').val()) == 0) {
			alert("한글만 입력하세요.");
			$('#field_name').val("");
			$('#field_name').focus();
			return;
		}

		if ($('#field_age').val().length == 0) {
			alert("나이를 입력하지 않으셨습니다.");
			$('#field_age').focus();
			return;
		}
		if ($('#field_phone').val().length == 0) {
			alert("휴대번호를 입력하지 않으셨습니다.");
			$('#field_phone').focus();
			return;
		}
		if ($('#field_phone').val().length != 10
				&& $('#field_phone').val().length != 11) {
			alert("휴대번호 자리수를 확인하세요.");
			$('#field_phone').focus();
			return;
		}
		if (isNumber($('#field_phone').val()) == 1) {
			alert("숫자만 입력하세요.");
			$('#field_phone').val("");
			$('#field_phone').focus();
			return;
		}
		if ($('#field_nick').val().length == 0) {
			alert("닉네임 입력하지 않으셨습니다.");
			$('#field_nick').focus();
			return;
		}
		if (isNick($('#field_nick').val()) == 0) {
			alert("한글+숫자만 입력하세요.");
			$('#field_nick').val("");
			$('#field_nick').focus();
			return;
		}
		if ($('#field_nick').val().length > 12
				|| $('#field_nick').val().length < 1) {
			alert("닉네임을 1~12자리로 입력하세요.");
			$('#field_nick').val("");
			$('#field_nick').focus();
			return;
		}
		if ($('#field_car').val().length == 0) {
			alert("차종을 입력하지 않으셨습니다.");
			$('#field_car').focus();
			return;
		}
		if ($('#field_ton').val().length == 0) {
			alert("톤수를 입력하지 않으셨습니다.");
			$('#field_ton').focus();
			return;
		}

		document.reg.submit();
	}

	function isKorean(str) {

		for (i = 0; i < str.length; i++) {
			if (str.charCodeAt(i) > 55203 || str.charCodeAt(i) < 44032) {
				return 0;
			}
		}

		return 1;

	}
	function isNick(str) {

		for (i = 0; i < str.length; i++) {
			if (!((str.charCodeAt(i) <= 57 && str.charCodeAt(i) >= 48) || (str
					.charCodeAt(i) <= 55203 && str.charCodeAt(i) >= 44032))) {
				return 0;
			}
		}

		return 1;

	}

	function isNumber(number) {
		if (isNaN(number) == true) {
			return 1;
		} else {
			return 2;
		}
	}
</script>

</head>
<body>
	<div id=Menu>
		<div id="CurrentPage">
			<a>프로필 수정</a>
		</div>
		<img id=back src='/Board/img/back.png' />
	</div>

	<div id="Profile">
		<form id="imgForm" name="msgwrite" method=post
			action="/Board/con?cmd=imgHandler" enctype="multipart/form-data">
			<div style='text-align: center'>
				<input id="path" type="file" name="file" size='0'
					style='opacity: 0; filter: alpha(opacity = 0);'
					onchange="reviewUploadImg(this);">
			</div>
			<div style='text-align: center'>
				<img id="update_mypic_" name='update_mypic_'
					src='/Board/img/pic_icon.png' />
			</div>

		</form>
		<form name=reg action="/Board/con?cmd=updateProfile" method="post">
			<div id="field">
				<div class="section">아이디</div>
				<div id="field_id" class="mention">abc</div>
				<div class="section">이름</div>
				<input type="text" id="field_name" name="field_name" class="mention"
					size="15">
				<div class="section">나이</div>
				<!-- <input type="text" id="field_age" name="field_age" class="mention" size="15"> -->
				<select id=field_age name="field_age" class="mention">
					<option>20대</option>
					<option>30대</option>
					<option>40대</option>
					<option>50대 이상</option>
				</select>
				<div class="section">휴대번호</div>
				<input type="text" id="field_phone" name="field_phone"
					class="mention" size="15">
				<div class="section">닉네임</div>
				<input type="text" id="field_nick" name="field_nick" class="mention"
					size="15">
				<div class="section">차종</div>

				<!-- <input type="text" id="field_car" name="field_car" class="mention">  value=""-->
				<select id=field_car name="field_car" class="mention">
					<option>카고</option>
					<option>내장탑</option>
					<option>냉동탑</option>
					<option>윙바디</option>
					<option>홈로리</option>
					<option>탱크로리</option>
					<option>덤프트럭</option>
					<option>암롤트럭</option>
					<option>진개차</option>
					<option>기타</option>
				</select>
				<div class="section">톤수</div>
				<!-- <input type="text" id="field_ton" name="field_ton" class="mention"> -->

				<select id="field_ton" name="field_ton" class="mention">
					<option>1</option>
					<option>1.4</option>
					<option>2.5</option>
					<option>3.5</option>
					<option>5</option>
					<option>8</option>
					<option>11</option>
					<option>14</option>
					<option>15</option>
					<option>18</option>
					<option>25</option>

				</select>

				<!-- <div class="section">
					차량연식
				</div>
				<input type="text" id="field_year" class="mention">
  					-->
			</div>
		</form>

		<!-- <<textarea id="textarea" class="fta-textarea" ></textarea> -->
	</div>
	<div id="writeCommentFrame_">
		<div style='text-align: center'>
			<img id="update_okay" src='/Board/img/okay.png' />
		</div>
	</div>

</body>
</html>