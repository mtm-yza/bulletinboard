<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post">
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
    <label for="title">Title</label> <input class="form-control"
      type="text" name="title" placeholder="Title">
  </div>
  <div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control" name="description" rows="3"></textarea>
  </div>
  <div class="form-group">
    <label for=status>Status</label><br/>
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <input type="radio"
        class="form-check-input" name="status" value="0" checked>Private
      </label>
    </div>
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <input type="radio"
        class="form-check-input" name="status" value="1">Public
      </label>
    </div>
  </div>
  <input class="btn btn-primary" formaction="add" type="submit"
    value="Submit">
</form>
