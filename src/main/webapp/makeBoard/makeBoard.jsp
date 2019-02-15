<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 생성</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<%-- <%@ include file="left.jsp" %> --%>
	<jsp:include page="/left.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<form action="<%=request.getContextPath()%>/boardclass" method="get" id="newBoardFrm">
			<label>게시판 이름</label> 
			<input type="text" id="newBoardNm"
				name="newBoardNm" />
			<select id="newBoardUse" name="newBoardUse">
				<option>사용</option>
				<option>미사용</option>
			</select>
			<button type="button" id="newBoardSub">생성</button>
		</form>
	</div>
	
	<c:if test="${bcList.size()>0}">
		<c:forEach items="${bcList}" var="bcVo">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form action="<%=request.getContextPath()%>/boardclass" method="post" id="updBoardFrm">
				<input type="hidden" id="updBoardCode" name="updBoardCode" value="${bcVo.board_code}">
					<label>게시판 이름</label> <input type="text" id="updBoardNm"
						name="updBoardNm" value="${bcVo.board_nm }" /> 
					<select id="updBoardUse" name="updBoardUse">
						<option>사용</option>
						<option <c:if test="${bcVo.board_use=='n'}">selected</c:if>>미사용</option>
					</select>
					<button type="button" class="updBoardCode" >수정</button>
				</form>
			</div>
		</c:forEach>
	</c:if>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#newBoardSub").on("click",function(){
			$("#newBoardFrm").submit();
		});
		$(".updBoardCode").on("click",function(){
			alert($(this).parent("#updBoardFrm").children("#updBoardCode").val());
			$(this).parent("#updBoardFrm").submit();
		});
	})
// 	function updSubmit(board_code){
// 		console.log(board_code);
// 		$("#updBoardFrm").submit();
// 	}
</script>
</html>