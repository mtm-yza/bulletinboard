<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Login Form -->
<form method="post" name="login">
  <div class="form-group">
    <label>Email</label> <input class="form-control" name="email"
      type="email" placeholder="Email">
    <div class="label-error">
      <c:out value="${errors['email']}" />
    </div>
  </div>
  <div class="form-group">
    <label>Password</label> <input class="form-control" name="password"
      type="password" placeholder="Password">
    <div class="label-error">
      <c:out value="${errors['password']}" />
    </div>
  </div>
  <input class="btn btn-primary" type="submit" value="Login">
</form>
