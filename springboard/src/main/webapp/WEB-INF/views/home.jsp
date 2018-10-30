<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="box-header with-border">
			<c:if test="${user == null}">
				<a href="${pageContext.request.contextPath}/user/register"><h3
						class="box-title">회원가입</h3></a>
				<a href="${pageContext.request.contextPath}/user/login"><h3
						class="box-title">로그인</h3></a>
			</c:if>
			<c:if test="${user != null}">
				<a href="${pageContext.request.contextPath}/user/logout"><h3
						class="box-title">로그아웃</h3></a>
			</c:if>
		</div>
	</div>
</section>
<c:if test="${insert != null }">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#dialog-confirm").dialog({
				resizable : false,
				height : "auto",
				width : 400,
				modal : true,
				buttons : {
					"닫기" : function() {
						$(this).dialog("close");
					}
				},
			});
		});
	</script>
</c:if>

<div id="dialog-confirm" title="회원가입" style="display: none">
	<p>
		<span class="ui-icon ui-icon-alert"
			style="float: left; margin: 12px 12px 20px 0;"></span>회원가입에
		성공하셨습니다.이제 로그인하고 사용하시면 됩니다.
	</p>
</div>
<%@include file="include/footer.jsp"%>
