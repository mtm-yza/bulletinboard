<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>

<div class="jumbotron jumbotron-fluid">
  <div class="container">

    <h1 class="display-5">
      <c:out value="${msg}" />
    </h1>

    <hr class="my-4">

    <ul class="lead">
      <c:forEach var="error" items="${errors}">
        <li><c:out value="${error.toString()}" /></li>
      </c:forEach>
    </ul>


  </div>
</div>