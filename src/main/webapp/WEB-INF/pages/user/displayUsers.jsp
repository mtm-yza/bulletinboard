<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- User Display Table -->
<table class="table table-bordered">
  <thead>
    <tr>
      <th class="text-center">No</th>
      <th class="text-center">Name</th>
      <th class="text-center">Email</th>
      <th class="w-50 text-center">Address</th>
      <th class="w-25 text-center" colspan="3">Actions</th>
    </tr>
  </thead>
  <tbody id="tbl-body"></tbody>
</table>
<!-- Edit Post Form -->
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog"
  aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Edit User Form</h5>
        <button type="button" class="close" data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="userEditForm" method="post">
          <c:if test="${errors != null }">
            <div class="alert alert-danger" role="alert">
              <h6>
                <c:out value="${msg}" />
              </h6>
              <c:forEach var="error" items="${errors}">
                <li><c:out value="${error.toString()}" /></li>
              </c:forEach>
            </div>
          </c:if>
          <div class="form-group hidden">
            <input id="txtId" class="form-control" type="number"
              name="id" required readonly>
          </div>
          <div class="form-group">
            <label for="title">Name</label> <input id="txtName"
              class="form-control" type="text" name="name"
              placeholder="Name">
          </div>
          <div class="form-group">
            <label for="title">Email</label> <input id="txtEmail"
              class="form-control" type="text" name="email"
              placeholder="Email">
          </div>
          <div class="form-group">
            <label for="address">Address</label>
            <textarea id="txtAddress" class="form-control"
              name="address" rows="3" placeholder="Address"></textarea>
          </div>
          <div class="container text-center">
            <button class="btn btn-primary" type="submit"
              formaction="update">Update</button>
            <button class="btn btn-primary hidden" type="submit"
              formaction="delete">Delete</button>
            <button class="btn btn-primary" type="button"
              data-dismiss="modal">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- Controls For Pagination -->
<c:set var="isStartPage" scope="page" value="${pageIndex == 1}" />
<c:set var="isEndPage" scope="page" value="${pageIndex == pageCount}" />
<nav>
  <ul class="pagination justify-content-center">
    <li class="page-item <c:if test="${isStartPage}">disabled</c:if>">
      <a class="page-link" tabindex="-1"
      href="list?page=<c:out value="${pageIndex - 1}"/>&size=<c:out value="${pageSize}"/>">Prev</a>
    </li>
    <c:forEach var="item" begin="1" end="${pageCount}">
      <li
        class="page-item <c:if test="${item == pageIndex}">active</c:if>"><a
        class="page-link"
        href="list?page=<c:out value="${item}"/>&size=<c:out value="${pageSize}"/>"><c:out
            value="${item}" /></a></li>
    </c:forEach>
    <li class="page-item <c:if test="${isEndPage}">disabled</c:if>">
      <a class="page-link"
      href="list?page=<c:out value="${pageIndex + 1}"/>&size=<c:out value="${pageSize}"/>">Next</a>
    </li>
  </ul>
</nav>
<!-- Confirm Form -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
  aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Confirm</h5>
        <button type="button" class="close" data-dismiss="modal"
          aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">Are you sure?</div>
      <div class="modal-footer">
        <button id="btnConfirm" class="btn btn-primary" type="button">Confirm</button>
        <button class="btn" type="button" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>
<!-- Data -->
<script>
    var pageIndex = ${pageIndex}
    var pageCount = ${pageCount}
    var pageSize = ${pageSize}
    var list = ${users}
</script>
