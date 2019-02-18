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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세보기</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<%-- <%@ include file="left.jsp" %> --%>
	<jsp:include page="/left.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<label>제목</label>
		<label>${selectBd.title}</label><br/>
		<label>글내용</label>
		<label>${selectBd.content }</label><br/>
		<label>첨부파일</label><br/>
		<form action="${pageContext.request.contextPath }/boardupd" method="get" id="updFrm">
			<input type="submit" id="updBoard" value="수정"/>
			<input type="submit" id="delBoard" value="삭제"/>
			<input type="hidden" id="board_code" name="board_code" value="${selectBd.board_code }">
			<input type="hidden" id="board_num2" name="board_num2" value="${selectBd.board_num }">
			<input type="hidden" id="board_num" name="board_num" value="${selectBd.board_num }">
			<input type="submit" id="ansBoard" value="답글"/><br/>
		</form>
		<label>댓글</label>
		<c:if test="${replyList!=null }">
			<c:forEach items="${replyList}" var="rvo">
				${rvo.reply_content}			
				<label>[${rvo.userid}/${rvo.reply_date}]</label><br/>		
			</c:forEach>
		</c:if>
		<form action="${pageContext.request.contextPath}/boarddetail" method="post">
			<textarea rows="1" cols="20" id="newReply" name="newReply"></textarea>
			<input type="hidden" id="board_num" name="board_num" value="${selectBd.board_num }">
			<input type="submit" id="newReplyBtn" value="댓글 저장">
		</form>
	</div>
	<script>
		$(document).ready(function(){
			$("#delBoard").on("click",function(){
				if(confirm("삭제하시겠습니까?")){
					$("#updFrm").attr("method","post");
					$("#updFrm").attr("action","${pageContext.request.contextPath }/boardpaging");
				}
			});
			$("#ansBoard").on("click",function(){
					$("#updFrm").attr("action","${pageContext.request.contextPath }/bdForm");
			});
		});
	</script>
</body>
</html>