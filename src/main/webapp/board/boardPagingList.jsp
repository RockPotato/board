<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="/header.jsp"%>
	<%-- <%@ include file="left.jsp" %> --%>
	<jsp:include page="/left.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">전체 사용자 리스트</h1>
		<!-- userList 정보를 화면에 출력하는 로직 작성 -->

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>사용자 아이디</th>
						<th>사용자 이름</th>
						<th>별명</th>
						<th>등록일시</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userpageList }" var="vo">
						<tr class="userTr" data-userid="${vo.userId }">
							<td></td>
							<!-- 생략 -->
							<td>${vo.userId }</td>
							<!-- userId -->
							<td>${vo.userNm }</td>
							<!-- userNm -->
							<td>-</td>
							<!-- 생략 -->
							<td><fmt:formatDate value="${vo.reg_dt}"
									pattern="yyyy/MM/dd"></fmt:formatDate></td>
							<!-- reg_dt -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form action="${pageContext.request.contextPath}/userForm"
				method="get">
				<button type="submit" class="btn_btn-default">사용자 등록</button>
			</form>

			<c:set var="lastPage"
				value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0))}" />

			<nav style="text-align: center;">
				<ul class="pagination">
					<c:choose>
						<c:when test="${page == 1 }">
							<li class="disabled"><a aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/userPagingList"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach begin="1" end="${lastPage }" var="i">
						<c:set var="active" value="" />
						<c:if test="${i == page }">
							<c:set var="active" value="active" />
						</c:if>

						<li class="${active }"><a
							href="${pageContext.servletContext.contextPath }/userPagingList?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<c:choose>
						<c:when test="${page ==lastPage}">
							<li class="disabled"><a aria-label="Next"> <span
									aria-hidden="true">&raquo;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/userPagingList?page=
												${lastPage}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</nav>


		</div>
	</div>
</body>
</html>