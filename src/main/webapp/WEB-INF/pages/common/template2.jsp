<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" errorPage="result"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value='/resources/css/bootstrap.min.css'/>"
  rel="stylesheet" />
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
  <footer class="bg-light fixed-bottom">
    <tiles:insertAttribute name="footer" />
  </footer>
</body>

<style>

  footer {
    position: absolute;
    bottom: 0;
    width: 100%;
  }

</style>

<tiles:insertAttribute name="javascript" ignore="true" />
</html>
