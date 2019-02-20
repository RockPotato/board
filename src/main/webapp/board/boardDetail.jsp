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
	<%
		UserVO vo = (UserVO)session.getAttribute("userVo");
		%>
	<c:set scope="request" value="<%=vo.getUserId() %>" var="sUserId"/>
	<%-- <%@ include file="left.jsp" %> --%>
	<jsp:include page="/left.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<label>제목</label>
		<label>${selectBd.title}</label><br/>
		<label>글내용</label>
		<label>${selectBd.content }</label><br/>
		<label>첨부파일 목록</label><br/>
		
		<c:if test="${attachList!=null }">
			<c:forEach items="${attachList}" var="avo">
<%-- 			avo : ${avo}  <br> --%>
<%-- 			${avo.attach_realnm}   ////  ${avo.attach_path} <br> --%>
			<a href='${pageContext.request.contextPath}/filedownload?attach_code=${avo.attach_code}&board_num=${avo.board_num}'>${avo.attach_nm}</a>
			</c:forEach>
		</c:if>
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
				<c:if test="${rvo.reply_del=='n' }">
					${rvo.reply_content}			
					<label>[${rvo.userid}/${rvo.reply_date}]</label>
					<c:if test="${rvo.userid==sUserId}">
					<form action="${pageContext.request.contextPath }/replyupd" method="post" >
						<input type="submit" value="삭제" class="replyDel" />
						<input type="hidden" name="reply_code" value="${rvo.reply_code}">
						<input type="hidden" name="board_num" value="${selectBd.board_num}">
					</form>
					</c:if>	
				</c:if>
				<c:if test="${rvo.reply_del=='y'}">
					삭제된 댓글입니다.<br/>
				</c:if>
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