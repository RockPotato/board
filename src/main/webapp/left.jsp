<%@page import="kr.or.ddit.board_class.service.Board_classServiceImpl"%>
<%@page import="kr.or.ddit.board_class.service.IBoard_classService"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board_class.model.Board_classVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li><a href="<%=request.getContextPath()%>/makeBoard/makeBoard.jsp">게시판 생성</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<c:if test="${bcList.size()>0}">
					<c:forEach items="${bcList}" var="bcVo">
						<c:if test="${bcVo.board_use=='y'}">
							<li><a href="${pageContext.request.contextPath}/boardpaging?board_code=${bcVo.board_code}">${bcVo.board_nm}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
</div>
