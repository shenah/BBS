<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Board</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.comrespond/1.4.2respond.min.js"></script>
    <![endif]-->

</head>
<!-- jQuery 2.1.4 -->
<script
	src="${pageContext.request.contextPath}/resources/jquery/jQuery-2.1.4.min.js"></script>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<div class="page-header">
				<h1>Spring MVC 게시판</h1>
			</div>
		</header>
	</div>
	<aside class="main-sidebar">

		<section class="sidebar">
			<ul class="nav nav-tabs">
				<c:if test="${user == null}">
					<li role="presentation"><a href="${pageContext.request.contextPath}/user/register">회원가입</a></li>
				</c:if>
				<c:if test="${user != null}">
					<li role="presentation"><a href="#"> <span class="badge"><img
								src="${pageContext.request.contextPath}/userimage/${user.image}"
								width="20" height="20" /></span>${user.nickname}님</a></li>
				</c:if>

				<li role="presentation"><a href="${pageContext.request.contextPath}/">메인</a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/board/list"">목록보기</a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/board/register">게시물 쓰기</a></li>
				
			</ul>
		</section>
	</aside>
	<div>