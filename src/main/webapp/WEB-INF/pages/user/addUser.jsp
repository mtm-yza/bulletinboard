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
    <label for="title">Name</label> <input id="txtName"
      class="form-control" type="text" name="name" placeholder="Name">
  </div>
  <div class="form-group">
    <label for="title">Email</label> <input id="txtEmail"
      class="form-control" type="text" name="email" placeholder="Email">
  </div>
  <div class="form-group">
    <label for="address">Address</label>
    <textarea id="txtAddress" class="form-control" name="address"
      rows="3" placeholder="Address"></textarea>
  </div>
  <div class="form-group">
    <label for="password">Password</label> <input id="txtPassword"
      class="form-control" type="text" name="password"
      placeholder="Password">
  </div>
  <input class="btn btn-primary" formaction="add" type="submit"
    value="Submit">
</form>
