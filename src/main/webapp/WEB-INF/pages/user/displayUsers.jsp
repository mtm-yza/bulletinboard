<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- User Display Table -->
<table class="table table-bordered">
  <thead>
    <tr>
      <th class="text-center">No</th>
      <th class="text-center">Name</th>
      <th class="text-center">Role</th>
      <th class="text-center">Email</th>
      <th class="w-25 text-center">Address</th>
      <th class="w-25 text-center" colspan="3">Actions</th>
    </tr>
  </thead>
  <tbody id="tbl-body"></tbody>
</table>
<!-- Edit Post Form -->
<div class="modal" id="editUserModal" tabindex="-1" role="dialog"
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
        <form:form id="userEditForm" method="post" modelAttribute="user">
          <div class="form-group hidden">
            <form:input id="txtId" class="form-control" type="number"
              path="id" readonly="true" />
            <div class="label-error">
              <c:out value="${errors['id']}" />
            </div>
          </div>
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
              <label class="form-check-label"> <form:radiobutton id="rdoUser"
                  class="form-check-input" path="role" value="1" /> Normal
              </label>
            </div>
            <div class="form-check form-check-inline">
              <label class="form-check-label"> <form:radiobutton id="rdoAdmin"
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
            <form:textarea id="txtAddress" class="form-control"
              path="address" rows="3" placeholder="Address"></form:textarea>
            <div class="label-error">
              <c:out value="${errors['address']}" />
            </div>
          </div>
          <div class="form-group hidden">
            <form:label path="password">Password</form:label>
            <form:input id="txtPassword" class="form-control"
              path="password" placeholder="Password"></form:input>
            <div class="label-error">
              <c:out value="${errors['password']}" />
            </div>
          </div>
          <div class="container text-center">
            <button class="btn btn-primary" type="submit"
              formaction="update">Update</button>
            <button class="btn btn-primary hidden" type="submit"
              formaction="delete">Delete</button>
            <button class="btn btn-primary" type="button"
              data-dismiss="modal">Cancel</button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
<!-- Controls For Pagination -->
<nav>
  <div id="pagination-container"
    class="pagination justify-content-center"></div>
</nav>
<!-- Data -->
<script>
    var pageIndex = ${pageIndex}
    var pageSize = ${pageSize}
    var list = ${users}
    var totalCount = ${totalCount}
    var userRoles = ${userRoles}
    var errors = '${errors}'
</script>
