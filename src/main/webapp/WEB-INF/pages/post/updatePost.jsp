<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
  <thead>
    <th>ID</th>
    <th>Title</th>
    <th>Description</th>
  </thead>

  <tbody>
    <c:forEach items="${posts}" var="item">
      <tr>
        <td><c:out value="${item.id}" /></td>
        <td><c:out value="${item.title}" /></td>
        <td><c:out value="${item.description}" /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<form method="post">

  <h2>Update Post Form</h2>
  <br />

  <c:if test="${errors != null }">
    <div class="alert alert-danger" role="alert">
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
  </c:if>

  <div class="form-group">
    <label for="id">ID</label> <input class="form-control" type="number"
      name="id" placeholder="ID" required>
  </div>

  <div class="form-group">
    <label for="title">Title</label> <input class="form-control"
      type="text" name="title" placeholder="Title">
  </div>

  <div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control" name="description" rows="3"></textarea>
  </div>

  <div class="form-group">
    <select name="status">
      <option value="0" selected>Disable</option>
      <option value="1">Active</option>
    </select>
  </div>

  <input class="btn btn-primary" formaction="update" type="submit"
    value="Edit"> <input class="btn btn-primary"
    formaction="delete" type="submit" value="Delete">
</form>

<div class="container py-5"></div>
