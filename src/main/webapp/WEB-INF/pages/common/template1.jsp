<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" errorPage="result"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
  prefix="tilesx"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<tilesx:useAttribute id="cssList" name="css" classname="java.util.List" />
<c:forEach var="item" items="${cssList}">
  <link rel="stylesheet" type="text/css"
    href="<c:url value='${item}' />" />
</c:forEach>
<tilesx:useAttribute id="initScripts" name="initScripts"
  classname="java.util.List" />
<c:forEach var="initialScript" items="${initScripts}">
  <script type="text/javascript"
    src="<c:url value='${initialScript}' />"></script>
</c:forEach>
</head>
<body>
  <header>
    <tiles:insertAttribute name="header" />
  </header>
  <main class="py-5">
    <div class="container">
      <tiles:insertAttribute name="body" />
    </div>
    <div class="py-5"></div>
  </main>
  <footer class="bg-dark fixed-bottom">
    <tiles:insertAttribute name="footer" />
  </footer>
  <tilesx:useAttribute id="scripts" name="scripts"
    classname="java.util.List" />
  <c:forEach var="item1" items="${scripts}">
    <script type="text/javascript" src="<c:url value='${item1}' />"></script>
  </c:forEach>
</body>
</html>
