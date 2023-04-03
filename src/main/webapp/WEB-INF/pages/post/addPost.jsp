<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="post">
  <c:if test="${errors != null }">
    <div class="alert alert-danger" role="alert">
      <div class="container">
        <h3 class="display-6">
          <c:out value="${msg}" />
        </h3>
        <hr class="my-4">
        <ul class="lead">
          <c:forEach var="error" items="${errors}">
            <li><c:out value="${error.toString()}" /></li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </c:if>
  <div class="form-group">
    <form:label path="title">Title</form:label>
    <form:input class="form-control" type="text" path="title"
      placeholder="Title" />
  </div>
  <div class="form-group">
    <form:label path="description">Description</form:label>
    <form:textarea class="form-control" path="description" rows="3"
      placeholder="Description"></form:textarea>
  </div>
  <div class="form-group">
    <label>Status</label><br />
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="isActive" value="true"
          checked="true" />Public
      </label>
    </div>
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="isActive" value="false" />Private
      </label>
    </div>
  </div>
  <input class="btn btn-primary" formaction="add" type="submit"
    value="Submit">
</form:form>
