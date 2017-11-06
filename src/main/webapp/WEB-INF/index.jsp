<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<h1>${gold}</h1>
		<div>
			<form action="/getgold" method="post">
				<h1>Farm</h1>
				<p>(earns 10-20 gold)</p>
			<input type="submit" value="farm" name="building">
			</form>
			<form action="/getgold" method="post">
				<h1>Cave</h1>
				<p>(earns 5-10 gold)</p>
			<input type="submit" value="cave" name="building">
			</form>
			<form action="/getgold" method="post">
				<h1>House</h1>
				<p>(earns 2-5 gold)</p>
			<input type="submit" value="house" name="building">
			</form>
			<form action="/getgold" method="post">
				<h1>Casino!</h1>
				<p>(earns/takes 0-50 gold)</p>
			<input type="submit" value="casino" name="building">
			</form>
			<form action="/reset" method="get">
			<input type="submit" value="reset">
			</form>
		</div>

		<div style="overflow:scroll; height:100px; width:500px">
			<c:forEach items = "${events}" var="event" >
				<c:choose>
					<c:when test='${event.contains("lost")}'>
						<p style="color:red">
							${event}
						</p>
					</c:when>
					<c:otherwise>
							<p style="color:green">
								${event}
							</p>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</body>
</html>