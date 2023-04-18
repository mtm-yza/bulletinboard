<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="user">
  <div class="form-group">
    <form:label path="name">Name</form:label>
    <form:input id="txtName" class="form-control" type="text"
      path="name" placeholder="Name" />
      <div class="label-error">
      <c:out value="${errors['name']}" />
    </div>
  </div>
  <div class="form-group">
    <label>Role</label><br />
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="role" value="1" checked="true" />User
      </label>
    </div>
    <div class="form-check form-check-inline">
      <label class="form-check-label"> <form:radiobutton
          class="form-check-input" path="role" value="2" />Admin
      </label>
    </div>
  </div>
  <div class="form-group">
    <form:label path="email">Email</form:label>
    <form:input id="txtEmail" class="form-control" type="text"
      path="email" placeholder="Email" />
    <div class="label-error">
      <c:out value="${errors['email']}" />
    </div>
  </div>
  <div class="form-group">
    <form:label path="address">Address</form:label>
    <form:textarea id="txtAddress" class="form-control" path="address"
      rows="3" placeholder="Address"></form:textarea>
    <div class="label-error">
      <c:out value="${errors['address']}" />
    </div>
  </div>
  <div class="form-group">
    <form:label path="password">Password</form:label>
    <form:input id="txtPassword" class="form-control" type="text"
      path="password" placeholder="Password"/>
    <div class="label-error">
      <c:out value="${errors['password']}" />
    </div>
  </div>
  <input class="btn btn-primary" formaction="add" type="submit"
    value="Submit">
</form:form>
