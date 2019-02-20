<%@page import="kr.or.ddit.board_detail.model.Board_detailVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css"
	rel="stylesheet">
<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">

	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

	$(document).ready(
			function() {
			<% Board_detailVO vo =(Board_detailVO)request.getAttribute("boardVo");%>
				// Editor Setting
				nhn.husky.EZCreator.createInIFrame({
					oAppRef : oEditors, // 전역변수 명과 동일해야 함.
					elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
					sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
					fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
					htParams : {
						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseToolbar : true,
						// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,
						// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,
					}
				});
				var content= '${boardVo.content}';
					$("#title").val("${boardVo.title}")
					$("#smarteditor").val(content);
				// 전송버튼 클릭이벤트
				$("#savebutton").click(
						function() {
							if (confirm("저장하시겠습니까?")) {
								// id가 smarteditor인 textarea에 에디터에서 대입
								oEditors.getById["smarteditor"].exec(
										"UPDATE_CONTENTS_FIELD", []);

								// 이부분에 에디터 validation 검증
								if (validation()) {
									$("#frm").submit();
								}
							}
						})
			});

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<%-- <%@ include file="left.jsp" %> --%>
	<jsp:include page="/left.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<form action="${pageContext.request.contextPath}/boardupd"
			method="post" id="frm" enctype="multipart/form-data">
			<label>제목 :&nbsp;</label>
			<textarea rows="1" cols="100" id="title" name="title"></textarea>
			<textarea name="smarteditor" id="smarteditor" rows="10" cols="100"
				style="width: 766px; height: 412px;"></textarea>
			<input type="hidden" name="board_code" id="board_code"
				value="${boardVo.board_code}"> <input type="hidden"
				name="board_num" id="board_num" value="${boardVo.board_num}">
			<input type="hidden" name="board_num2" id="board_num2"
				value="${boardVo.board_num2}">
			<h4>첨부파일 목록</h4>
			<c:forEach items="${attachList}" var="vo">
				<div>
					<label>${vo.attach_nm}</label> <input type="button"
						class="attachDelBtn" value="삭제" /><br /> <input type="hidden"
						value="${vo.attach_code}" class="attachCodeTemp" />
				</div>
			</c:forEach>
			<input type="hidden" name="attachCode" id="attachCode" /> <input
				type="button" id="addFile" value="파일 첨부 추가">
		</form>
		<input type="button" id="savebutton" value="저장" />
	</div>
</body>
<script>
	$(document).ready(function(){
		var cnt =5-${attachList.size()};
		$(".attachDelBtn").on("click",function(){
			$("#attachCode").val($(this).siblings(".attachCodeTemp").val());
			$("#frm").attr("action","${pageContext.request.contextPath}/filedelete");
			$("#frm").submit();
		})
		$("#addFile").on("click",function(){
			if($(".attach").size()<cnt){
				$("#frm").append("<input type=\"file\" name=\"uploadFile\" class=\"attach\" /><br/>");
			}
		})
	});
</script>
</html>