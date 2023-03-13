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
