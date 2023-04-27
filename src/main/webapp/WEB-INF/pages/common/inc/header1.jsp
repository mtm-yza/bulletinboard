<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<nav class="navbar-dark bg-dark">
  <div class="navbar navbar-expand navbar-dark">
    <a class="navbar-brand" href="/Demo/post"> BulletinBoard </a>
    <ul class="navbar-nav mr-auto">
      <security:authorize access="isAuthenticated()">
        <li class="nav-item"><a class="nav-link" href="/Demo/post">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="/Demo/post/add">Add Post</a></li>      
      </security:authorize>
      <security:authorize access="hasRole('ROLE_ADMIN')">
        <li class="nav-item"><a class="nav-link" href="/Demo/user">Users</a></li>
        <li class="nav-item"><a class="nav-link" href="/Demo/user/add">Add User</a></li>
      </security:authorize>
    </ul>
    <security:authorize access="isAuthenticated()">
      <form class="my-2 my-sm-0"  method="post" action="<%=request.getContextPath()%>/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input class="btn" type="submit" value="Logout" />
      </form>    
    </security:authorize>
  </div>
</nav>
