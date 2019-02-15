<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <!DOCTYPE html> -->
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
		<!-- bdList 정보를 화면에 출력하는 로직 작성 -->

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bdList}" var="vo">
						<tr class="bdTr" data-bdid="${vo.board_num }">
							<td>${vo.board_num }</td>
							<td>${vo.title}</td>
							<td>${vo.userid }</td>
							<td><fmt:formatDate value="${vo.upd_date}"
									pattern="yyyy/MM/dd"></fmt:formatDate></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:set var="lastPage"
				value="${Integer(bdCnt / pageSize + (bdCnt % pageSize > 0 ? 1 : 0))}" />

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
								href="${pageContext.servletContext.contextPath }/boarddetail?board_code=${board_code}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${page == 1 }">
							<li class="disabled"><a aria-label="Previous"> <span
									aria-hidden="true">&lt;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/boarddetail?page=${page-1}&board_code=${board_code}"
								aria-label="Previous"> <span aria-hidden="true">&lt;</span>
							</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach begin="1" end="${lastPage }" var="i">
						<c:set var="active" value="" />
						<c:if test="${i == page }">
							<c:set var="active" value="active" />
						</c:if>

						<li class="${active }"><a
							href="${pageContext.servletContext.contextPath }/boarddetail?board_code=${board_code}&page=${i}">${i}</a>
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
								href="${pageContext.servletContext.contextPath }/boarddetail?board_code=${board_code}&page=
												${page+1}"
								aria-label="Next"> <span aria-hidden="true">&gt;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${page ==lastPage}">
							<li class="disabled"><a aria-label="Next"> <span
									aria-hidden="true">&raquo;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/boarddetail?board_code=${board_code}&page=
												${lastPage}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<form action="${pageContext.request.contextPath}/bdForm"
					method="get">
					<button type="submit" class="btn_btn-default">글 등록</button>
				</form>
			</nav>


		</div>
	</div>
</body>
</html>