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
    <tiles:insertAttribute name="header" ignore="true" />
  </header>
  <main class="py-5">
    <div class="container">
      <c:if test="${msg != null}">
        <div
          class="alert 
            <c:choose>
              <c:when test="${msgType eq 'success'}">alert-success</c:when>
              <c:when test="${msgType eq 'error'}">alert-danger</c:when>
              <c:otherwise>alert-primary</c:otherwise>
            </c:choose>
          "
          role="alert">
          <div class="container">
            <h3 class="display-6">
              <c:out value="${msgHeader}" />

            </h3>
            <hr class="my-3">
            <ul class="lead">
              <c:if test="${msg!= null}"><c:out value="${msg}"/></c:if>
            </ul>
          </div>
        </div>
      </c:if>
      <tiles:insertAttribute name="body" />
    </div>
    <div class="py-5"></div>
  </main>
  <footer class="bg-dark fixed-bottom">
    <tiles:insertAttribute name="footer" />
  </footer>
  <!-- Confirm Form -->
  <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Confirm</h5>
          <button type="button" class="close" data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">Are you sure?</div>
        <div class="modal-footer">
          <button id="btnConfirm" class="btn btn-primary" type="button">Confirm</button>
          <button class="btn" type="button" data-dismiss="modal">Cancel</button>
        </div>
      </div>
    </div>
  </div>
  <tilesx:useAttribute id="scripts" name="scripts"
    classname="java.util.List" />
  <c:forEach var="item1" items="${scripts}">
    <script type="text/javascript" src="<c:url value='${item1}' />"></script>
  </c:forEach>
</body>
</html>
