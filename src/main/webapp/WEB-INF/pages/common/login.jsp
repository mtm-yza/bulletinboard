<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="h2 mb-3 font-weight-normal">Please sign in</h1>
<!-- Error Message -->
<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message != null}">
  <div class="alert alert-danger" role="alert">
    <div class="container">
      <h3 class="display-6">Failed to Login</h3>
      <hr class="my-3">
      <ul class="lead">
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
      </ul>
    </div>
  </div>
</c:if>
<!-- Login Form -->
<form class="form-signin" method="post" action="<%=request.getContextPath()%>/login">
  <div class="form-group">
    <label>Email</label> <input class="form-control" name="username"
      type="email" placeholder="Email">
  </div>
  <div class="form-group">
    <label>Password</label> <input class="form-control" name="password"
      type="password" placeholder="Password">
  </div>
  <input class="btn btn-primary" type="submit" value="Login"> <input
    type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
