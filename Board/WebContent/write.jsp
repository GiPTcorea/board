<HTML>
<HEAD>
<SCRIPT language="javascript">
	function reviewUploadImg(fileObj) {
		var filePath = fileObj.value;
		var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		var fileKind = fileName.split(".")[1];
		if (fileKind != "jpg" && fileKind != "gif" && fileKind != "png"
				&& fileKind != "jpeg") {
			alert("jpg, gif, png 확장자를 가진 이미지 파일만 올려주세요.");
			document.getElementById("path").value = "";
			document.getElementById("path").select();
			document.selection.clear();
		} else {
			document.msgwrite.submit();
			out.println("submitted");
		}

	}
</SCRIPT>
<style type="text/css">
</style>
<link herf="databoard.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
	<P>
	<FORM name="msgwrite" method=post action="/save.jsp"
		enctype="multipart/form-data">
		<CENTER>


			<table>
				<tr>
					<td width="124" bgcolor="#f4f4f4" style="padding: 0 0 0 14">
						<div align="center">imgupload</div>
					</td>
					<td width="494" style="padding: 0 0 0 10" height="25"><input
						id="path" type="file" name="file" size=0
						onchange="reviewUploadImg(this);"> <!-- 파일인풋처리하는 부분... jQuery를 이용해서 Custom input을 하는 방법이 있다고 함. -->
						<!-- 니가 저번에 JQuery 안다고 하기에 수정 안함.-->
					</td>
					<td><input type="submit" value="confirm"></td>
				</tr>

			</table>

		</CENTER>

	</FORM>
</BODY>
</HTML>